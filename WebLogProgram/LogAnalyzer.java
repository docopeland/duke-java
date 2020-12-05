
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String s : fr.lines()) {
             records.add(WebLogParser.parseEntry(s));
         }
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs() {
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (! uniqueIp.contains(ip)) {
                 uniqueIp.add(ip);
             }
         }
         return uniqueIp.size();
     }
     public void printAllHigherThanNum(int num) {
         System.out.println("These are the  log entries that have status codes " +
         " higher than " + num + ":");
         for (LogEntry le : records) {
             int status = le.getStatusCode();
             if (status > num) {
                 System.out.println(le);
             }
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> ipDay = new ArrayList<String>();
         for (LogEntry le : records) {
             String dates = le.getAccessTime().toString();
             String ip = le.getIpAddress();
             if (dates.contains(someday)) {
                 if (! ipDay.contains(ip)) {
                     ipDay.add(ip);
                    }
                }
         }
         return ipDay;
     }
     public int countUniqueIPsInRange(int low, int high) {
         int uniqueRange = 0;
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records) {
             int status = le.getStatusCode();
             if (status >= low && status <= high) {
                 String ip = le.getIpAddress();
                 if (! uniqueIp.contains(ip)) {
                     uniqueIp.add(ip);
                     uniqueRange++;
                 }
             }
         }
         return uniqueRange;
     }
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (! ipCounts.containsKey(ip)) {
                 ipCounts.put(ip,1);
             }
             else{
                 ipCounts.put(ip,ipCounts.get(ip)+1);
             }
         }
         return ipCounts;   
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> hash) {
         int mostVisits = 0;
         for (Integer current : hash.values()) {
             if (current > mostVisits) {
                 mostVisits = current;
             }
         }
         return mostVisits;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> hash) {
         ArrayList<String> ipMost = new ArrayList<String>();
         int mostVisits = mostNumberVisitsByIP(hash);
         for (String s : hash.keySet()) {
             int visits = hash.get(s);
             if (visits == mostVisits) {
                 ipMost.add(s);
             }
         }
         return ipMost;
     }
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> ipDay = new 
         HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             ArrayList<String> ipAdd = new ArrayList<String>();
             String dates = le.getAccessTime().toString();
             dates = dates.substring(4,10);
             String ip = le.getIpAddress();
             if (! ipDay.containsKey(dates)) {
                 ipAdd.clear();
                 ipAdd.add(ip);
                 ipDay.put(dates, ipAdd);
             }
             else {
                 ipAdd.clear();
                 ipAdd = ipDay.get(dates);
                 ipAdd.add(ip);
                 ipDay.put(dates, ipAdd);
             }
         }
         return ipDay;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hash) {
         String day = "";
         int most = 0;
         for (String s : hash.keySet()) {
             int current = hash.get(s).size();
             if (current > most) {
                 most = current;
                 day = s;
             }
         }
         return day;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(
     HashMap<String, ArrayList<String>> hash, String day) {
        ArrayList<String> ipMost = new ArrayList<String>();
        HashMap<String,Integer> strIntHash = new HashMap<String,Integer>();
        for (String s: hash.keySet()) {
            if (s.equals(day)) {
                ArrayList<String> arrStr = hash.get(s);
                for (int k = 0; k < arrStr.size(); k++) {
                    if (! strIntHash.containsKey(arrStr.get(k))) {
                        strIntHash.put(arrStr.get(k),1);
                    }
                    else{
                        strIntHash.put(arrStr.get(k),
                        strIntHash.get(arrStr.get(k))+1);
                    }
                }
            }
        }
        ipMost = iPsMostVisits(strIntHash);
        return ipMost;
     }
}
