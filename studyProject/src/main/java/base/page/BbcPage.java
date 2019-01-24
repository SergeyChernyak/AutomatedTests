package base.page;

import base.conf.InitDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BbcPage extends InitDriver {
    public BbcPage () {
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(css = "#orb-search-q")
    private WebElement field;

    @FindBy(css = "#orb-nav-links li.orb-nav-weather")
    private WebElement weatherButton;

    public BbcPage getUrl (String url) {
        webDriver.get(url);
        return this;
    }

    public BbcPage enterText (String str) throws InterruptedException {
        Thread.sleep(7000);
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
