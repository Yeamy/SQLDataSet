package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DsFactory<T> {
	private HashMap<Class<?>, DsAdapter<T>> map;
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
			DsField f = new DsField(field);
			if (f.isPrimitive()) {
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
			DsAdapter<T> adapter = map.get(clz);
			if (adapter != null) {
				return adapter;
			}
			clz = clz.getSuperclass();
			if (clz == null) {
				return null;
			}
		}
	}

	private void findColumn(ResultSet rs) throws SQLException {
		Iterator<DsField> iterator = this.fields.iterator();
		while (iterator.hasNext()) {
			DsField dsField = (DsField) iterator.next();
			if (!dsField.init(rs)) {
				iterator.remove();
			}
		}
	}

	Calendar getCalendar() {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		return cal;
	}

	public T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		findColumn(rs);
		List<DsField> list = this.fields;
		T t = null;
		if (rs.next()) {
			t = clz.newInstance();
			for (DsField f : list) {
				f.read(rs, t, this);
			}
		}
		return t;
	}

	public void readArray(List<T> out, ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {
		findColumn(rs);
		List<DsField> list = this.fields;
		while (rs.next()) {
			T t = clz.newInstance();
			for (DsField f : list) {
				f.read(rs, t, this);
			}
			out.add(t);
		}
	}

	public void readArray(List<T> out, ResultSet rs, int limit)
			throws SQLException, InstantiationException, IllegalAccessException {
		findColumn(rs);
		List<DsField> list = this.fields;
		while (rs.next()) {
			if (limit-- > 0) {
				break;
			}
			T t = clz.newInstance();
			for (DsField f : list) {
				f.read(rs, t, this);
			}
			out.add(t);
		}
	}

}
