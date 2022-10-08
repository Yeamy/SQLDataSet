package com.yeamy.sql.ds;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.yeamy.utils.array.BooleanArray;
import com.yeamy.utils.array.ByteArray;
import com.yeamy.utils.array.DoubleArray;
import com.yeamy.utils.array.FloatArray;
import com.yeamy.utils.array.IntArray;
import com.yeamy.utils.array.LongArray;

public class DsReader {

	public static boolean getBoolean(Statement stmt, String sql, boolean fallback) throws SQLException {
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

	public static boolean[] getBooleanArray(Statement stmt, String sql) throws SQLException {
		BooleanArray list = new BooleanArray();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getBoolean(1));
			}
			return list.getArray();
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

	public static byte getByte(Statement stmt, String sql, byte fallback) throws SQLException {
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

	public static byte[] getByteArray(Statement stmt, String sql) throws SQLException {
		ByteArray list = new ByteArray();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getByte(1));
			}
			return list.getArray();
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

	public static int getInt(Statement stmt, String sql, int fallback) throws SQLException {
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

	public static int[] getIntArray(Statement stmt, String sql) throws SQLException {
		IntArray list = new IntArray();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
			return list.toArray();
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

	public static long getLong(Statement stmt, String sql, long fallback) throws SQLException {
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

	public static long[] getLongArray(Statement stmt, String sql) throws SQLException {
		LongArray list = new LongArray();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getLong(1));
			}
			return list.getArray();
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

	public static float getFloat(Statement stmt, String sql, float fallback) throws SQLException {
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

	public static float[] getFloatArray(Statement stmt, String sql) throws SQLException {
		FloatArray list = new FloatArray();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getFloat(1));
			}
			return list.getArray();
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

	public static double getDouble(Statement stmt, String sql, double fallback) throws SQLException {
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

	public static double[] getDoubleArray(Statement stmt, String sql) throws SQLException {
		DoubleArray list = new DoubleArray();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getDouble(1));
			}
			return list.getArray();
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

	public static BigDecimal getBigDecimal(Statement stmt, String sql, BigDecimal fallback) throws SQLException {
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

	public static ArrayList<BigDecimal> getBigDecimalArray(Statement stmt, String sql) throws SQLException {
		ArrayList<BigDecimal> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getBigDecimal(1));
			}
			return list;
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

	public static Timestamp getTimestamp(Statement stmt, String sql, Timestamp fallback) throws SQLException {
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

	public static ArrayList<Timestamp> getTimestampArray(Statement stmt, String sql) throws SQLException {
		ArrayList<Timestamp> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getTimestamp(1));
			}
			return list;
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

	public static Date getDate(Statement stmt, String sql, Date fallback) throws SQLException {
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

	public static ArrayList<Date> getDateArray(Statement stmt, String sql) throws SQLException {
		ArrayList<Date> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getDate(1));
			}
			return list;
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

	public static Time getTime(Statement stmt, String sql, Time fallback) throws SQLException {
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

	public static ArrayList<Time> getTimeArray(Statement stmt, String sql) throws SQLException {
		ArrayList<Time> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getTime(1));
			}
			return list;
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

	public static String getString(Statement stmt, String sql, String fallback) throws SQLException {
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

	public static ArrayList<String> getStringArray(Statement stmt, String sql) throws SQLException {
		ArrayList<String> list = new ArrayList<>();
		getStringArray(stmt, sql, list);
		return list;
	}

	public static void getStringArray(Statement stmt, String sql, Collection<String> out) throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				out.add(rs.getString(1));
			}
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

	public static Map<String, Object> read(Statement stmt, String sql) throws SQLException {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			return DsMapFactory.read(rs);
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

	public static ArrayList<Map<String, Object>> readArray(Statement stmt, String sql)
			throws InstantiationException, IllegalAccessException, SQLException {
		try (ResultSet rs = stmt.executeQuery(sql)) {
			return DsMapFactory.readArray(rs);
		}
	}

	public static ArrayList<Map<String, Object>> readArray(Statement stmt, String sql, int limit)
			throws InstantiationException, IllegalAccessException, SQLException {
		try (ResultSet rs = stmt.executeQuery(sql)) {
			return DsMapFactory.readArray(rs, limit);
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

	public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> clz, int limit)
			throws InstantiationException, IllegalAccessException, SQLException {
		ArrayList<T> out = new ArrayList<>();
		readArray(stmt, sql, new DsFactory<T>(clz), limit, out);
		return out;
	}

	public static <T> void readArray(Statement stmt, String sql, Class<T> clz, Collection<T> out)
			throws InstantiationException, IllegalAccessException, SQLException {
		readArray(stmt, sql, new DsFactory<T>(clz), out);
	}

	public static <T> void readArray(Statement stmt, String sql, Class<T> clz, int limit, Collection<T> out)
			throws InstantiationException, IllegalAccessException, SQLException {
		readArray(stmt, sql, new DsFactory<T>(clz), limit, out);
	}

	public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, Collection<T> out)
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

	public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, int limit, Collection<T> out)
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
