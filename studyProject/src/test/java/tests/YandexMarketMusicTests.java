package tests;

import base.conf.BaseTest;
import base.page.YandexMarketMusicPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexMarketMusicTests extends BaseTest {
    private YandexMarketMusicPage yandexMarketMusicPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.by/");
        yandexMarketMusicPage = new YandexMarketMusicPage(driver, wait);
    }

    @Test
    public void checkAddProductToCompareTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.searchProduct("Note 8");
        yandexMarketMusicPage.addProductToCompare();
        yandexMarketMusicPage.goToComparePage();
        Assert.assertTrue(yandexMarketMusicPage.compareTwoList(),
                "Product not add to compare");
        yandexMarketMusicPage.deleteProductFromCompare();
        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkDeleteFromCompareTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.searchProduct("Note 8");
        yandexMarketMusicPage.addProductToCompare();
        yandexMarketMusicPage.goToComparePage();
        Assert.assertTrue(yandexMarketMusicPage.compareTwoList(),
                "Product not add to compare");
        yandexMarketMusicPage.deleteProductFromCompare();
        Assert.assertTrue(yandexMarketMusicPage.getTextAboutNoProduct().contains("Товаров нет."),
                "Product not delete from compare list");
        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkOrderPriceTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.chooseCamera();
        yandexMarketMusicPage.orderByPrice();
        Assert.assertEquals(java.util.Optional.ofNullable(yandexMarketMusicPage.numberOfReplacementsSortDesc(
                yandexMarketMusicPage.getAllPriceOfProduct())),
                java.util.Optional.of(0),
                "Not sorted by DESC");
        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkOrderWideTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.chooseFridges();
        yandexMarketMusicPage.chooseWidthToSort("50");
        Assert.assertTrue(yandexMarketMusicPage.getTitleSortOfMarketPage().contains("шириной до 50 см"),
                "Not sorted by width");
        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkChooseArtist () {
        yandexMarketMusicPage.clickToEnterMail();
        yandexMarketMusicPage.enterLogin("AutotestUser");
        yandexMarketMusicPage.enterPassword("AutotestUser123");
        yandexMarketMusicPage.loginToMail();
        Assert.assertEquals(yandexMarketMusicPage.getNameOfUser(),
                "AutotestUser", "Not correct user");

        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
        yandexMarketMusicPage.goToMusicPage();
        yandexMarketMusicPage.searchArtist("Metal");
        yandexMarketMusicPage.chooseArtistMetallicaFromDropDown();
        Assert.assertEquals(yandexMarketMusicPage.getNameOfArtist(),
                "Metallica",
                "Not metallica band");
        Assert.assertEquals(yandexMarketMusicPage.countOfNotMetallica(),
                0,
                "Bans not Metallica in Popular albums");

        yandexMarketMusicPage.logoutFromMusic();
        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkPlayMusicTest () {
        yandexMarketMusicPage.clickToEnterMail();
        yandexMarketMusicPage.enterLogin("AutotestUser");
        yandexMarketMusicPage.enterPassword("AutotestUser123");
        yandexMarketMusicPage.loginToMail();
        Assert.assertEquals(yandexMarketMusicPage.getNameOfUser(),
                "AutotestUser", "Not correct user");

        yandexMarketMusicPage.backToMainPage("https://yandex.by/");
        yandexMarketMusicPage.goToMusicPage();
        yandexMarketMusicPage.searchArtist("beyo");
        yandexMarketMusicPage.chooseArtistBeyonceFromDropDown();

        yandexMarketMusicPage.playPauseFirstMusic();
        wait.sleep(10);
        Assert.assertTrue(yandexMarketMusicPage.isPlayingTrack(),
                "Track not playing");

        yandexMarketMusicPage.playPauseFirstMusic();
        wait.sleep(10);
        Assert.assertEquals(yandexMarketMusicPage.getCurrentPlayingTime(),
                yandexMarketMusicPage.getCurrentPlayingTime(),
                "Track not paused");

        yandexMarketMusicPage.logoutFromMusic();
        yandexMarketMusicPage.backToMainPage("https://yandex.by/");

    }
}
