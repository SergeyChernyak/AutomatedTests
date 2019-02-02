package base.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BbcPage {

    private WebDriver webDriver;

    public BbcPage (WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "#orb-search-q")
    private WebElement searchInput;

    @FindBy(css = "#orb-nav-links li.orb-nav-weather")
    private WebElement weatherButton;

    public BbcPage textEnter(String str) {
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(str);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public BbcPage clickOnNavigationWeatherButton() {
        weatherButton.click();
        return this;
    }

    public boolean isDisplayedSearchInput() {
        return searchInput.isDisplayed();
    }

    public boolean isEnabledSearchInput() {
        return searchInput.isEnabled();
    }

    public String returnCurrUrl () {
        return webDriver.getCurrentUrl();
    }
}
