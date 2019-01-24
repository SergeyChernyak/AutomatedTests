package base.conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class InitDriver {

    protected static WebDriver webDriver;


    @Parameters("browser")
    @BeforeTest
    protected void initDriver (String browser) {
        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
            webDriver = new ChromeDriver();
        } else
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        } else
        if (browser.equals("ie")) {
            System.setProperty("webdriver.ie.driver", "./src/main/resources/IEDriverServer.exe");
            webDriver = new InternetExplorerDriver();
        }
            webDriver.manage().window().maximize();
    }

    @AfterTest
    protected void closeDriver () {
        if (webDriver != null)
            webDriver.quit();
    }
}
