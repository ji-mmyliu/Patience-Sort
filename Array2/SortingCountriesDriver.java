import java.util.*;
import java.util.regex.*;
import java.io.*;

public class SortingCountriesDriver {
    /** An arraylist of the names of the countries */
    public static ArrayList<String> countryName;

    /** An arraylist of the capital cities of the countries */
    public static ArrayList<String> countryCaptial;

    /** An arraylist of the areas of the countries */
    public static ArrayList<Double> area;

    /** An arraylist of the countries' populations */
    public static ArrayList<Integer> population;

    /** An array of all countries with two words */
    public static String doubleWordCountry[] = {"Burkina Faso","Cape Verde","Costa Rica","Czech Republic","Côte d'Ivoire","Dominican Republic","East Timor","El Salvador","Equatorial Guinea","Korea, North","Korea, South","Marshall Islands","Myanmar (Burma)","New Zealand","San Marino","Saudi Arabia","Sierra Leone","Solomon Islands","South Africa","Sri Lanka","St. Lucia","United Kingdom","United States","Vatican City","Western Sahara","Antigua and Barbuda","Bosnia and Herzegovina","Central African Republic","Congo, Republic of","Papua New Guinea","Trinidad and Tobago","United Arab Emirates","St. Kitts and Nevis","São Tomé and Príncipe","Congo, Democratic Republic of the","St. Vincent and the Grenadines"};

    public static void main(String[] args) throws IOException{
        countryName = new ArrayList<String>();
        countryCaptial = new ArrayList<String>();
        area = new ArrayList<Double>();
        population = new ArrayList<Integer>();
        parseFile("Countries-Populations.txt");
    }   
    
    /**
     * This method gets the country's index
     * @param country The name of the country
     * @return The index of the country
     */
    public static int getCountryNameIdx(String country){
        for(String countryName: doubleWordCountry) {
            if(country.startsWith(countryName)) return countryName.length();
        }
        return country.indexOf(' ');
    }

    /**
     * This method parses a text file into array data
     * @param fileName The name of the text file to parse
     * @throws IOException If the buffered reader throws an exception
     */
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
