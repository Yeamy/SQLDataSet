package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class DsExAdapter implements DsAdapter {
	private DsFactory<?> factory;
	private transient List<DsField> list;

	DsExAdapter(Class<?> clz) {
		factory = new DsFactory<>(clz);
	}

	@Override
	public void read(Object t, Field field, ResultSet rs, int columnIndex)
			throws SQLException, InstantiationException, IllegalAccessException {
		field.set(t, factory.readExtra(rs));
	}

	boolean findColumnIndex(ResultSet rs) {
		list = factory.findColumnIndex(rs);
		return list.size() > 0;
	}

}