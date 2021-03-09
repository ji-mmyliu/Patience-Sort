import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Main {
    public static ArrayList<String> countryName;
    public static ArrayList<String> countryCaptial;
    public static ArrayList<Double> area;
    public static ArrayList<Integer> population;
    public static String doubleWordCountry[] = {"Burkina Faso","Cape Verde","Costa Rica","Czech Republic","Côte d'Ivoire","Dominican Republic","East Timor","El Salvador","Equatorial Guinea","Korea, North","Korea, South","Marshall Islands","Myanmar (Burma)","New Zealand","San Marino","Saudi Arabia","Sierra Leone","Solomon Islands","South Africa","Sri Lanka","St. Lucia","United Kingdom","United States","Vatican City","Western Sahara","Antigua and Barbuda","Bosnia and Herzegovina","Central African Republic","Congo, Republic of","Papua New Guinea","Trinidad and Tobago","United Arab Emirates","St. Kitts and Nevis","São Tomé and Príncipe","Congo, Democratic Republic of the","St. Vincent and the Grenadines"};

    public static void main(String[] args) throws IOException{
        countryName = new ArrayList<String>();
        countryCaptial = new ArrayList<String>();
        area = new ArrayList<Double>();
        population = new ArrayList<Integer>();
        parseFile("Countries-Populations.txt");
    }   
    
    public static int getCountryNameIdx(String country){
        for(String countryName: doubleWordCountry) {
            if(country.startsWith(countryName)) return countryName.length();
        }
        return country.indexOf(' ');
    }

    public static void parseFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        Pattern pattern = Pattern.compile("([\\D ]+) ([\\d+,.]+) ([\\d+,]+)");
        // Regex "([\D ]+) ([\d+,.]+) ([\d+,]+)"
        while(true){
            String line = br.readLine();
            if(line == null) break;
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){
                String country = matcher.group(1).trim();
                int countryIdx = getCountryNameIdx(country);
                countryName.add(country.substring(0, countryIdx));
                countryCaptial.add(country.substring(countryIdx));

                String areaStr = matcher.group(2).replace(",", "");
                String popStr = matcher.group(3).replace(",","");
                area.add(Double.parseDouble(areaStr));
                population.add(Integer.parseInt(popStr));
            }else{
                throw new IOException("Invalid format");
            }
        }
    }
}
