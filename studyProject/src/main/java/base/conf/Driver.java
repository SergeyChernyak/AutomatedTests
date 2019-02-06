package base.conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver webDriver;
    public static final int IMPLICIT_WAIT = 10;

    @Parameters("browser")
    private static WebDriver getDriver(String browser) {
        if (Objects.isNull(webDriver)) {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
                    webDriver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
                    webDriver = new FirefoxDriver();
                    break;
                case "ie":
                    System.setProperty("webdriver.ie.driver", "./src/main/resources/drivers/IEDriverServer.exe");
                    webDriver = new InternetExplorerDriver();
                    break;
            }
        if (Objects.nonNull(webDriver)) {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
            }
        }
        return webDriver;
    }

    public static WebDriver getWebDriver() {
        return Objects.isNull(webDriver) ? getDriver("chrome") : webDriver;
    }

    public static void closeDriver() {
        if (Objects.nonNull(webDriver)) {
            webDriver.quit();
        }
    }
}
