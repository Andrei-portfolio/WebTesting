package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BrowserPerTestStrategyExtension.class)// ВАЖНО с помощью данного кода, после выполнения каждого
// автотеста данного из этого класса, закрывается браузер. Иначе, если запустить тесты без этого кода,
//отработает только первый, а второй упадёт. Т.к. второй автотест будет работать не с нового браузера,
//а будет продолжать с браузером из 1го автотеста. И получиться путаница, т.к. мы уже будем в корзине

// ВАЖНО!!! Данные автотесты такие же, как и в пакете page_object в классе LabirintTest, но здесь они выполнены с помощью
//selenide, а не selenium.

public class LabirintTest  {

@BeforeAll
public static void beforeAll() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    Configuration.pageLoadStrategy = "eager";
    Configuration.browser = "firefox";
    Configuration.browserPosition = "2500x50";
    Configuration.browserSize = "1024x768";
    Configuration.timeout = 20000;
    Configuration.headless = false;// запуск без UI браузера (безголовый режим). Быстрее тесты выполняются,
    // меньше ресурсов системы используется
}

@Test
public void testAddButton() {
    SelenideElement buttonToCart = $x("//a[contains(@class, 'btn-tocart')]");// Здесь создаётся скрытый
    // пустой элемент - обёртка, поэтому тест не падает, как падал бы в selenium. Когда мы обращаемся к этому
    // элементу, например говоря buttonToCart.click(); То эта пустая обёртка выполняет метод find element. И
    // вот именно в этот момент. Такая хитрость

    open("https://www.labirint.ru/");
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем наши куки
    refresh();
    $x("//input[@id='search-field']").type("Java").pressEnter();// Ищем элемент по xpath
    buttonToCart.click();
    buttonToCart.shouldHave(text("Оформить")).click();// говорим, когда кнопка будет иметь текст "Оформить", нажми её

    /*Тест, заключается в следующем. Заходим на сайт https://www.labirint.ru/, в поисковую строку вводим поиск книг
    по java в названии. Далее наводим мышку на кнопку в корзину. И тут ловушка, кнопка через какое-то время
    переименовывается в "Оформить". Нам нужно дождаться этот момент и нажать на эту кнопку*/

}

@Test
public void testAddButtons() {
    ElementsCollection buttonsList = $$x("//a[contains(@class, 'btn-tocart')]");// В данном случае $$x это поиск неск. элементов
    SelenideElement counter = $(".basket-in-cart-a");

    open("https://www.labirint.ru/");
    WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем наши куки
    refresh();
    $x("//input[@id='search-field']").type("Java").pressEnter();
    buttonsList.get(0).click();
    buttonsList.get(1).click();
    buttonsList.get(2).click();
    buttonsList.get(2).shouldHave(text("Оформить")).click();// Ждем пока get(2) будет иметь запись "Оформить", после этого попадаем в корзину
    counter.shouldHave(text("3"));
    }
    /*В данном тесте нам необходимо обратиться сразу к нескольким товарам на странице. Т.е. добавим сразу три книги в корзину*/
}

