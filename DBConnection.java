import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    static final String URL="jdbc:mysql://localhost:3306/expense_tracker";
    static final String USER="root";
    static final String PASSWORD="Root@1234567890";
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
