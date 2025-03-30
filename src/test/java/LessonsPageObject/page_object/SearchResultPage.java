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
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    private final By bookCardComponent = By.cssSelector(".product-card");

    public SearchResultPage(WebDriver driver) {
        super(driver);//это обращение к родительскому  конструктору
    }

    public BookCardComponent getBookCardComponent () {
        WebElement bookCardElement = driver.findElement(bookCardComponent);
        return new BookCardComponent(bookCardElement);
    }

    public List<BookCardComponent> getBookCardComponents () {
        List<BookCardComponent> bookCardComponents = new ArrayList<>();  //Создаём пустой список для наших элементов
        List<WebElement> bookCardElements = driver.findElements(bookCardComponent);// Находим наши 60 элементов на странице

        bookCardElements.forEach( bookCardElement -> bookCardComponents.add(new BookCardComponent(bookCardElement)));

        return bookCardComponents;
    }

    public String getHeaderText() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}


