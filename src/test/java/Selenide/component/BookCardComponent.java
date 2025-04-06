package Selenide.component;

/*Page Object and Companents Model это почти тоже что и Page Object Model, но его используют, когда какой-то
элемент встречается на нескольких страницах или вообще на всех. Например, поисковая строка. То здесь всё просто,
создаётся класс, BasePage, в переводе "базовая страница".
Если же элемент встречается не на всех страницах, а только на нескольких, в данном классе создадим Companents Model*/

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.text;

public class BookCardComponent {

    private final SelenideElement element;

    private final By button = By.cssSelector(".btn-tocart");

    private final By title = By.cssSelector(".product-card__name");


    public BookCardComponent(SelenideElement element) {
        this.element = element;
    }

    public SelenideElement findButton() {
        return element.find(this.button);
    }

    public String getTitle() {
        return element.find(this.title).getText();
    }

}

