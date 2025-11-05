package BasePage.utile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static WebDriver driver;

    /**
     * Возвращает единственный экземпляр драйвера
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            // Для CI/CD можно добавить: --headless, --no-sandbox и др.
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    /**
     * Закрывает драйвер и обнуляет ссылку
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}