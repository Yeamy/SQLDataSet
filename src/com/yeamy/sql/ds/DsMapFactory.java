package com.yeamy.sql.ds;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DsMapFactory {

	private static ArrayList<String> findColumnIndex(ResultSet rs) throws SQLException {
		ArrayList<String> fields = new ArrayList<>();
		ResultSetMetaData md = rs.getMetaData();
		for (int i = 1, c = md.getColumnCount(); i < c; i++) {
			fields.add(md.getColumnLabel(i));
		}
		return fields;
	}

	private static HashMap<String, Object> read(ResultSet rs, ArrayList<String> list) throws SQLException {
		HashMap<String, Object> map = new HashMap<>();
		for (int i = 0, l = list.size(); i < l; i++) {
			String label = list.get(i);
			Object value = rs.getObject(i);
			map.put(label, value);
		}
		return map;
	}

	public static HashMap<String, Object> read(ResultSet rs) throws SQLException {
		ArrayList<String> list = findColumnIndex(rs);
		if (rs.next()) {
			return read(rs, list);
		}
		return null;
	}

	public static ArrayList<HashMap<String, Object>> readArray(ResultSet rs) throws SQLException {
		ArrayList<HashMap<String, Object>> out = new ArrayList<>();
		ArrayList<String> list = findColumnIndex(rs);
		while (rs.next()) {
			out.add(read(rs, list));
		}
		return out;
	}

	public static ArrayList<HashMap<String, Object>> readArray(ResultSet rs, int limit) throws SQLException {
		ArrayList<HashMap<String, Object>> out = new ArrayList<>();
		ArrayList<String> list = findColumnIndex(rs);
		while (rs.next()) {
			if (limit-- <= 0) {
				break;
			}
			out.add(read(rs, list));
		}
		return out;
	}

}
