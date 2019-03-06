package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class YandexMarketPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;

    public YandexMarketPage(WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(css = "label[for='7893318_153043']")
    private WebElement appleLabelCheckBox;

    @FindBy(css = "input[name='Производитель Apple']")
    private WebElement appleCheckBox;

    @FindBy(css = ".button_arrow_down")
    private WebElement showFromDropDown;

    @FindBy(css = ".select__item:nth-child(1)>span")
    private WebElement showOf12Button;

    @FindBy(css = ".select__item:nth-child(2)>span")
    private WebElement showOf48Button;

    @FindBy(css = ".button_arrow_down>.button__text")
    private WebElement countElementsOfDispayedOnPage;

    @FindBy(css = "label[for='offer-shipping_delivery']")
    private WebElement withDeliveryLabelRadioButton;

    @FindBy(css = "#offer-shipping_delivery")
    private WebElement withDeliveryRadioButton;

    @FindBy(css = ".input__control")
    private WebElement searchField;

    @FindBy(css = ".n-filter-block_pos_left>:nth-child(3)")
    private WebElement byPriceSortDiv;

    @FindBy(css = ".n-filter-block_pos_left>:nth-child(3)>a")
    private WebElement byPriceSortButton;

    @FindBy(css = "div[data-id='model-14209841'] .n-snippet-cell2__title>a")
    private WebElement fistPhoneLink;

    @FindBy(css = ".preloadable__preloader>div")
    private WebElement preLoader;

    private By priceOfAllProduct = By.cssSelector(".n-snippet-cell2__price div[class='price']");
    private By countProduct = By.cssSelector("n-snippet-cell2_type_product");


    public void chooseCheckBoxManufacturer () {
        moveToElement(appleLabelCheckBox);
        if (!appleLabelCheckBox.isSelected())
            clickWithWait(appleLabelCheckBox);
    }

    public boolean getStatusCheckBox () {
        moveToElement(appleLabelCheckBox);
        return appleCheckBox.isSelected();
    }

    public void changeTheDisplayNumberOfItems (Integer value) {
        moveToElementAndClick(showFromDropDown);
        switch (value) {
            case 12:
                showOf12Button.click();
                break;
            case 48:
                showOf48Button.click();
                break;
            default:
                break;
        }
        wait.isPresentAlert();
        webDriver.switchTo().alert().accept();
    }

    public String countOfElementVisibleOfPage () {
        wait.visibleWithoutInputParamWait(countElementsOfDispayedOnPage);
        moveToElement(countElementsOfDispayedOnPage);
        return countElementsOfDispayedOnPage.getText();
    }

    public Integer getCountProductOnPage () {

//        wait.visibleBy(countProduct,20);
//        wait.notVisibleWithoutInputParamWait(preLoader);
//        wait.waitForJSandJQueryToLoad();

        wait.waitForAjaxToFinish();
        wait.waitForJQueryToBeActive();
//        wait.waitForJSandJQueryToLoad();

        // вот тут висит ajax!

        int countOfProduct = webDriver.findElements(countProduct).size();
        return countOfProduct;
    }

    public void chooseDeliveryMethod() {
        moveToElementAndClick(withDeliveryLabelRadioButton);
    }

    public boolean getStatusRadioButton () {
        moveToElement(withDeliveryLabelRadioButton);
        return withDeliveryRadioButton.isSelected();
    }

    public void enterTextToSearchInput (String value) {
        sendKeysWithWait(searchField, value);
        searchField.sendKeys(Keys.ENTER);
    }

    public String getValueForSearchInput () {
        return searchField.getAttribute("value");
    }

    public void sortByPrice() {
        byPriceSortButton.click();
    }

    public double[] getAllPriceOfProduct () {
        List<String> pricesAllProduct = new ArrayList<>();
        List<WebElement> prices = webDriver.findElements(priceOfAllProduct);
        for (WebElement e : prices) {
            String temp = e.getText();
            String strWithoutChars = temp.replace(" б.p.", "");
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

    public String getClassSortByPrice () {
        return byPriceSortDiv.getAttribute("class");
    }

    public String getNameOfFirstPhone () {
        return fistPhoneLink.getAttribute("title");
    }
}
