package tests;

import base.conf.BaseTest;
import base.page.YandexMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

public class YandexTest extends BaseTest {

    private YandexMainPage yandexMainPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.ru");
        yandexMainPage = new YandexMainPage(driver, wait);
    }

    @Test
    public void compareContentsOfTheMoreDropdownList() {
        yandexMainPage.changeLocationToLondon();
        yandexMainPage.saveToListNameOfLinksLondon();
        yandexMainPage.changeLocationToParis();
        yandexMainPage.saveToListNameOfLinksParis();
        Assert.assertEquals(Arrays.toString(yandexMainPage.valuesLondon.toArray()),
                Arrays.toString(yandexMainPage.valuesParis.toArray()),
                "Lists of element not identical");
    }
}
