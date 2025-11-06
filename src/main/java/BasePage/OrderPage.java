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

    // Локаторы полей заказа — ссылки на элементы интерфейса формы заказа
    private final By firstName = By.xpath("//input[@placeholder='* Имя']"); // Поле ввода имени
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']"); // Поле ввода фамилии
    private final By fieldAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); // Поле адреса доставки
    private final By stMetro = By.xpath(".//input[@placeholder='* Станция метро']"); // Поле выбора станции метро
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); // Поле телефона
    private final By nextButton = By.className("Button_Middle__1CSJM"); // Кнопка «Далее»
    private final By dateForm = By.xpath("//input[@placeholder='* Когда привезти самокат']"); // Поле даты доставки
    private final By blackCheckbox = By.cssSelector("label[for='black'] input"); // Чекбокс выбора чёрного самоката
    private final By greyCheckbox = By.cssSelector("label[for='grey'] input"); // Чекбокс выбора серого самоката
    private final By searchComment = By.xpath("//input[@placeholder='Комментарий для курьера']"); // Поле комментария курьеру
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Заказать')]"); // Кнопка «Заказать»
    private final By okButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Да')]"); // Кнопка подтверждения «Да»
    private final By orderConfirm = By.xpath("//div[contains(@class,'Order_ModalHeader__3FDaJ') and contains(text(),'Заказ оформлен')]"); // Элемент с подтверждением оформления заказа


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        // Создаём объект ожидания с таймаутом из конфигурации (15 сек)
        this.wait = new WebDriverWait(driver, AllConfig.TIMEOUT_15);
    }

    // Метод для ожидания появления элемента на странице
    public void waitForElementVisible(By locator) {
        // Ожидает, пока элемент станет видимым на странице
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Методы заполнения полей формы
    public void enterFirstName(String name) {
        // Вводит имя в поле «Имя»
        driver.findElement(firstName).sendKeys(name);
    }

    public void enterLastName(String surname) {
        // Вводит фамилию в поле «Фамилия»
        driver.findElement(lastName).sendKeys(surname);
    }

    public void enterFieldAddress(String address) {
        // Вводит адрес доставки в соответствующее поле
        driver.findElement(fieldAddress).sendKeys(address);
    }

    public void enterStMetro(String metro) {
        // Кликает по полю «Станция метро», чтобы открыть список
        driver.findElement(stMetro).click();

        // Формирует локатор для конкретной станции метро по её названию
        By metroSelect = By.xpath(".//div[@class='select-search__select']//*[text()='" + metro +"']");
        // Ждёт появления станции в списке
        waitForElementVisible(metroSelect);
        // Выбирает станцию метро из списка
        driver.findElement(metroSelect).click();
    }

    public void enterPhone(String phoneNumber) {
        // Вводит номер телефона в поле «Телефон»
        driver.findElement(phone).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        // Нажимает кнопку «Далее» для перехода к следующему шагу оформления
        driver.findElement(nextButton).click();
    }

    public void enterDate(String date) {
        // Вводит дату доставки и подтверждает нажатием Enter
        driver.findElement(dateForm).sendKeys(date, Keys.ENTER);
    }

    public void enterRentalPeriod(String dayRent) {
        // Открывает выпадающий список периода аренды
        WebElement dropdown = driver.findElement(By.className("Dropdown-root"));
        dropdown.click();
        // Ждёт появления нужной опции в списке и выбирает её
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + dayRent + "']")));
        option.click();
    }

    public void enterBlack(boolean colorScooter) {
        // Определяет, какой чекбокс выбрать (чёрный или серый самокат)
        By clickScooterColor = (colorScooter) ? blackCheckbox : greyCheckbox;
        // Ставит галочку на выбранном цвете самоката
        driver.findElement(clickScooterColor).click();
    }

    public void enterComments(String comment) {
        // Вводит комментарий для курьера в соответствующее поле
        driver.findElement(searchComment).sendKeys(comment);
    }

    public void clickOrderButton() {
        // Нажимает кнопку «Заказать» для отправки формы
        driver.findElement(orderButton).click();
    }

    public void clickOkButton() {
        // Подтверждает действие кнопкой «Да»
        driver.findElement(okButton).click();
    }

    public void clickConfirm() {
        // Кликает на элемент подтверждения оформления заказа
        driver.findElement(orderConfirm).click();
    }
}
