import yeamy.sql.ds.DsReader;

import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        try (JDBC jdbc = new JDBC();
             Statement st = jdbc.getStatement()) {
//            System.out.println(DsReader.getString(st, "select PlaceName from bsPlace limit 1"));
            String sql = "select PlaceName, ab_cd_ef from bsPlace limit 1";
            System.out.println("map: " + DsReader.read(st, sql));
            System.out.println("bean: " + DsReader.read(st, sql, Bean.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
