package tests;

import base.conf.BaseTest;
import base.page.YandexMarketPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;

public class YandexMarketTests extends BaseTest {

    private YandexMarketPage yandexMarketPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://market.yandex.by/catalog/54726/list?local-offers-first=0&onstock=1");
        yandexMarketPage = new YandexMarketPage(driver, wait);
    }

    @Test
    public void checkChooseManufacturer() {
        yandexMarketPage.chooseCheckBoxManufacturer();
        Assert.assertTrue(yandexMarketPage.getStatusCheckBox(), "CheckBox not selected");
    }

    @Test
    public void checkDisplayElementInMenu () throws AWTException {
        yandexMarketPage.changeTheDisplayNumberOfItems();
        Assert.assertEquals(yandexMarketPage.countOfElementVisibleOfPage(),"Показывать по 12",
                "Displayed not 12 element");
    }

    @Test
    public void checkChooseDeliveryMethod () {
        yandexMarketPage.chooseDeliveryMethod();
        Assert.assertTrue(yandexMarketPage.getStatusRadioButton(), "RadioButton not selected");
    }

    @Test
    public void checkEnteredValue () {
        yandexMarketPage.enterTextToSearchInput("iphone");
        Assert.assertEquals(yandexMarketPage.getValueForSearchInput(), "iphone", "Value not equals");
    }

    @Test
    public void checkClassSortPriceButton () {
        yandexMarketPage.sortByPrice();
        Assert.assertTrue(yandexMarketPage.getClassSortByPrice().contains("asc"), "Not ASC");
        yandexMarketPage.sortByPrice();
        Assert.assertTrue(yandexMarketPage.getClassSortByPrice().contains("desc"), "Not DESC");
    }

    @Test
    public void checkFistPhoneInMenu () {
        yandexMarketPage.enterTextToSearchInput("iPhone 6s");
        Assert.assertEquals(yandexMarketPage.getNameOfFirstPhone(), "Смартфон Apple iPhone 6S 32GB",
                "Not same");
    }
 }
