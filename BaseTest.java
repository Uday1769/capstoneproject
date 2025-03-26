package com.demo.base;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void BrowserSetup(String browser) throws Exception {
        WebDriver localDriver;
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            localDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            localDriver = new EdgeDriver();
        } else {
            throw new Exception("Invalid Browser");
        }
        driver.set(localDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver OpenBrowser() {
        WebDriver driverInstance = getDriver();
        driverInstance.get(ConfigReader.getURL("url"));
        driverInstance.manage().window().maximize();
        driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driverInstance;
    }

    public static void CloseBrowser() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
