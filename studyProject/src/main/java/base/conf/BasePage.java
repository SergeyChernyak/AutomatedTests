package base.conf;

import base.util.Wait;
import org.openqa.selenium.WebElement;

public class BasePage {

    private Wait wait = new Wait(Driver.getWebDriver());

    protected void mySendKeysWithWait (WebElement webElement, String string) {
        wait.visibleWithoutInputParamWait(webElement);
        webElement.sendKeys(string);
    }

    protected void myClickWithWait (WebElement webElement) {
        wait.clickWhenVisibleAndClickable(webElement);
    }
}
