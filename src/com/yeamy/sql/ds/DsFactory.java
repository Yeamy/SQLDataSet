package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DsFactory<T> {
	public static boolean DEBUG = false;

	private HashMap<Class<?>, DsAdapter> map;
	private List<DsField> fields;
	private Class<T> clz;
	private Calendar cal;

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
				if (f.isBaseType()) {
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

	Calendar getCalendar() {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		return cal;
	}

	List<DsField> findColumnIndex(ResultSet rs) {
		List<DsField> fields = new ArrayList<>();
		for (DsField dsField : this.fields) {
			if (dsField.findColumnIndex(rs)) {
				fields.add(dsField);
			}
		}
		return fields;
	}

	private T read(ResultSet rs, List<DsField> list)
			throws SQLException, InstantiationException, IllegalAccessException {
		T t = clz.newInstance();
		for (DsField f : list) {
			f.read(rs, t, this);
		}
		if (t instanceof DsObserver) {
			((DsObserver) t).onDsFinish();
		}
		return t;
	}

	public T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		if (rs.next()) {
			return read(rs, list);
		}
		return null;
	}

	public T readExtra(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		return read(rs, list);
	}

	public void readArray(List<T> out, ResultSet rs, int limit)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		while (rs.next()) {
			if (limit-- <= 0) {
				break;
			}
			out.add(read(rs, list));
		}
	}

	public void readArray(List<T> out, ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		while (rs.next()) {
			out.add(read(rs, list));
		}
	}

}
