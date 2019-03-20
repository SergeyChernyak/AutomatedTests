package base.conf;

import base.util.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {

    private Wait wait = new Wait(Driver.getWebDriver());
    private Actions actions = new Actions(Driver.getWebDriver());

    protected void sendKeysWithWait(WebElement webElement, String string) {
        wait.visibleWithoutInputParamWait(webElement);
        webElement.clear();
        webElement.sendKeys(string);
    }

    protected void clickWithWait(WebElement webElement) {
        wait.clickWhenVisibleAndClickable(webElement);
    }

    protected void moveToElement(WebElement webElement) {
        actions.moveToElement(webElement).perform();
    }

    protected void moveToElementAndClick(WebElement webElement) {
        moveToElement(webElement);
        webElement.click();
    }
}
