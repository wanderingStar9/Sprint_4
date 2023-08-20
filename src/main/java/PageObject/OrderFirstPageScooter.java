package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class OrderFirstPageScooter {
    private WebDriver driver;

    // заголовок первой страницы заказа
    private By orderHeaderText = By.className("Order_Header__BZXOb");
    // поле ввода имени в форме заказа
    private By nameInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Имя']");

    // поле ввода фамилии в форме заказа
    private By surnameInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Фамилия']");
    // поле ввода адреса доставки в форме заказа
    private By deliveryAddressInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    // поле выбора станции метро в форме заказа
    private By subwaySelectField = By.className("select-search__input");
    // локатор выбранной станции метро
    private By subwayStationSelected = By.xpath(".//div[@class='select-search__select'][1]");
    // поле ввода номера телефона в форме заказа
    private By phoneNumberInputField = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Телефон: на него позвонит курьер']");
    // кнопка "Далее"
    private By orderNextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");

    public OrderFirstPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    // метод ввода значения в поле "Имя"
    public void setNameInputField (String name){
        driver.findElement(nameInputField).sendKeys(name);
    }
    // метод ввода значения в поле "Фамилия"
    public void setSurnameInputField (String surName){
        driver.findElement(surnameInputField).sendKeys(surName);
    }
    // метод ввода значения в поле "Адрес доставки"
    public void setDeliveryAddressInputField(String address){
        driver.findElement(deliveryAddressInputField).sendKeys(address);
    }

    // метод выбора станции метро
    public void setSubwayStationField(){
        driver.findElement(subwaySelectField).click();
        driver.findElement(subwayStationSelected).click();
    }

    // метод ввода значения "Телефон"
    public void setPhoneNumberInputField(String phoneNumber) {
        driver.findElement(phoneNumberInputField).sendKeys(phoneNumber);
    }

    // метод нажатия кнопки "Далее"
    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).isDisplayed();
        driver.findElement(orderNextButton).click();
    }

    // метод ожидания загрузки первой страницы заказа
    public void waitForLoadOrderFirstPage(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeaderText));
    }

    // метод заполнения полей первой страницы заказа
    public void setDataOrderFirstPage(String name, String surname, String deliveryAddress, String phoneNumber) {
        setNameInputField(name);
        setSurnameInputField(surname);
        setDeliveryAddressInputField(deliveryAddress);
        setSubwayStationField();
        setPhoneNumberInputField(phoneNumber);
        clickOrderNextButton();
    }

}
