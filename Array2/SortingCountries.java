
/**
 * Names: Di Hu, Jimmy Liu, Josh Liu
 * Date: March 11, 2021
 * Teacher: Ms. Krasteva
 * Description: This class sorts the countries by name and by population using insertion sort.
 * 
 * Resources:
 * https://docs.oracle.com/javase/8/docs/api/index.html?java/util/regex/package-summary.html
 */
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class SortingCountries {
   /** An arraylist that stores the country names */
   private ArrayList<String> countryName;

   /** This arraylist stores the capital cities */
   private ArrayList<String> countryCaptial;

   /** This arraylist stores the countries' areas */
   private ArrayList<Double> area;

   /** This arraylist stores the countries' population */
   private ArrayList<Integer> population;

   /** This array stores all the countries' names with two words */
   private String doubleWordCountry[] = { "Burkina Faso", "Cape Verde", "Costa Rica", "Czech Republic", "Côte d'Ivoire",
         "Dominican Republic", "East Timor", "El Salvador", "Equatorial Guinea", "Korea, North", "Korea, South",
         "Marshall Islands", "Myanmar (Burma)", "New Zealand", "San Marino", "Saudi Arabia", "Sierra Leone",
         "Solomon Islands", "South Africa", "Sri Lanka", "St. Lucia", "United Kingdom", "United States", "Vatican City",
         "Western Sahara", "Antigua and Barbuda", "Bosnia and Herzegovina", "Central African Republic",
         "Congo, Republic of", "Papua New Guinea", "Trinidad and Tobago", "United Arab Emirates", "St. Kitts and Nevis",
         "São Tomé and Príncipe", "Congo, Democratic Republic of the", "St. Vincent and the Grenadines" };

   /**
    * This default constructor initializes the array lists to empty lists
    */
   public SortingCountries() {
      countryName = new ArrayList<String>();
      countryCaptial = new ArrayList<String>();
      area = new ArrayList<Double>();
      population = new ArrayList<Integer>();
   }

   /**
    * This method finds the index of the country in the arraylist
    * 
    * @param country The country to find
    * @return The index of the country given
    */
   public int getCountryNameIdx(String country) {
      for (String countryName : doubleWordCountry) {
         if (country.startsWith(countryName))
            return countryName.length();
      }
      return country.indexOf(' ');
   }

   /**
    * This method parses the input text file into the arraylists
    * 
    * @param fileName The name of the text file to parse
    */
   public void parseFile(String fileName) {
      try {
         BufferedReader br = new BufferedReader(new FileReader(fileName));
         Pattern pattern = Pattern.compile("([\\D ]+) ([\\d+,.]+) ([\\d+,]+)");
         // Regex "([\D ]+) ([\d+,.]+) ([\d+,]+)"
         while (true) {
            String line = br.readLine();
            if (line == null)
               break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
               String country = matcher.group(1).trim();
               int countryIdx = getCountryNameIdx(country);
               countryName.add(country.substring(0, countryIdx));
               countryCaptial.add(country.substring(countryIdx));

               String areaStr = matcher.group(2).replace(",", "");
               String popStr = matcher.group(3).replace(",", "");
               area.add(Double.parseDouble(areaStr));
               population.add(Integer.parseInt(popStr));
            } else {
               throw new IOException("Invalid format");
            }
         }
      } catch (IOException e) {
      }
   }

   /**
    * This method writes out all the final data into a file
    * 
    * @param fileName The text file of the data to output to
    */
   private void outputData(String fileName) {
      try {
         PrintWriter pw = new PrintWriter(new FileWriter(fileName));
         for (int i = 0; i < countryName.size(); i++) {
            if (countryName.get(i).length() > 31)
               pw.println(countryName.get(i) + "\t" + population.get(i));
            else if (countryName.get(i).length() > 23)
               pw.println(countryName.get(i) + "\t\t" + population.get(i));
            else if (countryName.get(i).length() > 15)
               pw.println(countryName.get(i) + "\t\t\t" + population.get(i));
            else if (countryName.get(i).length() > 7)
               pw.println(countryName.get(i) + "\t\t\t\t" + population.get(i));
            else
               pw.println(countryName.get(i) + "\t\t\t\t\t" + population.get(i));
         }
         pw.close();
      } catch (IOException e) {
      }
   }

   /**
    * This method sorts the information in the arraylists by country
    */
   public void sortByCountry() {
      for (int i = 1; i < countryName.size(); i++) {
         String tempCountry = countryName.get(i);
         int tempPop = population.get(i);

         int j = i;
         while (j > 0 && countryName.get(j - 1).compareTo(tempCountry) > 0) {
            countryName.set(j, countryName.get(j - 1));
            population.set(j, population.get(j - 1));
            j--;
         }

         countryName.set(j, tempCountry);
         population.set(j, tempPop);
      }

      outputData("sortedByCountry.txt");
   }

   /**
    * This method sorts the information in the arraylists by population
    */
   public void sortByPopulation() {
      for (int i = 1; i < population.size(); i++) {
         String tempCountry = countryName.get(i);
         int tempPop = population.get(i);

         int j = i;
         while (j > 0 && population.get(j - 1) < tempPop) {
            countryName.set(j, countryName.get(j - 1));
            population.set(j, population.get(j - 1));
            j--;
         }

         countryName.set(j, tempCountry);
         population.set(j, tempPop);
      }

      outputData("sortedByPopulation.txt");
   }

   /**
    * This main method sets the flow of the program
    * 
    * @param args The command line arguments
    * @throws IOException If the buffered reader throws an exception
    */
   public static void main(String[] args) throws IOException {
      SortingCountries s = new SortingCountries();
      s.parseFile("Countries-Population.txt");
      s.sortByCountry();
      s.sortByPopulation();
   }
}