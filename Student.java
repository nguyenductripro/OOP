package Project;

public class Student {
    // Attribute
    private String Student_ID;
    private String Name, DOB, Major, Class;
    private long Tuition_fee_per_credit;
    private int Grade;
    private int Semester;
    private float GPA;
    // Method
    public Student(String Student_ID,String Name,String DOB,String Major,String Class,
                   long Tuition_fee_per_credit, int Grade, int Semester, float GPA){
                    this.Student_ID = Student_ID;
                    this.Name = Name;
                    this.DOB = DOB;
                    this.Major = Major;
                    this.Class = Class;
                    this.Tuition_fee_per_credit = Tuition_fee_per_credit;
                    this.Grade = Grade;
                    this.Semester = Semester;
                    this.GPA = GPA;
    }
    public String Get_Student_ID(){
        return this.Student_ID;
    }
    public String Name_toStandardize(){
        //hOnag nGHia pHAt -> Hoang Nghia Phat
        String[] list_words = this.Name.toLowerCase().trim().split("\\s+");
        String New_Name = "";
        for (String words : list_words){
            char[] word = words.toCharArray();
            String res = "";
            for (int idx_word =0 ; idx_word < word.length; idx_word ++){
                if (idx_word == 0){
                    res = res + Character.toUpperCase(word[idx_word]);
                }else{
                    res = res + word[idx_word];
                }
            }
            New_Name = New_Name + res + " ";
        }
        return New_Name.trim();
    }
    public String DOB_toStandardize(){
        StringBuilder stringBuilder = new StringBuilder(this.DOB);
        // 02/02/1222
        // 012
        if (stringBuilder.charAt(1) == '/'){
            stringBuilder.insert(0, '0');
        }
        if(stringBuilder.charAt(4) == '/'){
            stringBuilder.insert(3, '0');
        }
        return stringBuilder.toString();
    }
    public String Major_toStandardize(){
        return this.Major.toUpperCase();
    }
    public String Get_Name(){
        return this.Student_ID;
    }
    public String Get_DOB(){
        return this.DOB;
    }
    public void Set_GPA(float GPA){
        this.GPA = GPA;
    }
    public String toString(){
        return this.Student_ID + " " + this.Name_toStandardize() + " " + this.DOB_toStandardize() + " " + this.Major_toStandardize() + " " + this.Class +
               " " + this.Tuition_fee_per_credit+ " " + this.Grade + " " + this.Semester + " " + this.GPA;
    }
}
