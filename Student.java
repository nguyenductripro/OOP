import java.util.*;
/**
 *
 * @author 9mar3
 */
public class Student {
    private String ID,name,birth,classRoom;
    private HashSet<String> listCourse=new HashSet<>();
    private HashMap<String,Double> listCoursePoint=new HashMap<>();
    private double GPA;
    
    
    public Student() {
    }

    public Student(String ID, String name, String birth, String classRoom) {
        this.ID = ID.toUpperCase();
        setName(name);
        setBirth(birth);
        this.classRoom = classRoom.toUpperCase();
    }
    public void addCourse(String a){
        if(!Management.containCourse(a)){
            System.out.println("This course is not exist.");
            return;
        }
        if(!listCourse.contains(a)){
            listCourse.add(a.toUpperCase());
            Course b=Management.getCourseFromID(a);
            b.addStudent(ID);
            Management.updateCourseInfo(b);
        }
    }
    
    public void updatePointofCourse(String id,double pts){ 
        if(!listCourse.contains(id)){
            System.out.println("Please add this course first");
        }
        else if(pts>4 || pts<0){
            System.out.println("Point is invalid");
        }
        else if(this.getPointofCourse(id)!=pts){
            listCoursePoint.put(id.toUpperCase(), pts);
            Course b=Management.getCourseFromID(id);
            b.updatePointofStudent(this.ID, pts);
        }
    }

    public void setGPA() {
        double sum=0;
        int credit=0;
        for(String x:listCourse){
            sum+=this.getPointofCourse(x)*Management.getCourseFromID(x).getCredit();
            credit+=Management.getCourseFromID(x).getCredit();
        }
        this.GPA=sum/credit;
    }
    
    public void setName(String name) {
        this.name=Format.juanName(name);
    }

    public void setBirth(String birth) {
        this.birth=Format.juanDate(birth);
    }
    
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getClassRoom() {
        return classRoom;
    }
    public double getPointofCourse(String id){
        return listCoursePoint.get(id);
    }

    public double getGPA() {
        setGPA();
        return GPA;
    }
    @Override
    public String toString() {
        return "Student{" + "ID=" + ID + ", name=" + name + ", birth=" + birth + ", classRoom=" + classRoom + '}';
    }
    
    
    public void printListCourse(){
        for(String x:listCourse){
            System.out.println(x);
        }
    }
    
}
