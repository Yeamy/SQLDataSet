package com.yeamy.sql.ds;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DsReader {

	public static boolean getBoolean(Statement stmt, String sql, boolean fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getBoolean(1);
			}
			return fallback;
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

	public static byte getByte(Statement stmt, String sql, byte fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getByte(1);
			}
			return fallback;
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

	public static int getInt(Statement stmt, String sql, int fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
			return fallback;
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

	public static long getLong(Statement stmt, String sql, long fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getLong(1);
			}
			return fallback;
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

	public static float getFloat(Statement stmt, String sql, float fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getFloat(1);
			}
			return fallback;
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

	public static double getDouble(Statement stmt, String sql, double fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getDouble(1);
			}
			return fallback;
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

	public static BigDecimal getBigDecimal(Statement stmt, String sql, BigDecimal fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getBigDecimal(1);
			}
			return fallback;
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

	public static Timestamp getTimestamp(Statement stmt, String sql, Timestamp fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getTimestamp(1);
			}
			return fallback;
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

	public static Date getDate(Statement stmt, String sql, Date fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getDate(1);
			}
			return fallback;
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

	public static Time getTime(Statement stmt, String sql, Time fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getTime(1);
			}
			return fallback;
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

	public static String getString(Statement stmt, String sql, String fallback)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString(1);
			}
			return fallback;
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
		return read(stmt, sql, new DsFactory<T>(clz), null);
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
