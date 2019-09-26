package com.uiautomator.peppermill;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class PeppermillGlobals {

    public static String reportsPath;
    public static String chromedriverpath;
    public static String firefoxdriverpath;
    public static String iedriverpath;
    public static String testdatapath;
    public static int implicitlyWait = 90;
    public static int pageLoadTimeout = 90;
    public static String TestReportType = "console";
    public static String tblCurrentNoRecordsText;
    public static String[] tblNoRecordsText = {"No Results Found",
            "There are currently no records to display",
            "No records",
            "No data available in table",
            "No data",
            "No Results",
            "No Results to Display"};

    /**
     * @return UUID
     */
    public static String getrandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Prints anything to console
     *
     * @param text
     */
    public static void print2Console(String text) {
        System.out.println(text);
    }

    /**
     * @return Unique date string with time
     */
    public static String getUniqueInteger() {
        Date now = new Date();
        return new SimpleDateFormat("MMddHHmmss_YYYY", Locale.ENGLISH).format(now);
    }

    /**
     * @return Host name
     */
    public static String getHostName() {
        String hostname = "Unknown";

        try {
            hostname = InetAddress.getLocalHost().getHostName();
            return hostname;
        } catch (Exception ex) {
            System.out.println("Hostname can not be resolved");
        }
        return hostname;
    }
}