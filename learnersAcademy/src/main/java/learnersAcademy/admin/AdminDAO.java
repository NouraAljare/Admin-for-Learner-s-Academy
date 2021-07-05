package learnersAcademy.admin;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import learnersAcademy.utils.*;

public class AdminDAO { 
    private Connection jdbcConnection;
     
    public AdminDAO() {
        try {
			this.jdbcConnection  = MySQLDatabaseUtils.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
      
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
          
    public boolean adminLogin(String name, String pass) throws SQLException { 
         
        String sql = "SELECT * FROM admin where name = '"+name+"' and `pass` = '"+pass+"'";  
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boolean exist = false; 
        while (resultSet.next()) {
        	exist = true;
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return exist;
    } 
}
