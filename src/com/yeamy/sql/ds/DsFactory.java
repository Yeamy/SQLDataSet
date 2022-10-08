package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DsFactory<T> {
	public static boolean DEBUG = false;

	private HashMap<Class<?>, DsAdapter> map;
	private List<DsField> fields;
	private Class<T> clz;

	public DsFactory(Class<T> clz) {
		this.clz = clz;
		LinkedList<DsField> list = new LinkedList<>();
		Field[] fields = clz.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(DsIgnore.class)) {
				continue;
			}
			DsField f = DsField.get(field, this);
			if (f != null) {
				if (f instanceof DsBaseField) {
					list.addFirst(f);
				} else {
					list.addLast(f);
				}
			}
		}
		this.fields = list;
	}

	public void addAdapter(Class<?> clz, DsAdapter adapter) {
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(clz, adapter);
	}

	public DsAdapter getAdapter(Class<?> clz) {
		if (map == null) {
			return null;
		}
		while (true) {
			if (clz == null) {
				return null;
			}
			DsAdapter adapter = map.get(clz);
			if (adapter == null) {
				clz = clz.getSuperclass();
			} else {
				return adapter;
			}
		}
	}

	List<DsColumnIndex> findColumnIndex(ResultSet rs) throws SQLException {
		HashMap<String, Integer> colMap = new HashMap<>();
		ResultSetMetaData md = rs.getMetaData();
		for (int i = 1, c = md.getColumnCount(); i <= c; i++) {
			colMap.put(md.getColumnLabel(i).replace("_", "").toLowerCase(), i);
		}
		return findColumnIndex(rs, colMap);
	}

	List<DsColumnIndex> findColumnIndex(ResultSet rs, HashMap<String, Integer> colMap) {
		List<DsColumnIndex> fields = new ArrayList<>();
		for (DsField dsField : this.fields) {
			DsColumnIndex i = dsField.findColumnIndex(rs, colMap);
			if (i != null) {
				fields.add(i);
			}
		}
		return fields;
	}

	T read(ResultSet rs, List<DsColumnIndex> list) throws SQLException, InstantiationException, IllegalAccessException {
		T t = clz.newInstance();
		for (DsColumnIndex f : list) {
			f.read(rs, t);
		}
		if (t instanceof DsObserver) {
			((DsObserver) t).onDsFinish();
		}
		return t;
	}

	public T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsColumnIndex> list = findColumnIndex(rs);
		if (rs.next()) {
			return read(rs, list);
		}
		return null;
	}

	public void readArray(Collection<T> out, ResultSet rs, int limit)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsColumnIndex> list = findColumnIndex(rs);
		while (rs.next()) {
			if (limit-- <= 0) {
				break;
			}
			out.add(read(rs, list));
		}
	}

	public void readArray(Collection<T> out, ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsColumnIndex> list = findColumnIndex(rs);
		while (rs.next()) {
			out.add(read(rs, list));
		}
	}

}
