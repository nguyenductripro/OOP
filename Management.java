
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
    private static HashMap<String,Course> listCourse=new HashMap<>();
    private static HashMap<String,Student> listStudent=new HashMap<>();
    
    
    public static boolean containStudent(String a){
        if(listStudent.containsKey(a)) return true;
        else return false;
    }
    public static boolean containCourse(String a){
        if(listCourse.containsKey(a)) return true;
        else return false;
    }
    
    
    public static  void addStudent(Student a){
        if(listStudent.containsKey(a)){
            System.out.println("This student has been declare");
        }
        else listStudent.put(a.getID(), a);
    }
    public static Student getStudentFromID(String id){
        return listStudent.get(id);
    }
    public static void updateStudentInfo(Student a){
        listStudent.put(a.getID(), a);
    }
    
    
    public static  void addCourse(Course a){
        if(listCourse.containsKey(a)){
            System.out.println("This Course has been declare");
        }
        else listCourse.put(a.getID(), a);
    }
    public static Course getCourseFromID(String id){
        return listCourse.get(id);
    }
    public static void updateCourseInfo(Course a){
        listCourse.put(a.getID(), a);
    }
    
    public static void printListStudent(){
        for(String x:listStudent.keySet()){
            System.out.println(listStudent.get(x));
        }
    }
    public static void printListCourse(){
        for(String x:listCourse.keySet()){
            System.out.println(listCourse.get(x));
        }
    }
}
