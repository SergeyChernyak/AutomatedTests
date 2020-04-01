package base.conf;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import base.util.Wait;

public class BasePage {

    private Wait wait = new Wait(Driver.getWebDriver());
    private Actions actions = new Actions(Driver.getWebDriver());
    private JavascriptExecutor executor = (JavascriptExecutor)Driver.getWebDriver();

    protected void sendKeysWithWait(WebElement webElement, String string) {
        wait.visibleWithoutInputParamWait(webElement);
        webElement.clear();
        webElement.sendKeys(string);
    }

    protected void clickWithWait(WebElement webElement) {
        wait.clickWhenVisibleAndClickable(webElement);
    }

    protected void clickActon (WebElement webElement) {
        actions.click(webElement).perform();
    }

    protected void sendKeysAction (WebElement webElement, String string) {
        webElement.clear();
        actions.sendKeys(webElement, string).perform();
    }

    protected void clickJS (WebElement webElement) {
        executor.executeScript("arguments[0].click();", webElement);
    }

    protected void sendKeysJS (WebElement webElement, String string) {
        executor.executeScript("var input = Array.prototype.slice.call(arguments)[0];" +
                "var lastValue = input.value;\n" +
                "input.value = '"+ string +"';\n" +
                "var event = new Event('input', { bubbles: true });\n" +
                "event.simulated = true;\n" +
                "var tracker = input._valueTracker;\n" +
                "if (tracker) {\n" +
                "  tracker.setValue(lastValue);\n" +
                "}\n" +
                "input.dispatchEvent(event)", webElement);
    }

    protected void moveToElement(WebElement webElement) {
        actions.moveToElement(webElement).perform();
    }

    protected void moveToElementAndClick(WebElement webElement) {
        moveToElement(webElement);
        webElement.click();
    }
}
