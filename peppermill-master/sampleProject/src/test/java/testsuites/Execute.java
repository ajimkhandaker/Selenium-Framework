package testsuites;

import appModules.Globals;
import appModules.appModulesinitialize;
import com.uiautomator.peppermill.PeppermillGlobals;
import com.uiautomator.peppermill.Peppermillinitialize;
import testcases.peppermilltest;

import java.util.Arrays;

public class Execute {
    public static void main(String[] args) {
        // Set Test Globals
        Globals.Set();

        //Initialize framework
        Peppermillinitialize init = new Peppermillinitialize();

        //Initialize app Globals
        appModulesinitialize appmodulesinit =  new appModulesinitialize(init);

        //Set Execution arguments to application
        appmodulesinit.login.setTesEnvironmet(args[0].trim().toLowerCase());

        //Set testing type details
        init.reporter.testingType = args[2];

        try {
            switch (args[1].trim().toLowerCase()) {
                case "first-test":
                    peppermilltest qas = new peppermilltest();
                    qas.Test(init,
                            appmodulesinit,
                            args);
            }
            if (!init.reporter.currentTestStatus)
            {
                System.exit(-1);
            }
        } catch (Exception e) {
            if(PeppermillGlobals.TestReportType.equals("console")){
                e.printStackTrace();
                init.driver.quit();
                System.exit(-1);
            }else {
                init.reporter.event("FAIL","ERROR REPORT", Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
                init.driver.quit();
                System.exit(-1);
            }
        }
    }
}
