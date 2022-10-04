package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

class DsBaseField implements DsField {
	private Field field;
	private DsType dsType;
	private DsAdapter adapter;

	protected DsBaseField(Field field, DsType dsType, DsAdapter adapter) {
		this.field = field;
		this.dsType = dsType;
		this.adapter = adapter;
	}

	@Override
	public DsColumnIndex findColumnIndex(ResultSet rs, HashMap<String, Integer> colMap) {
		DsColumn column = field.getAnnotation(DsColumn.class);
		String label = (column == null) ? null : column.value();
		if (label != null && label.length() > 0) {
			try {
				return new DsBaseColumnIndex(this, rs.findColumn(label));
			} catch (Exception e) {
				if (DsFactory.DEBUG) {
					e.printStackTrace();
				}
				return null;
			}
		} else {
			label = field.getName();
			try {
				return new DsBaseColumnIndex(this, rs.findColumn(label));
			} catch (Exception e) {
				Integer i = colMap.get(label.replace("_", "").toLowerCase());
				return (i == null) ? null : new DsBaseColumnIndex(this, i);
			}
		}
	}

	@Override
	public void read(ResultSet rs, Object t, DsColumnIndex index)
			throws SQLException, InstantiationException, IllegalAccessException {
		DsBaseColumnIndex i = (DsBaseColumnIndex) index;
		int columnIndex = i.index;
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
			field.set(t, rs.getDate(columnIndex, i.getCalendar()));
			break;
		case Time:
			field.set(t, rs.getTime(columnIndex, i.getCalendar()));
			break;
		case Timestamp:
			field.set(t, rs.getTimestamp(columnIndex, i.getCalendar()));
			break;
		case URL:
			field.set(t, rs.getURL(columnIndex));
			break;
		default:
		}
	}

	private static class DsBaseColumnIndex implements DsColumnIndex {
		private final DsField field;
		private final int index;
		private Calendar calendar;

		public DsBaseColumnIndex(DsField field, int index) {
			this.field = field;
			this.index = index;
		}

		@Override
		public void read(ResultSet rs, Object t) {
			try {
				field.read(rs, t, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private Calendar getCalendar() {
			if (calendar == null) {
				calendar = Calendar.getInstance();
			}
			return calendar;
		}
	}

}