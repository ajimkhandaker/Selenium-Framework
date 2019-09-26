package appModules;

import com.uiautomator.peppermill.PeppermillGlobals;

public class Globals {

    public static void Set(){
        //Set global data for test session
        String driverPath = "C:\\drivers\\";
        PeppermillGlobals.reportsPath = "C:\\PePperMill_Reports\\";
        PeppermillGlobals.chromedriverpath = driverPath + "chromedriver.exe";
        PeppermillGlobals.firefoxdriverpath = driverPath + "geckodriver.exe";
        PeppermillGlobals.iedriverpath = driverPath + "IEDriverServer.exe";
        PeppermillGlobals.testdatapath = "path\\";
    }

}
