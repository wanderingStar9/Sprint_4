import PageObject.HomePageScooter;
import org.hamcrest.MatcherAssert;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class CheckDropDownListTextTest {
    private final String expectedText;
    private final String faqLocatorButton;
    private final String faqLocatorActualText;
    private final String name;
    //private final String URL = "https://qa-scooter.praktikum-services.ru";
    private WebDriver driver;

    public CheckDropDownListTextTest(String name, String locatorButton, String expectedText,
                                     String locatorActualText) {
        this.expectedText = expectedText;
        this.faqLocatorButton = locatorButton;
        this.faqLocatorActualText = locatorActualText;
        this.name = name;
    }

    @Parameterized.Parameters(name = "Тест: {0}")
    public static Object[][] getData() {
        return new Object[][] {
                {"Сколько стоит?", "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "0"},
                {"Заказ нескольких самокатов", "1", "Пока что у нас так: один заказ", "1"},
                {"Расчет времени аренды",  "2", "Допустим, вы оформляете заказ на 8 мая", "2"},
                {"Заказ самоката сегодня", "3", "Только начиная с завтрашнего дня.", "3"},
                {"Проделение заказа", "4", "Пока что нет! Но если что-то срочное", "4"},
                {"Начилие зарядного устройства", "5", "Самокат приезжает к вам с полной зарядкой.", "5"},
                {"Отмена заказа", "6", "Да, пока самокат не привезли.", "6"},
                {"Доставка за МКАД", "7", "Да, обязательно. Всем самокатов!", "7"},
        };
    }

    @Rule
    public DriverRule rule = new DriverRule();

    @Test
    public void checkTextInDropDownList() {
        WebDriver driver = rule.getDriver();
        // создание объекта класса домашней страницы
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);

        // Перейти к разделу "Вопросы о важном"
        objHomePageScooter.turnToFaqList();

        // нажать на стрелку "Сколько это стоит? И как оплатить?"
        objHomePageScooter.clickDropDownList(faqLocatorButton);

        // ожидание загрузки текста
        objHomePageScooter.waitForLoadProfileDataHomePage(faqLocatorActualText);

        // проверить, что в выпадающем списке отображается соответствующий текст
        MatcherAssert.assertThat("Отображается несоответствующий текст", driver.findElement
                (objHomePageScooter.getLocatorActualText(faqLocatorActualText)).getText(), containsString(expectedText));
    }

}
