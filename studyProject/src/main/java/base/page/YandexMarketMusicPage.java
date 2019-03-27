package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class YandexMarketMusicPage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    private List<String> nameOfTwoElement = new ArrayList<>();
    private List<String> nameOfTwoElementCompare = new ArrayList<>();

    public YandexMarketMusicPage(WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "a[data-id='market']")
    private WebElement marketNavigateLink;

    @FindBy(css = "#header-search")
    private WebElement searchInput;

    @FindBy(css = ".button2_type_submit")
    private WebElement searchButton;

    @FindBy(css = ".snippet-list_js_inited>div:nth-child(1) .n-user-lists_type_compare_js_inited")
    private WebElement addToCompareFirstProductDiv;

    @FindBy(css = ".snippet-list_js_inited>div:nth-child(2) .n-user-lists_type_compare_js_inited")
    private WebElement addToCompareSecondProductDiv;

    @FindBy(css = ".header2-menu__item_type_compare")
    private WebElement compareButton;

    @FindBy(css = ".snippet-list_js_inited>div:nth-child(1) .n-snippet-cell2__title>a")
    private WebElement nameOfFirstProductText;

    @FindBy(css = ".snippet-list_js_inited>div:nth-child(2) .n-snippet-cell2__title>a")
    private WebElement nameOfSecondProductText;

    @FindBy(css = ".n-compare-content__line>div:nth-child(1)>a")
    private WebElement nameOfFirstProductOnComparePageText;

    @FindBy(css = ".n-compare-content__line>div:nth-child(2)>a")
    private WebElement nameOfSecondProductOnComparePageText;

    @FindBy(css = ".n-compare-toolbar__action>span")
    private WebElement deleteListSpan;

    @FindBy(css = ".title_size_18")
    private WebElement notProductDiv;

    @FindBy(css = "div[class=\"n-w-tabs__horizontal-tabs n-adaptive-layout\"]>div:nth-child(2)>a")
    private WebElement electronikLink;

    @FindBy(css = ".MjXb8xWfBe div[class=\"_1YdrMWBuYy\"]:nth-child(4)>._3VMnEHDoVX div:nth-child(3)>a")
    private WebElement actionCameraLink;

    @FindBy(css = ".n-filter-block_pos_left>:nth-child(3)>a")
    private WebElement byPriceSortButton;

    @FindBy(css = "div[class=\"n-w-tabs__horizontal-tabs n-adaptive-layout\"]>div:nth-child(3)>a")
    private WebElement tehnicLink;

    @FindBy(css = ".MjXb8xWfBe div[class=\"_1YdrMWBuYy\"]:nth-child(1)>._3VMnEHDoVX div:nth-child(1)>a")
    private WebElement fridgesLink;

    @FindBy(css = "fieldset[data-autotest-id=\"15464317\"] li:nth-child(2) input")
    private WebElement widthToInput;

    @FindBy(css = ".headline__header_regional>h1")
    private WebElement titleOfSortText;

    @FindBy(css = ".desk-notif-card__login-enter-expanded")
    private WebElement eneterMailButton;

    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = "button[type='submit']")
    private WebElement enterButton;

    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordField;

    @FindBy(css = ".mail-User-Name")
    private WebElement nameOfUserText;

    @FindBy(css = "a[data-id='music']")
    private WebElement musicNavigateLink;

    @FindBy(css = ".d-suggest input")
    private WebElement searchArtistInput;

    @FindBy(css = "a[href=\"/artist/3121\"]")
    private WebElement metalicaFromDropDownLink;

    @FindBy(css = ".page-artist h1")
    private WebElement titleOfArtistPageText;

    @FindBy(css = "a[class=\"head__userpic not-handled\"]")
    private WebElement userAvatarLink;

    @FindBy(css = ".multi-auth__container li:nth-child(10)>a")
    private WebElement logoutButton;

    @FindBy(css = ".head__user>a")
    private WebElement loginButton;

    @FindBy(css = "a[href=\"/artist/27995\"")
    private WebElement beyonceFromDropDownLink;

    @FindBy(css = ".d-generic-page-head__main-actions>button")
    private WebElement firstMusicButton;

    @FindBy(css = ".progress__bar.progress__text>.progress__left")
    private WebElement musicPrigressBar;

    private By priceOfAllProduct = By.cssSelector(".n-snippet-cell2__price div[class='price']");
    private By namesBandOfPopularAlbums = By.cssSelector(".page-artist__albums .album__artist");

    public void goToMarket () {
        clickWithWait(marketNavigateLink);
    }

    public void backToMainPage (String url) {
        webDriver.get(url);
    }

    public void searchProduct (String product) {
        sendKeysWithWait(searchInput, product);
        clickWithWait(searchButton);
    }

    private void clickOnInvisibleElement(WebElement element) {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);"
                ;
        ((JavascriptExecutor)webDriver).executeScript(script, element);
    }

    public void addProductToCompare () {
        clickOnInvisibleElement(addToCompareFirstProductDiv);
        clickOnInvisibleElement(addToCompareSecondProductDiv);
        getTwoTitleOfProduct();
    }

    public void goToComparePage () {
        clickWithWait(compareButton);
        getTwoTitleOfProductOnComparePage();
    }

    private void getTwoTitleOfProduct() {

        nameOfTwoElement.add(nameOfFirstProductText.getText());
        nameOfTwoElement.add(nameOfSecondProductText.getText());
    }

    private void getTwoTitleOfProductOnComparePage() {
        nameOfTwoElementCompare.add(nameOfFirstProductOnComparePageText.getText());
        nameOfTwoElementCompare.add(nameOfSecondProductOnComparePageText.getText());
    }

    public boolean compareTwoList () {
        int count = 0;
        for (int i = 0; i < nameOfTwoElement.size(); i++) {
            for (int j = 0; j < nameOfTwoElement.size(); j++) {
                if (nameOfTwoElement.get(i).equals(nameOfTwoElementCompare.get(j))) {
                } else {
                    count = count++;
                }
            }
        }
        if (count == 0) {
            return true;
        } else
            return false;
    }

    public void deleteProductFromCompare () {
        clickWithWait(deleteListSpan);
    }

    public String getTextAboutNoProduct () {
        wait.visibleWithoutInputParamWait(notProductDiv);
        return notProductDiv.getText();
    }

    public void chooseCamera () {
        clickWithWait(electronikLink);
        moveToElementAndClick(actionCameraLink);
    }

    public void orderByPrice() {
        byPriceSortButton.click();
        byPriceSortButton.click();
        wait.sleep(3);
        webDriver.navigate().refresh();
    }

    public double[] getAllPriceOfProduct () {
        List<String> pricesAllProduct = new ArrayList<>();
        List<WebElement> prices = webDriver.findElements(priceOfAllProduct);
        for (WebElement e : prices) {
            String temp = e.getText();
            String strWithoutChars0 = temp.replace("от ", "");
            String strWithoutChars = strWithoutChars0.replace(" б.p.", "");
            String strInFormatDouble = strWithoutChars.replace(",", ".");
            String strPrice = strInFormatDouble.replace(" ", "");
            pricesAllProduct.add(strPrice);
        }
        double[] arrPricesAllProduct = new double[pricesAllProduct.size()];
        for (int i = 0; i< arrPricesAllProduct.length; i++) {
            arrPricesAllProduct[i] = Double.parseDouble(pricesAllProduct.get(i));
        }
        return arrPricesAllProduct;
    }

    public Integer numberOfReplacementsSortAsc (double[] arrPriceOfProduct) {
        int count = 0;
        for (int i = arrPriceOfProduct.length-1; i > 0; i--) {
            for (int j = 0; j < i; j ++) {
                if (arrPriceOfProduct[j]>arrPriceOfProduct[j+1]) {
                    double tmp = arrPriceOfProduct[j];
                    arrPriceOfProduct[j] = arrPriceOfProduct[j+1];
                    arrPriceOfProduct[j+1] = tmp;
                    count++;
                }
            }
        }
        return count;
    }

    public Integer numberOfReplacementsSortDesc (double[] arrPriceOfProduct) {
        int count = 0;
        for (int i = 0; i < arrPriceOfProduct.length; i++) {
            for (int j = i + 1; j < arrPriceOfProduct.length; j++) {
                if (arrPriceOfProduct[i]<arrPriceOfProduct[j]) {
                    double tmp = arrPriceOfProduct[i];
                    arrPriceOfProduct[i] = arrPriceOfProduct[j];
                    arrPriceOfProduct[j] = tmp;
                    count++;
                }
            }
        }
        return count;
    }

    public void chooseFridges() {
        clickWithWait(tehnicLink);
        moveToElementAndClick(fridgesLink);
    }

    public void chooseWidthToSort (String widthTo) {
        moveToElement(widthToInput);
        sendKeysWithWait(widthToInput, widthTo);
        wait.sleep(3);
        webDriver.navigate().refresh();
    }

    public String getTitleSortOfMarketPage () {
        moveToElement(titleOfSortText);
        return titleOfSortText.getText();
    }

    public void clickToEnterMail () {
        clickWithWait(eneterMailButton);
    }

    public void enterLogin (String login) {
        sendKeysAction(loginField, login);
        clickActon(enterButton);
    }

    public void enterPassword (String password) {
        sendKeysWithWait(passwordField, password);
    }

    public void loginToMail() {
        clickWithWait(enterButton);
    }

    public String getNameOfUser () {
        wait.visibleWithoutInputParamWait(nameOfUserText);
        return nameOfUserText.getText();
    }

    public void goToMusicPage () {
        clickWithWait(musicNavigateLink);
    }

    public void searchArtist (String art) {
        sendKeysWithWait(searchArtistInput, art);
    }

    public void chooseArtistMetallicaFromDropDown() {
        clickWithWait(metalicaFromDropDownLink);
    }

    public String getNameOfArtist () {
        wait.visibleWithoutInputParamWait(titleOfArtistPageText);
        return titleOfArtistPageText.getText();
    }

    public int countOfNotMetallica () {
        int count = 0;
        List<String> namesOfBand = new ArrayList<>();
        List<WebElement> band = webDriver.findElements(namesBandOfPopularAlbums);
        for (WebElement e : band) {
            if (!e.getText().equals("Metallica")) {
                count++;
            }
        }
        return count;
    }

    public void logoutFromMusic () {
        clickWithWait(userAvatarLink);
        clickWithWait(logoutButton);
        wait.visibleWithoutInputParamWait(loginButton);
    }

    public void chooseArtistBeyonceFromDropDown() {
        clickWithWait(beyonceFromDropDownLink);
    }

    public void playPauseFirstMusic() {
        clickWithWait(firstMusicButton);
    }

    public String getCurrentPlayingTime() {
        moveToElement(musicPrigressBar);
        return musicPrigressBar.getText();
    }

    public boolean isPlayingTrack () {
        if (getCurrentPlayingTime().equals("0:00")) {
            return false;
        }
        return true;
    }
}
