package yeamy.sql.ds;

import yeamy.utils.array.*;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DsReader {

    public static boolean getBoolean(Statement stmt, String sql, boolean fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return fallback;
        }
    }

    public static Boolean getBoolean(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return null;
        }
    }

    public static boolean[] getBooleanArray(Statement stmt, String sql) throws SQLException {
        BooleanArray list = new BooleanArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getBoolean(1));
            }
        }
        return list.getArray();
    }

    public static byte getByte(Statement stmt, String sql, byte fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getByte(1);
            }
            return fallback;
        }
    }

    public static Byte getByte(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getByte(1);
            }
            return null;
        }
    }

    public static byte[] getByteArray(Statement stmt, String sql) throws SQLException {
        ByteArray list = new ByteArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getByte(1));
            }
        }
        return list.getArray();
    }

    public static byte[] getBytes(Statement stmt, String sql, byte[] fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBytes(1);
            }
            return fallback;
        }
    }

    public static byte[] getBytes(Statement stmt, String sql) throws SQLException {
        return getBytes(stmt, sql, null);
    }

    public static ArrayList<byte[]> getBytesArray(Statement stmt, String sql) throws SQLException {
        ArrayList<byte[]> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getBytes(1));
            }
        }
        return list;
    }


    public static short getShort(Statement stmt, String sql, short fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getShort(1);
            }
            return fallback;
        }
    }

    public static Short getShort(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getShort(1);
            }
            return null;
        }
    }

    public static short[] getShortArray(Statement stmt, String sql) throws SQLException {
        ShortArray list = new ShortArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getShort(1));
            }
        }
        return list.getArray();
    }


    public static int getInt(Statement stmt, String sql, int fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return fallback;
        }
    }

    public static Integer getInt(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return null;
        }
    }

    public static int[] getIntArray(Statement stmt, String sql) throws SQLException {
        IntArray list = new IntArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        }
        return list.getArray();
    }

    public static long getLong(Statement stmt, String sql, long fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            return fallback;
        }
    }

    public static Long getLong(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            return null;
        }
    }

    public static long[] getLongArray(Statement stmt, String sql) throws SQLException {
        LongArray list = new LongArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getLong(1));
            }
        }
        return list.getArray();
    }

    public static float getFloat(Statement stmt, String sql, float fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getFloat(1);
            }
            return fallback;
        }
    }

    public static Float getFloat(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getFloat(1);
            }
            return null;
        }
    }

    public static float[] getFloatArray(Statement stmt, String sql) throws SQLException {
        FloatArray list = new FloatArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getFloat(1));
            }
            return list.getArray();
        }
    }

    public static double getDouble(Statement stmt, String sql, double fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return fallback;
        }
    }

    public static Double getDouble(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return null;
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
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
            return fallback;
        }
    }

    public static BigDecimal getBigDecimal(Statement stmt, String sql) throws SQLException {
        return getBigDecimal(stmt, sql, null);
    }

    public static ArrayList<BigDecimal> getBigDecimalArray(Statement stmt, String sql) throws SQLException {
        ArrayList<BigDecimal> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getBigDecimal(1));
            }
            return list;
        }
    }

    public static Timestamp getTimestamp(Statement stmt, String sql, Timestamp fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
            return fallback;
        }
    }

    public static Timestamp getTimestamp(Statement stmt, String sql) throws SQLException {
        return getTimestamp(stmt, sql, null);
    }

    public static ArrayList<Timestamp> getTimestampArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Timestamp> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getTimestamp(1));
            }
            return list;
        }
    }

    public static Date getDate(Statement stmt, String sql, Date fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDate(1);
            }
            return fallback;
        }
    }

    public static Date getDate(Statement stmt, String sql) throws SQLException {
        return getDate(stmt, sql, null);
    }

    public static ArrayList<Date> getDateArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Date> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getDate(1));
            }
        }
        return list;
    }

    public static Time getTime(Statement stmt, String sql, Time fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getTime(1);
            }
            return fallback;
        }
    }

    public static Time getTime(Statement stmt, String sql) throws SQLException {
        return getTime(stmt, sql, null);
    }

    public static ArrayList<Time> getTimeArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Time> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getTime(1));
            }
        }
        return list;
    }

    public static String getString(Statement stmt, String sql, String fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString(1);
            }
            return fallback;
        }
    }

    public static String getString(Statement stmt, String sql) throws SQLException {
        return getString(stmt, sql, null);
    }

    public static ArrayList<String> getStringArray(Statement stmt, String sql) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        getStringArray(stmt, sql, list);
        return list;
    }

    public static void getStringArray(Statement stmt, String sql, Collection<String> out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getString(1));
            }
        }
    }

    public static URL getURL(Statement stmt, String sql, URL fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getURL(1);
            }
            return fallback;
        }
    }

    public static URL getURL(Statement stmt, String sql) throws SQLException {
        return getURL(stmt, sql, null);
    }

    public static ArrayList<URL> getURLArray(Statement stmt, String sql) throws SQLException {
        ArrayList<URL> list = new ArrayList<>();
        getURLArray(stmt, sql, list);
        return list;
    }

    public static void getURLArray(Statement stmt, String sql, Collection<URL> out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getURL(1));
            }
        }
    }

    public static Blob getBlob(Statement stmt, String sql, Blob fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBlob(1);
            }
            return fallback;
        }
    }

    public static Blob getBlob(Statement stmt, String sql) throws SQLException {
        return getBlob(stmt, sql, null);
    }

    public static ArrayList<Blob> getBlobArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Blob> list = new ArrayList<>();
        getBlobArray(stmt, sql, list);
        return list;
    }

    public static void getBlobArray(Statement stmt, String sql, Collection<Blob> out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getBlob(1));
            }
        }
    }

    public static InputStream getBinaryStream(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBinaryStream(1);
            }
            return null;
        }
    }

    public static InputStream getAsciiStream(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getAsciiStream(1);
            }
            return null;
        }
    }

    public static Reader getCharacterStream(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getCharacterStream(1);
            }
            return null;
        }
    }

    public static Map<String, Object> read(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            return DsMapFactory.read(rs);
        }
    }

    public static ArrayList<Map<String, Object>> readArray(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            return DsMapFactory.readArray(rs);
        }
    }

    public static ArrayList<Map<String, Object>> readArray(Statement stmt, String sql, int limit) throws SQLException {
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
        try (ResultSet rs = stmt.executeQuery(sql)) {
            T t = factory.read(rs);
            return (t != null) ? t : fallback;
        }
    }

    public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> clz)
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<T> out = new ArrayList<>();
        readArray(stmt, sql, new DsFactory<>(clz), out);
        return out;
    }

    public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> clz, int limit)
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<T> out = new ArrayList<>();
        readArray(stmt, sql, new DsFactory<>(clz), limit, out);
        return out;
    }

    public static <T> void readArray(Statement stmt, String sql, Class<T> clz, Collection<T> out)
            throws InstantiationException, IllegalAccessException, SQLException {
        readArray(stmt, sql, new DsFactory<>(clz), out);
    }

    public static <T> void readArray(Statement stmt, String sql, Class<T> clz, int limit, Collection<T> out)
            throws InstantiationException, IllegalAccessException, SQLException {
        readArray(stmt, sql, new DsFactory<>(clz), limit, out);
    }

    public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, Collection<T> out)
            throws SQLException, InstantiationException, IllegalAccessException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            factory.readArray(out, rs);
        }
    }

    public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, int limit, Collection<T> out)
            throws SQLException, InstantiationException, IllegalAccessException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            factory.readArray(out, rs, limit);
        }
    }

}
