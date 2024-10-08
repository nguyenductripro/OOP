import java.util.*;

public class ManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Thêm một số sinh viên và khóa học mẫu
        Student student1 = new Student("SV001", "Nguyễn Việt Hà", "07/07/2004", "CTK42");
        Student student2 = new Student("SV002", "Nguyễn Đức Trí", "30/10/2004", "CTK43");
        Student student3 = new Student("SV003", "Nguyễn Đại Phát", "25/10/2004", "CTK47");
        Student student4 = new Student("SV004", "Hoàng Nghĩa Phát", "11/10/2004", "CTK48");
        Student student5 = new Student("SV005", "Phạm Anh Minh", "12/10/2004", "CTK42");
        Student student6 = new Student("SV006", "Lương Nhật Minh", "2/6/2004", "CTK40");
        Student student7 = new Student("SV007", "Phạm Anh Duy", "15/9/2004", "CTK50");
        Student student8 = new Student("SV008", "Đinh Mạnh Hùng", "1/1/2004", "CTK50");
        Student student9 = new Student("SV009", "Nguyễn Việt Hoàng Hải", "11/1/2004", "CTK50");
        Student student10 = new Student("SV010", "Nguyễn Văn Tôn", "30/2/2004", "CTK50");
        Management.addStudent(student1);
        Management.addStudent(student2);
        Management.addStudent(student3);
        Management.addStudent(student4);
        Management.addStudent(student5);
        Management.addStudent(student6);
        Management.addStudent(student7);
        Management.addStudent(student8);
        Management.addStudent(student9);
        Management.addStudent(student10);
        
        Course course1 = new Course("CS101", "Lập Trình Hướng Đối Tượng", 3);
        Course course2 = new Course("CS102", "Cơ Sở Dữ Liệu", 4);
        Course course3 = new Course("CS103","An Toàn Thông Tin ",3);
        Course course4 = new Course("CS104","Mạng Máy Tính",3);
        Course course5 = new Course("CS105","Hệ Điều Hành",3);
        Course course6 = new Course("CS106","Kinh Tế Chính Trị",3);
        Management.addCourse(course1);
        Management.addCourse(course2);
        Management.addCourse(course3);
        Management.addCourse(course4);
        Management.addCourse(course5);
        Management.addCourse(course6);
        // Thêm khóa học cho sinh viên
       
        student1.addCourse("CS101");
        
        student1.addCourse("CS102");
        student3.addCourse("CS103");
        student4.addCourse("CS104");
        student2.addCourse("CS105");
        student7.addCourse("CS106");
        student10.addCourse("CS101");
        student9.addCourse("CS102");
        student9.addCourse("CS104");
        student5.addCourse("CS105");
        student6.addCourse("CS105");
        student8.addCourse("CS106");
        
        // Cập nhật điểm cho sinh viên
        student1.updatePointofCourse("CS101", 3.5);
        student1.updatePointofCourse("CS102", 4.0);
        student3.updatePointofCourse("CS103", 3.0);
        student4.updatePointofCourse("CS104", 2.0);
        student2.updatePointofCourse("CS105", 1.0);
        student7.updatePointofCourse("CS106", 2.0);
        student10.updatePointofCourse("CS101", 3.0);
        student9.updatePointofCourse("CS102", 4.0);
        student9.updatePointofCourse("CS104", 2.5);
        student6.updatePointofCourse("CS105", 1.5);
        student5.updatePointofCourse("CS105", 1.0);
        student8.updatePointofCourse("CS106", 1.0);
        
        // Hiển thị thông tin sinh viên và khóa học
        System.out.println("GPA của " + student1.getName() + ": " + student1.getGPA());
        System.out.println("GPA của " + student2.getName() + ": " + student2.getGPA());
        System.out.println("GPA của " + student3.getName() + ": " + student3.getGPA());
        System.out.println("GPA của " + student4.getName() + ": " + student4.getGPA());
        System.out.println("GPA của " + student5.getName() + ": " + student5.getGPA());
        System.out.println("GPA của " + student6.getName() + ": " + student6.getGPA());
        System.out.println("GPA của " + student7.getName() + ": " + student7.getGPA());
        System.out.println("GPA của " + student8.getName() + ": " + student8.getGPA());
        System.out.println("GPA của " + student9.getName() + ": " + student9.getGPA());
        System.out.println("GPA của " + student10.getName() + ": " + student10.getGPA());
      // In ra danh sách sinh viên và danh sách khóa học
        System.out.println("Danh Sách Sinh Viên");
        Management.printListStudent();
        Management.printListCourse();
        // Cho phép người dùng nhập thêm sinh viên và khóa học
        System.out.print("Nhập mã sinh viên mới: ");
        String newID = sc.nextLine();
        System.out.print("Nhập tên sinh viên mới: ");
        String newName = sc.nextLine();
        System.out.print("Nhập ngày sinh sinh viên mới (dd/MM/yyyy): ");
        String newBirth = sc.nextLine();
        System.out.print("Nhập lớp sinh viên mới: ");
        String newClassRoom = sc.nextLine();
        Student newStudent = new Student(newID, newName, newBirth, newClassRoom);
        Management.addStudent(newStudent);
        System.out.println("Sinh viên mới đã được thêm: " + newStudent);
        // In ra toàn bộ danh sách sinh viên sau khi thêm
        System.out.println("Danh sách sinh viên sau thêm");
        Management.printListStudent();
        Management.printListCourse();

    }
}
