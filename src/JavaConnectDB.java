
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JavaConnectDB {
   public static Connection ConnectDB(){
       try{
           Class.forName("oracle.jdbc.OracleDriver");
           Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","chaitanyadoshi","i9koi");
           return con;
       }
       catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
           
       }
       return null;
   }
}
