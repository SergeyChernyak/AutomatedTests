package base.conf;

import base.page.BbcPage;
import base.util.Wait;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver;
    private Wait wait;
    protected BbcPage bbcPage;

    @BeforeSuite
    protected void initDriver () {
        driver = Driver.getWebDriver();
        wait = new Wait(driver);
        bbcPage = new BbcPage(driver, wait);
    }

    @AfterSuite
    protected void closeDriver () {
        Driver.closeDriver();
    }
}
