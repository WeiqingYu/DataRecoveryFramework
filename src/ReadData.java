import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ReadData {
    private String host;
    private String user;
    private String password;
    private String table;
    public int rowNum;
    public double[][] data;

    public ReadData(String h, String u, String p, String t){
        host = h; user = u; password = p; table = t;
    }
    public void printPara(){
        System.out.println("jdbc:postgresql://"+host+","+user+","+password+","+table);
    }

    private int getRowNum(){
        Connection c = null;
        String url ="jdbc:postgresql://"+ host;
        Statement stmt = null;
        int rownum = 0;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url,
                            user, password);
            c.setAutoCommit(false);
            System.out.println("Get Row Number: Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT count(*) cnt FROM "+ table+";" );
            rs.next();
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

    public void read(){
        rowNum = getRowNum();
        data = new double[rowNum][96];
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://"+host,
                            user, password);
            c.setAutoCommit(false);
            System.out.println("Read Data: Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM "+ table+";" );
            int row = 0;
            String colname;
            while(rs.next()){
                for(int ind = 0; ind<96 ; ind = ind +1){
                    colname = "p"+Integer.toString(ind);
                    data[row][ind] = rs.getDouble(colname);
                }
                row = row+1;
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
