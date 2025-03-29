package LessonsPageObject.page_object;

/*Здесь представлен Page Object на страницу результатов поиска. Подробно про Page Object Model написано
в классе MainPage*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage extends BasePage {

    private final By button = By.xpath("//a[contains(@class, 'btn-tocart')]");

    public SearchResultPage(WebDriver driver) {
        super(driver);//это обращение к родительскому конструктору
    }

    public WebElement findButton() {
        return driver.findElement(this.button);
       /*Данный метод нужен только в том случае, если переменная private final By button приватная. Если её сделать
       публичной public, то такого метода не нужно*/
    }

    public void waitButtonChanged() {
        wait.until(ExpectedConditions.textToBe(this.button, "оформить"));
    }

    public String getHeaderText() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}


