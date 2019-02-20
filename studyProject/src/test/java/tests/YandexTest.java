package tests;

import base.conf.BaseTest;
import base.page.YandexMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class YandexTest extends BaseTest {

    private YandexMainPage yandexMainPage;
    private List listOfLondon = new ArrayList<>();
    private List listOfParis = new ArrayList<>();

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.ru");
        yandexMainPage = new YandexMainPage(driver, wait);
    }

    @Test
    public void compareContentsOfTheMoreDropdownList() {
        yandexMainPage.changeLocation("Лондон");
        listOfLondon = yandexMainPage.getList();
        yandexMainPage.changeLocation("Париж");
        listOfParis = yandexMainPage.getList();
        Assert.assertEquals(listOfLondon, listOfParis, "Lists of element not identical");
    }
}
