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

	private HashMap<Class<?>, DsAdapter<T>> map;
	private List<DsField<T>> fields;
	private Class<T> clz;
	private Calendar cal;

	public DsFactory(Class<T> clz) {
		this.clz = clz;
		LinkedList<DsField<T>> list = new LinkedList<>();
		Field[] fields = clz.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(DsIgnore.class)) {
				continue;
			} else if (field.isAnnotationPresent(DsExtra.class)) {
				Class<?> t = field.getType();
				addAdapter(t, new DsExAdapter<T>(t));
			}
			DsField<T> f = new DsField<T>(field);
			if (f.isBaseType()) {
				list.addFirst(f);
			} else {
				list.addLast(f);
			}
		}
		this.fields = list;
	}

	public void addAdapter(Class<?> clz, DsAdapter<T> adapter) {
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(clz, adapter);
	}

	public DsAdapter<T> getAdapter(Class<?> clz) {
		if (map == null) {
			return null;
		}
		while (true) {
			if (clz == null) {
				return null;
			}
			DsAdapter<T> adapter = map.get(clz);
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

	private List<DsField<T>> findColumn(ResultSet rs) throws SQLException {
		List<DsField<T>> fields = new ArrayList<>();
		for (DsField<T> dsField : this.fields) {
			if (dsField.init(rs, this)) {
				fields.add(dsField);
			}
		}
		return fields;
	}

	private T read(ResultSet rs, List<DsField<T>> list)
			throws SQLException, InstantiationException, IllegalAccessException {
		T t = clz.newInstance();
		for (DsField<T> f : list) {
			f.read(rs, t, this);
		}
		if (t instanceof DsObserver) {
			((DsObserver) t).onDsFinish();
		}
		return t;
	}

	public T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField<T>> list = findColumn(rs);
		if (rs.next()) {
			return read(rs, list);
		}
		return null;
	}

	public T readExtra(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField<T>> list = findColumn(rs);
		return read(rs, list);
	}

	public void readArray(List<T> out, ResultSet rs, int limit)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField<T>> list = findColumn(rs);
		while (rs.next()) {
			if (limit-- <= 0) {
				break;
			}
			out.add(read(rs, list));
		}
	}

	/**
	 * default read top 200
	 */
	public void readArray(List<T> out, ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {
		readArray(out, rs, 200);
	}

}
