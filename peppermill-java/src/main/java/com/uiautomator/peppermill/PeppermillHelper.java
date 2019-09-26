package com.uiautomator.peppermill;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Holds any helper functions on WebDriver or WebElement
 */
public class PeppermillHelper {

    public WebDriver driver;
    public PeppermillReporter reporter;

    /**
     * Returns page source
     *
     * @return
     */
    public String getPageSource() {
        return driver.getPageSource();
    }

    /**
     * Check's a value in page source
     *
     * @param Value
     * @return
     */
    public boolean pageSourceContains(String Value) {
        return driver.getPageSource().contains(Value);
    }

    public boolean verifyPageSourceContains(String Value) {
        if (pageSourceContains(Value)) {
            reporter.event("PASS", "Verify - " + Value + "' is displayed", Value + "' is displayed");
            return true;
        }
        reporter.event("FAIL", "Verify - " + Value + "' is displayed", Value + "' is NOT displayed");
        return false;
    }

    public boolean verifyPageLinkEquals(String linkText) {

        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        int i = 0;
        for (WebElement link : allLinks) {
            if (link.getText().trim().equals(linkText)) {
                reporter.event("PASS", "Verify link- " + linkText + "' is displayed", linkText + "' link is displayed");
                return true;
            }
            i = i + 1;
        }
        reporter.event("FAIL", "Verify link- " + linkText + "' is displayed", linkText + "' link is NOT displayed");
        return false;
    }

    /**
     * Check for a string with in a string
     *
     * @param conditionFor
     * @param source
     * @param target
     * @return
     */
    public boolean verifyIfContains(String conditionFor, String source, String target) {
        if (target.trim().toLowerCase().isEmpty()) {
            target = "NoValueInTarget";
        }
        if (source.trim().toLowerCase().contains(target.trim().toLowerCase())) {
            reporter.event("PASS", "verifyIfContains - " + target + "[" + conditionFor + "]", "Vaue '" + target + "' is available in '" + source + "'");
            return true;
        } else {
            reporter.event("FAIL", "verifyIfContains - " + target + "[" + conditionFor + "]", "Vaue '" + target + "' is NOT available in '" + source + "'");
        }
        return false;
    }

    /**
     * Check for a string equals to a string
     *
     * @param conditionFor
     * @param source
     * @param target
     * @return
     */
    public boolean verifyIfEquals(String conditionFor, String source, String target) {
        if (target.trim().toLowerCase().isEmpty()) {
            target = "NoValueInTarget";
        }
        if (source.trim().toLowerCase().equals(target.trim().toLowerCase())) {
            reporter.event("PASS", "verifyIfEquals - " + target + "[" + conditionFor + "]", "Vaue '" + target + "' is available in '" + source + "'");
            return true;
        } else {
            reporter.event("FAIL", "verifyIfEquals - " + target + "[" + conditionFor + "]", "Vaue '" + target + "' is NOT available in '" + source + "'");
        }
        return false;
    }

}
