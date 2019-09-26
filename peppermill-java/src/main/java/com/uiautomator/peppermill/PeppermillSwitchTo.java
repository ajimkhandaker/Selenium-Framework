package com.uiautomator.peppermill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

/**
 * All custom driver class actions from peppermill
 */
public class PeppermillSwitchTo {

    public WebDriver driver;

    /**
     * Switch to top window
     */
    public void topWindow() {

        Set<String> allWindows = driver.getWindowHandles();
        for (String curWindow : allWindows) {
            driver.switchTo().window(curWindow);
        }
    }

    /**
     * Switch to parent window
     */
    public void toParentWindow() {
        driver.switchTo().parentFrame().getWindowHandle();
    }

    /**
     * switch to specific window
     *
     * @param index
     */
    public void toWindow(int index) {
        Set<String> windows = driver.getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                driver.switchTo().window(window);
            } else {
                i++;
            }
        }
    }

    /**
     * Method to switchToFrame based on frame index
     *
     * @param frameIndex
     */
    public void toFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }

    /**
     * Method to switchToFrame based on frame name
     *
     * @param frameName
     */
    public void toFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    /**
     * Method to switchToFrame based on frame WebElement
     *
     * @param element
     */
    public void toFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * Accepts alert Window if displayed
     *
     * @return true if displayed else false
     */
    public boolean alertAccept() {
        try {
            driver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            //exception handling
            return false;
        }
    }

    /**
     * Dismiss alert Window if displayed
     *
     * @return true if displayed else false
     */
    public boolean alertDismiss() {
        try {
            driver.switchTo().alert().dismiss();
            return true;
        } catch (Exception e) {
            //exception handling
            return false;
        }
    }

}
