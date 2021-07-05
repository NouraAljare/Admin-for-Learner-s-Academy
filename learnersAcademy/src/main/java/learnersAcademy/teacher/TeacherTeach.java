package learnersAcademy.teacher;



public class TeacherTeach {
    protected int id;
    protected int teacher_id;
    protected int class_id;
    protected int subject_id;
    protected String class_name; 
    protected String subject_name; 
 
    public TeacherTeach() {
    }
 
    public TeacherTeach(int id) {
        this.id = id;
    }
 
    public TeacherTeach(int id, String class_name, String subject_name) {
        this(class_name, subject_name);
        this.id = id;
    }
     
    public TeacherTeach(String class_name, String subject_name) {
        this.class_name = class_name;
        this.subject_name = subject_name;
    }
 
    public int getId() {
        return id;
    }

    public void setClassID(int id) {
        this.class_id = id;
    }


    public void setSubjectID(int id) {
        this.subject_id = id;
    }


    public void setId(int id) {
        this.id = id;
    }

    
    public String getclassname() {
        return class_name;
    }
 
    public void setclassname(String name) {
        this.class_name = name;
    }

    public String getsubjectname() {
        return subject_name;
    }
 
    public void setsubjectname(String name) {
        this.subject_name = name;
    }
 
}