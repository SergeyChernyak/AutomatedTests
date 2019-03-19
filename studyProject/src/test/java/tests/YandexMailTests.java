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

    @Test()
    public void checkLoginToMailTest () {
        yandexMailPage.clickToEnterMail();
        yandexMailPage.enterLogin("AutotestUser");
        yandexMailPage.enterPassword("AutotestUser123");
        yandexMailPage.loginToMail();
        Assert.assertEquals(yandexMailPage.getNameOfUser(), "AutotestUser", "Not correct user");
    }

    @Test(priority = 1)
    public void checkLogoutFromMailTest () {
        yandexMailPage.logoutFromMail();
        Assert.assertTrue(yandexMailPage.getStatusEnterMailButton(), "Not logout user");
    }

    @Test(priority = 2)
    public void checkErrorInvalidPasswordTest () {
        yandexMailPage.clickToEnterMail();
        yandexMailPage.backToEnterLoginForm();
        yandexMailPage.enterLogin("AutotestUser");
        yandexMailPage.enterPassword("NoAutotestUser123");
        yandexMailPage.loginToMail();
        Assert.assertEquals(yandexMailPage.getErrorLoginMessage(), "Неверный пароль", "Not correct error message");
    }

    @Test(priority = 3)
    public void checkErrorInvalidLoginTest () {
        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToEnterMail();
        yandexMailPage.backToEnterLoginForm();
        yandexMailPage.enterLogin("NoAutotestUser");
        Assert.assertEquals(yandexMailPage.getErrorLoginMessage(), "Такого аккаунта нет", "Not correct error message");
    }

    @Test(priority = 4)
    public void checkClickForNavigationButtonTest () {
        yandexMailPage.backToMainPage("https://yandex.by/");

        yandexMailPage.clickToNavigationLink("Видео");
        Assert.assertEquals(yandexMailPage.getCurrentIndexUrl(1), "https://yandex.by/video/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Картинки");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://yandex.by/images/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Новости");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://news.yandex.by/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Карты");
        Assert.assertEquals(yandexMailPage.getTitleCurrentUlr(), "Яндекс.Карты — подробная карта Беларуси и мира",
                "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Маркет");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(),
                "https://market.yandex.by/?clid=505&utm_source=face_abovesearch&utm_campaign=face_abovesearch",
                "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Переводчик");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://translate.yandex.by/", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");

        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.clickToNavigationLink("Музыка");
        Assert.assertEquals(yandexMailPage.getCurrentUrl(), "https://music.yandex.by/home", "Not found page");
        Assert.assertEquals(yandexMailPage.getResponseCode(yandexMailPage.getCurrentUrl()), 200,
                "Response code not 200");
    }

    @Test(priority = 5)
    public void checkSwitchLanguageToEnglishTest () {
        yandexMailPage.backToMainPage("https://yandex.by/");
        yandexMailPage.openDropDownWithLanguages();
        yandexMailPage.chooseEnglishLanguage();
        yandexMailPage.saveLanguage();
        Assert.assertEquals(yandexMailPage.getCurrentLanguage(), "English", "Not English");
    }
}
