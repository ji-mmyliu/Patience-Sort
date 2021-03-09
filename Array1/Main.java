import java.util.*;
import java.io.*;

public class Main {

    public static ArrayList<String> names;
    public static ArrayList<Integer> marks;
    public static void main(String[] args) throws IOException{
        names = new ArrayList<String>();
        marks = new ArrayList<Integer>();
        
    }

    public static void parseFile(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        while(true) {
            String line = br.readLine()
            if(line == null)break;
            names[i]=br.readLine();
            marks[i]=Integer.parseInt(br.readLine());
        }
    }
}
