/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class DBConnection {
    private static String DB_URL = "jdbc:sqlserver://localhost;"
            + "databaseName=Project_1_test1;";
    private static String USER_NAME = "SA";
    private static String PASSWORD = "123456"; 
    public static Connection conn;
    public DBConnection(){
        
        try {            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi thiếu thư viện kết nối");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối CSDL!");
        }
    }
}
