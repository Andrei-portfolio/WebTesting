package LessonsPageObject.ext;

import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.MainPage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static LessonsPageObject.ext.ChromeDriverHelper.DRIVER;
import static LessonsPageObject.ext.ChromeDriverHelper.getDriver;

public class CartPageResolver implements ParameterResolver {// Нужен для того, чтобы мы напрямую прокидывали
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CartPage.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        WebDriver driver = (WebDriver) extensionContext.getStore(ChromeDriverHelper.namespace)
                .getOrComputeIfAbsent(DRIVER, k -> getDriver()); // получи значение из хранилища,
        // либо создай его если его нет
        return new CartPage(driver);
    }
}