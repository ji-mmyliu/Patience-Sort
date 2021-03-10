import java.util.*;
import java.io.*;

public class SortingStudentsDriver {
    /** This array list contains the list of the student names */
    public static ArrayList<String> names;

    /** This array list contains the list of the student marks */
    public static ArrayList<Integer> marks;

    /**
     * This is the program main method which will initialize the fields
     * @param args The command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        names = new ArrayList<String>();
        marks = new ArrayList<Integer>();
    }

    /**
     * This method parses the input file and stores the information in the arraylists
     * @param fileName
     * @throws IOException
     */
    public static void parseFile(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        while(true) {
            String line = br.readLine();
            if(line == null)break;
            names.add(br.readLine());
            marks.add(Integer.parseInt(br.readLine()));
        }
    }
}
