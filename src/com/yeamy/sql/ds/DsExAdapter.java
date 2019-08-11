package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsExAdapter<T> implements DsAdapter<T> {
	private DsFactory<?> factory;

	public DsExAdapter(Class<?> clz) {
		factory = new DsFactory<>(clz);
	}

	@Override
	public void read(T t, Field field, ResultSet rs, int columnIndex)
			throws SQLException, InstantiationException, IllegalAccessException {
		field.set(t, factory.readExtra(rs));
	}

}