package com.uiautomator.peppermill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;

/**
 * Handles actions and events.
 */
public class PeppermillActions {
    public Actions Fire(WebDriver driver) {
        return new Actions(driver);
    }

    public void mouseMoveToElementXY(WebElement element) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(element.getLocation().getX(), element.getLocation().getY() + 120);
        } catch (AWTException e) {
            //e.printStackTrace();
        }
    }

}
