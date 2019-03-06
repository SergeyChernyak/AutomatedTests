package base.util;

import base.conf.Driver;
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

    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)Driver.getWebDriver()).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) Driver.getWebDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }


    public void waitForAjaxToFinish() {

        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0").equals(true);
            }

        });
    }

    public void waitForJQueryToBeActive() {
        Boolean isJqueryUsed = (Boolean) ((JavascriptExecutor) webDriver)
                .executeScript("return (typeof(jQuery) != 'undefined')");
        if (isJqueryUsed) {
            while (true) {
                // JavaScript test to verify jQuery is active or not
                Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) webDriver)
                        .executeScript("return jQuery.active == 0"));
                if (ajaxIsComplete)
                    break;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void isPresentAlert() {
        wait(ExpectedConditions.alertIsPresent(), WAIT_TIME);
    }

    public void  notVisibility(By by) {
        wait(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)), WAIT_TIME);
    }



    public void notVisible(WebElement webElement, int waitTime) {
        wait(ExpectedConditions.invisibilityOf(webElement),waitTime);
    }

    public void notVisibleWithoutInputParamWait (WebElement webElement) {
        this.notVisible(webElement, WAIT_TIME);
    }

    public void visibleBy (By by, int waitTime) {
        wait(ExpectedConditions.visibilityOfAllElementsLocatedBy(by), waitTime);
    }

    public void visibleByWithoutInputParamWait (By by) {
        this.visibleBy(by, WAIT_TIME);
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
