package LessonsPageObject.page_object;

/*Здесь представлен Page Object на страницу результатов поиска. Подробно про Page Object Model написано
в классе MainPage*/

import LessonsPageObject.component.BookCardComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage extends BasePage {

    public BookCardComponent bookCardComponent;

    public SearchResultPage(WebDriver driver) {
        super(driver);//это обращение к родительскому конструктору
        bookCardComponent = new BookCardComponent(driver, wait);
    }

    public String getHeaderText() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}


