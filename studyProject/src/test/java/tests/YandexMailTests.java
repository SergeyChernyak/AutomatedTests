package tests;

import base.conf.BaseTest;
import base.page.YandexMailPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexMailTests extends BaseTest {

    private YandexMailPage yandexMailPage;

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.by/");
        yandexMailPage = new YandexMailPage(driver, wait);
    }

    @Test
    public void checkLoginToMailTest () {
        yandexMailPage.clickToEnterMail();
        yandexMailPage.enterLogin("AutotestUser");
        yandexMailPage.enterPassword("AutotestUser123");
        yandexMailPage.loginToMail();
        Assert.assertEquals(yandexMailPage.getNameOfUser(), "AutotestUser", "Not correct user");
        yandexMailPage.logoutFromMail();
        yandexMailPage.clickToEnterMail();
        yandexMailPage.deleteLogoutAccountFromList();
        yandexMailPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkLogoutFromMailTest () {
        yandexMailPage.clickToEnterMail();
        yandexMailPage.enterLogin("AutotestUser");
        yandexMailPage.enterPassword("AutotestUser123");
        yandexMailPage.loginToMail();
        yandexMailPage.logoutFromMail();
        Assert.assertTrue(yandexMailPage.getStatusEnterMailButton(), "Not logout user");
        yandexMailPage.clickToEnterMail();
        yandexMailPage.deleteLogoutAccountFromList();
        yandexMailPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkErrorInvalidPasswordTest () {
        yandexMailPage.clickToEnterMail();
        yandexMailPage.enterLogin("AutotestUser");
        yandexMailPage.enterPassword("NoAutotestUser123");
        yandexMailPage.loginToMail();
        Assert.assertEquals(yandexMailPage.getErrorLoginMessage(), "Неверный пароль", "Not correct error message");
        yandexMailPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkErrorInvalidLoginTest () {
        yandexMailPage.clickToEnterMail();
        yandexMailPage.enterLogin("NoAutotestUser");
        Assert.assertEquals(yandexMailPage.getErrorLoginMessage(), "Такого аккаунта нет", "Not correct error message");
        yandexMailPage.backToMainPage("https://yandex.by/");
    }


    @Test
    public void checkClickForNavigationButtonTest () {
        yandexMailPage.backToMainPage("https://yandex.by/");

        yandexMailPage.clickToNavigationLink("Видео");
        Assert.assertEquals(yandexMailPage.getCurrentIndexUrl(1), "https://yandex.by/video/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertTrue(yandexMailPage.getStatusOfElementOnVideoPage(), "Page not found");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Картинки");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://yandex.by/images/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertTrue(yandexMailPage.getStatusOfElementOnImagesPage(), "Page not found");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Новости");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://news.yandex.by/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertTrue(yandexMailPage.getStatusOfElementOnNewsPage(), "Page not found");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Карты");
        Assert.assertEquals(yandexMailPage.getTitleCurrentUlr(), "Яндекс.Карты — подробная карта Беларуси и мира",
                "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertEquals(yandexMailPage.getStatusOfElementOnMapsPage(), "Поиск мест и адресов",
                "Page not found");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Маркет");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(),
                "https://market.yandex.by/?clid=505&utm_source=face_abovesearch&utm_campaign=face_abovesearch",
                "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertTrue(yandexMailPage.getStatusOfElementOnMarketPage(), "Page not found");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Переводчик");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://translate.yandex.by/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertTrue(yandexMailPage.getStatusOfElementOnTranslatePage(), "Page not found");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Музыка");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://music.yandex.by/home", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
        Assert.assertTrue(yandexMailPage.getStatusOfElementOnMusicPage(), "Page not found");
        yandexMailPage.backToMainPage("https://yandex.by/");
    }

    @Test
    public void checkSwitchLanguageToEnglishTest () {
        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.openDropDownWithLanguages();
        yandexMailPage.chooseEnglishLanguage();
        yandexMailPage.saveLanguage();
        Assert.assertEquals(yandexMailPage.getCurrentLanguage(), "English", "Not English");
    }
}
