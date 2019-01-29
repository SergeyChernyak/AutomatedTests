package tests;

import base.conf.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBbc extends BaseTest {

    @Test
    public void checkField() {
        basePage.bbcPage
                .getUrl("https://www.bbc.com/")
                .enterText("TEST");
        Assert.assertEquals("https://www.bbc.co.uk/search?q=TEST", (webDriver.getCurrentUrl()));
    }

    @Test
    public void checkIsDisplayed() {
        Assert.assertEquals(true, ((webDriver.findElement(By.cssSelector("#orb-search-q"))).isDisplayed()));
    }

    @Test
    public void checkIsEnabled() {
        Assert.assertEquals(true, ((webDriver.findElement(By.cssSelector("#orb-search-q"))).isEnabled()));
    }

    @Test
    public void checkNavigationButton () {
        basePage.bbcPage
            .clickToButton();
        Assert.assertEquals("https://www.bbc.com/weather", webDriver.getCurrentUrl());
    }
}
