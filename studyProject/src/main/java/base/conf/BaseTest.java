package base.conf;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver = Driver.getWebDriver();

    @BeforeSuite
    protected void initDriver () {
        Driver.getWebDriver();
    }

    @AfterSuite
    protected void closeDriver () {
        Driver.closeDriver();
    }
}
