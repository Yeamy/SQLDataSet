package yeamy.sql.ds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * The factory to generate java bean,
 * add custom types' adapter to create fields value.
 * You can cache the factory to avoid too much reflection.
 */
public interface DsFactory<T> {

    T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException;

    void readArray(Collection<T> out, ResultSet rs, int limit) throws SQLException, InstantiationException, IllegalAccessException;

    void readArray(Collection<T> out, ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException;

}
