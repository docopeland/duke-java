
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public void testLogAnalyzer() {
        LogAnalyzer logA = new LogAnalyzer();
        logA.readFile("short-test_log");
        logA.printAll();
    }
    public void testUniqueIP() {
        LogAnalyzer logA = new LogAnalyzer();
        logA.readFile("weblog2_log");
        //weblog3-short_log
        int uniqueIps = logA.countUniqueIPs();
        System.out.println("There are " + uniqueIps + " unique IP addresses.");
        HashMap<String, Integer> hash = logA.countVisitsPerIP();
        System.out.println("HashMap of counts per IP: \n" + hash);
        int mostVisits = logA.mostNumberVisitsByIP(hash);
        System.out.println("The most number of visits by one IP is " + mostVisits);
        ArrayList<String> ipMost = logA.iPsMostVisits(hash);
        System.out.println("The IPs with the most number of visits are " + ipMost);
        HashMap<String, ArrayList<String>> ipDays = logA.iPsForDays();
        System.out.println("HashMap of IPs per day: \n" + ipDays);
        String ipDay = logA.dayWithMostIPVisits(ipDays);
        System.out.println("The day with the most IP visits is : " + ipDay);
        String day = "Sep 29";
        ArrayList<String> IpMostVisits = logA.iPsWithMostVisitsOnDay(ipDays, day);
        System.out.println("IPs with the most visits on " + day + ":" + "\n" + IpMostVisits);
    }
    public void testHigher() {
        LogAnalyzer logA = new LogAnalyzer();
        logA.readFile("weblog2_log");
        logA.printAllHigherThanNum(400);
    }
    public void testDates() {
        LogAnalyzer logA = new LogAnalyzer();
        logA.readFile("weblog2_log");
        String date = "Sep 27";
        ArrayList<String> uniqueDay = logA.uniqueIPVisitsOnDay(date);
        System.out.println("For " + date + ", there " + uniqueDay.size() +
        " unique IP visits.");
    }
    public void testRange() {
        LogAnalyzer logA = new LogAnalyzer();
        logA.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        //int low = 300;
        //int high = 399;
        int uniqueRange = logA.countUniqueIPsInRange(low, high);
        System.out.println("There are " + uniqueRange + " unique IP addresses that "
         + "have a status code from " + low + " and " + high);
    }
}
