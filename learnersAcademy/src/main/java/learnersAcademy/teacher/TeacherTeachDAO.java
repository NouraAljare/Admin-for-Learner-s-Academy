package learnersAcademy.teacher;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import learnersAcademy.classes.ClassDAO;
import learnersAcademy.subject.SubjectDAO;
import learnersAcademy.utils.MySQLDatabaseUtils;


public class TeacherTeachDAO {

    private Connection jdbcConnection;
     
    public TeacherTeachDAO() {
    	
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

    
    public List<TeacherTeach> listAllTeaching(int teacher_id) throws SQLException {
    	connect() ;
        List<TeacherTeach> listteacher = new ArrayList<TeacherTeach>();
         
        String sql = "SELECT * FROM teacher_teach where teacher_id = " + teacher_id;
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int subject_id = resultSet.getInt("subject_id"); 
            int class_id = resultSet.getInt("class_id"); 

            ClassDAO _class = new ClassDAO();
            SubjectDAO subject = new SubjectDAO();

            String subjectName = subject.getsubject(subject_id).getname();
            String className = _class.get_class(class_id).getname();
            TeacherTeach teacherT = new TeacherTeach(id, className, subjectName);
            teacherT.setClassID(class_id);
            teacherT.setSubjectID(subject_id);
            listteacher.add(teacherT);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listteacher;
    }
     
    public boolean removeLink(TeacherTeach t) throws SQLException {
    	connect() ;
        String sql = "DELETE FROM teacher_teach where id = ?";
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, t.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }

  
}
