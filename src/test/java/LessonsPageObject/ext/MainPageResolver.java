package LessonsPageObject.ext;


import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.MainPage;
import LessonsPageObject.page_object.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageResolver implements ParameterResolver, BeforeEachCallback, AfterEachCallback {// Нужен для того, чтобы мы напрямую прокидывали

    static Namespace namespace = ExtensionContext.Namespace.create("my_store");

    private WebDriver driver;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(MainPage.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        extensionContext.getStore(namespace).put("driver", driver);// В это хранилище
        //ложим объект, например driver. Что за хранилище? Это типо map с параметрами ключ, значение

        return new MainPage(driver);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
            driver.manage().window().setPosition(new Point(2500, 50));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        }
    }
