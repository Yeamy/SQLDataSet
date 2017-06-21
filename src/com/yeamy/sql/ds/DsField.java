package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsField {
	int columnIndex;
	private Field field;
	private String columnLabel;
	private DsType dsType;

	DsField(Field field) {
		this.field = field;
		DsColumn column = field.getAnnotation(DsColumn.class);
		String label = (column == null) ? null : column.value();
		if (label != null) {
			columnLabel = label;
		} else {
			columnLabel = field.getName();
		}
		dsType = DsType.getType(field);
	}

	boolean isPrimitive() {
		return dsType == DsType.NotDefined;
	}

	boolean init(ResultSet rs) throws SQLException {
		try {
			this.columnIndex = rs.findColumn(columnLabel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	<T> void read(ResultSet rs, T t, DsFactory<T> factory)
			throws SQLException, IllegalArgumentException, IllegalAccessException {
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
			field.set(t, rs.getTimestamp(columnLabel, factory.getCalendar()));
			break;
		case URL:
			field.set(t, rs.getURL(columnIndex));
			break;
		default:
			DsAdapter<T> adapter = factory.getAdapter(field.getType());
			if (adapter != null) {
				adapter.read(t, field, rs, columnIndex);
			}
		}
	}

}