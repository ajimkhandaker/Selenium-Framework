package appModules;

import com.uiautomator.peppermill.PeppermillDriver;
import com.uiautomator.peppermill.Peppermillinitialize;

public class Login {

    public Peppermillinitialize init;

    public Login(Peppermillinitialize init){
        this.init = init;
    }

    public void GoTo( String browserType, String Url, String ID, String Password){
        //Please make sure WebDriver path is set for PeppermillGlobals members.
        switch (browserType.trim().toLowerCase()) {
            case "chrome"  :
                /*if chrome options to be used please uncomment this code block
                ChromeOptions options = new ChromeOptions();
                new PeppermillDriver(init).setChrome(Url, options);*/
                new PeppermillDriver(init).setChrome(Url);
                break;
            case "firefox"  :
                /*if firefox options to be used please uncomment this code block
                FirefoxOptions options = new FirefoxOptions();
                new PeppermillDriver(init).setFireFox(Url, options);*/
                new PeppermillDriver(init).setFireFox(Url);
                break;
            case "ie"  :
                /*if IE options to be used please uncomment this code block
                InternetExplorerOptions options = new InternetExplorerOptions();
                new PeppermillDriver(init).setIE(Url,options);*/
                new PeppermillDriver(init).setIE(Url);
                break;
            default :
                break;
        }
        /*write code to submit username and password
        and login to AUT(application under test*/
    }

    public void setTesEnvironmet(String Key)
    {
        init.reporter.testEnvironmentID = Key;
        switch (Key.trim().toLowerCase())
        {
            case ("sweb"):
                init.reporter.testEnvironmentUrl = "https://www.seleniumhq.org/";
                break;
            default:
                break;
        }
    }

}
