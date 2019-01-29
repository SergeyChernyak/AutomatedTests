package base.conf;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseTest extends Driver{
    protected BasePage basePage;

    @BeforeClass
    public void setup () {
        super.getDriver("chrome");
        basePage = new BasePage(webDriver);
    }

    @AfterClass
    protected void closeDriver () {
        if (webDriver != null)
            webDriver.quit();
    }
}
