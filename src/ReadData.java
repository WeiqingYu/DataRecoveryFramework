import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadData {
    private String host;
    private String user;
    private String password;
    private String table;
    public ReadData(String h, String u, String p, String t){
        host = h; user = u; password = p; table = t;
    }

    private int getRowNum(){
        Connection c = null;
        Statement stmt = null;
        int rownum = 0;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(host,
                            user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT count(*) cnt FROM "+ table+" LIMIT 200;" );
            rownum = rs.getInt("cnt");
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return rownum;
    }

    private int rowNum = getRowNum();
    public double[][] data = new double[rowNum][96];

    public void read(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(host,
                            user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * cnt FROM "+ table+" LIMIT 200;" );
            int row = 0;
            String colname;
            while(rs.next()){
                for(int ind = 0; ind<96 ; ind = ind +1){
                    colname = "p"+Integer.toString(ind);
                    data[row][ind] = rs.getDouble(colname);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

}
