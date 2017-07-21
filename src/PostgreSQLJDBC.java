/**
 * Created by Weiqing on 7/20/2017.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PostgreSQLJDBC {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://192.168.2.1:5433/test_dl",
                            "postgres", "root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM power_4yr_rec LIMIT 200;" );
            while ( rs.next() ) {
                long id = rs.getLong("meterid");
                String  date = rs.getString("mdate");
                double value1  = rs.getDouble("p0");
                System.out.println( "ID = " + id );
                System.out.println( "DATE = " + date );
                System.out.println( "value = " + value1 );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
