package base.conf;

import base.util.Wait;
import org.openqa.selenium.WebElement;

public class BasePage {

    private Wait wait = new Wait(Driver.getWebDriver());

    protected void customSendKeysWithWait(WebElement webElement, String string) {
        wait.visibleWithoutInputParamWait(webElement);
        webElement.sendKeys(string);
    }

    protected void customClickWithWait(WebElement webElement) {
        wait.clickWhenVisibleAndClickable(webElement);
    }
}
