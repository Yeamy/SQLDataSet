package com.yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public interface DsAdapter<T> {

	void read(T t, Field field, ResultSet rs, int columnIndex);

}