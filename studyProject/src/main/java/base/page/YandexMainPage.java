package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class YandexMainPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;

    public YandexMainPage (WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = ".geolink__reg")
    private WebElement destinationButton;

    @FindBy(css = ".input__control")
    private WebElement citySearchInput;

    @FindBy(css = ".home-tabs__more-switcher.dropdown2__switcher")
    private WebElement moreDropDownButton;

    @FindBy(css = ".checkbox__control")
    private WebElement findCityCheckBox;

    private By allElementsFromMoreDropDown = By.cssSelector(".home-tabs__more>div>div>a[href]");

    public void changeLocation (String city) {
        customClickWithWait(destinationButton);
        findCityCheckBox.click();
        citySearchInput.clear();
        customSendKeysWithWait(citySearchInput, city);
        citySearchInput.submit();
    }

    public List getList() {
        List<String> valuesOfSomeCity = new ArrayList<>();
        customClickWithWait(moreDropDownButton);
        List<WebElement> elementsOfSomeCity = webDriver.findElements(allElementsFromMoreDropDown);
        for (WebElement e : elementsOfSomeCity)
            valuesOfSomeCity.add(e.getText());
        return valuesOfSomeCity;
    }

}
