package BasePage;

import BasePage.utile.AllConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String url = AllConfig.BASE_URL;

    // Локаторы
    private final By locatorUpButton = By.xpath(".//div[contains(@class,'Header')]/button[text()='Заказать']");
    private final By locatorDowmButton = By.xpath(".//div[contains(@class,'Home')]/button[text()='Заказать']");
    private final By locatorQuestions = By.cssSelector("div[id^='accordion__heading-']");
    private final By locatorAnswers = By.cssSelector("div[id^='accordion__panel-']");
    private final By locatorButtonCookie = By.cssSelector(".App_CookieButton__3cvqF");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, AllConfig.TIMEOUT_10);
    }

    public void openHomePage() {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
    }

    public void clickCookie() {
        try {
            WebElement cookieButton = wait.until(
                    ExpectedConditions.elementToBeClickable(locatorButtonCookie));
            cookieButton.click();
        } catch (Exception e) {
            System.err.println("Кнопка куки не найдена или уже скрыта: " + e.getMessage());
        }
    }

    public void clickOrderButton() {
        for (By locator : List.of(locatorUpButton, locatorDowmButton)) {
            try {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
                button.click();
                return;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Кнопка 'Заказать' не найдена");
    }

    /**
     * Проверяет вопрос и ответ в блоке FAQ по индексу
     * @param index индекс элемента в списке вопросов/ответов
     * @param expectedQuestion ожидаемый текст вопроса
     * @param expectedAnswer ожидаемый текст ответа
     */
    public void locatorFaq(int index, String expectedQuestion, String expectedAnswer) {
        // Ждём видимости вопроса и получаем его
        List<WebElement> questions = driver.findElements(locatorQuestions);
        WebElement question = wait.until(ExpectedConditions.visibilityOf(questions.get(index)));

        // Сравниваем текст вопроса
        String actualQuestion = question.getText();
        org.junit.Assert.assertEquals(
                "Текст вопроса не совпадает", expectedQuestion, actualQuestion);

        // Кликаем для раскрытия ответа
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", question);

        // Ждём видимости ответа и проверяем текст
        List<WebElement> answers = driver.findElements(locatorAnswers);
        WebElement answer = wait.until(ExpectedConditions.visibilityOf(answers.get(index)));
        String actualAnswer = answer.getText();
        org.junit.Assert.assertEquals(
                "Текст ответа не совпадает", expectedAnswer, actualAnswer);
    }
}
