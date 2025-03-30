package LessonsPageObject.ext;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/*В этом классе у нас будет всё, что связано с driver и его созданием. Это создать, положить в хранилище и т.д. Т.е.
 всё что было в MainPageResolver*/

public class ChromeDriverHelper implements AfterEachCallback {// Нужен для того, чтобы мы напрямую прокидывали

    public static ExtensionContext.Namespace namespace = ExtensionContext.Namespace.create("my_store");// Создали хранилище my_store

    public static final String DRIVER = "driver";

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        WebDriver driver = (WebDriver) context.getStore(ChromeDriverHelper.namespace)
                .get(DRIVER); // получи значение из хранилища,
        // либо создай его если его нет

        if (driver != null) {
            driver.quit();
            context.getStore(namespace).remove(DRIVER);
        }
    }

    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        return driver;

    }
}