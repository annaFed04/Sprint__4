
import BasePage.HomePage;
import BasePage.OrderPage;
import BasePage.utile.AllConfig;
import BasePage.utile.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private OrderPage orderPage;
    private HomePage homePage;

    // Параметры теста (теперь с браузером)
    private final String browser; // "chrome" или "firefox"
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public OrderTest(String browser, String name, String surname, String address, String metro,
                     String phoneNumber, String date, String rentalPeriod,
                     String scooterColor, String comment) {
        this.browser = browser;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тест {0}: {1} {2}")
    public static Collection<Object[]> Data() {
        return Arrays.asList(new Object[][]{
                // Тесты для Chrome
                {"chrome", "Иван", "Царевич", "Болото", "Фрунзенская", "+79188192233", "25.10.2025", "трое суток", "black", "с лягушкой в корыте"},
                {"chrome", "Царевна", "Несмеяная", "Башня", "Владыкино", "+9188192233", "10.11.2025", "семеро суток", "grey", "с доп колесами"},
                // Тесты для Firefox
                {"firefox", "Иван", "Царевич", "Болото", "Фрунзенская", "+79188192233", "25.10.2025", "трое суток", "black", "с лягушкой в корыте"},
                {"firefox", "Царевна", "Несмеяная", "Башня", "Владыкино", "+9188192233", "10.11.2025", "семеро суток", "grey", "с доп колесами"},
        });
    }

    @Before
    public void setUp() {
        // Открытие браузера через DriverFactory
        driver = DriverFactory.getDriver(browser);

        WebDriverWait wait = new WebDriverWait(driver, AllConfig.TIMEOUT_15);

        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);


        homePage.openHomePage();
        homePage.clickCookie();
    }

    @Test
    public void scooterOrderTest() {
        homePage.clickOrderButton();

        orderPage.enterFirstName(name);
        orderPage.enterLastName(surname);
        orderPage.enterFieldAddress(address);
        orderPage.enterStMetro(metro);
        orderPage.enterPhone(phoneNumber);
        orderPage.clickNextButton();
        orderPage.enterDate(date);
        orderPage.enterRentalPeriod(rentalPeriod);
        orderPage.enterBlack(scooterColor.equals("black"));
        orderPage.enterComments(comment);
        orderPage.clickOrderButton();
        orderPage.clickOkButton();
        orderPage.clickConfirm();
    }

    @After
    public void tearDown() {
        // Закрытие браузера через DriverFactory
        DriverFactory.quitDriver();
    }
}