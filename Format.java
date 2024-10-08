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
}
