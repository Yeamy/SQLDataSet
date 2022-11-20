import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC implements Closeable {
	private final Properties info = new Properties();
	private final String url;
	protected Connection conn = null;
	protected Statement stmt = null;

	public JDBC() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.url = "jdbc:mysql://127.0.0.1/power";
		info.setProperty("allowPublicKeyRetrieval", "true");
		info.setProperty("useSSL", "false");
		info.setProperty("serverTimezone", "GMT+8");
		info.setProperty("user", "admin");
		info.setProperty("password", "admin123");
	}

	private void initConnection() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(url, info);
		}
	}

	private void initStatement() throws SQLException {
		initConnection();
		if (stmt == null) {
			stmt = conn.createStatement();
		}
		conn.setAutoCommit(true);
	}

	@Override
	public void close() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Statement getStatement() throws SQLException {
		initStatement();
		return stmt;
	}

}
