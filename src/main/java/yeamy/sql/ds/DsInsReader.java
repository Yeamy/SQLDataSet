package yeamy.sql.ds;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * instance type of DsReader
 */
public class DsInsReader {
    private final Map<Class<?>, DsFieldReader<?>> fieldMap = new HashMap<>();

    /**
     * add a field reader for current instance.
     *
     * @param type   field type
     * @param reader the field reader
     */
    public <F> DsInsReader with(Class<F> type, DsFieldReader<F> reader) {
        fieldMap.put(type, reader);
        return this;
    }

    /**
     * read the first row as the given type
     *
     * @param stmt the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return the row data as object, null if no result
     */
    public <T> T read(Statement stmt, String sql, Class<T> type)
            throws ReflectiveOperationException, SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            return new InternalDsFactory<>(type).read(rs, fieldMap);
        }
    }

    /**
     * read the first row as the given type
     *
     * @param stmt the database to read
     * @param type given type to return
     * @return the row data as object, null if no result
     */
    public <T> T read(PreparedStatement stmt, Class<T> type) throws ReflectiveOperationException, SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            return new InternalDsFactory<>(type).read(rs, fieldMap);
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
    public <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> type)
            throws ReflectiveOperationException, SQLException {
        ArrayList<T> out = new ArrayList<>();
        readArray(stmt, sql, type, out);
        return out;
    }

    /**
     * read rows as the given type in a list
     *
     * @param stmt the database to read
     * @param type given type to return
     * @return rows data as object in a list, empty if no result
     */
    public <T> ArrayList<T> readArray(PreparedStatement stmt, Class<T> type)
            throws ReflectiveOperationException, SQLException {
        ArrayList<T> out = new ArrayList<>();
        readArray(stmt, type, out);
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
    public <T> void readArray(Statement stmt, String sql, Class<T> type, Collection<T> out)
            throws ReflectiveOperationException, SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            new InternalDsFactory<>(type).readArray(rs, fieldMap, out);
        }
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt the database to read
     * @param type given type to return
     * @param out  list to accept data
     */
    public <T> void readArray(PreparedStatement stmt, Class<T> type, Collection<T> out)
            throws ReflectiveOperationException, SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            new InternalDsFactory<>(type).readArray(rs, fieldMap, out);
        }
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
    public <T> ArrayList<T> readArray(Statement stmt, String sql, Class<T> type, int limit)
            throws ReflectiveOperationException, SQLException {
        ArrayList<T> out = new ArrayList<>(limit);
        readArray(stmt, sql, type, out, limit);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt  the database to read
     * @param type  given type to return
     * @param limit limit how many rows to read
     * @return rows data as object in a list, empty if no result
     */
    public <T> ArrayList<T> readArray(PreparedStatement stmt, Class<T> type, int limit)
            throws ReflectiveOperationException, SQLException {
        ArrayList<T> out = new ArrayList<>(limit);
        readArray(stmt, type, out, limit);
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
    public <T> void readArray(Statement stmt, String sql, Class<T> type, Collection<T> out, int limit)
            throws ReflectiveOperationException, SQLException {
        try (ResultSet rs = stmt.executeQuery(sql)) {
            new InternalDsFactory<>(type).readArray(rs, fieldMap, out, limit);
        }
    }

    /**
     * read rows as the given type into the given list
     *
     * @param stmt  the database to read
     * @param type  given type to return
     * @param limit limit how many rows to read
     */
    public <T> void readArray(PreparedStatement stmt, Class<T> type, Collection<T> out, int limit)
            throws ReflectiveOperationException, SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            new InternalDsFactory<>(type).readArray(rs, fieldMap, out, limit);
        }
    }
}
