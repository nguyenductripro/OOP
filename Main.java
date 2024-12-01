import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc=new Scanner(new File("DATA.in"));
        Format.fillData(sc);
        MenuJFrame x = new MenuJFrame();
      
        x.setVisible(true);
    }
    
}