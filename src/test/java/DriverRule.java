import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverRule extends ExternalResource {
    private WebDriver driver;
    private final String URL = "https://qa-scooter.praktikum-services.ru";

    @Override
    protected void before() throws Throwable {
        //драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //переход на страницу тестового приложения
        driver.get(URL);
    }

    @Override
    protected void after() {
        // Закрытие браузера
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
