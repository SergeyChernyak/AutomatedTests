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

    public List saveToListNameOfLinksLondon() {
        myClickWithWait(destinationButton);
        findCityCheckBox.click();
        citySearchInput.clear();
        citySearchInput.sendKeys("Лондон");
        citySearchInput.submit();
        myClickWithWait(moreDropDownButton);

        By mySelectorLondon = By.cssSelector(".home-tabs__more>div>div>a[href]");
        List<WebElement> elementsLondon = webDriver.findElements(mySelectorLondon);
        List<String> valuesLondon = new ArrayList<>();
        for (WebElement e : elementsLondon)
            valuesLondon.add(e.getText());
        return valuesLondon;
    }

    public List saveToListNameOfLinksParis () {
        myClickWithWait(destinationButton);
        findCityCheckBox.click();
        citySearchInput.clear();
        citySearchInput.sendKeys("Париж");
        citySearchInput.submit();
        myClickWithWait(moreDropDownButton);

        By mySelectorParis = By.cssSelector(".home-tabs__more>div>div>a[href]");
        List<WebElement> elementsParis = webDriver.findElements(mySelectorParis);
        List<String> valuesParis = new ArrayList<>();
        for (WebElement e : elementsParis)
            valuesParis.add(e.getText());
        return valuesParis;
    }
}
