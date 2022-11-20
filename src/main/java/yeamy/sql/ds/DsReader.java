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

    /**
     * Return the value of the first column of the first row as boolean
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as boolean
     */
    public static boolean getBoolean(Statement stmt, String sql, boolean fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as boolean
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as boolean or null if no query result
     */
    public static Boolean getBoolean(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return null;
        }
    }

    /**
     * Return the value as boolean
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as boolean in an array, empty array if no query result
     */
    public static boolean[] getBooleanArray(Statement stmt, String sql) throws SQLException {
        BooleanArray list = new BooleanArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getBoolean(1));
            }
        }
        return list.getArray();
    }

    /**
     * Return the value of the first column of the first row as byte
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as byte
     */
    public static byte getByte(Statement stmt, String sql, byte fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getByte(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as byte
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as byte or null if no query result
     */
    public static Byte getByte(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getByte(1);
            }
            return null;
        }
    }

    /**
     * Return the value as byte
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as byte in an array, empty array if no query result
     */
    public static byte[] getByteArray(Statement stmt, String sql) throws SQLException {
        ByteArray list = new ByteArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getByte(1));
            }
        }
        return list.getArray();
    }

    /**
     * Return the value of the first column of the first row as byte array
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as byte array
     */
    public static byte[] getBytes(Statement stmt, String sql, byte[] fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBytes(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as byte array
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as byte array or null if no query result
     */
    public static byte[] getBytes(Statement stmt, String sql) throws SQLException {
        return getBytes(stmt, sql, null);
    }

    /**
     * Return the values of the first column as byte array
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as byte array in a list, empty if no result
     */
    public static ArrayList<byte[]> getBytesArray(Statement stmt, String sql) throws SQLException {
        ArrayList<byte[]> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getBytes(1));
            }
        }
        return list;
    }

    /**
     * Return the value of the first column of the first row as short
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as short
     */
    public static short getShort(Statement stmt, String sql, short fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getShort(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as short
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as short or null if no query result
     */
    public static Short getShort(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getShort(1);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as short
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as short in an array, empty array if no query result
     */
    public static short[] getShortArray(Statement stmt, String sql) throws SQLException {
        ShortArray list = new ShortArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getShort(1));
            }
        }
        return list.getArray();
    }

    /**
     * Return the value of the first column of the first row as int
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as int
     */
    public static int getInt(Statement stmt, String sql, int fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as int
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as int or null if no query result
     */
    public static Integer getInt(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as int
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as int in an array, empty array if no query result
     */
    public static int[] getIntArray(Statement stmt, String sql) throws SQLException {
        IntArray list = new IntArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        }
        return list.getArray();
    }

    /**
     * Return the value of the first column of the first row as long
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as long
     */
    public static long getLong(Statement stmt, String sql, long fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as long
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as long or null if no query result
     */
    public static Long getLong(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as long
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as long in an array, empty array if no query result
     */
    public static long[] getLongArray(Statement stmt, String sql) throws SQLException {
        LongArray list = new LongArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getLong(1));
            }
        }
        return list.getArray();
    }

    /**
     * Return the value of the first column of the first row as float
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as float
     */
    public static float getFloat(Statement stmt, String sql, float fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getFloat(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as float
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as float or null if no query result
     */
    public static Float getFloat(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getFloat(1);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as float
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as float in an array, empty array if no query result
     */
    public static float[] getFloatArray(Statement stmt, String sql) throws SQLException {
        FloatArray list = new FloatArray();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getFloat(1));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as double
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as double
     */
    public static double getDouble(Statement stmt, String sql, double fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as double
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as double or null if no query result
     */
    public static Double getDouble(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as double
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as double in an array, empty array if no query result
     */
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

    /**
     * Return the value of the first column of the first row as decimal
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as decimal
     */
    public static BigDecimal getBigDecimal(Statement stmt, String sql, BigDecimal fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as decimal
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as decimal or null if no query result
     */
    public static BigDecimal getBigDecimal(Statement stmt, String sql) throws SQLException {
        return getBigDecimal(stmt, sql, null);
    }

    /**
     * Return the values of the first column as decimal
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as decimal in an array, empty array if no query result
     */
    public static ArrayList<BigDecimal> getBigDecimalArray(Statement stmt, String sql) throws SQLException {
        ArrayList<BigDecimal> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getBigDecimal(1));
            }
            return list;
        }
    }

    /**
     * Return the value of the first column of the first row as timestamp
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as timestamp
     */
    public static Timestamp getTimestamp(Statement stmt, String sql, Timestamp fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as timestamp
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as timestamp or null if no query result
     */
    public static Timestamp getTimestamp(Statement stmt, String sql) throws SQLException {
        return getTimestamp(stmt, sql, null);
    }

    /**
     * Return the values of the first column as timestamp
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as timestamp in an array, empty array if no query result
     */
    public static ArrayList<Timestamp> getTimestampArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Timestamp> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getTimestamp(1));
            }
            return list;
        }
    }

    /**
     * Return the value of the first column of the first row as date
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as date
     */
    public static Date getDate(Statement stmt, String sql, Date fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDate(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as date
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as date or null if no query result
     */
    public static Date getDate(Statement stmt, String sql) throws SQLException {
        return getDate(stmt, sql, null);
    }

    /**
     * Return the values of the first column as date
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as date in an array, empty array if no query result
     */
    public static ArrayList<Date> getDateArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Date> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getDate(1));
            }
        }
        return list;
    }

    /**
     * Return the value of the first column of the first row as time
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as time
     */
    public static Time getTime(Statement stmt, String sql, Time fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getTime(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as time
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as time or null if no query result
     */
    public static Time getTime(Statement stmt, String sql) throws SQLException {
        return getTime(stmt, sql, null);
    }

    /**
     * Return the values of the first column as time
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as time in an array, empty array if no query result
     */
    public static ArrayList<Time> getTimeArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Time> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getTime(1));
            }
        }
        return list;
    }

    /**
     * Return the value of the first column of the first row as string
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as string
     */
    public static String getString(Statement stmt, String sql, String fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as string
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as string or null if no query result
     */
    public static String getString(Statement stmt, String sql) throws SQLException {
        return getString(stmt, sql, null);
    }

    /**
     * Return the values of the first column as string
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as string in an array, empty array if no query result
     */
    public static ArrayList<String> getStringArray(Statement stmt, String sql) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        getStringArray(stmt, sql, list);
        return list;
    }

    /**
     * Return the values of the first column as string int the given collection
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param out  collection to accept data
     */
    public static void getStringArray(Statement stmt, String sql, Collection<String> out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getString(1));
            }
        }
    }

    /**
     * Return the value of the first column of the first row as url
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as url
     */
    public static URL getURL(Statement stmt, String sql, URL fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getURL(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as url
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as url or null if no query result
     */
    public static URL getURL(Statement stmt, String sql) throws SQLException {
        return getURL(stmt, sql, null);
    }

    public static ArrayList<URL> getURLArray(Statement stmt, String sql) throws SQLException {
        ArrayList<URL> list = new ArrayList<>();
        getURLArray(stmt, sql, list);
        return list;
    }

    /**
     * Return the values of the first column as url int the given collection
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param out  collection to accept data
     */
    public static void getURLArray(Statement stmt, String sql, Collection<URL> out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getURL(1));
            }
        }
    }

    /**
     * Return the value of the first column of the first row as Blob
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as Blob
     */
    public static Blob getBlob(Statement stmt, String sql, Blob fallback) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBlob(1);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as Blob
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as Blob or null if no query result
     */
    public static Blob getBlob(Statement stmt, String sql) throws SQLException {
        return getBlob(stmt, sql, null);
    }

    /**
     * Return the values of the first column as Blob
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return values as Blob in an array, empty array if no query result
     */
    public static ArrayList<Blob> getBlobArray(Statement stmt, String sql) throws SQLException {
        ArrayList<Blob> list = new ArrayList<>();
        getBlobArray(stmt, sql, list);
        return list;
    }

    /**
     * Return the values of the first column as Blob int the given collection
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param out  collection to accept data
     */
    public static void getBlobArray(Statement stmt, String sql, Collection<Blob> out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getBlob(1));
            }
        }
    }

    /**
     * Return the value of the first column of the first row as BinaryStream
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as BinaryStream or null if no query result
     */
    public static InputStream getBinaryStream(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getBinaryStream(1);
            }
            return null;
        }
    }

    /**
     * Return the value of the first column of the first row as AsciiStream
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as AsciiStream or null if no query result
     */
    public static InputStream getAsciiStream(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getAsciiStream(1);
            }
            return null;
        }
    }

    /**
     * Return the value of the first column of the first row as CharacterStream
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return value as CharacterStream or null if no query result
     */
    public static Reader getCharacterStream(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getCharacterStream(1);
            }
            return null;
        }
    }

    /**
     * read the first row as map
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return the row data in a map or null if no result
     */
    public static Map<String, Object> read(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            return DsMapFactory.read(rs);
        }
    }

    /**
     * read the first row as map
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param out  given map to receive row data
     * @return the given map with row data or null if no result
     */
    public static <T extends Map<String, Object>> T read(Statement stmt, String sql, T out) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            DsMapFactory.read(rs, out);
            return out;
        }
    }

    /**
     * read rows as map into a list
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @return rows data in a list, empty if no result
     */
    public static ArrayList<Map<String, Object>> readArray(Statement stmt, String sql) throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            return DsMapFactory.readArray(rs);
        }
    }

    /**
     * read rows as map into a list
     *
     * @param stmt  the database to read
     * @param sql   the query sql statement
     * @param limit limit how many rows to read
     * @return rows data in a list, empty if no result
     */
    public static ArrayList<Map<String, Object>> readArray(Statement stmt, String sql, int limit) throws SQLException {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            DsMapFactory.readArray(rs, list, limit);
        }
        return list;
    }

    /**
     * read rows as map into the given collection
     *
     * @param stmt       the database to read
     * @param sql        the query sql statement
     * @param collection the list to accept rows data
     * @param limit      limit how many rows to read
     */
    public static void readArray(Statement stmt, String sql, Collection<Map<String, Object>> collection, int limit)
            throws SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DsMapFactory.readArray(rs, collection, limit);
            }
        }
    }

    /**
     * read the first row as the given type
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return the row data as object, null if no result
     */
    public static <T> T read(Statement stmt, String sql, Class<T> type)
            throws InstantiationException, IllegalAccessException, SQLException {
        return read(stmt, sql, new DsFactory<T>(type), null);
    }

    /**
     * read the first row as the given type
     *
     * @param stmt     the database to read
     * @param sql      the query sql statement
     * @param factory  factory of given type
     * @param fallback if no result
     * @return the row data as object or return fallback if no result
     */
    public static <T> T read(Statement stmt, String sql, DsFactory<T> factory, T fallback)
            throws InstantiationException, IllegalAccessException, SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            T t = factory.read(rs);
            return (t != null) ? t : fallback;
        }
    }

    /**
     * read rows as the given type in a list
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return rows data as object in a list, empty if no result
     */
    public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> type)
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<T> out = new ArrayList<>();
        readArray(stmt, sql, new DsFactory<>(type), out);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @param out  list to accept data
     */
    public static <T> void readArray(Statement stmt, String sql, Class<T> type, Collection<T> out)
            throws InstantiationException, IllegalAccessException, SQLException {
        readArray(stmt, sql, new DsFactory<>(type), out);
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt  the database to read
     * @param sql   the query sql statement
     * @param type  given type to return
     * @param limit limit how many rows to read
     * @return rows data as object in a list, empty if no result
     */
    public static <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> type, int limit)
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<T> out = new ArrayList<>();
        readArray(stmt, sql, new DsFactory<>(type), limit, out);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt  the database to read
     * @param sql   the query sql statement
     * @param type  given type to return
     * @param limit limit how many rows to read
     */
    public static <T> void readArray(Statement stmt, String sql, Class<T> type, Collection<T> out, int limit)
            throws InstantiationException, IllegalAccessException, SQLException {
        readArray(stmt, sql, new DsFactory<>(type), limit, out);
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt    the database to read
     * @param sql     the query sql statement
     * @param factory factory of given type
     * @param out     collection to accept data
     */
    public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, Collection<T> out)
            throws SQLException, InstantiationException, IllegalAccessException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            factory.readArray(out, rs);
        }
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt    the database to read
     * @param sql     the query sql statement
     * @param factory factory of given type
     * @param limit   limit how many rows to read
     * @param out     collection to receive data
     */
    public static <T> void readArray(Statement stmt, String sql, DsFactory<T> factory, int limit, Collection<T> out)
            throws SQLException, InstantiationException, IllegalAccessException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            factory.readArray(out, rs, limit);
        }
    }

}
