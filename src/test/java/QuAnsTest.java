import BasePage.HomePage;
import BasePage.utile.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuAnsTest {
    private final int index;
    private final String questionText;
    private final String answerText;


    public QuAnsTest(int index, String questionText, String answerText) {
        this.index = index;
        this.questionText = questionText;
        this.answerText = answerText;
    }

    @Parameterized.Parameters(name = "Тест {0}: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        });
    }


    private WebDriver driver;
    private HomePage homePage;


    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickCookie();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testQuAnsOk() {
        try {
            homePage.locatorFaq(index, questionText, answerText);
            System.out.println("Тест пройден успешно для вопроса №" + index + ": " + questionText);
        } catch (AssertionError e) {
            System.err.println("Ошибка проверки FAQ для вопроса №" + index + ": " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка при тестировании вопроса №" + index + ": " + e.getMessage());
            throw new RuntimeException("Тест не удалось выполнить", e);
        }
    }

}