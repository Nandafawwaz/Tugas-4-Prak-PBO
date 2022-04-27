package Connection;

import java.sql.*;
import javax.swing.JOptionPane;

public class Connector {
    public Connection koneksi;
    public Statement statement;
    public Connector() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/tugasjdbc","root", "");
            System.out.print("Database is connected !");
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
    }
}