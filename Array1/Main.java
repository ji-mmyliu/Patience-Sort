import java.util.*;
import java.io.*;

public class Main {

    public static String names[] = new String[35];
    public static int marks[] = new int[35];
    public static void main(String[] args) throws IOException{
        
    }

    public static void parseFile(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        for(int i=0; i<35; i++) {
            names[i]=br.readLine();
            marks[i]=Integer.parseInt(br.readLine());
        }
    }
}