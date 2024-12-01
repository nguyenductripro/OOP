
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 9mar3
 */
public class Management {
    private  static HashMap<String,Course> listCourse=new HashMap<>();
    private  static HashMap<String,Student> listStudent=new HashMap<>();
    
    
    
    public static boolean containStudent(String a){
        if(listStudent.containsKey(a)) return true;
        else return false;
    }
    public static boolean containCourse(String a){
        if(listCourse.containsKey(a)) return true;
        else return false;
    }
    // phatthoang
    public static  HashMap<String,Course> get_listCourse(){
        return listCourse;
    }
    public static HashMap<String,Student> get_listStudent(){
        return listStudent;
    }
    public static  void addStudent(Student a){
        if(listStudent.containsKey(a.getID())){
            System.out.println("This student with ID "+a.getID()+ " has been declare");
        }
        else listStudent.put(a.getID(), a);
    }
    
    public static Student getStudentFromID(String id) {
        Student student = listStudent.get(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " does not exist.");
        }
        return student; 
    }
    
    public static void updateStudentInfo(Student a){
        listStudent.put(a.getID(), a);
    }
    // end
    public static  void addCourse(Course a){
        if(listCourse.containsKey(a)){
            System.out.println("This Course has been declare");
        }
        else listCourse.put(a.getID(), a);
    }
    
    public static Course getCourseFromID(String id) {
        Course course = listCourse.get(id);
        if (course == null) {
            System.out.println("Course with ID " + id + " does not exist.");
        }
        return course; 
    }
    public static void updateCourseInfo(Course a){
        listCourse.put(a.getID(), a);
    }
    public static void printListStudent(){
        System.out.println("Number of list student: " + listStudent.size());
        System.out.println("List of Student: ");
        for(String x:listStudent.keySet()){
            System.out.println( listStudent.get(x));
        }
    }
    public static void printListCourse(){
        System.out.println("Number of list course: " + listCourse.size());
        System.out.println("List of Course: ");
        for(String x:listCourse.keySet()){
            System.out.println( listCourse.get(x));
        }
    }
    public static boolean removeStudentByID(String id) {
        // Kiểm tra nếu học sinh tồn tại
        if (!listStudent.containsKey(id)) {
            System.out.println("Student with ID " + id + " does not exist.");
            return false;
        }
    
        // Lấy đối tượng học sinh
        Student student = listStudent.get(id);
    
        // Tạo bản sao danh sách khóa học để tránh ConcurrentModificationException
        List<String> coursesToRemove = new ArrayList<>(student.getListCourse());
    
        // Xóa học sinh khỏi tất cả các khóa học mà học sinh tham gia
        for (String courseID : coursesToRemove) {
            Course course = listCourse.get(courseID);
            if (course != null) {
                course.removeStudent(id); // Xóa học sinh khỏi khóa học
            }
        }
    
        // Xóa danh sách khóa học và điểm số của học sinh
        student.getListCourse().clear();
        student.getListCoursePoint().clear();
    
        // Xóa học sinh khỏi danh sách
        listStudent.remove(id);
        System.out.println("Student with ID " + id + " has been removed from the system.");
        return true;
    }
    public static boolean removeCourseByID(String courseID) {
        // Kiểm tra nếu khóa học tồn tại
        if (!listCourse.containsKey(courseID)) {
            System.out.println("Course with ID " + courseID + " does not exist.");
            return false;
        }
    
        // Lấy đối tượng khóa học
        Course course = listCourse.get(courseID);
    
        // Tạo bản sao danh sách sinh viên trong khóa học để tránh ConcurrentModificationException
        List<String> studentsToRemove = new ArrayList<>(course.getListStudent());
    
        // Xóa khóa học khỏi danh sách khóa học của từng sinh viên
        for (String studentID : studentsToRemove) {
            Student student = listStudent.get(studentID);
            if (student != null) {
                student.removeCourse(courseID); // Xóa khóa học khỏi danh sách của sinh viên
                // Xóa điểm của sinh viên trong khóa học này
                // student.getListCoursePoint().remove(courseID); // Xóa điểm
            }
        }
    
        // Xóa danh sách sinh viên khỏi khóa học
        course.getListStudent().clear();
        course.getListStudentPoint().clear();
    
        // Xóa khóa học khỏi danh sách
        listCourse.remove(courseID);
    
        System.out.println("Course with ID " + courseID + " has been removed from the system.");
        return true;
    }

    
    // end
    //test
    
    public static String[][] getCourseWithStudentsInfo() {
        // Tính tổng số khóa học
        int totalCourses = listCourse.size();
    
        // Tạo mảng 2 chiều với kích thước xác định
        // 4 cột: CourseID, Name_Course, Credit, Số lượng học sinh
        String[][] result = new String[totalCourses][4];
    
        int row = 0;
        for (String courseID : listCourse.keySet()) {
            Course course = listCourse.get(courseID);
            HashSet<String> studentIDs = course.getListStudent();
    
            // Lấy thông tin khóa học
            result[row][0] = courseID; // Course ID
            result[row][1] = course.getName(); // Name_Course
            result[row][2] = String.valueOf(course.getCredit()); // Credit
            result[row][3] = String.valueOf(studentIDs.size()); // Số lượng học sinh
    
            row++;
        }
        return result;
    }
    
    public static String[][] getStudentWithCourseInfo() {
        List<String[]> studentCoursesList = new ArrayList<>();
    
        // Duyệt qua tất cả sinh viên
        for (String studentID : listStudent.keySet()) {
            Student student = listStudent.get(studentID);
    
            // Tạo mảng con chứa thông tin của sinh viên
            List<String> studentInfo = new ArrayList<>();
            studentInfo.add(student.getID());
            studentInfo.add(student.getName());
            studentInfo.add(student.getBirth());
            studentInfo.add(student.getClassRoom());
    
            // Lấy danh sách các khóa học mà sinh viên tham gia
            HashSet<String> courses = (HashSet<String>) student.getListCourse();
    
            // Kết hợp tất cả khóa học thành một chuỗi duy nhất, cách nhau bằng dấu phẩy (hoặc dấu khác)
            StringBuilder courseList = new StringBuilder();
            for (String course : courses) {
                if (courseList.length() > 0) {
                    courseList.append(", ");  // Thêm dấu phân cách giữa các khóa học
                }
                courseList.append(course);
            }
    
            // Thêm chuỗi khóa học vào danh sách thông tin sinh viên
            studentInfo.add(courseList.toString());
            //Thêm GPA
            if(student.getGPA() < 0){
                studentInfo.add("/haven't had yet");
            }else{
                studentInfo.add(String.format("%.2f", student.getGPA()));
            }
            // Thêm mảng con vào danh sách thông tin sinh viên
            studentCoursesList.add(studentInfo.toArray(new String[0]));  // Chuyển List thành mảng
        }
    
        // Chuyển List thành mảng 2 chiều
        return studentCoursesList.toArray(new String[0][0]);
    }
    
}