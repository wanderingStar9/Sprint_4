import Constants.UserTestData;
import PageObject.HomePageScooter;
import PageObject.OrderFirstPageScooter;
import PageObject.OrderSecondPageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CheckOrderTest {
    private WebDriver driver;
    private final String URL = "https://qa-scooter.praktikum-services.ru";
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    public CheckOrderTest(String rentalPeriod, String colour, String comment) {
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] makingOrderVariations() {
        return new Object[][] {
                {"сутки", "black", "позвонить за час"},
                {"двое суток", "grey", "позвонить за 30 минут"},
        };
    }

    @Before
    public void setUp() {
        //драйвер для браузера Chrome
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        //переход на страницу тестового приложения
        driver.get(URL);
    }

    @Test
    public void testOrderFomHeaderButton() {
        //создание объекта класса домашней страницы
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        // создание объектов классов страниц заказа
        OrderFirstPageScooter objOrderFirstPageScooter = new OrderFirstPageScooter(driver);
        OrderSecondPageScooter objOrderSecondPageScooter = new OrderSecondPageScooter(driver);
        // создание объекта данных заказчика
        UserTestData objCustomerData = new UserTestData();

        // Нажать на кнопку "Заказать" вверху страницы
        objHomePageScooter.clickOrderButtonTop();

        objOrderFirstPageScooter.waitForLoadOrderFirstPage();

        // вызов метода, который заполняет все поля на странице и нажимает кнопку "Далее"
        objOrderFirstPageScooter.setDataOrderFirstPage(objCustomerData.getUSER_NAME(), objCustomerData.getUSER_SURNAME(),
                objCustomerData.getUSER_DELIVERY_ADDRESS(), objCustomerData.getUSER_PHONE_NUMBER());

        objOrderSecondPageScooter.waitForLoadOrderSecondPage();

        // вызов метода, который заполняет все поля на странице и нажимает кнопку "Заказать"
        objOrderSecondPageScooter.setDataOrderSecondPage(rentalPeriod, colour, comment);

        objOrderSecondPageScooter.clickOrderButton();

        // вызов метода, который нажимает на кнопку "Да" во всплывающем окне "Хотите оформить заказ?"
        objOrderSecondPageScooter.clickModalYesButton();

        // проверка, что отображается всплывающее окно "Заказ оформлен"
        assertTrue("Окно не отображается",
                objOrderSecondPageScooter.isOrderSuccessful());
    }

    @Test
    public void testOrderFromMiddleButton() {
        // создание объекта класса домашней страницы
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        // создание объектов классов страниц заказа
        OrderFirstPageScooter objOrderFirstPageScooter = new OrderFirstPageScooter(driver);
        OrderSecondPageScooter objOrderSecondPageScooter = new OrderSecondPageScooter(driver);
        // создание объекта данных заказчика
        UserTestData objCustomerData = new UserTestData();

        // Нажать на кнопку "Заказать" внизу страницы
        objHomePageScooter.clickOrderButtonMiddle();

        objOrderFirstPageScooter.waitForLoadOrderFirstPage();

        // вызов метода, который заполняет все поля на странице и нажимает кнопку "Далее"
        objOrderFirstPageScooter.setDataOrderFirstPage(objCustomerData.getUSER_NAME(), objCustomerData.getUSER_SURNAME(),
                objCustomerData.getUSER_DELIVERY_ADDRESS(), objCustomerData.getUSER_PHONE_NUMBER());

        objOrderSecondPageScooter.waitForLoadOrderSecondPage();

        // вызов метода, который заполняет все поля на странице и нажимает кнопку "Заказать"
        objOrderSecondPageScooter.setDataOrderSecondPage(rentalPeriod, colour, comment);
        objOrderSecondPageScooter.clickOrderButton();

        // вызов метода, который нажимает на кнопку "Да" во всплывающем окне "Хотите оформить заказ?"
        objOrderSecondPageScooter.clickModalYesButton();

        // проверка, что отображается всплывающее окно "Заказ оформлен"
        assertTrue("Окно не отображается",
                objOrderSecondPageScooter.isOrderSuccessful());
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
