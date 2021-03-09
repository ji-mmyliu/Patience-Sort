import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Main {
    public static String countryName[] = new String[195];
    public static String countryCaptial[] = new String[195];
    public static double area[] = new double[195];
    public static int population[] = new int[195];
    public static int countryIdx[] = {11,7,7,7,6,19,9,7,9,7,10,7,7,10,8,7,7,6,5,6,7,22,8,6,6,8,12,7,8,8,6,10,24,4,5,5,8,7,18,33,10,13,7,4,6,14,7,8,8,18,10,7,5,11,17,7,7,8,4,7,6,5,6,7,7,5,6,7,9,6,13,6,5,8,7,7,5,9,4,4,7,6,5,7,5,6,10,5,8,12,12,6,10,4,6,7,7,7,5,13,9,10,9,10,6,8,8,4,5,16,10,9,6,10,7,6,8,10,7,10,15,7,5,5,11,11,9,5,7,6,4,8,5,6,16,8,4,11,6,8,5,7,6,6,19,9,30,5,10,21,12,7,6,10,12,9,8,8,15,7,12,5,9,5,8,9,6,11,5,6,10,8,8,4,5,19,7,6,12,6,6,7,20,14,13,7,10,7,12,9,7,14,5,6,8};

    public static void main(String[] args) throws IOException{
        parseFile("Countries-Populations.txt");
    }   
    
    public static void parseFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        Pattern pattern = Pattern.compile("([\\D ]+) ([\\d+,.]+) ([\\d+,]+)");
        // Regex "([\D ]+) ([\d+,.]+) ([\d+,]+)"
        for(int i=0; i<195; i++) {
            Matcher matcher = pattern.matcher(br.readLine());
            if(matcher.find()){
                String country = matcher.group(1).strip();
                
                countryName[i] = country.substring(0, countryIdx[i]);
                countryCaptial[i] = country.substring(countryIdx[i]);

                String areaStr = matcher.group(2).replace(",", "");
                String popStr = matcher.group(3).replace(",","");
                area[i] = Double.parseDouble(areaStr);
                population[i] = Integer.parseInt(popStr);
            }else{
                throw new IOException("Invalid format");
            }
        }
    }
}
