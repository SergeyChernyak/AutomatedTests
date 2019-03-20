package tests;

import base.conf.BaseTest;
import base.page.YandexMarketPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexMarketTests extends BaseTest {

    private YandexMarketPage yandexMarketPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://market.yandex.by/catalog/54726/list?local-offers-first=0&onstock=1");
        yandexMarketPage = new YandexMarketPage(driver, wait);
    }

    @Test
    public void checkChooseManufacturerTest() {
        yandexMarketPage.chooseCheckBoxManufacturer();
        Assert.assertTrue(yandexMarketPage.getStatusCheckBox(), "CheckBox not selected");
    }

    @Test
    public void checkDisplayElementInMenuTest() {
        yandexMarketPage.changeTheDisplayNumberOfItems(12);
        Assert.assertEquals(yandexMarketPage.countOfElementVisibleOfPage(),"Показывать по 12",
                "Displayed not 12 element");
        Assert.assertEquals(java.util.Optional.ofNullable(yandexMarketPage.getCountProductOnPage()),
                java.util.Optional.of(12),
                "Not same count elements");
    }

    @Test
    public void checkChooseDeliveryMethodTest() {
        yandexMarketPage.chooseDeliveryMethod();
        Assert.assertTrue(yandexMarketPage.getStatusRadioButton(), "RadioButton not selected");
    }

    @Test
    public void checkEnteredValueTest() {
        yandexMarketPage.enterTextToSearchInput("iphone");
        Assert.assertEquals(yandexMarketPage.getValueForSearchInput(), "iphone", "Value not equals");
    }

    @Test
    public void checkClassSortPriceButtonTest() {
        yandexMarketPage.sortByPrice();
        Assert.assertTrue(yandexMarketPage.getClassSortByPrice().contains("asc"), "Not ASC");
        Assert.assertEquals(java.util.Optional.ofNullable(yandexMarketPage.numberOfReplacementsSortAsc(
                yandexMarketPage.getAllPriceOfProduct())),
                java.util.Optional.of(0),
                "Not sorted by ASC");

        yandexMarketPage.sortByPrice();
        Assert.assertTrue(yandexMarketPage.getClassSortByPrice().contains("desc"), "Not DESC");
        Assert.assertEquals(java.util.Optional.ofNullable(yandexMarketPage.numberOfReplacementsSortDesc(
                yandexMarketPage.getAllPriceOfProduct())),
                java.util.Optional.of(0),
                "Not sorted by DESC");
    }

    @Test
    public void checkFistPhoneInMenuTest() {
        yandexMarketPage.enterTextToSearchInput("iPhone 6s");
        Assert.assertEquals(yandexMarketPage.getNameOfFirstPhone(), "Смартфон Apple iPhone 6S 32GB",
                "Not same");
    }
 }
