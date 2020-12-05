
/**
 * Write a description of BabyNames here.
 * 
 * @author Danielle
 * @version 18 July 2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths(FileResource fr) {
        int totalBirth = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int uniqueGirls = 0;
        int uniqueBoys = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirth += numBorn;
            if (record.get(1).equals("F")) {
                totalGirls += numBorn;
                uniqueGirls++;
            }
            else {
                totalBoys += numBorn;
                uniqueBoys++;
            }
        }
        System.out.println("Total number of births: " + totalBirth);
        System.out.println("Total number of girls: " + totalGirls);
        System.out.println("Number of unique girl names: " + uniqueGirls);
        System.out.println("Total number of boys: " + totalBoys);
        System.out.println("Number of unique boys names: " + uniqueBoys);
    }
    public int getRank(int year, String name, String gender) {
        int count = 0;
        int rank = -1;
        FileResource fr = new FileResource
           ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                count++;
                if (record.get(0).equals(name)) {
                    rank = count;
                }
            }
        }
        return rank;
    }
    public String getName(int year, int rank, String gender) {
        int girls = 0;
        String name = "NO NAME";
        FileResource fr = new FileResource
            ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals("F")) {
                girls++;
                if (gender.equals("F") && 
                   parser.getCurrentLineNumber() == rank) {
                       name = record.get(0);
                    }
            }
            else {
                if (gender.equals("M") && 
                   ((parser.getCurrentLineNumber() - girls) == rank)) {
                       name = record.get(0);
                    }
            }
        }
        return name;
    }
    public void whatIsNameInYear(String name, int year, int newYear, 
       String gender) {
         int rank = getRank(year, name, gender);
         String newName = getName(newYear, rank, gender);
         System.out.print(newName);
    }
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int highestYear = -1;
        for (File f : dr.selectedFiles()) {
            String fname = f.getName();
            int currentYear = Integer.parseInt(fname.substring(3,7));
            int currentRank = getRank(currentYear, name, gender);
            if (highestRank == 0) {
                if (currentRank != -1 ) {
                    highestRank = currentRank;
                    highestYear = currentYear;
                }
            }
            else {
                if (currentRank != -1 && currentRank < highestRank) {
                    highestRank = currentRank;
                    highestYear = currentYear;
                }
            }
        }
        return highestYear;
    }
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;
        for (File f : dr.selectedFiles()) {
            String fname = f.getName();
            int year = Integer.parseInt(fname.substring(3,7));
            int currentRank = getRank(year, name, gender);
            if (currentRank != -1) {
                totalRank += currentRank;
                count++;
            }
        }
        double avgRank = (double)totalRank/count;
        if (Double.isNaN(avgRank)) {
            avgRank = -1;
        }
        return avgRank;
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int birthsTotal = 0; 
        int birthsHigher = 0;
        FileResource fr = new FileResource
            ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    birthsHigher = birthsTotal;
                }
                else {
                    birthsTotal = birthsTotal + Integer.parseInt(record.get(2));
                }
            }
        }
        return birthsHigher;
    }
    public void testTotalBirth() {
        System.out.println(1900);
        FileResource fr1 = new 
           FileResource("us_babynames/us_babynames_by_year/yob1900.csv");
        totalBirths(fr1);
        System.out.println(1905);
        FileResource fr2 = new 
           FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr2);
    }
    public void testGetRank() {
        int year1 = 1960;
        String name1 = "Emily";
        String gender1 = "F";
        int rank1 = getRank (year1, name1, gender1);
        if (rank1 != -1) {
           System.out.println("The rank for " + name1 + " is " + rank1); 
        }
        else {
            System.out.println("There is no mention of a " + gender1 + " named "
               + name1 + " in " + year1);
        }
        int year2 = 1971;
        String name2 = "Frank";
        String gender2 = "M";
        int rank2 = getRank (year2, name2, gender2);
        if (rank2 != -1) {
           System.out.println("The rank for " + name2 + " is " + rank2); 
        }
        else {
            System.out.println("There is no mention of a " + gender2 + " named "
               + name2 + " in " + year2);
        }
    }
    public void testGetName() {
        int year1 = 1980;
        int rank1 = 350;
        String gender1 = "F";
        String name1 = getName(year1, rank1, gender1);
        System.out.println("The name at " + rank1 + " for " + gender1 + " is " 
           + name1);
        int year2 = 1982;
        int rank2 = 450;
        String gender2 = "M";
        String name2 = getName(year2, rank2, gender2);
        System.out.println("The name at " + rank2 + " for " + gender2 + " is " 
           + name2);
    } 
    public void testWhatIsNameInYear() {
        String name1 = "Susan";
        int year1 = 1972;
        int newYear1 = 2014;
        String gender1 = "F";
        System.out.print(name1 + " born in " + year1 + " would be ");
        whatIsNameInYear(name1, year1, newYear1, gender1);
        System.out.println(" if she was born in " + newYear1);
        String name2 = "Owen";
        int year2 = 1974;
        int newYear2 = 2014;
        String gender2 = "M";
        System.out.print(name2 + " born in " + year2 + " would be ");
        whatIsNameInYear(name2, year2, newYear2, gender2);
        System.out.println(" if she was born in " + newYear2);
    }
    public void testYearOfHighestRank() {
        String name1 = "Genevieve";
        String gender1 = "F";
        int year1 = yearOfHighestRank(name1, gender1);
        if (year1 == -1) {
            System.out.println("There is no mention of a " + gender1 + 
               " named " + name1);
        }
        else {
            System.out.println(name1 + " was ranked highest in " + year1);
        }
        String name2 = "Mich";
        String gender2 = "M";
        int year2 = yearOfHighestRank(name2, gender2);
        if (year2 == -1) {
            System.out.println("There is no mention of a " + gender2 + 
               " named " + name2);
        }
        else {
            System.out.println(name2 + " was ranked highest in " + year2);
        }
    }
    public void testGetAverageRank() {
        String name1 = "Susan";
        String gender1 = "F";
        double avgRank1 = getAverageRank(name1, gender1);
        if (avgRank1 == -1) {
            System.out.println("There is no mention of a " + gender1 + 
               " named " + name1);
        }
        else {
            System.out.println(name1 + " has an average rank of " + avgRank1);
        }
        String name2 = "Robert";
        String gender2 = "M";
        double avgRank2 = getAverageRank(name2, gender2);
        if (avgRank2 == -1) {
            System.out.println("There is no mention of a " + gender2 + 
               " named " + name2);
        }
        else {
            System.out.println(name2 + " has an average rank of " + avgRank2);
        }
    }
    public void testGetTotalBirthsRankedHigher() {
        int year1 = 1990;
        String name1 = "Emily";
        String gender1 = "F";
        int births1 = getTotalBirthsRankedHigher(year1, name1, gender1);
        if (births1 == 0) {
            System.out.println("There are no births ranked higher");
        }
        else {
            System.out.println("There are " + births1 
               + " births of names ranked higher than " + name1);
        }
        int year2 = 1990;
        String name2 = "Drew";
        String gender2 = "M";
        int births2 = getTotalBirthsRankedHigher(year2, name2, gender2);
        if (births2 == 0) {
            System.out.println("There are no births ranked higher");
        }
        else {
            System.out.println("There are " + births2 
               + " births of names ranked higher than " + name2);
        }
    }
}
