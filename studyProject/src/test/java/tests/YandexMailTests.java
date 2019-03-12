package tests;

import base.conf.BaseTest;
import base.page.YandexMail;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YandexMailTests extends BaseTest {

    private YandexMail yandexMail;

    @BeforeTest
    public void getUrl () {
        driver.get("https://yandex.by/");
        yandexMail = new YandexMail(driver, wait);
    }

    @Test(priority = 0)
    public void checkLoginToMailTest () {
        yandexMail.clickToEnterMail();
        yandexMail.enterLogin("AutotestUser");
        yandexMail.enterPassword("AutotestUser123");
        yandexMail.loginToMail();
        Assert.assertEquals(yandexMail.getNameOfUser(), "AutotestUser", "Not correct user");
    }

    @Test(priority = 1)
    public void checkLogoutFromMailTest () {
        yandexMail.logoutFromMail();
        Assert.assertTrue(yandexMail.getStatusEnterMailButton(), "Not logout user");
    }

    @Test(priority = 2)
    public void checkErrorInvalidPasswordTest () {
        yandexMail.clickToEnterMail();
        yandexMail.backToEnterLoginForm();
        yandexMail.enterLogin("AutotestUser");
        yandexMail.enterPassword("NoAutotestUser123");
        yandexMail.loginToMail();
        Assert.assertEquals(yandexMail.getErrorLoginMessage(), "Неверный пароль", "Not correct error message");
    }

    @Test(priority = 3)
    public void checkErrorInvalidLoginTest () {
        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToEnterMail();
        yandexMail.backToEnterLoginForm();
        yandexMail.enterLogin("NoAutotestUser");
        Assert.assertEquals(yandexMail.getErrorLoginMessage(), "Такого аккаунта нет", "Not correct error message");
    }

    @Test(priority = 4)
    public void checkClickForNavigationButtonTest () {
        yandexMail.backToMainPage("https://yandex.by/");

        yandexMail.clickToNavigationLink("Видео");
        Assert.assertEquals(yandexMail.getCurrentIndexUrl(1), "https://yandex.by/video/", "Not found page");

        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToNavigationLink("Картинки");
        Assert.assertEquals(yandexMail.getCurrentUrl(), "https://yandex.by/images/", "Not found page");

        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToNavigationLink("Новости");
        Assert.assertEquals(yandexMail.getCurrentUrl(), "https://news.yandex.by/", "Not found page");

        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToNavigationLink("Карты");
        Assert.assertEquals(yandexMail.getCurrentUrl(), "https://yandex.by/maps/10275/polotsk/?ll=28.768349%2C55.485576&z=13",
                "Not found page");

        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToNavigationLink("Маркет");
        Assert.assertEquals(yandexMail.getCurrentUrl(),
                "https://market.yandex.by/?clid=505&utm_source=face_abovesearch&utm_campaign=face_abovesearch",
                "Not found page");

        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToNavigationLink("Переводчик");
        Assert.assertEquals(yandexMail.getCurrentUrl(), "https://translate.yandex.by/", "Not found page");

        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.clickToNavigationLink("Музыка");
        Assert.assertEquals(yandexMail.getCurrentUrl(), "https://music.yandex.by/home", "Not found page");
    }

    @Test(priority = 5)
    public void checkSwitchLanguageToEnglishTest () {
        yandexMail.backToMainPage("https://yandex.by/");
        yandexMail.openDropDownWithLanguages();
        yandexMail.chooseEnglishLanguage();
        yandexMail.saveLanguage();
        Assert.assertEquals(yandexMail.getCurrentLanguage(), "English", "Not English");
    }
}
