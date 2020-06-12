package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsField {
	private Field field;
	private DsType dsType;
	private DsAdapter adapter;
	transient int columnIndex;

	static DsField get(Field field, DsFactory<?> factory) {
		DsType dsType = DsType.getDsType(field);
		if (dsType == DsType.Extra) {// extra type
			Class<?> type = field.getType();
			DsAdapter adapter = factory.getAdapter(type);
			if (adapter == null) {// not defined
				if (field.isAnnotationPresent(DsColumn.class)) {
					return null;
				} else {
					adapter = new DsExAdapter(type);
					factory.addAdapter(type, adapter);
					return new DsField(field, dsType, adapter);
				}
			} else {
				return new DsField(field, dsType, adapter);
			}
		} else {// base type
			return new DsField(field, dsType, null);
		}
	}

	private DsField(Field field, DsType dsType, DsAdapter adapter) {
		this.field = field;
		this.dsType = dsType;
		this.adapter = adapter;
	}

	boolean isBaseType() {
		return adapter == null;
	}

	boolean findColumnIndex(ResultSet rs) {
		if (adapter != null && adapter instanceof DsExAdapter) {
			DsExAdapter exAdapter = (DsExAdapter) adapter;
			return exAdapter.findColumnIndex(rs);
		}
		DsColumn column = field.getAnnotation(DsColumn.class);
		String label = (column == null) ? null : column.value();
		String columnLabel;
		if (label != null && label.length() > 0) {
			columnLabel = label;
		} else {
			columnLabel = field.getName();
		}
		try {
			columnIndex = rs.findColumn(columnLabel);
			return true;
		} catch (Exception e) {
			if (DsFactory.DEBUG) {
				e.printStackTrace();
			}
			return false;
		}
	}

	void read(ResultSet rs, Object t, DsFactory<?> factory)
			throws SQLException, InstantiationException, IllegalAccessException {
		if (adapter != null) {
			adapter.read(t, field, rs, columnIndex);
			return;
		}
		switch (dsType) {
		case _boolean:
		case Boolean:
			field.setBoolean(t, rs.getBoolean(columnIndex));
			break;
		case _byte:
		case Byte:
			field.setByte(t, rs.getByte(columnIndex));
			break;
		case _short:
		case Short:
			field.setShort(t, rs.getShort(columnIndex));
			break;
		case _int:
		case Integer:
			field.setInt(t, rs.getInt(columnIndex));
			break;
		case _long:
		case Long:
			field.setLong(t, rs.getLong(columnIndex));
			break;
		case _float:
		case Float:
			field.setFloat(t, rs.getFloat(columnIndex));
			break;
		case _double:
		case Double:
			field.setDouble(t, rs.getDouble(columnIndex));
			break;
		case BigDecimal:
			field.set(t, rs.getBigDecimal(columnIndex));
			break;
		case String:
			field.set(t, rs.getString(columnIndex));
			break;
		case Date:
			field.set(t, rs.getDate(columnIndex, factory.getCalendar()));
			break;
		case Time:
			field.set(t, rs.getTime(columnIndex, factory.getCalendar()));
			break;
		case Timestamp:
			field.set(t, rs.getTimestamp(columnIndex, factory.getCalendar()));
			break;
		case URL:
			field.set(t, rs.getURL(columnIndex));
			break;
		default:
		}
	}

}