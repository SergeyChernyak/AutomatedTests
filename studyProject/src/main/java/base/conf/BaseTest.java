package base.conf;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    protected void initDriver () {
        Driver.getWebDriver();
    }

    @AfterSuite
    protected void closeDriver () {
        Driver.closeDriver();
    }
}
