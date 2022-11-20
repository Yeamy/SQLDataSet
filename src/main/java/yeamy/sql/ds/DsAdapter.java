package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DsAdapter {

	/**
	 * @param field the reflection field
	 * @param rs the query result
	 * @param columnIndex the zero-based column index for the given column name,
	 *                    or -1 if the column name does not exist.
	 * @return the field value
	 */
	Object read(Field field, ResultSet rs, int columnIndex)
			throws SQLException, InstantiationException, IllegalAccessException;

}