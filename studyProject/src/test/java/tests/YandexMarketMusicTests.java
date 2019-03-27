package tests;

import base.conf.BaseTest;
import base.page.YandexMarketMusicPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexMarketMusicTests extends BaseTest {
    private YandexMarketMusicPage yandexMarketMusicPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.by/");
        yandexMarketMusicPage = new YandexMarketMusicPage(driver, wait);
    }

    @AfterMethod
    public void openPage() {
        yandexMarketMusicPage.openPage("https://yandex.by/");
    }

    @Test
    public void checkAddProductToCompareTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.searchProduct("Note 8");
        yandexMarketMusicPage.addFirstTwoProductsToCompare();
        yandexMarketMusicPage.goToComparePage();
        Assert.assertTrue(yandexMarketMusicPage.compareTwoListWithTitleFirstTwoProduct(),
                "Product not add to compare");
        yandexMarketMusicPage.deleteProductsFromCompare();
    }

    @Test
    public void checkDeleteFromCompareTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.searchProduct("Note 8");
        yandexMarketMusicPage.addFirstTwoProductsToCompare();
        yandexMarketMusicPage.goToComparePage();
        Assert.assertTrue(yandexMarketMusicPage.compareTwoListWithTitleFirstTwoProduct(),
                "Product not add to compare");
        yandexMarketMusicPage.deleteProductsFromCompare();
        Assert.assertTrue(yandexMarketMusicPage.getTextAboutNoProduct().contains("Товаров нет."),
                "Product not delete from compare list");
    }

    @Test
    public void checkOrderPriceTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.chooseProduct("camera");
        yandexMarketMusicPage.orderByPrice();
        Assert.assertEquals(java.util.Optional.ofNullable(yandexMarketMusicPage.numberOfReplacementsSortDesc(
                yandexMarketMusicPage.getAllPriceOfProduct())),
                java.util.Optional.of(0),
                "Not sorted by DESC");
    }

    @Test
    public void checkOrderWideTest () {
        yandexMarketMusicPage.goToMarket();
        yandexMarketMusicPage.chooseProduct("fridges");
        yandexMarketMusicPage.chooseWidthToSort("50");
        Assert.assertTrue(yandexMarketMusicPage.getTitleSortOfMarketPage().contains("шириной до 50 см"),
                "Not sorted by width");
    }

    @Test
    public void checkChooseArtistTest () {
        yandexMarketMusicPage.clickToEnterMail();
        yandexMarketMusicPage.enterLogin("AutotestUser");
        yandexMarketMusicPage.enterPassword("AutotestUser123");
        yandexMarketMusicPage.loginToMail();
        Assert.assertEquals(yandexMarketMusicPage.getNameOfUser(),
                "AutotestUser", "Not correct user");

        yandexMarketMusicPage.openPage("https://yandex.by/");
        yandexMarketMusicPage.goToMusicPage();
        yandexMarketMusicPage.searchArtist("Metal");
        yandexMarketMusicPage.chooseArtistFromDropDown("Metallica");
        Assert.assertEquals(yandexMarketMusicPage.getNameOfArtist(),
                "Metallica",
                "Not metallica band");
        Assert.assertEquals(yandexMarketMusicPage.countOfNotRequiredBand("Metallica"),
                0,
                "Bands not Metallica in Popular albums");

        yandexMarketMusicPage.logoutFromMusic();
    }

    @Test
    public void checkPlayMusicTest () {
        yandexMarketMusicPage.clickToEnterMail();
        yandexMarketMusicPage.enterLogin("AutotestUser");
        yandexMarketMusicPage.enterPassword("AutotestUser123");
        yandexMarketMusicPage.loginToMail();
        Assert.assertEquals(yandexMarketMusicPage.getNameOfUser(),
                "AutotestUser", "Not correct user");

        yandexMarketMusicPage.openPage("https://yandex.by/");
        yandexMarketMusicPage.goToMusicPage();
        yandexMarketMusicPage.searchArtist("beyo");
        yandexMarketMusicPage.chooseArtistFromDropDown("Beyonce");
        yandexMarketMusicPage.playPauseFirstMusic();
        wait.sleep(10);
        Assert.assertTrue(yandexMarketMusicPage.isPlayingTrack(),
                "Track not playing");

        yandexMarketMusicPage.playPauseFirstMusic();
        yandexMarketMusicPage.setCurrentTimeTrackAfterPause();
        wait.sleep(10);
        Assert.assertEquals(yandexMarketMusicPage.getCurrentPlayingTime(),
                yandexMarketMusicPage.currentTimeTrack,
                "Track not paused");

        yandexMarketMusicPage.logoutFromMusic();
    }
}
