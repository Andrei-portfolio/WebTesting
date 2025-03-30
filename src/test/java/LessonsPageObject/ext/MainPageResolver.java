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

import static LessonsPageObject.ext.ChromeDriverHelper.DRIVER;
import static LessonsPageObject.ext.ChromeDriverHelper.getDriver;

public class MainPageResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(MainPage.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        WebDriver driver = (WebDriver) extensionContext.getStore(ChromeDriverHelper.namespace)
                .getOrComputeIfAbsent(DRIVER, k -> getDriver()); // получи значение из хранилища,
                                                                        // либо создай его если его нет

        return new MainPage(driver);
    }
 }
