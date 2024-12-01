
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LEGION
 */
public class Format {
    static public String juanWord(String a){
        StringBuilder c=new StringBuilder();
        c.append(Character.toUpperCase(a.charAt(0)));
        a=a.toLowerCase();
        c.append(a.substring(1));
        return c.toString();
    }
    static public String juanName(String name){
        String Name="";
        String s[]=name.toLowerCase().trim().split("\\s+");
        for(String x:s){
            Name+=juanWord(x)+" ";
        }
        return Name;
    }
    static public String juanDate(String birth){
        String Birth="";
        String s[]=birth.split("/");
        if(s[0].length()==1) s[0]="0"+s[0];
        if(s[1].length()==1) s[1]="0"+s[1];
        while(s[2].length()<4) s[2]="0"+s[2];
        Birth=s[0]+"/"+s[1]+"/"+s[2];
        return Birth;
    }
    static void fillData(Scanner sc){
        while(sc.hasNextLine()){
            String id=sc.nextLine();
            if(id.equals("END")) break;
            else{
                String name=sc.nextLine();
                String Birth=sc.nextLine();
                String Class=sc.nextLine();
                Student a;
                if(id.equals("")){
                    a=new Student(name,Birth,Class);
                }
                else a=new Student(id,name,Birth,Class);
                Management.addStudent(a);
                
            }
        }
        while(sc.hasNextLine()){
            String id=sc.nextLine();
            if(id.equals("END")) break;
            else{
                String name=sc.nextLine();
                int credit=Integer.parseInt(sc.nextLine());
                Course course=new Course(id,name,credit);
                Management.addCourse(course);
                
            }
        }
        //Management.printListStudent();
        // Management.printListCourse();
        // Test
        //System.out.println("test Add_student:");
        Student a = new Student("E22DCCN300", "    hoang       NGHIa     phat      ", "02/03/2004", "E22CQCN05");
        // System.out.println(a);
        Management.addStudent(a);
        //Management.printListStudent();
        //Management.printListCourse();

        // test remove student
        //System.out.println("Test Remove_Student:");
        //Management.getCourseFromID("INT01").print_listStudent_inCourse();
        Management.getCourseFromID("INT01").addStudent("E22DCCN011");
        Management.getCourseFromID("INT69").addStudent("E22DCCN300");
        Management.getCourseFromID("INT01").addStudent("E22DCCN300");
        Management.getCourseFromID("INT49").addStudent("E22DCCN300");

        Management.getCourseFromID("INT01").addStudent("E22DCCN573");
        Management.getCourseFromID("INT01").addStudent("E22DCCN010");

        //Management.getCourseFromID("INT01").print_listStudent_inCourse();
        // Management.getCourseFromID("INT49").print_listStudent_inCourse();


        // Management.removeStudentByID("E22DCCN300");
        // Management.removeStudentByID("E22DCCN573");

        // Management.printListStudent();//check list studetnt sáu khi xóa
        // Management.getCourseFromID("INT01").print_listStudent_inCourse();//check course chứa học sinh bị xóa
        // Management.getCourseFromID("INT49").print_listStudent_inCourse();
        // xóa học sinh trog lớp
        // Management.getCourseFromID("INT01").removeStudent("E22DCCN300");
        //Management.printListStudent();
        //Management.getCourseFromID("INT01").print_listStudent_inCourse();
        //Management.getStudentFromID("E22DCCN300").printListCourse();


        // Test Point of student in each_course
        Management.getStudentFromID("E22DCCN300").updatePointofCourse("INT53", 2.31);//add course first
        Management.getStudentFromID("E22DCCN300").updatePointofCourse("INT01", 4.99);// point valid
        Management.getStudentFromID("E22DCCN300").updatePointofCourse("INT53", 1.0);
        Management.getStudentFromID("E22DCCN300").updatePointofCourse("INT01", 2.31);//updae point
        Management.getStudentFromID("E22DCCN300").updatePointofCourse("INT69", 2.0);
        Management.getStudentFromID("E22DCCN300").updatePointofCourse("INT49", 1.0);
        //Management.getStudentFromID("E22DCCN300").Printlist_point_of_Student();
        // test point of course in each_student
        Management.getCourseFromID("INT01").addStudent("E22DCCN184");
        Management.getCourseFromID("INT01").addStudent("E22DCCN183");
        Management.getCourseFromID("INT01").addStudent("E22DCCN947");
        Management.getCourseFromID("INT01").addStudent("E22DCCN507");
        Management.getCourseFromID("INT01").addStudent("E22DCCN105");

        Management.getCourseFromID("INT01").updatePointofStudent("E22DCCN184", 2.3);
        Management.getCourseFromID("INT01").updatePointofStudent("E22DCCN184", 4.0);
        Management.getCourseFromID("INT01").updatePointofStudent("E22DCCN947", 3.5);
        Management.getCourseFromID("INT01").updatePointofStudent("E22DCCN507", 2.0);
        Management.getCourseFromID("INT01").updatePointofStudent("E22DCCN105", 1.0);
        //Management.getCourseFromID("INT01").print_listStudent_inCourse();

        // test remove course from manage
        //Management.getStudentFromID("E22DCCN300").printListCourse();

        //Management.removeCourseByID("INT01");
        //Management.printListCourse();
        //Management.getStudentFromID("E22DCCN300").Printlist_point_of_Student();
       // Management.getStudentFromID("E22DCCN300").printListCourse();
        // test remove course from student
        Management.getStudentFromID("E22DCCN300").removeCourse("INT69");
        //Management.getCourseFromID("INT69").print_listStudent_inCourse();
        //Management.getStudentFromID("E22DCCN300").Printlist_point_of_Student();
        //
        for(int i=1;i<=7;i++){
            String mon=sc.next();
            Course x=Management.getCourseFromID(mon);
            int t=sc.nextInt();
            while(t-- >0){
                String sv=sc.next();
                x.addStudent(sv);
            }
            Management.updateCourseInfo(x);
        }
    }
}
