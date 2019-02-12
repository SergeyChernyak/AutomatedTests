package tests;

import base.conf.BaseTest;
import base.page.BbcPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BbcTests extends BaseTest {

    private BbcPage bbcPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://www.bbc.com/");
        bbcPage = new BbcPage(driver, wait);
    }

    @Test (priority = 0)
    public void checkSearchInputTest() {
        bbcPage
                .textEnter("TEST");
        Assert.assertEquals("https://www.bbc.co.uk/search?q=TEST", bbcPage.returnCurrentUrl());
    }

    @Test (priority = 1)
    public void checkIsDisplayedSearchInputTest() {
        Assert.assertTrue(bbcPage.isDisplayedSearchInput());
    }

    @Test (priority = 2)
    public void checkIsEnabledSearchInputTest() {
        Assert.assertTrue(bbcPage.isEnabledSearchInput());
    }

    @Test (priority = 3)
    public void checkNavigationButtonTest() {
        bbcPage
            .clickOnNavigationWeatherButton();
        Assert.assertEquals("https://www.bbc.com/weather", bbcPage.returnCurrentUrl());
    }
}
