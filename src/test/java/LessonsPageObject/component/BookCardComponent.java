package LessonsPageObject.component;

/*Page Object and Companents Model это почти тоже что и Page Object Model, но его используют, когда какой-то
элемент встречается на нескольких страницах или вообще на всех. Например, поисковая строка. То здесь всё просто,
создаётся класс, BasePage, в переводе "базовая страница".
Если же элемент встречается не на всех страницах, а только на нескольких, в данном классе создадим Companents Model*/

import LessonsPageObject.page_object.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookCardComponent {

    protected final WebDriver driver;// обращу вниманее, сделали protected, чтобы можно было обратиться

    protected final WebDriverWait wait;

    private final By button = By.xpath("//a[contains(@class, 'buy-link')]");

    public BookCardComponent(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement findButton() {
        return driver.findElement(this.button);
       /*Данный метод нужен только в том случае, если переменная private final By button приватная. Если её сделать
       публичной public, то такого метода не нужно*/
    }

    public void waitButtonChanged() {
        wait.until(ExpectedConditions.textToBe(this.button, "оформить"));
    }
}



