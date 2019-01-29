package base.conf;

import base.page.BbcPage;
import org.openqa.selenium.WebDriver;

public class BasePage extends BaseTest{

    public BbcPage bbcPage;

    public BasePage(WebDriver webDriver) {
        bbcPage = new BbcPage(webDriver);
    }
}
