package LessonsPageObject;

import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.MainPage;
import LessonsPageObject.page_object.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*В данном классе представлен более объёмный код, без примененич Resolver из пакета ext. Если есть желание
посмотреть более сокращённый код с применением Resolver, то он представлен в классе LabirintTest*/

/*public class LabirintTestTwo {

    private WebDriver driver;

    private WebDriverWait wait;

    private MainPage mainPage;// Объявили данный класс, после того, как перенесли наши методы open() и findBook("Java")
    // в класс MainPage

    private SearchResultPage searchResultPage;// Аналогично, как и выше объявляли, создаем экземпляр класса для
    // SearchResultPage, после того, как перенесли в него всё необходимое с данного класса

    private CartPage cartPage;//Аналогично, как и выше объявляли, создаем экземпляр класса для
    // CartPage, после того, как перенесли в него всё необходимое с данного класса

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
        mainPage = new MainPage(driver);// прописали после того, как объявили данный класс выше и приняли драйвер
        //в классе MainPage. Объявили свой драйвер
        searchResultPage = new SearchResultPage(driver);//прописали аналогично, как и в строке выше, но только
        // для класс SearchResultPage
        cartPage = new CartPage(driver);//Как и в двух строках выше, прописали данный код, после того, как
        // объявили данный класс выше и приняли драйвер в классе CartPag. Объявили свой драйвер
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    //ТЕСТ по началу НЕ ОТРАБАТЫВАЛ. Оказалось, что из-за КУКОВ. После того, как в данном классе в методе openMainPage
    // прописали, что после захода на сайт, нужно принять КУКИ. То кейс успешно отработал. Добавили про КУКИ именно
    // в данный метод, т.к. это логично, что после захода на страницу, след. шагом сначала принять КУКИ. Более
    // подробно про куки расписано в классе FirstTest
    public void testList() {
        mainPage.open();// вынесли в MaimPage, объявили данный класс mainPage. Говорим открой главную страницу
        mainPage.findBook("Java");// вынесли в MaimPage, объявили данный класс mainPage.
        // строка с кодом была длинная, поэтому его вынесли в отдельный метод findBook()

        WebElement buttonToCart = searchResultPage.bookCardComponent.findButton();//Находим нужную нам кнопку на странице
        buttonToCart.click();//кликаем на кнопку и у нас товар добавляется в корзину и ч/з какое то время происх. замена
        // наименования кнопки "В корзину" на "Оформить".
        // Вот далее и НУЖНА ЗАДЕРЖКА
        searchResultPage.bookCardComponent.waitButtonChanged();// wait - явное ожидание, пока наша кнопка не
        // переименуется в оформить вынесли в отдельный метод waitButtonChanged в классе searchResultPage
        buttonToCart.click();
        assertTrue(driver.findElement(cartPage.cartTitle).isDisplayed());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ ЕСТЬ НАШ ЭЛЕМЕНТ.
        //говорим, driver найди элемент cartTitle и проверь, что он есть в корзине
        assertEquals("1", driver.findElement(cartPage.cartCounter).getText());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ
        // у нас один товар, который мы положили
    }
    /*
    Тест, заключается в следующем. Заходим на сайт https://www.labirint.ru/, в поисковую строку вводим поиск книг
    по java в названии. Далее наводим мышку на кнопку в корзину. И тут ловушка, кнопка через какое-то время
    переименовывается в "Оформить". Нам нужно поймать этот момент
    */
//}