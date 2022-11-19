package yeamy.sql.ds;

import java.sql.ResultSet;

public interface DsColumnIndex {

	void read(ResultSet rs, Object t);
}