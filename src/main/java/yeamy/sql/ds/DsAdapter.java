package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DsAdapter {

	void read(Object t, Field field, ResultSet rs, int columnIndex)
			throws SQLException, InstantiationException, IllegalAccessException;

}