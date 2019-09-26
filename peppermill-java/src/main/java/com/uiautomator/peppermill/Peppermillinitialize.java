package com.uiautomator.peppermill;

import org.openqa.selenium.WebDriver;

/**
 * It initializes PepperMill framework
 */
public class Peppermillinitialize {

    public WebDriver driver;
    public PeppermillWait wait;
    public PeppermillGlobals globals;
    public PeppermillActions actions;
    public PeppermillFields fields;
    public PeppermillReporter reporter;
    public PeppermillHelper helper;
    public PeppermillLogger log;
    public PeppermillExcel excel;
    public PeppermillSwitchTo switchTo;

    public Peppermillinitialize() {
        wait = new PeppermillWait();
        globals = new PeppermillGlobals();
        actions = new PeppermillActions();
        fields = new PeppermillFields();
        reporter = new PeppermillReporter();
        helper = new PeppermillHelper();
        log = new PeppermillLogger();
        excel = new PeppermillExcel();
        switchTo = new PeppermillSwitchTo();

        //Share created instances to PepperMill classes.
        fields.actions = actions;
        fields.reporter = reporter;
        fields.Wait = wait;
        excel.reporter = reporter;
        helper.reporter = reporter;
    }

    /**
     * Set's the global WebDriver instance
     *
     * @param driver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
        fields.driver = driver;
        switchTo.driver = driver;
        helper.driver = driver;
        reporter.driver = driver;
    }
}
