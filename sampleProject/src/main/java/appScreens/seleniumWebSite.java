package appScreens;

import com.uiautomator.peppermill.Peppermillinitialize;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class seleniumWebSite {
    @FindBy(how = How.LINK_TEXT, using = "Download Selenium")
    public WebElement lnkDownload;
}
