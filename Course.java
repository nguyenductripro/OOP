
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
    private HashSet<String>listStudent=new HashSet<>();
    private HashMap<String,Double>listStudentPoint=new HashMap<>();
    public Course() {
    }
    public Course(String id, String name, int credit) {
        this.ID = id.toUpperCase();
        this.name = Format.juanName(name);
        this.credit = credit;
    }
    public void addStudent(String Sid){
        if(!Management.containStudent(Sid)){
            System.out.println("This student is not exist.");
            return;
        }
        if(!listStudent.contains(Sid)){
            listStudent.add(Sid);
            Student b=Management.getStudentFromID(Sid);
            b.addCourse(ID);
            Management.updateStudentInfo(b);
        }
    }
    public void updatePointofStudent(String id,double pts){
        if(!listStudent.contains(id)){
            System.out.println("Please add this student first");
        }
        else if(pts>4 || pts<0){
            System.out.println("Point is invalid");
        }
        else if(this.getPointofStudent(id)!=pts){
            listStudentPoint.put(id.toUpperCase(), pts);
            Student b=Management.getStudentFromID(id);
            b.updatePointofCourse(this.ID, pts);
        }
    }

    @Override
    public String toString() {
        return "Course{" + "ID=" + ID + ", name=" + name + ", credit=" + credit + '}';
    }
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }
    public double getPointofStudent(String id){
        return listStudentPoint.get(id);
    }
    
}
