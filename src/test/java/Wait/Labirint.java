package Wait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Labirint {
    private WebDriver driver;

    private WebDriverWait wait;

    private By buttonXpath = By.xpath("//a[contains(@class, 'btn-tocart')]");

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

    @Test//ТЕСТ по началу НЕ ОТРАБАТЫВАЛ. Оказалось, что из-за КУКОВ. После того, как в данном тесте
    // прописали, что после захода на сайт, нужно принять КУКИ (2я и 3я строка кода). То кейс успешно отработал.
    // Добавили КУКИ, т.к. после захода на страницу, след. шагом нужно сначала принять КУКИ. Более
    // подробно про куки расписано в классе FirstTest
    public void testList() {
        driver.get("https://www.labirint.ru/");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
        driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys("Java", Keys.RETURN);
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)

        WebElement buttonToCart = driver.findElement(buttonXpath);//Находим нужную нам книгу
        buttonToCart.click();//кликаем на кнопку и у нас товар добавляется в корзину и ч/з какое то время происх. замена
        // наименования кнопки "В корзину" на "Оформить".
        // Вот далее и НУЖНА ЗАДЕРЖКА
        wait.until(ExpectedConditions.textToBe(buttonXpath, "оформить"));// wait - явное ожидание, пока наша кнопка
        //не переименуется в оформить
        buttonToCart.click();
        WebElement cartTitle = driver.findElement(By.xpath("//span[@class='cart-title']"));
        assertTrue(cartTitle.isDisplayed());// ПРОВЕРЯЕМ, ЧТО В КОРЗИНЕ ЕСТЬ НАШ ЭЛЕМЕНТ
    }
    /*
    Тест, заключается в следующем. Заходим на сайт https://www.labirint.ru/, в поисковую строку вводим поиск книг
    по java в названии. Далее наводим мышку на кнопку в корзину. И тут ловушка, кнопка через какое-то время
    переименовывается в "Оформить". Нам нужно поймать этот момент
    */

}