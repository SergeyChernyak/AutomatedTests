package tests;

import base.conf.BaseTest;
import base.page.YandexMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexTest extends BaseTest {

    private YandexMainPage yandexMainPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.ru");
        yandexMainPage = new YandexMainPage(driver, wait);
    }

    @Test
    public void compareContentsOfTheDropdownList () {
        Assert.assertEquals(yandexMainPage.saveToListNameOfLinksLondon(), yandexMainPage.saveToListNameOfLinksParis(),
                "Lists of element not identical");
    }
}
