package learnersAcademy.subject;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import learnersAcademy.utils.MySQLDatabaseUtils;
import learnersAcademy.classes.*;

public class SubjectDAO {

    private Connection jdbcConnection;
    private ClassDAO classDAO;
    public SubjectDAO () {
    	classDAO = new ClassDAO();
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
     
    public boolean insertsubject(Subject subject) throws SQLException {
    	connect();
        String sql = "INSERT INTO subjects (name , class_id) VALUES (? , ?)";          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, subject.getname());
        statement.setInt(2, subject.getclassid());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Subject> listAllsubject() throws SQLException {
    	connect() ;
        List<Subject> listsubject = new ArrayList<Subject>();
         
        String sql = "SELECT * FROM subjects";
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name"); 
            int class_id = resultSet.getInt("class_id"); 
            Classes c = classDAO.get_class(class_id);
            Subject subject = new Subject(id, name, class_id , c.getname());
            listsubject.add(subject);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listsubject;
    } 
    
    public boolean deletesubject(Subject subject) throws SQLException {
    	connect() ;
        String sql = "DELETE FROM subjects where id = ?";
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, subject.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updatesubject(Subject subject) throws SQLException {
    	connect() ;
        String sql = "UPDATE subjects SET name = ? , class_id = ? ";
        sql += " WHERE id = ?"; 

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, subject.getname());
        statement.setInt(2, subject.getclassid());
        statement.setInt(3, subject.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Subject getsubject(int id) throws SQLException {
    	connect() ;
    	Subject subject = null;
        String sql = "SELECT * FROM subjects WHERE id = ?";
          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int class_id = resultSet.getInt("class_id");
            
            subject = new Subject(id, name, class_id);
        }
         
        resultSet.close();
        statement.close();
         
        return subject;
    }
}
