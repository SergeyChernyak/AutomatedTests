package base.page;

import base.conf.BasePage;
import base.util.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BbcPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;

    public BbcPage (WebDriver driver, Wait wait) {
        this.webDriver = driver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "#orb-search-q")
    private WebElement searchInput;

    @FindBy(css = "#orb-nav-links li.orb-nav-weather")
    private WebElement weatherButton;

    public BbcPage textEnter(String str) {
        customClickWithWait(searchInput);
        searchInput.clear();
        customSendKeysWithWait(searchInput, str);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public BbcPage clickOnNavigationWeatherButton() {
        customClickWithWait(weatherButton);
        return this;
    }

    public boolean isDisplayedSearchInput() {
        wait.visibleWithoutInputParamWait(searchInput);
        return searchInput.isDisplayed();
    }

    public boolean isEnabledSearchInput() {
        wait.clickableThenClickWithoutInputParamWait(searchInput);
        return searchInput.isEnabled();
    }

    public String returnCurrentUrl() {
        return webDriver.getCurrentUrl();
    }
}
