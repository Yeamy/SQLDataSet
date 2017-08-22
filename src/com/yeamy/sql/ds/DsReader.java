package com.yeamy.sql.ds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DsReader {

	@Deprecated
	public static int getCount(Statement stmt, String countSql)
			throws InstantiationException, IllegalAccessException, SQLException {
		return getNum(stmt, countSql);
	}

	public static int getNum(Statement stmt, String sql)
			throws InstantiationException, IllegalAccessException, SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			return rs.getInt(1);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> T read(Statement stmt, String sql, Class<T> clz, T fallback)
			throws InstantiationException, IllegalAccessException, SQLException {
		return read(stmt, sql, new DsFactory<T>(clz), fallback);
	}

	public static <T> T read(Statement stmt, String sql, DsFactory<T> factory, T fallback)
			throws InstantiationException, IllegalAccessException, SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			T t = factory.read(rs);
			return (t != null) ? t : fallback;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> T read(Statement stmt, String sql, Class<T> clz)
			throws InstantiationException, IllegalAccessException, SQLException {
		return read(stmt, sql, new DsFactory<T>(clz));
	}

	public static <T> T read(Statement stmt, String sql, DsFactory<T> factory)
			throws SQLException, InstantiationException, IllegalAccessException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			return factory.read(rs);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw e;
				}
			}
		}
	}

	public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> clz)
			throws InstantiationException, IllegalAccessException, SQLException {
		ArrayList<T> out = new ArrayList<>();
		readArray(stmt, sql, new DsFactory<T>(clz), out);
		return out;
	}

	public static <T> void readArray(Statement stmt, String sql, Class<T> clz, List<T> out)
			throws InstantiationException, IllegalAccessException, SQLException {
		readArray(stmt, sql, new DsFactory<T>(clz), out);
	}

	public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, List<T> out)
			throws SQLException, InstantiationException, IllegalAccessException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			factory.readArray(out, rs);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> clz, int limit)
			throws InstantiationException, IllegalAccessException, SQLException {
		ArrayList<T> out = new ArrayList<>();
		readArray(stmt, sql, new DsFactory<T>(clz), limit, out);
		return out;
	}

	public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, int limit, List<T> out)
			throws SQLException, InstantiationException, IllegalAccessException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			factory.readArray(out, rs, limit);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
