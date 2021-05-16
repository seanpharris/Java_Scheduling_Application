
package DBConnection;

        import javax.management.Query;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.HashMap;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ07op5";
    private static final String password = "53689088581";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;

    private static final String MYSQLJBCDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "U07op5";

    public static Connection conn = null;

    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }
}