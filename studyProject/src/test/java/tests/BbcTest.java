package tests;

import base.conf.BaseTest;
import base.conf.Driver;
import base.page.BbcPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BbcTest extends BaseTest {

    private BbcPage bbcPage = new BbcPage();
    private WebDriver driver = Driver.getWebDriver();

    @BeforeTest
    public void getUrl () {
        driver.get("https://www.bbc.com/");
    }

    @Test (priority = 0)
    public void checkSearchInput() {
        bbcPage
                .textEnter("TEST");
        Assert.assertEquals("https://www.bbc.co.uk/search?q=TEST", bbcPage.returnCurrUrl());
    }

    @Test (priority = 1)
    public void checkIsDisplayedSearchInput() {
        Assert.assertEquals(true, bbcPage.returnSearchInput().isDisplayed());
    }

    @Test (priority = 2)
    public void checkIsEnabledSearchInput() {
        Assert.assertEquals(true, bbcPage.returnSearchInput().isEnabled());
    }

    @Test (priority = 3)
    public void checkNavigationButton () {
        bbcPage
            .clickOnNavigationWeatherButton();
        Assert.assertEquals("https://www.bbc.com/weather", bbcPage.returnCurrUrl());
    }
}
