package LessonsPageObject;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import LessonsPageObject.component.BookCardComponent;
import LessonsPageObject.ext.CartPageResolver;
import LessonsPageObject.ext.ChromeDriverHelper;
import LessonsPageObject.ext.MainPageResolver;
import LessonsPageObject.ext.SearchResultPageResolver;
import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.MainPage;
import LessonsPageObject.page_object.SearchResultPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


/*В данном классе представлено с сокращением кода, с применением Resolver из пакета ext. Если есть желание
посмотреть более объёмный код без применения Resolver, то он представлен в классе LabirintTestTwo*/

@ExtendWith(MainPageResolver.class)
@ExtendWith(CartPageResolver.class)
@ExtendWith(SearchResultPageResolver.class)
@ExtendWith(ChromeDriverHelper.class)

//Более подробно про аннотации для allure по ссылке https://allurereport.org/docs/junit5-reference/
@Owner("John")// автор автотеста
@Link(url = "https://ya.ru", name = "Яндекс")// если хотим прикрепить, например ссылку к задаче
@Epic("Онлайн магазин книг")// В jira есть Epic, это большая задача, к которой прикрепляются более маленькие
@Feature("Поисковая система")// аннотация фича

public class LabirintTest {

    @Test
    @DisplayName("Добавление товаров из поиска")//аннотация названия теста, в т.ч для Allure
    @Description("Выводим в поиск слово, находим товар, добавляем в корзину")//аннотация описания теста, в т.ч для Allure
    @Severity(SeverityLevel.BLOCKER)// аннотация важности
    @Tag("Позитивный")// Тэгов может быть несколько
    //ТЕСТ по началу НЕ ОТРАБАТЫВАЛ. Оказалось, что из-за КУКОВ. После того, как в данном классе в методе openMainPage
    // прописали, что после захода на сайт, нужно принять КУКИ. То кейс успешно отработал. Добавили про КУКИ именно
    // в данный метод, т.к. это логично, что после захода на страницу, след. шагом сначала принять КУКИ. Более
    // подробно про куки расписано в классе FirstTest
    public void testList(SearchResultPage searchResultPage, CartPage cartPage) {//Применили наши Resolver из пакета exp
        cartPage.open();// вынесли в MaimPage, объявили данный класс mainPage. Говорим открой главную страницу
        cartPage.driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        cartPage.driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
        cartPage.findBook("Java");// вынесли в MaimPage, объявили данный класс mainPage.
        // строка с кодом была длинная, поэтому его вынесли в отдельный метод findBook()

        BookCardComponent bookCardComponent = searchResultPage.getBookCardComponent();
        WebElement buttonToCart = bookCardComponent.findButton();//Находим нужную нам кнопку на странице
        step("Жмёи на кнопку Добавить в корзину", () -> {buttonToCart.click();});// Чтобы код строчкой ниже,
        // отобразился в шагах в allure, нужен второй вариант использования Step, т.к. это уже не наш метод,
        // а интерфеса. Мы не сможем там ничего написать. Поэтому мы пишем Step в самом тесте. Напомню, что () ->
        // это лямбда функция
        step("Жмёи на кнопку Оформить", () -> {bookCardComponent.waitButtonChanged(cartPage.wait);
            cartPage.attachImage(buttonToCart);//Атачмент для картинки, который должен сфоткать кнопку в отчёте Allure
            buttonToCart.click();// См. комментарий в коде выше, для чего мы поставили здесь step
        });

        //buttonToCart.click();//кликаем на кнопку и у нас товар добавляется в корзину и ч/з какое то время происх. замена
        // наименования кнопки "В корзину" на "Оформить". ЗАКОМИТИЛ, т.к. для отчетов Allure перед данным кодом
        // необходимо поставить step. Я это сделал в коде выше

        // Вот далее и НУЖНА ЗАДЕРЖКА
        //bookCardComponent.waitButtonChanged(cartPage.wait);// wait - явное ожидание, пока наша кнопка не
        // переименуется в оформить вынесли в отдельный метод waitButtonChanged в классе searchResultPage

        cartPage.attachData("Прикладываем такой вот текст select * from table");// Атачмент для текста

        assertTrue(cartPage.cartTitle.isDisplayed());// Сократили код за счёт аннотации @FindBy в классе CartPage.
        // ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ ЕСТЬ НАШ ЭЛЕМЕНТ. Говорим, driver найди элемент cartTitle и проверь,
        // что он есть в корзине
        assertEquals("1", cartPage.driver.findElement(cartPage.cartCounter).getText());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ
        // у нас один товар, который мы положили
    }
    /*
    Тест, заключается в следующем. Заходим на сайт https://www.labirint.ru/, в поисковую строку вводим поиск книг
    по java в названии. Далее наводим мышку на кнопку в корзину. И тут ловушка, кнопка через какое-то время
    переименовывается в "Оформить". Нам нужно поймать этот момент
    */

    @Test
    public void testThreeButtons (SearchResultPage searchResultPage, CartPage cartPage) {//Применили наши Resolver из пакета exp
        cartPage.open();// вынесли в MaimPage, объявили данный класс mainPage. Говорим открой главную страницу
        cartPage.driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        cartPage.driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
        cartPage.findBook("Java");// вынесли в MaimPage, объявили данный класс mainPage.
        // строка с кодом была длинная, поэтому его вынесли в отдельный метод findBook()

        List<BookCardComponent> bookCardComponents = searchResultPage.getBookCardComponents();
        /*WebElement buttonToCart = bookCardComponents.get(1).findButton();//Находим нужную нам кнопку на странице
        buttonToCart.click();//кликаем на кнопку и у нас товар добавляется в корзину и ч/з какое то время происх. замена
        // наименования кнопки "В корзину" на "Оформить".
        WebElement buttonToCart2 = bookCardComponents.get(2).findButton();//Находим вторую нужную нам кнопку на странице
        buttonToCart2.click();
        WebElement buttonToCart3 = bookCardComponents.get(3).findButton();//Находим третью нужную нам кнопку на странице
        buttonToCart3.click();
        System.out.println(bookCardComponents.get(0).getTitle());
        System.out.println(bookCardComponents.get(1).getTitle());
        System.out.println(bookCardComponents.get(2).getTitle());*/

        BookCardComponent bookCardComponent1 = bookCardComponents.get(1);
        bookCardComponent1.findButton().click();
        BookCardComponent bookCardComponent2 = bookCardComponents.get(2);
        bookCardComponent2.findButton().click();
        BookCardComponent bookCardComponent3 = bookCardComponents.get(3);
        bookCardComponent3.findButton().click();
        System.out.println(bookCardComponent2.getTitle());
        System.out.println(bookCardComponent3.getTitle());
    }

    /*В данном тесте нам необходимо обратиться сразу к нескольким товарам на странице*/

}
