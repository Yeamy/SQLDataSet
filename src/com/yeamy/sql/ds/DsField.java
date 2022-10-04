package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

abstract interface DsField {

	static DsField get(Field field, DsFactory<?> factory) {
		DsType dsType = DsType.getDsType(field);
		if (dsType == DsType.Extra) {// extra type
			Class<?> type = field.getType();
			if (field.isAnnotationPresent(DsColumn.class)) {
				DsAdapter adapter = factory.getAdapter(type);
				if (adapter != null) {// not defined
					return new DsBaseField(field, dsType, adapter);
				}
				return null;
			} else {
				return new DsExtendField(field, type);
			}
		} else {// base type
			return new DsBaseField(field, dsType, null);
		}
	}

	DsColumnIndex findColumnIndex(ResultSet rs, HashMap<String, Integer> colMap);

	void read(ResultSet rs, Object t, DsColumnIndex index)
			throws SQLException, InstantiationException, IllegalAccessException;

}