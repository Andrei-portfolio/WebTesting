package LessonsPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import LessonsPageObject.ext.CartPageResolver;
import LessonsPageObject.ext.ChromeDriverHelper;
import LessonsPageObject.ext.MainPageResolver;
import LessonsPageObject.ext.SearchResultPageResolver;
import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.MainPage;
import LessonsPageObject.page_object.SearchResultPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


/*В данном классе представлено с сокращением кода, с применением Resolver из пакета ext. Если есть желание
посмотреть более объёмный код без применения Resolver, то он представлен в классе LabirintTestTwo*/

@ExtendWith(MainPageResolver.class)
@ExtendWith(CartPageResolver.class)
@ExtendWith(SearchResultPageResolver.class)
@ExtendWith(ChromeDriverHelper.class)

public class LabirintTest {

    @Test
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

        WebElement buttonToCart = searchResultPage.bookCardComponent.findButton();//Находим нужную нам кнопку на странице
        buttonToCart.click();//кликаем на кнопку и у нас товар добавляется в корзину и ч/з какое то время происх. замена
        // наименования кнопки "В корзину" на "Оформить".
        // Вот далее и НУЖНА ЗАДЕРЖКА
        searchResultPage.bookCardComponent.waitButtonChanged();// wait - явное ожидание, пока наша кнопка не
        // переименуется в оформить вынесли в отдельный метод waitButtonChanged в классе searchResultPage
        buttonToCart.click();
        assertTrue(cartPage.driver.findElement(cartPage.cartTitle).isDisplayed());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ ЕСТЬ НАШ ЭЛЕМЕНТ.
        //говорим, driver найди элемент cartTitle и проверь, что он есть в корзине
        assertEquals("1", cartPage.driver.findElement(cartPage.cartCounter).getText());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ
        // у нас один товар, который мы положили
    }
    /*
    Тест, заключается в следующем. Заходим на сайт https://www.labirint.ru/, в поисковую строку вводим поиск книг
    по java в названии. Далее наводим мышку на кнопку в корзину. И тут ловушка, кнопка через какое-то время
    переименовывается в "Оформить". Нам нужно поймать этот момент
    */
}