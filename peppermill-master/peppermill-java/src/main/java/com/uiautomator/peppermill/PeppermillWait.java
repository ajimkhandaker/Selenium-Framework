package com.uiautomator.peppermill;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PeppermillWait {

    /**
     * Common browser based Sync handler
     *
     * @param driver
     */
    public void syncHandler(WebDriver driver, WebElement element) {

        forBrowserReadyState(driver);
        forBrowserJQueryReadyState(driver);
    }

    /**
     * Method to wait till document is in ready state
     *
     * @param driver
     */
    private void forBrowserReadyState(WebDriver driver) {
        for (int i = 1; i < 50; i++) {
            try {
                JavascriptExecutor js;
                String pageLoadStatus;
                do {
                    js = (JavascriptExecutor) driver;
                    pageLoadStatus = (String) js.executeScript("return document.readyState");
                } while (!pageLoadStatus.equals("complete"));
            } catch (Exception e) {
                System.out.println("Browser Eception Occured - " + e.getMessage());
            }
        }
    }

    /**
     * Method to wait till document JQuery is in ready state
     *
     * @param driver
     */
    private void forBrowserJQueryReadyState(WebDriver driver) {
        for (int i = 1; i < 50; i++) {
            try {
                JavascriptExecutor js;
                Boolean pageLoadStatus;
                do {
                    js = (JavascriptExecutor) driver;
                    pageLoadStatus = (Boolean) js.executeScript("return jQuery.active == 0");
                } while (!pageLoadStatus);
            } catch (Exception e) {
                System.out.println("Browser Eception Occured - " + e.getMessage());
            }
        }
    }

    /**
     * Hard Wait to thread sleep
     *
     * @param milliseconds
     */
    public void hardWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
