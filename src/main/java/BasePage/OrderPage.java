package BasePage;

import BasePage.utile.AllConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы полей заказа
    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By fieldAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.className("Button_Middle__1CSJM");
    private final By dateForm = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By blackCheckbox = By.cssSelector("label[for='black'] input");
    private final By greyCheckbox = By.cssSelector("label[for='grey'] input");
    private final By searchComment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Заказать')]");
    private final By okButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Да')]");
    private final By orderConfirm = By.xpath("//div[contains(@class,'Order_ModalHeader__3FDaJ') and contains(text(),'Заказ оформлен')]");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        // Используем Duration из AllConfig
        this.wait = new WebDriverWait(driver, AllConfig.TIMEOUT_15);

    }
    //метод для ожидания появления элемента
    public void waitForElementVisible(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Методы заполнения полей формы
    public void enterFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    public void enterLastName(String surname) {
        driver.findElement(lastName).sendKeys(surname);
    }

    public void enterFieldAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
    }

    public void enterStMetro(String metro) {
        driver.findElement(stMetro).click();

        By metroSelect = By.xpath(".//div[@class='select-search__select']//*[text()='" + metro +"']");
        waitForElementVisible(metroSelect);
        driver.findElement(metroSelect).click();
    }

    public void enterPhone(String phoneNumber) {
        driver.findElement(phone).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void enterDate(String date) {
        driver.findElement(dateForm).sendKeys(date, Keys.ENTER);
    }

    public void enterRentalPeriod(String dayRent) {
        WebElement dropdown = driver.findElement(By.className("Dropdown-root"));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + dayRent + "']")));
        option.click();
    }

    public void enterBlack(boolean colorScooter) {
        By clickScooterColor = (colorScooter) ? blackCheckbox : greyCheckbox;
        driver.findElement(clickScooterColor).click();
    }

    public void enterComments(String comment) {
        driver.findElement(searchComment).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickOkButton() {
        driver.findElement(okButton).click();
    }

    public void clickConfirm() {
        driver.findElement(orderConfirm).click();
    }

    // Метод заполнения всей формы
    public void enterAllForm(String name, String surname, String address, String metro,
                             String phoneNumber, String date, boolean colorScooter, String dayRent, String comment) {
        enterFirstName(name);
        enterLastName(surname);
        enterFieldAddress(address);
        enterStMetro(metro);
        enterPhone(phoneNumber);
        enterDate(date);
        enterRentalPeriod(dayRent);
        enterComments(comment);
        enterBlack(colorScooter);
    }
}
