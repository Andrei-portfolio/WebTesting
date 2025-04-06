package Selenide.page_object;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;

/*ВАЖНО!!! У Page Object Model есть ответвление Page Object and Companents Model, т.е. страницы и компаненты.
В чём суть. Если про плюсы Page Object Model, то
- с его помощью можно использовать код повторно;
- наглядно видно, что есть в нашем методе ещё.
ВАЖНО!!! Page Object and Companents Model это почти тоже что и Page Object Model, но его используют, когда какой-то
элемент встречается на нескольких страницах или вообще на всех. Например, поисковая строка. То здесь всё просто,
создаётся класс, BasePage, в переводе "базовая страница". И туда кидаем всё общее, что есть на всех страницах.

ОЧЕНЬ ВАЖНО!!! Делаем данный класс public ABSTRACT class BasePage. Для чего же делаем abstract. Этот класс BasePage
не должен быть, т.е. не должно быть экземпляров класса BasePage. Потому что BasePage, это "базовая страница.
Это БАЗА, ШАБЛОН, ОСНОВА, это не реальная страница, её не существует, поэтому делаем её абстрактной.

ТЕПЕРЬ ВСЕ КЛАССЫ НАСЛЕДУЮТСЯ ОТ BasePage. А для этого к каждому из классов page_object (их названию)
припишем extends BasePage*/

public abstract class BasePage {

    //private final By searchField = By.xpath("//input[@id='search-field']");
    /*Закомитили выше, так как здесь в данном классе приведём пример использования аннотации FindBy
    Аннотация, благодаря которой можно раз и навсегда заменить метод findElement (поиск элементов) в автотестах.
    Что делает данная аннотация? Ничего не делает, пока мы в кострукторе не пропишем
    PageFactory.initElements(driver, this). Как результат, мы теперь не будем писать driver.findElement и заменим
    его */

    public SelenideElement searchField = $x("//input[@id='search-field']");// данный код нуже при создании аннотации @FindBy

   @Step("Найти книгу {name}")// Данная аннотация, позволяеть  увидеть данный комментарий в allure отчёте, если этот
                               // метод используется в автотесте. И если автотест полетит, то будет видно, на каком шаге
    public void findBook(String name) {
        searchField.setValue(name).pressEnter();
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)
    }

    @Attachment(value = "data", type = "text/plain", fileExtension = ".txt")//Атачмент для текста
    public String attachData(String text) {
        return text;
    }

    @Attachment(value = "data", type = "image/png", fileExtension = ".png")// Аттачмент для картинки
    public byte[] attachImage(SelenideElement element) {
        return element.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Несовпадение пикселей", type = "image/png", fileExtension = ".png")
    public byte[] attachPng(byte[] image) {
        return image;
    }

    public void acceptCookies(){
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));
        refresh();
    }
}
