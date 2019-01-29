package base.page;

import base.conf.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BbcPage extends Driver {

    private WebDriver webDriver;

    public BbcPage (WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"orb-search-q\"]")
    private WebElement field;

    @FindBy(css = "#orb-nav-links li.orb-nav-weather")
    private WebElement weatherButton;

    public BbcPage getUrl (String url) {
        webDriver.get(url);
        return this;
    }

    public BbcPage enterText (String str) {
        field.click();
        field.clear();
        field.sendKeys(str);
        field.sendKeys(Keys.ENTER);
        return this;
    }

    public BbcPage clickToButton () {
        weatherButton.click();
        return this;
    }
}
