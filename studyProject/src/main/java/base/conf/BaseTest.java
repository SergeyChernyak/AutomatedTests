package base.conf;

import base.util.Wait;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver;
    protected Wait wait;

    @BeforeSuite
    protected void initDriver () {
        driver = Driver.getWebDriver();
        wait = new Wait(driver);
    }

    @AfterSuite
    protected void closeDriver () {
        Driver.closeDriver();
    }
}
