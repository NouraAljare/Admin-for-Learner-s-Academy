package learnersAcademy.classes;



public class Classes {
    protected int id;
    protected String name; 
 
    public Classes() {
    }
 
    public Classes(int id) {
        this.id = id;
    }
 
    public Classes(int id, String name) {
        this(name);
        this.id = id;
    }
     
    public Classes(String name) {
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