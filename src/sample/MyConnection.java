package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static Connection getConnection(){
       Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/login","root", "");
            System.out.println("Database is connected !");
        }
        catch(Exception e)
        {
            System.out.print("Do not connect to DB - Error:"+e);
        }
        return conn;
    }

}
