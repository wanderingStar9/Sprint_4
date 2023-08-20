package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class OrderSecondPageScooter {
    private WebDriver driver;
    // заголовок первой страницы заказа
    private By orderHeaderText = By.className("Order_Header__BZXOb");
    // поле выбора даты доставки заказа
    private By deliveryDatePickField =  By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Когда привезти самокат']");
    // выбранная дата доставки заказа
    private By deliveryDateSelected = By.xpath(".//div[@class='react-datepicker__month-container']//div[contains(@class, 'selected')]");
    // поле выбора срока аренды
    private By rentPeriodSelectField = By.className("Dropdown-placeholder");
    // выбранный срок аренды
    private By orderSecondPageFieldRentalPeriodSublist;
    // первый вариант цввета самоката на выбор
    private By blackScooterColourCheckbox =By.id("black");
    // второй вариант цвета самоката на выбор
    private By grayScooterColourCheckbox =By.id("grey");

    // поле ввода комментария для курьера
    private By commentInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='Комментарий для курьера']");
    // кнопка "Заказать" под формой заказа
    private By orderSecondPageOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // кнопка "Назад"
    private By orderBackButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Назад'");
    // кнопка подтверждениыя заказа
    private By orderModalYesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // кнопка отмены подтверждения заказа
    private By orderModalNoButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Нет'");
    // сообщение о подтверждении заказа
    private By successOrderForm = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");
    // кнопка проверки статуса заказа
    private By checkOrderStatusButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");

    public OrderSecondPageScooter(WebDriver driver){
        this.driver = driver;
    }

    // метод ожидания загрузки второй страницы
    public void waitForLoadOrderSecondPage(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeaderText));
    }

    // метод выбора даты доставки заказа
    public void setDeliveryDatePickField() {
        driver.findElement(deliveryDatePickField).click();
        driver.findElement(deliveryDateSelected).click();
    }

    // метод выбора срока аренды
    public void setRentalPeriodSelectField(String rentalPeriod){
        driver.findElement(rentPeriodSelectField).click();
        orderSecondPageFieldRentalPeriodSublist = By.xpath(".//div[@class='Dropdown-menu']/div[contains(text(),'" + rentalPeriod + "')]");
        driver.findElement(orderSecondPageFieldRentalPeriodSublist).click();
    }
    // метод выбора черного цвета
    public void setBlackScooterColourCheckbox(){
        driver.findElement(blackScooterColourCheckbox).click();
    }
    // метод выбора серого цвета
    public void setGrayScooterColourCheckbox(){
        driver.findElement(grayScooterColourCheckbox).click();
    }
    // метод ввода комментария
    public void setCommentInputField(String comment){
        driver.findElement(commentInputField).sendKeys(comment);
    }
    // метод проверки успешного заказа

    // метод нажатия на кнопку "Заказать"
    public void clickOrderButton(){
        driver.findElement(orderSecondPageOrderButton).isDisplayed();
        driver.findElement(orderSecondPageOrderButton).click();
    }

    // метод нажатия на кнопку "Назад"
    public void clickOrderBackButton(){
        driver.findElement(orderBackButton).isDisplayed();
        driver.findElement(orderBackButton).click();
    }

    // метод нажатия на кнопку подтверждения заказа
    public void clickModalYesButton(){
        driver.findElement(orderModalYesButton).isDisplayed();
        driver.findElement(orderModalYesButton).click();
    }

    // метод нажатия на кнпоку отмены заказа
    public void clickModalNoButton(){
        driver.findElement(orderModalNoButton).isDisplayed();
        driver.findElement(orderModalNoButton).click();
    }

    // метод заполнения полей второй страницы заказа
    public void setDataOrderSecondPage(String rentalPeriod, String colour, String comment) {
        setDeliveryDatePickField();
        setRentalPeriodSelectField(rentalPeriod);
        if(Objects.equals(colour, "black")){setBlackScooterColourCheckbox();}
        else if(Objects.equals(colour, "grey")){setGrayScooterColourCheckbox();}
        setCommentInputField(comment);
    }

    public boolean isOrderSuccessful(){
        return driver.findElement(successOrderForm).isEnabled();
    }

    // метод нажатия кнопки проверки заказа
    public void clickCheckOrderStatusButton() {
        driver.findElement(checkOrderStatusButton).isDisplayed();
        driver.findElement(checkOrderStatusButton).click();
    }

}
