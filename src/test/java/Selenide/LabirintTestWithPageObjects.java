package Selenide;

import Selenide.component.BookCardComponent;
import Selenide.page_object.CartPage;
import Selenide.page_object.SearchResultPage;
import Selenide.page_object.MainPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Owner("John")// автор автотеста
@Epic("Онлайн магазин книг")// В jira есть Epic, это большая задача, к которой прикрепляются более маленькие
@Feature("Поисковая система")// аннотация фича
@DisplayName("Тест для добавления товаров из поиска")//аннотация названия теста, в т.ч для Allure

public class LabirintTestWithPageObjects {

    MainPage mainPage;
    SearchResultPage searchResultPage;
    CartPage cartPage;

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserPosition = "2500x50";
        Configuration.browserSize = "1024x768";
        Configuration.timeout = 20000;
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage();
        searchResultPage = new SearchResultPage();
        cartPage = new CartPage();
    }

    @Test
    @DisplayName("Добавление товара из поиска")
    @Description("Вводим в поиск слово, находим товар, добавляем в корзину")//аннотация описания теста, в т.ч для Allure
    @Severity(SeverityLevel.BLOCKER)// аннотация важности
    @Link(url = "https://ya.ru", name = "Яндекс")
    @Tag("Позитивный")// Тэгов может быть несколько

    public void testList() {
        cartPage.open();
        cartPage.acceptCookies();
        cartPage.findBook("Java");
        BookCardComponent bookCardComponent = searchResultPage.getBookCardComponent();
        SelenideElement buttonToCart = bookCardComponent.findButton();
        step("Жмем на кнопку Добавить в корзину", () -> buttonToCart.click());
        step("Жмем на кнопку Оформить", () -> {
            bookCardComponent.findButton().shouldHave(text("Оформить"));
            cartPage.attachImage(buttonToCart);
            buttonToCart.click();
        });
        cartPage.attachData("Прикладываем такой вот текст select * from table;");

        cartPage.cartTitle.shouldBe(visible);
        cartPage.cartCounter.shouldHave(text("1"));
    }

    @Test
    public void testThreeButtons() {
        cartPage.open();
        cartPage.acceptCookies();
        cartPage.findBook("Java");

        List<BookCardComponent> bookCardComponents = searchResultPage.getBookCardComponents();
        BookCardComponent bookCardComponent1 = bookCardComponents.get(1);
        bookCardComponent1.findButton().click();
        BookCardComponent bookCardComponent2 = bookCardComponents.get(2);
        bookCardComponent2.findButton().click();
        BookCardComponent bookCardComponent3 = bookCardComponents.get(3);
        bookCardComponent3.findButton().click();
        System.out.println(bookCardComponent2.getTitle());
        System.out.println(bookCardComponent3.getTitle());
    }
}