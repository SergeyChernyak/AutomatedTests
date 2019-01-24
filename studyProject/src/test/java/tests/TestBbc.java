package tests;

import base.conf.InitDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.page.BbcPage;

public class TestBbc extends InitDriver {
    private BbcPage bbcPage = new BbcPage();

    @Test
    public void checkIsDisplayed() {
        bbcPage
                .getUrl("https://www.bbc.com/");
        Assert.assertEquals(true, ((webDriver.findElement(By.cssSelector("#orb-search-q"))).isDisplayed()));
    }

    @Test
    public void checkIsEnabled() {
        bbcPage
                .getUrl("https://www.bbc.com/");
        Assert.assertEquals(true, ((webDriver.findElement(By.cssSelector("#orb-search-q"))).isEnabled()));
    }

    @Test
    public void checkField() throws InterruptedException {
        bbcPage
            .getUrl("https://www.bbc.com/")
            .enterText("TEST");
        Assert.assertEquals("https://www.bbc.co.uk/search?q=TEST", (webDriver.getCurrentUrl()));
    }

    @Test
    public void checkNavigationButton () {
        bbcPage
            .getUrl("https://www.bbc.com/")
            .clickToButton();
        Assert.assertEquals("https://www.bbc.com/weather", webDriver.getCurrentUrl());
    }
}
