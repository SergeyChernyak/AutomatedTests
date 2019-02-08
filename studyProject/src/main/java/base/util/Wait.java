package base.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Wait {

    private WebDriver webDriver;
    private static final int WAIT_TIME = 10;
    private static final int ZERO_WAIT_TIME = 0;

    public Wait(WebDriver driver) {
        this.webDriver = driver;
    }

    public WebDriverWait createWait (long timeout) {
        return new WebDriverWait(webDriver, timeout);
    }

    public void wait (ExpectedCondition<?> condition, int waitTime) {
        this.createWait(waitTime)
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(WebDriverException.class)
                .until(condition);
    }

    public void visible(WebElement webElement, int waitTime) {
        wait(ExpectedConditions.visibilityOf(webElement), waitTime);
    }

    public void visibleWithInputParamWait(WebElement element, int waitTime) {
        this.visible(element, waitTime);
    }

    public void visibleWithoutInputParamWait(WebElement element) {
        this.visibleWithInputParamWait(element, WAIT_TIME);
    }

    public void visibleAndClickableWithInputParamWait(WebElement webElement, int waitTime) {
        wait(ExpectedConditions.elementToBeClickable(webElement), waitTime);
    }

    public void clickableThenClickWithoutInputParamWait(WebElement webElement) {
        this.visibleAndClickableWithInputParamWait(webElement, WAIT_TIME);
    }

    public void clickWhenVisibleAndClickable (WebElement webElement) {
        this.visibleAndClickableWithInputParamWait(webElement, WAIT_TIME);
        webElement.click();
    }

    void turnOffImplicitWaits() {
        webDriver.manage().timeouts().implicitlyWait(ZERO_WAIT_TIME, TimeUnit.SECONDS);
    }

    void turnOnImplicitWaits() {
        webDriver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
    }

    public void sleep (int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
