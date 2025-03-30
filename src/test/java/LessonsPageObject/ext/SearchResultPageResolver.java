package LessonsPageObject.ext;

import LessonsPageObject.page_object.MainPage;
import LessonsPageObject.page_object.SearchResultPage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;

import static LessonsPageObject.ext.ChromeDriverHelper.DRIVER;
import static LessonsPageObject.ext.ChromeDriverHelper.getDriver;

public class SearchResultPageResolver implements ParameterResolver {// Нужен для того, чтобы мы напрямую прокидывали
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(SearchResultPage.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        WebDriver driver = (WebDriver) extensionContext.getStore(ChromeDriverHelper.namespace)
                .getOrComputeIfAbsent(DRIVER, k -> getDriver()); // получи значение из хранилища,
        // либо создай его если его нет
        return new SearchResultPage(driver);
    }
}
