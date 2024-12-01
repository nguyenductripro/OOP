import java.util.*;
/**
 *
 * @author 9mar3
 */
public class Student {
    private String ID,name,birth,classRoom;
    private  HashSet<String> listCourse=new HashSet<>();
    private  HashMap<String,Double> listCoursePoint=new HashMap<>();
    private double GPA;
    private static int i=0;
    
    public Student() {
    }

    public Student(String ID, String name, String birth, String classRoom) {
        this.ID = ID.toUpperCase();
        setName(name);
        setBirth(birth);
        this.classRoom = classRoom.toUpperCase();
    }
    // phatthoang
    public boolean hasCourse(String courseID) {
        return listCourse.contains(courseID.toUpperCase());
    }
    public Set<String> getListCourse() {
        return listCourse;
    }
    public HashMap<String,Double> getListCoursePoint(){
        return listCoursePoint;
    }
    // end
    public Student(String name, String birth, String classRoom) {
        i++;
        while(Management.containStudent(String.format("E22DCCN%03d", i))==true) i++;
        this.ID=String.format("E22DCCN%03d", i);
        setName(name);
        setBirth(birth);
        this.classRoom = classRoom.toUpperCase();
    }
    // phatthoang
    public void addCourse(String a) {
        if (!Management.containCourse(a)) {
            System.out.println("This course is not exist.");
            return;
        }
    
        if (!listCourse.contains(a)) {
            listCourse.add(a.toUpperCase());
            Course b = Management.getCourseFromID(a);
    
            // Chỉ thêm sinh viên vào khóa học nếu chưa tồn tại
            if (!b.hasStudent(ID)) {  // Thêm phương thức kiểm tra trong Course
                b.addStudent(ID);
                Management.updateCourseInfo(b);
            }
        }
    }
    // end
    
    // public void updatePointofCourse(String id,double pts){ 
    //     if(!listCourse.contains(id)){
    //         System.out.println("Please add this course first");
    //     }
    //     else if(pts>4 || pts<0){
    //         System.out.println("Point is invalid");
    //     }
    //     else if(this.getPointofCourse(id)!=pts){
    //         listCoursePoint.put(id.toUpperCase(), pts);
    //         Course b=Management.getCourseFromID(id);
    //         b.updatePointofStudent(this.ID, pts);
    //     }
    // }
    //Phat Hoang
    public void updatePointofCourse(String ID_Course,double Score){ 
        if(!listCourse.contains(ID_Course)){
            System.out.println("Please add this course first");
        }
        else if(Score>4 || Score<0){
            System.out.println("Point is invalid");
        }
        else if(this.getPointofCourse(ID_Course) != Score){
            listCoursePoint.put(ID_Course.toUpperCase(), Score);
            Course course=Management.getCourseFromID(ID_Course);
            if(course != null){
                course.updatePointofStudent(this.ID,Score);
            }
            else{
                System.out.println("Course not found.");
            }
        }
    }
    //end
    public void setGPA() {
        double sum=0;
        int credit=0;
        for(String x:this.listCoursePoint.keySet()){
            int cre=Management.getCourseFromID(x).getCredit();
            sum+=listCoursePoint.get(x)*cre;
            credit+=cre;
        }
        if(credit==0) this.GPA=-1.0;
        else this.GPA=sum/credit;
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

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
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
    // public double getPointofCourse(String id){
    //     return listCoursePoint.get(id);
    // }
    //Phat Hoang
    public double getPointofCourse(String ID_Course){
        if (!listCoursePoint.containsKey(ID_Course)){
            return -1.0;
        }
        return listCoursePoint.get(ID_Course);
    }
    public void Printlist_point_of_Student(){
        if (listCourse.isEmpty()) {
            System.out.println("There are no course.");
            return;
        }
        System.out.println("Numbers course of Student " + this.ID +":" + listCourse.size());
        System.out.println("List point of Student " + this.ID + ":");
        for (Map.Entry<String, Double> entry : listCoursePoint.entrySet()){
            for (String ID_Course : Management.get_listCourse().keySet()){
                if (entry.getKey().compareTo(ID_Course) ==0){
                    System.out.println("    " + "ID:"+entry.getKey() + " " + "Name:"+ Management.get_listCourse().get(ID_Course).getName()
                                        + " " + "GPA:"+ entry.getValue());
                    break;
                }
            }
        }
        System.out.println("=>" + " GPA of Student " + this.ID + ":" + this.getGPA());
    }
    public void removeCourse(String courseID) {
        if (listCourse.contains(courseID)) {
            listCourse.remove(courseID); // Xóa khóa học khỏi danh sách
            listCoursePoint.remove(courseID); // Xóa điểm liên quan
            // System.out.println("Course " + courseID + " has been removed from the student's course list.");
            // 
            Course course = Management.get_listCourse().get(courseID);
            if(course != null){
                course.removeStudent(this.ID);
            }
        } else {
            // System.out.println("Course " + courseID + " is not in the student's course list.");
        }
    }
    
    //end
    public double getGPA() {
        setGPA();
        return GPA;
    }
    @Override
    public String toString() {
        return "" + ID + " " + name + " " + birth + " " + classRoom ;
    }
    // phathoang
    
    public void printListCourse(){
        System.out.println("List course of Student " + this.ID + ":");
        for(String x:listCourse){
            System.out.println("    " + x);
        }
    }
    // phatthoang
    public String[][] get_course_of_Student() {
        // Tính số lượng khóa học của sinh viên
        int totalCourses = this.listCourse.size();
    
        // Mỗi khóa học sẽ chiếm một hàng
        // 5 cột: Student ID, Name_student, ID_course, Name_course, Credit
        String[][] result = new String[totalCourses][4];
    
        int row = 0;
        for (String courseID : this.listCourse) {
            Course course = Management.getCourseFromID(courseID); // Giả sử bạn có phương thức lấy thông tin khóa học theo ID
    
            // Điền thông tin vào từng hàng
            result[row][0] = courseID; // ID_course
            result[row][1] = course.getName(); // Name_course
            result[row][2] = String.valueOf(course.getCredit()); // Credit
            String x="/haven't had yet";
            if(this.listCoursePoint.containsKey(courseID)){
                x=""+listCoursePoint.get(courseID);
            }
            result[row][3]= x;
            row++;
        }
    
        return result;
    }
    
}
