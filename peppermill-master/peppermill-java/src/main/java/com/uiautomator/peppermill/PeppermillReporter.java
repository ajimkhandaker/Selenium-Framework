package com.uiautomator.peppermill;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Handles all test reporting needs.
 */
public class PeppermillReporter {

    public String testEnvironmentID = "testEnvironmentID";
    public String testEnvironmentUrl = "testEnvironmentUrl";
    public String testingType = "testingType";
    public String currentTestName = "NoTestNameYet";
    public boolean currentTestStatus = true;
    public String currentResultFileName = "NotYetCreatedOne";
    public String autName = "NotYetAssignedOne";

    public WebDriver driver;

    private ExtentTest test;
    private ExtentReports extent;
    private boolean isreportFileCreated = false;

    /**
     * Log message into console
     *
     * @param Status
     * @param Step
     * @param StepDetails
     */
    public void clog(String Status, String Step, String StepDetails) {
        System.out.println(Status + " | " + Step + " | " + StepDetails);
    }

    /**
     * method to configure extent report
     *
     * @param reportsPath
     */
    private void setUp(String reportsPath) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportsPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    /**
     * Method to set test case
     *
     * @param Name
     * @param Description
     * @return
     */
    public ExtentTest setTestName(String Name, String Description) {
        currentTestName = Name;
        currentTestStatus = true;
        if (PeppermillGlobals.TestReportType.equals("html")) {
            //TODO : catch error when extent object is not created
            test = extent.createTest(Name, Description);
            clog("********** EXECUTING", "Test", Name + " **********");
        }
        return test;
    }

    /**
     * method to create test results file
     *
     * @param reportType
     * @param testfileName
     */
    public void generateReportsFile(String reportType, String testfileName) {
        if (PeppermillGlobals.reportsPath.trim() == null) {
            PeppermillGlobals.TestReportType = "console";
            return;
        }
        PeppermillGlobals.TestReportType = reportType.trim();
        if (PeppermillGlobals.TestReportType.equals("html")) {
            String fileName = PeppermillGlobals.getUniqueInteger();
            currentResultFileName = "";
            currentResultFileName = testEnvironmentID + "_" + testingType + "_" + testfileName + "_" + fileName + ".html";
            setUp(PeppermillGlobals.reportsPath + currentResultFileName);
            isreportFileCreated = true;
        } else {
            PeppermillGlobals.TestReportType = "console";
            isreportFileCreated = false;
        }
    }

    /**
     * Method to report any event to results file
     *
     * @param testStatus
     * @param Step
     * @param StepDetails
     */
    public void event(String testStatus, String Step, String StepDetails) {
        if (PeppermillGlobals.TestReportType.equals("html") && isreportFileCreated) {
            switch (testStatus.toLowerCase()) {
                case "pass":
                    test.log(Status.PASS, Step + " | " + StepDetails);
                    break;
                case "fail":
                    test.log(Status.FAIL, Step + " | " + StepDetails);
                    clog("FAIL", Step, StepDetails);
                    currentTestStatus = false;
                    attachScreenShotToTest(driver);
                    break;
                case "info":
                    test.log(Status.INFO, Step + " | " + StepDetails);
                    clog("INFO", Step, StepDetails);
                    break;
                case "warning":
                    test.log(Status.WARNING, Step + " | " + StepDetails);
                    clog("WARNING", Step, StepDetails);
                    break;
                case "error":
                    test.log(Status.ERROR, Step + " | " + StepDetails);
                    clog("ERROR", Step, StepDetails);
                    //Report.CaptureAppScreen();
                    break;
                case "skip":
                    test.log(Status.SKIP, Step + " | " + StepDetails);
                    clog("SKIP", Step, StepDetails);
                    break;
                default:
                    break;
            }
            extent.flush();
        } else {
            clog(testStatus, Step, StepDetails);
        }
    }

    /**
     * Method to attach screenshot to test in results file
     *
     * @param driver
     */
    public void attachScreenShotToTest(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String getrandomUUID = PeppermillGlobals.getrandomUUID();
        String screenShotPath = "ScreenShots\\" + getrandomUUID + ".png";
        String SetFileName = PeppermillGlobals.reportsPath + screenShotPath;
        try {
            FileUtils.copyFile(scrFile, new File(SetFileName));
            test.addScreenCaptureFromPath(screenShotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extent.flush();
    }
}
