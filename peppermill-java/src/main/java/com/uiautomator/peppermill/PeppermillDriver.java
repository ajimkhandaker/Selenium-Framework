package com.uiautomator.peppermill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

/**
 * Peppermill driver class to create and manage test browsers.
 */
public class PeppermillDriver {

    /**
     * Defined Peppermill report object
     */
    public PeppermillReporter reporter;
    /**
     * Definition for Peppermill initializer object
     */
    Peppermillinitialize pepperInit;
    /**
     * Defined WebDriver object
     */
    private WebDriver driver;

    public PeppermillDriver(Peppermillinitialize init) {
        this.reporter = init.reporter;
        this.pepperInit = init;
    }

    /**
     * @return current WebDriver
     */
    public WebDriver get() {
        return this.driver;
    }

    /**
     * Method to set chrome as test browser with default driver manage and generic options
     *
     * @param Url
     * @return current WebDriver
     */
    public WebDriver setChrome(String Url) {
        reporter.testEnvironmentUrl = Url;
        System.setProperty("webdriver.chrome.driver", PeppermillGlobals.chromedriverpath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PeppermillGlobals.pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(PeppermillGlobals.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Url);
        reporter.event("INFO", "Open Chrome browser", "URL - " + Url + " is opened");
        this.pepperInit.setDriver(driver);
        return driver;
    }

    public WebDriver setChrome(String Url, ChromeOptions options) {
        reporter.testEnvironmentUrl = Url;
        System.setProperty("webdriver.chrome.driver", PeppermillGlobals.chromedriverpath);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PeppermillGlobals.pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(PeppermillGlobals.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Url);
        reporter.event("INFO", "Open Chrome browser", "URL - " + Url + " is opened");
        this.pepperInit.setDriver(driver);
        return driver;
    }

    public WebDriver setFireFox(String Url) {
        reporter.testEnvironmentUrl = Url;
        System.setProperty("webdriver.gecko.driver", PeppermillGlobals.firefoxdriverpath);
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("no-sandbox");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PeppermillGlobals.pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(PeppermillGlobals.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Url);
        reporter.event("INFO", "Open FireFox browser", "URL - " + Url + " is opened");
        this.pepperInit.setDriver(driver);
        return driver;
    }

    public WebDriver setFireFox(String Url, FirefoxOptions options) {
        reporter.testEnvironmentUrl = Url;
        System.setProperty("webdriver.gecko.driver", PeppermillGlobals.firefoxdriverpath);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PeppermillGlobals.pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(PeppermillGlobals.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Url);
        reporter.event("INFO", "Open FireFox browser", "URL - " + Url + " is opened");
        this.pepperInit.setDriver(driver);
        return driver;
    }

    public WebDriver setIE(String Url) {
        reporter.testEnvironmentUrl = Url;
        System.setProperty("webdriver.ie.driver", PeppermillGlobals.iedriverpath);
        InternetExplorerOptions options = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PeppermillGlobals.pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(PeppermillGlobals.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Url);
        reporter.event("INFO", "Open IE browser", "URL - " + Url + " is opened");
        this.pepperInit.setDriver(driver);
        return driver;
    }

    public WebDriver setIE(String Url, InternetExplorerOptions options) {
        reporter.testEnvironmentUrl = Url;
        System.setProperty("webdriver.ie.driver", PeppermillGlobals.iedriverpath);
        driver = new InternetExplorerDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PeppermillGlobals.pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(PeppermillGlobals.implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Url);
        reporter.event("INFO", "Open IE browser", "URL - " + Url + " is opened");
        this.pepperInit.setDriver(driver);
        return driver;
    }
}

