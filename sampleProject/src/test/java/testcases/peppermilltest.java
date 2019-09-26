package testcases;

import appModules.appModulesinitialize;
import appScreens.seleniumWebSite;
import com.uiautomator.peppermill.Peppermillinitialize;
import org.openqa.selenium.support.PageFactory;

public class peppermilltest {
    public void Test(Peppermillinitialize init,
                     appModulesinitialize appmodulesinit,
                     String[] args){

        //Initialize reports
        init.reporter.generateReportsFile("html","seleniumwebsite");
        init.reporter.setTestName("Click","Click Download Selenium");
        appmodulesinit.login.GoTo("chrome",init.reporter.testEnvironmentUrl,"","");

        //Initiate application page objects
        seleniumWebSite site = PageFactory.initElements(init.driver, seleniumWebSite.class);
        init.reporter.attachScreenShotToTest(init.driver);
        init.fields.clickIt(site.lnkDownload,"lnkDownload");
        init.helper.verifyPageSourceContains("Selenium Standalone Server");
        init.reporter.attachScreenShotToTest(init.driver);
        init.driver.quit();
    }
}
