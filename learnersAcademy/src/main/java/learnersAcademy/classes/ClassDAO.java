package learnersAcademy.classes;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import learnersAcademy.utils.MySQLDatabaseUtils;


public class ClassDAO {

    private Connection jdbcConnection;
     
    public ClassDAO() {
    	
    }
      

    protected void connect() throws SQLException {
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
     
    public boolean insert_class(Classes _class) throws SQLException {
    	connect();
        String sql = "INSERT INTO classes (name) VALUES (?)";          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, _class.getname());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Classes> listAllClasses() throws SQLException {
    	connect() ;
        List<Classes> list_class = new ArrayList<Classes>();
         
        String sql = "SELECT * FROM classes";
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name"); 
             
            Classes _class = new Classes(id, name);
            list_class.add(_class);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return list_class;
    }
     
    public boolean delete_class(Classes _class) throws SQLException {
    	connect() ;
        String sql = "DELETE FROM classes where id = ?";
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, _class.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean update_class(Classes _class) throws SQLException {
    	connect() ;
        String sql = "UPDATE classes SET name = ?";
        sql += " WHERE id = ?"; 
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, _class.getname());
        statement.setInt(2, _class.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Classes get_class(int id) throws SQLException {
    	connect() ;
        Classes _class = null;
        String sql = "SELECT * FROM classes WHERE id = ?";
          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
             
            _class = new Classes(id, name);
        }
         
        resultSet.close();
        statement.close();
         
        return _class;
    }
}
