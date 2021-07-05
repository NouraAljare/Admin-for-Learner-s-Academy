package learnersAcademy.subject;



public class Subject {
    protected int id;
    protected int class_id;
    protected String name; 
    protected String classname; 
 
    public Subject() {
    }
 
    public Subject(int id) {
        this.id = id;
    }

    public Subject(int id, String name, int class_id) {
        this(name , class_id);
        this.id = id;
        
    }

    public Subject(int id, String name, int class_id, String classname) {
        this(name , class_id);
        this.id = id;
        this.classname = classname;
        
    }
     
    public Subject(String name, int class_id) {
        this.name = name;
        this.class_id = class_id;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getname() {
        return name;
    }
 
    public int getclassid() {
    	return class_id;
    }
    
    public void setname(String name) {
        this.name = name;
    }
    
    public void setClassName(String n) {
    	this.classname = n;
    }
    
    public String getclassname() {
    	return classname;
    }
 
}