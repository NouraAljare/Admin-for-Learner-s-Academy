package learnersAcademy;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import learnersAcademy.admin.AdminDAO;
import learnersAcademy.classes.*;
import learnersAcademy.students.*;
import learnersAcademy.subject.*;
import learnersAcademy.teacher.*;  


public class ControllerServlet extends HttpServlet {  
    private ClassDAO classDAO;
	private SubjectDAO subjectDAO;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	private TeacherTeachDAO teacherTeachDAO;
 
    public void init() {
        classDAO = new ClassDAO();
        subjectDAO = new SubjectDAO();        
        studentDAO = new StudentDAO();      
        teacherDAO = new TeacherDAO();
        teacherTeachDAO = new TeacherTeachDAO();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        HttpSession session = request.getSession();  
    	String name=(String)session.getAttribute("name");  
        
        
        try {
        	System.out.println(action); 
        	if(name!=null){   
	        	if(action.equals("/home"))
	            	response.sendRedirect(request.getContextPath() + "/home.jsp");

	        	// class operations
	        	else if(action.equals("/NewClass"))
	        		showNewClassForm(request, response);	
	        	else if(action.equals("/ListClasses"))
	        		listOfClasses(request, response); 
	        	else if(action.equals("/insertClass"))
	        		insertClass(request, response);
	        	else if(action.equals("/classEdit"))
	        		showClassEditForm(request, response);
	        	else if(action.equals("/updateClass"))
	        		updateClass(request, response);
	        	else if(action.equals("/classDelete"))
	        		deleteClass(request, response);
	        	


	        	// subject operations
	        	else if(action.equals("/NewSubject"))
	        		showNewSubjectForm(request, response);	
	        	else if(action.equals("/ListSubjects"))
	        		listOfSubjects(request, response); 
	        	else if(action.equals("/insertSubject"))
	        		insertSubject(request, response);
	        	else if(action.equals("/subjectEdit"))
	        		showSubjectEditForm(request, response);
	        	else if(action.equals("/updateSubject"))
	        		updateSubject(request, response);
	        	else if(action.equals("/subjectDelete"))
	        		deleteSubject(request, response);
	        	


	        	// student operations
	        	else if(action.equals("/NewStudent"))
	        		showNewStudentForm(request, response);	
	        	else if(action.equals("/ListStudents"))
	        		listOfStudents(request, response); 
	        	else if(action.equals("/insertStudent"))
	        		insertStudent(request, response);
	        	else if(action.equals("/studentEdit"))
	        		showStudentEditForm(request, response);
	        	else if(action.equals("/updateStudent"))
	        		updateStudent(request, response);
	        	else if(action.equals("/studentDelete"))
	        		deleteStudent(request, response);
	        	


	        	// teacher operations
	        	else if(action.equals("/NewTeacher"))
	        		showNewTeacherForm(request, response);	
	        	else if(action.equals("/ListTeachers"))
	        		listOfTeachers(request, response); 
	        	else if(action.equals("/insertTeacher"))
	        		insertTeacher(request, response);
	        	else if(action.equals("/teacherEdit"))
	        		showTeacherEditForm(request, response);
	        	else if(action.equals("/updateTeacher"))
	        		updateTeacher(request, response);
	        	else if(action.equals("/teacherDelete"))
	        		deleteTeacher(request, response);
	        	else if(action.equals("/addSubject"))
	        		showAddTeacherSubjectForm(request, response);
	        	else if(action.equals("/viewSubject"))
	        		showTeacherSubjectList(request, response);
	        	else if(action.equals("/removeLink"))
	        		removeLinkTeacherSubject(request, response);
	        	


	        	else if(action.equals("/Logout"))
	        		Logout(request, response);
        	}  
            else{    
            	if(action.equals("/adminLogin")) {
	                adminLogin(request, response); 
	        	}else {
	        		response.sendRedirect(request.getContextPath() + "/login.jsp");
	        	}
            }  
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 

    //---------------------------------------------------
    //----------------------------- CLASS OPERATIONS ------
    //---------------------------------------------------
    

    private void showNewClassForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("classForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showClassEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Classes existingClass = classDAO.get_class(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classForm.jsp");
        request.setAttribute("_class", existingClass);
        dispatcher.forward(request, response);
 
    }
 

    private void insertClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
 
        Classes _class= new Classes(name);
        classDAO.insert_class(_class);
        response.sendRedirect("ListClasses");
    }
    

    private void updateClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");  
        Classes c = new Classes(id, name);
        classDAO.update_class(c);
        response.sendRedirect("ListClasses");
    }
    
    private void listOfClasses(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Classes> list = classDAO.listAllClasses();
        request.setAttribute("listClasses", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classList.jsp");
        dispatcher.forward(request, response);
    }
    

    private void deleteClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Classes c = new Classes(id);
        classDAO.delete_class(c);
        response.sendRedirect("ListClasses");
 
    }

    //-----------------------------------
    //-----------------------------------
    //-----------------------------------
    

    //---------------------------------------------------
    //----------------------------- SUBJECT OPERATIONS ------
    //---------------------------------------------------
    

    private void showNewSubjectForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	List<Classes> list = classDAO.listAllClasses();
        request.setAttribute("listClasses", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subjectForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showSubjectEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Subject existingSubject = subjectDAO.getsubject(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subjectForm.jsp");
        request.setAttribute("subject", existingSubject);
    	List<Classes> list = classDAO.listAllClasses();
        request.setAttribute("listClasses", list);
        dispatcher.forward(request, response);
 
    }
 

    private void insertSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int class_id = Integer.parseInt(request.getParameter("class_id"));
 
        Subject subject= new Subject(name , class_id);
        subjectDAO.insertsubject(subject);
        response.sendRedirect("ListSubjects");
    }
    

    private void updateSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");  
        int class_id = Integer.parseInt(request.getParameter("class_id"));

        Subject subject= new Subject(id , name , class_id);
        subjectDAO.updatesubject(subject);
        response.sendRedirect("ListSubjects");
    }
    
    private void listOfSubjects(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Subject> list = subjectDAO.listAllsubject();
        request.setAttribute("listSubjects", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subjectList.jsp");
        dispatcher.forward(request, response);
    }
    

    private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Subject c = new Subject(id);
        subjectDAO.deletesubject(c);
        response.sendRedirect("ListSubjects");
 
    }

    //-----------------------------------
    //-----------------------------------
    //-----------------------------------
    

    //---------------------------------------------------
    //----------------------------- STUDENT OPERATIONS ------
    //---------------------------------------------------
    

    private void showNewStudentForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	List<Classes> list = classDAO.listAllClasses();
        request.setAttribute("listClasses", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showStudentEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.getStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentForm.jsp");
        request.setAttribute("student", existingStudent);
    	List<Classes> list = classDAO.listAllClasses();
        request.setAttribute("listClasses", list);
        dispatcher.forward(request, response);
 
    }
 

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int class_id = Integer.parseInt(request.getParameter("class_id"));
 
        Student student= new Student(name , class_id);
        studentDAO.insertstudent(student);
        response.sendRedirect("ListStudents");
    }
    

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");  
        int class_id = Integer.parseInt(request.getParameter("class_id"));

        Student subject= new Student(id , name , class_id);
        studentDAO.updateStudent(subject);
        response.sendRedirect("ListStudents");
    }
    
    private void listOfStudents(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> list = studentDAO.listAllStudents();
        request.setAttribute("listStudents", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
        dispatcher.forward(request, response);
    }
    

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Student c = new Student(id);
        studentDAO.deleteStudent(c);
        response.sendRedirect("ListStudents");
 
    }


    //---------------------------------------------------
    //----------------------------- TEACHER OPERATIONS ------
    //---------------------------------------------------
    

    private void showNewTeacherForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showTeacherEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher existingTeacher = teacherDAO.getteacher(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherForm.jsp");
        request.setAttribute("teacher", existingTeacher);
        dispatcher.forward(request, response);
 
    }
 

    private void insertTeacher(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
 
        Teacher teacher= new Teacher(name);
        teacherDAO.insertteacher(teacher);
        response.sendRedirect("ListTeachers");
    }
    

    private void updateTeacher(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");  
        Teacher c = new Teacher(id, name);
        teacherDAO.updateteacher(c);
        response.sendRedirect("ListTeachers");
    }
    
    private void listOfTeachers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Teacher> list = teacherDAO.listAllteacher();
        request.setAttribute("listTeachers", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherList.jsp");
        dispatcher.forward(request, response);
    }
    

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Teacher c = new Teacher(id);
        teacherDAO.deleteteacher(c);
        response.sendRedirect("ListTeachers");
 
    }
    
    
    private void showAddTeacherSubjectForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

    	List<Classes> list = classDAO.listAllClasses();
        int class_id = 0;
        if(request.getParameter("class_id") != null)
        	class_id =Integer.parseInt(request.getParameter("class_id"));
        
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher existingTeacher = teacherDAO.getteacher(id); 
        
        request.setAttribute("teacher", existingTeacher);
        request.setAttribute("listClasses", list);
        request.setAttribute("class_id", class_id);
        

        List<Subject> subjectsList = subjectDAO.listAllsubject();
        request.setAttribute("listSubjects", subjectsList);

        if(request.getParameter("add") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("teacher_subject_link_form.jsp");
            dispatcher.forward(request, response);
        }
		else 
			linkTeacherSubject(request, response);		
        
    }

    
    private void linkTeacherSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	int class_id = 0;
        if(request.getParameter("class_id") != null)
        	class_id =Integer.parseInt(request.getParameter("class_id"));
        
        int id = Integer.parseInt(request.getParameter("id"));
        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
 
        teacherDAO.linkSubjectTeacher(id, class_id, subject_id);
        response.sendRedirect("ListTeachers");
    }
    
    private void removeLinkTeacherSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        TeacherTeach c = new TeacherTeach(id);
        teacherTeachDAO.removeLink(c);
        response.sendRedirect("ListTeachers");
 
    }

    private void showTeacherSubjectList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
    	List<TeacherTeach> list = teacherTeachDAO.listAllTeaching(id);  
    
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher_subject_link_list.jsp");

        request.setAttribute("teachingList", list);
        dispatcher.forward(request, response); 
        
    }
    
    //-----------------------------------
    //-----------------------------------
    //-----------------------------------
    
    //-----------------------------------
    //----------------------------------- ADMIN LOGIN AND LOGOUT
    //-----------------------------------

    private void adminLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException,  ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password"); 
        AdminDAO admin = new AdminDAO(); 
        if(admin.adminLogin(name , password)) {

            HttpSession session=request.getSession();  
            session.setAttribute("name",name);  
            
            response.sendRedirect("home");
        }else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("loginProcess", 0);
            dispatcher.forward(request, response);
        }
    }
    
    private void Logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException,  ServletException, IOException  {

        HttpSession session=request.getSession();  
        session.invalidate();  
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp"); 
        dispatcher.forward(request, response);
    }
    

    //-----------------------------------
    //-----------------------------------
    //-----------------------------------
}