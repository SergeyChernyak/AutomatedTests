package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexMarketPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;

    public YandexMarketPage(WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "label[for='7893318_153043']")
    private WebElement appleLabelCheckBox;

    @FindBy(css = "input[name='Производитель Apple']")
    private WebElement appleCheckBox;

    @FindBy(css = ".button_arrow_down")
    private WebElement showFromDropDown;

    @FindBy(css = ".select__item:nth-child(1)>span")
    private WebElement showOf12Button;

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

    public void chooseCheckBoxManufacturer () {
        customMoveToElement(appleLabelCheckBox);
        if (!appleLabelCheckBox.isSelected())
            customClickWithWait(appleLabelCheckBox);
    }

    public boolean getStatusCheckBox () {
        customMoveToElement(appleLabelCheckBox);
        return appleCheckBox.isSelected();
    }

    public void changeTheDisplayNumberOfItems () {
        customMoveToElementAndClick(showFromDropDown);
        showOf12Button.click();
        wait.isPresentAlert();
        webDriver.switchTo().alert().accept();
    }

    public String countOfElementVisibleOfPage () {
        wait.visibleWithoutInputParamWait(countElementsOfDispayedOnPage);
        customMoveToElement(countElementsOfDispayedOnPage);
        return countElementsOfDispayedOnPage.getText();
    }

    public void chooseDeliveryMethod() {
        customMoveToElementAndClick(withDeliveryLabelRadioButton);
    }

    public boolean getStatusRadioButton () {
        customMoveToElement(withDeliveryLabelRadioButton);
        return withDeliveryRadioButton.isSelected();
    }

    public void enterTextToSearchInput (String value) {
        customSendKeysWithWait(searchField, value);
        searchField.sendKeys(Keys.ENTER);
    }

    public String getValueForSearchInput () {
        return searchField.getAttribute("value");
    }

    public void sortByPrice() {
        byPriceSortButton.click();
    }

    public String getClassSortByPrice () {
        return byPriceSortDiv.getAttribute("class");
    }

    public String getNameOfFirstPhone () {
        return fistPhoneLink.getAttribute("title");
    }
}
