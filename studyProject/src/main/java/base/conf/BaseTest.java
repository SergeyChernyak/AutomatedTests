package base.conf;

import org.testng.annotations.AfterSuite;

public class BaseTest {

    @AfterSuite
    protected void closeDriver () {
        Driver.closeDriver();
    }
}
