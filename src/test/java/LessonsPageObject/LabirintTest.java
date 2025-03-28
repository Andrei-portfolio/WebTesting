package LessonsPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LabirintTest {
    private WebDriver driver;

    private WebDriverWait wait;

    private final By button = By.xpath("//a[contains(@class, 'btn-tocart')]");
    private final By searchField = By.xpath("//input[@id='search-field']");
    private final By cartTitle = By.xpath("//span[@class='cart-title']");
    private final By cartCounter = By.xpath("//span[contains(@class, 'j-cart-count')]");

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
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
        openMainPage();// вынесли в отдельный метод openMainPage() ниже. Говорим открой главную страницу
        findBook("Java");// строка с кодом была длинная, поэтому его вынесли в отдельный метод findBook() ниже

        WebElement buttonToCart = driver.findElement(button);//Находим нужную нам книгу
        buttonToCart.click();//кликаем на кнопку и у нас товар добавляется в корзину и ч/з какое то время происх. замена
        // наименования кнопки "В корзину" на "Оформить".
        // Вот далее и НУЖНА ЗАДЕРЖКА
        wait.until(ExpectedConditions.textToBe(button, "оформить"));// wait - явное ожидание, пока наша кнопка
        //не переименуется в оформить
        buttonToCart.click();
        assertTrue(driver.findElement(cartTitle).isDisplayed());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ ЕСТЬ НАШ ЭЛЕМЕНТ.
        //говорим, driver найди элемент cartTitle и проверь, что он есть в корзине
        assertEquals("1", driver.findElement(cartCounter).getText());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ
        // у нас один товар, который мы положили
    }

    private void openMainPage() {
        driver.get("https://www.labirint.ru/");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
    }

    private void findBook(String name) {
        driver.findElement(searchField).sendKeys(name, Keys.RETURN);
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)
    }
    /*
    Тест, заключается в следующем. Заходим на сайт https://www.labirint.ru/, в поисковую строку вводим поиск книг
    по java в названии. Далее наводим мышку на кнопку в корзину. И тут ловушка, кнопка через какое-то время
    переименовывается в "Оформить". Нам нужно поймать этот момент
    */
}