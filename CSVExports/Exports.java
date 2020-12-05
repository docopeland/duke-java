
/**
 * Write a description of Exports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports {
    public void countryInfo(CSVParser parser, String country) {
        System.out.println("Country Info for " + country);
        String info = "NOT FOUND";
        for (CSVRecord record : parser) {
            // look at the country column
            String countryF = record.get(0);
            if (countryF.contains(country)) {
                String exportF = record.get(1);
                String valueF = record.get(2);
                info = countryF + " : " + exportF + ": " + valueF;
            }
        }
        System.out.println(info);
    }
    public void listExportersTwoProducts(CSVParser parser, 
    String exportItem1, String exportItem2) {
        System.out.println("Exporter of " + exportItem1 + " & " + exportItem2);
        String info = "NOT FOUND";
        for (CSVRecord record : parser) {
            String exp = record.get(1);
            if (exp.contains(exportItem1) && exp.contains(exportItem2)) {
                System.out.println(record.get(0));
            }
        }
    }
    public void numberOfExporters(CSVParser parser, String exportItem) {
        System.out.println("Number of exports of " + exportItem);
        int countEx = 0;
        for (CSVRecord record : parser) {
            String exp = record.get(1);
            if (exp.contains(exportItem)) {
                countEx++;
            }
        }
        System.out.println(countEx);
    }
    public void bigExporter(CSVParser parser, String amount) {
        System.out.println("Countries that export more than " + amount);
        int length = amount.length();
        for (CSVRecord record : parser) {
            String value = record.get(2);
            if (value.length() > length) {
                String country = record.get(0);
                System.out.println(country + " : " + value);
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        numberOfExporters(parser, "cocoa");
        parser = fr.getCSVParser();
        bigExporter(parser, "$999,999,999,999");
    }
}
