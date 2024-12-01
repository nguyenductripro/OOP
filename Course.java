import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 9mar3 
 */
public class Course {
    private String ID,name;
    private int credit;
    private  HashSet<String>listStudent=new HashSet<>();
    private  HashMap<String,Double>listStudentPoint=new HashMap<>();
    public Course() {
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setListStudent(HashSet<String> listStudent) {
        this.listStudent = listStudent;
    }

    public void setListStudentPoint(HashMap<String, Double> listStudentPoint) {
        this.listStudentPoint = listStudentPoint;
    }
    public Course(String id, String name, int credit) {
        this.ID = id.trim().toUpperCase();
        this.name = Format.juanName(name);
        this.credit = credit;
    }
    // phatthoang
    public boolean hasStudent(String studentID) {
        return listStudent.contains(studentID.toUpperCase());
    }
    public HashSet<String> getListStudent() {
        return listStudent;
    }
    public HashMap<String,Double> getListStudentPoint() {
        return listStudentPoint;
    }
    public void addStudent(String Sid) {
        // Kiểm tra sinh viên có tồn tại không
        if (!Management.containStudent(Sid)) {
            System.out.println("This student does not exist.");
            return;
        }
    
        // Kiểm tra sinh viên đã có trong khóa học không
        if (!listStudent.contains(Sid)) {
            listStudent.add(Sid);
            //listStudentPoint.put(Sid, 0.0);//
            // Lấy thông tin sinh viên từ ID
            Student b = Management.getStudentFromID(Sid);
    
            // Cập nhật thông tin khóa học cho sinh viên (nếu chưa có)
            if (!b.hasCourse(ID)) {  // Thêm phương thức kiểm tra trong Student
                b.addCourse(ID);
                Management.updateStudentInfo(b);
            }
        } else {
            System.out.println("This student is added in the course.");
        }
    }
    
    public void print_listStudent_inCourse() {
        // Kiểm tra nếu không có sinh viên trong khóa học
        if (listStudentPoint.isEmpty()) {
            System.out.println("There are no students enrolled in this course.");
            return;
        }
    
        // In thông tin tổng số sinh viên
        System.out.println("Number of students in Course " + this.ID + ": " + listStudentPoint.size());
        System.out.println("List of information for students in course " + this.ID + ":");
    
        // Duyệt qua danh sách sinh viên trong khóa học
        for (Map.Entry<String, Double> entry : listStudentPoint.entrySet()) {
            String studentID = entry.getKey(); // Lấy ID sinh viên
            Double gpa = entry.getValue();     // Lấy điểm GPA
    
            // Lấy đối tượng sinh viên từ danh sách quản lý
            Student student = Management.getStudentFromID(studentID);
    
            if (student != null) { // Kiểm tra nếu sinh viên tồn tại
                System.out.println("    ID: " + studentID 
                    + " Name: " + student.getName()
                    + " GPA: " + gpa);
            } else {
                System.out.println("    ID: " + studentID + " (Student not found in the system) GPA: " + gpa);
            }
        }
    }
    
    // Phatt Hoang
    public void updatePointofStudent(String StuID, double point) {
        if (!this.listStudent.contains(StuID)) {
            System.out.println("Please add this student first");
        } else if (!(point > 4.0) && !(point < 0.0)) {
            Double currentPoint = this.getPointofStudent(StuID);
            if (currentPoint == null || currentPoint != point) {
                this.listStudentPoint.put(StuID.toUpperCase(), point);
                Student var4 = Management.getStudentFromID(StuID);
                if (var4 != null) {
                    var4.updatePointofCourse(this.ID, point);
                } else {
                    System.out.println("Student not found.");
                }
            }
        } else {
            System.out.println("Point is invalid");
        }
    }

    public void removeStudent(String studentID) {
        if (listStudent.contains(studentID)) {
            listStudent.remove(studentID);
            listStudentPoint.remove(studentID); // Xóa điểm số của học sinh
            // System.out.println("Student with ID " + studentID + " has been removed from course " + this.ID);
    
            // Xóa khóa học khỏi danh sách khóa học của học sinh
            Student student = Management.getStudentFromID(studentID);
            if (student != null) {
                student.removeCourse(this.ID);
            }
        } else {
            // System.out.println("Student with ID " + studentID + " is not exist in course " + this.ID);
        }
    }
    
    // end

    @Override
    public String toString() {
        return "" + ID + " " + name + " " + credit ;
    }
    // Phatt Hoang
    public String PrintCourse_inlist(){
        return "    " + "ID=" + ID + ", name=" + name + ", credit=" + credit;
    }
    //end
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }
    // public double getPointofStudent(String id){
    //     return listStudentPoint.get(id);
    // }
    // phatt hoang
    public double getPointofStudent(String id) {
        Double point = listStudentPoint.get(id);
        if (point == null) {
            return -1.0; // Hoặc giá trị mặc định khác
        }
        return point;
    }
    public int getNumberofStudent(){
        return listStudent.size();
    }
    public String[][] get_students_in_course() {
        // Mảng 2 chiều chứa thông tin học sinh của khóa học
        String[][] courseInfo = new String[listStudent.size()][5];  // listStudent là danh sách học sinh tham gia khóa học
    
        int rowIndex = 0;
    
        // Duyệt qua danh sách học sinh tham gia khóa học
        for (String studentID : this.listStudent) {
            // Lấy thông tin sinh viên từ listStudent trong lớp Management (hoặc bất kỳ nơi nào bạn lưu trữ danh sách sinh viên)
            Student student = Management.getStudentFromID(studentID);
            
            if (student != null) {
                // Lấy thông tin của học sinh
                courseInfo[rowIndex][0] = student.getID();
                courseInfo[rowIndex][1] = student.getName();
                courseInfo[rowIndex][2] = student.getBirth();
                courseInfo[rowIndex][3] = student.getClassRoom();
                String s="/haven't had yet";
                if(this.listStudentPoint.containsKey(student.getID())){
                    s=""+listStudentPoint.get(student.getID());
                }
                courseInfo[rowIndex][4] = s;
                rowIndex++;
            }
        }
    
        // Trả về mảng 2 chiều
        return courseInfo;
    }
    
}
