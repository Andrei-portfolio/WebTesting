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

    private final WebElement element;

    private final By button = By.cssSelector(".btn-tocart");

    private final By title = By.cssSelector(".product-card__name");

    /*В пределах одного класса поиск элементов необходимо делать только одним локатором, например, только
    cssSelector или x-patch*/

    public BookCardComponent(WebElement element) {
    this.element = element;
    }

    public WebElement findButton() {
        return element.findElement(this.button);//
       /*ОЧЕНЬ ВАЖНО!!! Говорим ищи не на всей странице, а от какой-то области или родителя,
       что снижает риск получить кучу всяких hidden элементов*/
    }

    public String getTitle() {
        return element.findElement(this.title).getText();//
       /*ОЧЕНЬ ВАЖНО!!! Говорим ищи не на всей странице, а от какой-то области или родителя,
       что снижает риск получить кучу всяких hidden элементов*/
    }

    public void waitButtonChanged(WebDriverWait wait) {
        wait.until(ExpectedConditions.textToBe(this.button, "оформить"));
    }
}



