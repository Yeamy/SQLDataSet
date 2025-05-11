package yeamy.sql.ds;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DsFieldReader<T> {

    /**
     * @param rs jdbc query resultSet
     * @param columnIndex column index in resultSet
     * @return field instance
     */
    T read(ResultSet rs, int columnIndex) throws SQLException, ReflectiveOperationException;

}
