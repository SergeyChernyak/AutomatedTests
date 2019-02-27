package base.conf;

import base.util.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {

    private Wait wait = new Wait(Driver.getWebDriver());
    private Actions actions = new Actions(Driver.getWebDriver());

    protected void customSendKeysWithWait(WebElement webElement, String string) {
        wait.visibleWithoutInputParamWait(webElement);
        webElement.sendKeys(string);
    }

    protected void customClickWithWait(WebElement webElement) {
        wait.clickWhenVisibleAndClickable(webElement);
    }

    protected void customMoveToElement (WebElement webElement) {
        actions.moveToElement(webElement).perform();
    }

    protected void customMoveToElementAndClick (WebElement webElement) {
        actions.moveToElement(webElement).perform();
        webElement.click();
    }
}
