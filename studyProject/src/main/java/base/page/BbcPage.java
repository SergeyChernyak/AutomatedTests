package base.page;

import base.conf.InitDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Policy;

public class BbcPage extends InitDriver {

    public BbcPage () {
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//*[@id=\"orb-search-q\"]")
    //for IE .orb-nav-section.orb-nav-search #orb-search-q
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
