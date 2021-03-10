import java.util.*;
import java.io.*;

public class SortingStudents {
   /** An array of the students' names */
   private String[] names;

   /** An array of the students' marks */
   private int[] marks;

   /** Constructor that initializes the names and marks of the students */
   public SortingStudents(){
      names = new String[35];
      marks = new int[35];
   }
   
   /**
    * Parses the text file into array data
    * @param fileName The name of the file to parse
    */
   public void parseFile(String fileName){
      try{
         BufferedReader br = new BufferedReader(new FileReader(fileName));
         for(int i=0; i<35; i++) {
            names[i]=br.readLine();
            marks[i]=Integer.parseInt(br.readLine());
         }
      }
      catch(IOException e){}
   }
   
   /**
    * Displays the sorted array data
    */
   public void displayData(){
      for(int i = 0; i < names.length; i++){
         if(names[i].length() > 5)
            System.out.println(names[i] + "\t" + marks[i]);
         else
            System.out.println(names[i] + "\t\t" + marks[i]);
      }
   }
   
   /**
    * Sorts the parallel arrays by name
    */
   public void sortByName(){
      for(int i = 1; i < names.length; i++){
         String tempName = names[i];
         int tempMark = marks[i];
         
         int j = i;
         while(j > 0 && names[j-1].compareTo(tempName) > 0){
            names[j] = names[j-1];
            marks[j] = marks[j-1];
            j--;
         }
         
         names[j] = tempName;
         marks[j] = tempMark;
      }
      
      displayData();
   }
   
   /**
    * Sorts the parallel arrays by mark
    */
   public void sortByMark(){
      for(int i = 1; i < marks.length; i++){
         String tempName = names[i];
         int tempMark = marks[i];
         
         int j = i;
         while(j > 0 && marks[j-1] < tempMark){
            names[j] = names[j-1];
            marks[j] = marks[j-1];
            j--;
         }
         
         names[j] = tempName;
         marks[j] = tempMark;
      }
      
      displayData();
   }
   
   /**
    * This main method is a driver method to test the sorting methods
    * @param args The command line arguments
    */
   public static void main(String[] args){
      SortingStudents s = new SortingStudents();
      s.parseFile("A7-1.txt");
      System.out.println("Original Data:");
      s.displayData();
      System.out.println("\nSorted by Name:");
      s.sortByName();
      System.out.println("\nSorted by Mark:");
      s.sortByMark();
   }
}