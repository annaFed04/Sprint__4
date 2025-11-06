package BasePage.utile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static WebDriver driver;
    private static String defaultBrowser = "chrome"; // Браузер по умолчанию


    //Получить драйвер с указанным браузером
    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    ffOptions.addArguments("--start-maximized");
                    driver = new FirefoxDriver(ffOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    //Получить драйвер с браузером по умолчанию (chrome)

    public static WebDriver getDriver() {
        return getDriver(defaultBrowser);
    }


    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}



