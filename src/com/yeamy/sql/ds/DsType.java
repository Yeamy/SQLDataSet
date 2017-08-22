package com.yeamy.sql.ds;

import java.lang.reflect.Field;

public enum DsType {
	_int(int.class), //
	_long(long.class), //
	_float(float.class), //
	_double(double.class), //
	_boolean(boolean.class), //
	_short(short.class), //
	_byte(byte.class), //
	Integer(Integer.class), //
	Long(Long.class), //
	Float(Float.class), //
	Double(Double.class), //
	Short(Short.class), //
	Boolean(Boolean.class), //
	Byte(Byte.class), //
	BigDecimal(java.math.BigDecimal.class), //
	String(String.class), //
	Date(java.sql.Date.class), //
	Time(java.sql.Time.class), //
	Timestamp(java.sql.Timestamp.class), //
	URL(java.net.URL.class), //
	NotDefined(Object.class), //
	;

	private Class<?> type;

	DsType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

	public static DsType getType(Field field) {
		DsType[] types = DsType.values();
		Class<?> src = field.getType();
		for (DsType type : types) {
			if (src.isAssignableFrom(type.type)) {
				return type;
			}
		}
		return NotDefined;
	}

}