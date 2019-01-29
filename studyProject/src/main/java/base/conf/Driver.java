package base.conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

    protected static WebDriver webDriver;

    protected WebDriver getDriver(String browser) {
        if (webDriver == null) {
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
            webDriver.manage().window().maximize();
        }
            return webDriver;
    }
}
