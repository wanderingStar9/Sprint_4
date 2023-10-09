package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageScooter {
    private WebDriver driver;
    // Логотип "Яндекс"
    private final By YANDEX_LOGO = By.className("Header_LogoYandex__3TSOI");
    // Логотип страницы
    private By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    // Кнопка «Заказать» вверху страницы
    private By orderButtonTop = By.xpath(".//button[@class='Button_Button__ra12g' and (text()='Заказать')]");
    // Кнопка "Статус заказа"
    private By orderStatusButton = By.className("Header_Link__1TAG7");
    // Кнопка "Заказать" внизу страницы
    private By orderButtonMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and (text() = 'Заказать')]");
    // Заголовок раздела "Вопросы о важном"
    private By faqHeader = By.className("Home_SubHeader__zwi_E");
    // Выпадающий список в разделе «Вопросы о важном»
    private By faqList = By.className("Home_FAQ__3uVm4");
    // Кнопка разрешения сохранения куки "да все привыкли"
    private By cookieAcceptButton = By.id("rcc-confirm-button");

    // конструктор страницы
    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания прогрузки домашней страницы
    public void waitForLoadProfileDataHomePage(String locatorActualText){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(faqLineActualText(locatorActualText)));
    }
    // метод нажатия на логотип Яндекса
    public void clickYandexLogo() {
        driver.findElement(YANDEX_LOGO).click();
    }
    // метод нажатия на логотип Самоката
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    // метод нажатия на кнопку "Заказать" вверху страницы
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).isDisplayed();
        driver.findElement(orderButtonTop).click();
    }
    // метод нажатия на кнопку "Заказать" внизу страницы
    public void clickOrderButtonMiddle() {
        WebElement element = driver.findElement(orderButtonMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // общий метод для нажатия кнопки "Заказать"

    public void clickOrderButton(boolean flag) {
        if (flag) {
            driver.findElement(orderButtonTop).click();
        } else {
            WebElement element = driver.findElement(orderButtonMiddle);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }
    }

    // метод нажатия на кнопку "Статус заказа"
    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    // метод нажатия на кнопку "да все приняли"
    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).isDisplayed();
        driver.findElement(cookieAcceptButton).click();
    }

    //метод перехода к блоку "Вопросы о важном"
    public void turnToFaqList () {
        WebElement element = driver.findElement(faqList);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //метод возвращает список локаторов кнопок "Выпадающих списков"
    private By faqButtons (String locatorButton) {
        return By.id("accordion__heading-" + locatorButton);
    }
    //метод возвращает список локаторов элементов, содержащих текст в выпадающем списке
    private By faqLineActualText (String locatorActualText) {
        return (By.xpath(".//div[@id='accordion__panel-" + locatorActualText + "']/p"));
    }
    //метод нажатия на стрелку вниз "Выпадающего списка"
    public void clickDropDownList(String number){
        driver.findElement(faqButtons(number)).click();
    }
    //метод для получения локаторов элементов, содержащих текст в выпадающем списке
    public By getLocatorActualText(String locatorActualText) {
        return faqLineActualText(locatorActualText);
    }

}
