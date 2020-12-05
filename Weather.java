
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {
    public CSVRecord lowTwo(CSVRecord current, CSVRecord lowest, String col) {
        if (lowest == null) {
            lowest = current;
        }
        else {
            double doCurrent = Double.parseDouble(current.get(col));
            double doLowest = Double.parseDouble(lowest.get(col));
            if (doCurrent < doLowest && doCurrent != -9999) {
                lowest = current;
                doLowest = doCurrent;
            }
        }
        return lowest;
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord current : parser) {
            lowest = lowTwo(current,lowest,"TemperatureF");
        }
        return lowest;
    }
    public String fileWithColdestTemperature() {
        CSVRecord coldDay = null;
        String coldestDay = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currDay = coldestHourInFile(parser);
            if (coldDay == null) {
                coldDay = currDay;
            }
            else {
                double currCold = Double.parseDouble(currDay.get("TemperatureF"));
                double coldest = Double.parseDouble(coldDay.get("TemperatureF"));
                if (currCold < coldest && currCold != -9999) {
                    coldDay = currDay;
                    coldest = currCold;
                    coldestDay = f.getName();
                }
            }
        }
        return coldestDay;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord current : parser) {
            if (current.get("Humidity").contains("N/A")) {
                lowest = lowest;
            }
            else {
                lowest = lowTwo(current, lowest, "Humidity");
            }
        }
        return lowest;
    }
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = lowestHumidityInFile(parser);
            lowest = lowTwo(current, lowest, "Humidity");
        }
        return lowest;
    }
    public Double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (temp != -9999) {
                totalTemp = totalTemp + temp;
                count++;
            }
        }
        double avgTemp = totalTemp/count;
        return avgTemp;
    }
    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser,
       int value) {
        double totalTemp = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("TemperatureF").contains("N/A") == false) {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                double hum = Double.parseDouble(record.get("Humidity"));
                if (temp != -9999) {
                    if (hum >= value) {
                        totalTemp = totalTemp + temp;
                        count++;
                    }
                }
            }
        }
        double avgTemp = totalTemp/count;
        return avgTemp;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String coldTemp = coldestHourInFile(parser).get("TemperatureF");
        parser = fr.getCSVParser();
        String coldHour = coldestHourInFile(parser).get("DateUTC");
        System.out.println("The coldest temperature was " + coldTemp + " at "
         + coldHour);
    }
    public void testFileWithColdestTemperature() {
        String coldest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldest);
        FileResource fr = new FileResource("nc_weather/2014/" + coldest);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest temp on that day was " + 
           record.get("TemperatureF"));
        System.out.println("All the temperatures on that day were :");
        for (CSVRecord temps : fr.getCSVParser()) {
            System.out.println(temps.get("DateUTC") + " " + 
               temps.get("TemperatureF"));
        }
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String lowHumid = lowestHumidityInFile(parser).get("Humidity");
        parser = fr.getCSVParser();
        String time = lowestHumidityInFile(parser).get("DateUTC");
        System.out.println("Lowest humidity was " + lowHumid + " at " + time);
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + record.get("Humidity") + 
           " on " + record.get("DateUTC"));
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in this file is " + avgTemp);
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avgTemp != Double.NaN) {
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
        else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
