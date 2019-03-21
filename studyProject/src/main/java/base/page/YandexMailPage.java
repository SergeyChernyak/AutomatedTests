package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class YandexMailPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;

    public YandexMailPage(WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(css = ".desk-notif-card__login-enter-expanded")
    private WebElement eneterMailButton;

    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = "button[type='submit']")
    private WebElement enterButton;

    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordField;

    @FindBy(css = ".passp-auth>a")
    private WebElement backButton;

    @FindBy(css = "div[class='passp-account-list']>a")
    private WebElement anotherAccountLink;

    @FindBy(css = ".mail-User-Name")
    private WebElement nameOfUserText;

    @FindBy(css = ".mail-User-Avatar_header")
    private WebElement userAvatarHeader;

    @FindBy(css = ".b-user-dropdown-content>div:nth-child(7)>a")
    private WebElement logoutButton;

    @FindBy(css = ".passp-form-field__error")
    private WebElement errorMessageText;

    @FindBy(css = "a[data-id='video']")
    private WebElement videoNavigateLink;

    @FindBy(css = "a[data-id='images']")
    private WebElement imagesNavigateLink;

    @FindBy(css = "a[data-id='news']")
    private WebElement newsNavigateLink;

    @FindBy(css = "a[data-id='maps']")
    private WebElement mapsNavigateLink;

    @FindBy(css = "a[data-id='market']")
    private WebElement marketNavigateLink;

    @FindBy(css = "a[data-id='translate']")
    private WebElement translateNavigateLink;

    @FindBy(css = "a[data-id='music']")
    private WebElement musicNavigateLink;

    @FindBy(css = ".b-langs .link_black_yes")
    private WebElement languageButton;

    @FindBy(css = ".i-bem.menu_js_inited span")
    private WebElement namesOfLanguageText;

    @FindBy(css = "a[aria-label='En']")
    private WebElement englishLanguageLink;

    @FindBy(css = "a[aria-label='ещё']")
    private WebElement moreButton;

    @FindBy(css = ".option__content .button_js_inited")
    private WebElement languageDropDown;

    @FindBy(css = ".popup__content>div>:nth-child(6)")
    private WebElement englishFromDropDownButton;

    @FindBy(css = ".form__save")
    private WebElement saveLanguageButton;

    @FindBy(css = ".option__content .button_js_inited>span")
    private WebElement nameCurrentLanguageText;

    @FindBy(css = ".popular>.related-serp__title")
    private WebElement nameTitleVideoPageText;

    @FindBy(css = ".collections-menu>.collections-menu__menu-item_cur")
    private WebElement myTapeLinkImagesPage;

    @FindBy(css = ".logo_name_news-ru-77x26")
    private WebElement newsLogoNewsPage;

    @FindBy(css = ".header .input_air-search-large__control")
    private WebElement searchInputMapsPage;

    @FindBy(css = ".logo_part_market")
    private WebElement marketLogoMarketPage;

    @FindBy(css = ".name")
    private WebElement traslateLogoTranslatePage;

    @FindBy(css = ".head .d-logo__ya-sub")
    private WebElement musicLogoMusicPage;

    @FindBy(css = ".passp-account-list-item__remove-button")
    private WebElement deleteFromListAccountButton;

    @FindBy(css = ".passp-account-list-item-block>a")
    private WebElement userAccountBlock;

    public void clickToEnterMail () {
        clickWithWait(eneterMailButton);
    }

    public void enterLogin (String login) {
        sendKeysAction(loginField, login);
        clickActon(enterButton);
    }

    public void enterLoginJs (String login) {
        sendKeysJS(loginField, login);
        clickJS(enterButton);
    }

    public void enterPassword (String password) {
        sendKeysWithWait(passwordField, password);
    }

    private void clickOnInvisibleElement(WebElement element) {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);"
                ;
        ((JavascriptExecutor)webDriver).executeScript(script, element);
    }

    public void deleteLogoutAccountFromList () {
        clickWithWait(backButton);
        clickOnInvisibleElement(deleteFromListAccountButton);
    }

    public void loginToMail() {
        clickWithWait(enterButton);
    }

    public String getNameOfUser () {
        wait.visibleWithoutInputParamWait(nameOfUserText);
        return nameOfUserText.getText();
    }

    public void logoutFromMail () {
        clickWithWait(userAvatarHeader);
        clickWithWait(logoutButton);
    }

    public Boolean getStatusEnterMailButton () {
        wait.visibleWithoutInputParamWait(eneterMailButton);
        return eneterMailButton.isDisplayed();
    }

    public String getErrorLoginMessage () {
        wait.visibleWithoutInputParamWait(errorMessageText);
        return errorMessageText.getText();
    }

    public void backToMainPage (String url) {
        webDriver.navigate().to(url);
    }

    public void clickToNavigationLink(String nameOfLink) {
        switch (nameOfLink) {
            case "Видео":
                clickWithWait(videoNavigateLink);
                break;
            case "Картинки":
                clickWithWait(imagesNavigateLink);
                break;
            case "Новости":
                clickWithWait(newsNavigateLink);
                break;
            case "Карты":
                clickWithWait(mapsNavigateLink);
                break;
            case "Маркет":
                clickWithWait(marketNavigateLink);
                break;
            case "Переводчик":
                clickWithWait(translateNavigateLink);
                break;
            case "Музыка":
                clickWithWait(musicNavigateLink);
                break;
            default:
                break;
        }
    }

    public boolean getVisibilityOfElementOnVideoPage() {
        return nameTitleVideoPageText.isDisplayed();
    }

    public boolean getVisibilityOfElementOnImagesPage() {
        return myTapeLinkImagesPage.isDisplayed();
    }

    public boolean getVisibilityOfElementOnNewsPage() {
        return newsLogoNewsPage.isDisplayed();
    }

    public String getPlaceholderOfElementOnMapsPage() {
        return searchInputMapsPage.getAttribute("placeholder");
    }

    public boolean getVisibilityOfElementOnMarketPage() {
        return marketLogoMarketPage.isDisplayed();
    }

    public boolean getVisibilityOfElementOnTranslatePage() {
        return traslateLogoTranslatePage.isDisplayed();
    }

    public boolean getVisibilityOfElementOnMusicPage() {
        return musicLogoMusicPage.isDisplayed();
    }

    public String getCurrentIndexUrl(Integer index) {
        ArrayList<String> tabs2 = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(index));
        return webDriver.getCurrentUrl();
    }

    public String getCurrentUrl () {
        return webDriver.getCurrentUrl();
    }

    public int getResponseCode(String link) {
        URL url;
        HttpURLConnection con = null;
        int responsecode = 0;
        try {
            url = new URL(link);
            con = (HttpURLConnection) url.openConnection();
            responsecode = con.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != con)
                con.disconnect();
        }
        return responsecode;
    }

    public String getTitleCurrentUlr () {
        return webDriver.getTitle();
    }

    public void openDropDownWithLanguages () {
        clickWithWait(languageButton);
    }

    private boolean existsElement (WebElement webElement) {
        try {
            webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void chooseEnglishLanguage () {
        if (existsElement(englishLanguageLink))
            clickWithWait(englishLanguageLink);
        else {
            clickWithWait(moreButton);
            clickWithWait(languageDropDown);
            clickWithWait(englishFromDropDownButton);
        }
    }

    public void saveLanguage () {
        clickWithWait(saveLanguageButton);
    }

    public String getCurrentLanguage () {
        openDropDownWithLanguages();
        clickWithWait(moreButton);
        return nameCurrentLanguageText.getText();
    }
}
