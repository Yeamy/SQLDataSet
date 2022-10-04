package com.yeamy.sql.ds;

import java.sql.ResultSet;

public interface DsColumnIndex {

	public void read(ResultSet rs, Object t);
}