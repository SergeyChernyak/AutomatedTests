package base.page;

import base.util.Wait;
import base.conf.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BbcPage {

    private WebDriver webDriver;
    private Wait wait;

    public BbcPage (WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, Driver.IMPLICIT_WAIT), this);
    }

    @FindBy(css = "#orb-search-q")
    private WebElement searchInput;

    @FindBy(css = "#orb-nav-links li.orb-nav-weather")
    private WebElement weatherButton;

    public BbcPage textEnter(String str) {
        wait.clickWhenVisibleAndClickable(searchInput);
        searchInput.clear();
        searchInput.sendKeys(str);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public BbcPage clickOnNavigationWeatherButton() {
        wait.clickWhenVisibleAndClickable(weatherButton);
        return this;
    }

    public boolean isDisplayedSearchInput() {
        wait.visibleWithoutWait(searchInput);
        return searchInput.isDisplayed();
    }

    public boolean isEnabledSearchInput() {
        wait.clickableThenClickWithoutWait(searchInput);
        return searchInput.isEnabled();
    }

    public String returnCurrUrl () {
        return webDriver.getCurrentUrl();
    }
}
