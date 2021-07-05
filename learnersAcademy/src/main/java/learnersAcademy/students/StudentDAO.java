package learnersAcademy.students;


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

public class StudentDAO {

    private Connection jdbcConnection;
    private ClassDAO classDAO;
    public StudentDAO () {
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
     
    public boolean insertstudent(Student student) throws SQLException {
    	connect();
        String sql = "INSERT INTO students (name , class_id) VALUES (? , ?)";          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, student.getname());
        statement.setInt(2, student.getclassid());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Student> listAllStudents() throws SQLException {
    	connect() ;
        List<Student> liststudent = new ArrayList<Student>();
         
        String sql = "SELECT * FROM students";
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name"); 
            int class_id = resultSet.getInt("class_id"); 
            Classes c = classDAO.get_class(class_id);
            Student student = new Student(id, name, class_id , c.getname());
            liststudent.add(student);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return liststudent;
    }
     
    public boolean deleteStudent(Student student) throws SQLException {
    	connect() ;
        String sql = "DELETE FROM students where id = ?";
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, student.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateStudent(Student student) throws SQLException {
    	connect() ;
        String sql = "UPDATE students SET name = ? , class_id = ? ";
        sql += " WHERE id = ?"; 

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, student.getname());
        statement.setInt(2, student.getclassid());
        statement.setInt(3, student.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Student getStudent(int id) throws SQLException {
    	connect() ;
    	Student student = null;
        String sql = "SELECT * FROM students WHERE id = ?";
          
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int class_id = resultSet.getInt("class_id");
            
            student = new Student(id, name, class_id);
        }
         
        resultSet.close();
        statement.close();
         
        return student;
    }
}
