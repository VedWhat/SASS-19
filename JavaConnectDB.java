
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ved
 */
public class JavaConnectDB {
   public static Connection ConnectDB(){
       try{
           Class.forName("oracle.jdbc.OracleDriver");
           Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ved","Sanved58683");
           return con;
       }
       catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
           
       }
       return null;
   }
}
