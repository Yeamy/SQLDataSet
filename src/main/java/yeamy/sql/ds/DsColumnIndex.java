package yeamy.sql.ds;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DsColumnIndex {

	void read(ResultSet rs, Object t)throws SQLException, InstantiationException, IllegalAccessException;
}