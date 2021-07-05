package learnersAcademy.teacher;



public class Teacher {
    protected int id;
    protected String name; 
 
    public Teacher() {
    }
 
    public Teacher(int id) {
        this.id = id;
    }
 
    public Teacher(int id, String name) {
        this(name);
        this.id = id;
    }
     
    public Teacher(String name) {
        this.name = name;
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
 
    public void setname(String name) {
        this.name = name;
    }
 
}