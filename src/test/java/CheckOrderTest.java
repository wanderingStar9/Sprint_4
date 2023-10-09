import Constants.UserTestData;
import PageObject.HomePageScooter;
import PageObject.OrderFirstPageScooter;
import PageObject.OrderSecondPageScooter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CheckOrderTest {
    private WebDriver driver;
    //private final String URL = "https://qa-scooter.praktikum-services.ru";
    private final String rentalPeriod;
    private final String colour;
    private final String comment;
    private final boolean flagButton;

    public CheckOrderTest(boolean flagButton, String rentalPeriod, String colour, String comment) {
        this.flagButton = flagButton;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] makingOrderVariations() {
        return new Object[][] {
                {true, "сутки", "black", "позвонить за час"},
                {false, "двое суток", "grey", "позвонить за 30 минут"},
        };
    }

    @Rule
    public DriverRule rule = new DriverRule();

    @Test
    public void testOrderButton() {
        WebDriver driver = rule.getDriver();
        //создание объекта класса домашней страницы
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        // создание объектов классов страниц заказа
        OrderFirstPageScooter objOrderFirstPageScooter = new OrderFirstPageScooter(driver);
        OrderSecondPageScooter objOrderSecondPageScooter = new OrderSecondPageScooter(driver);
        // создание объекта данных заказчика
        UserTestData objCustomerData = new UserTestData();

        // Нажать на кнопку "Заказать" вверху страницы
        objHomePageScooter.clickOrderButton(flagButton);

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


}
