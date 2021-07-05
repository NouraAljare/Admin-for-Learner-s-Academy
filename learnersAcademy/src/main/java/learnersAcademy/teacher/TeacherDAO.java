package learnersAcademy.teacher;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import learnersAcademy.utils.MySQLDatabaseUtils;


public class TeacherDAO {

    private Connection jdbcConnection;
     
    public TeacherDAO() {
    	
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
     
    public boolean insertteacher(Teacher teacher) throws SQLException {
    	connect();
        String sql = "INSERT INTO teachers (name) VALUES (?)";          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, teacher.getname());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Teacher> listAllteacher() throws SQLException {
    	connect() ;
        List<Teacher> listteacher = new ArrayList<Teacher>();
         
        String sql = "SELECT * FROM teachers";
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name"); 
             
            Teacher teacher = new Teacher(id, name);
            listteacher.add(teacher);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listteacher;
    }
     
    public boolean deleteteacher(Teacher teacher) throws SQLException {
    	connect() ;
        String sql = "DELETE FROM teachers where id = ?";
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, teacher.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateteacher(Teacher teacher) throws SQLException {
    	connect() ;
        String sql = "UPDATE teachers SET name = ?";
        sql += " WHERE id = ?"; 
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, teacher.getname());
        statement.setInt(2, teacher.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Teacher getteacher(int id) throws SQLException {
    	connect() ;
        Teacher teacher = null;
        String sql = "SELECT * FROM teachers WHERE id = ?";
          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
             
            teacher = new Teacher(id, name);
        }
         
        resultSet.close();
        statement.close();
         
        return teacher;
    }


	public boolean linkSubjectTeacher(int id, int class_id, int subject_id) throws SQLException {
		connect();
        String sql = "INSERT INTO teacher_teach (teacher_id , class_id, subject_id) VALUES (? , ? , ?)";          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setInt(2, class_id);
        statement.setInt(3, subject_id);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
	}
}
