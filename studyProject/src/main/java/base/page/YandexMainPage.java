package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    private WebElement cityInput;

    @FindBy(css = ".home-tabs__more-switcher.dropdown2__switcher")
    private WebElement moreDropDown;



}
