package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClickDemo {// Тесты связанные с кликом элементов
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testText() throws InterruptedException {
        driver.get("https://habr.com/ru");
        WebElement webElement = driver.findElement(By.xpath("//span[@class='tm-header__logo-wrap']"));
        webElement.click();// В реальной жизни, после этого необходимо проверить, что изменилось, что что то
                          // произошло после нашего нажатия
    }

    @Test
    @DisplayName("Пример ОШИБКИ при клике на не кликабельные элементы")
    public void testClickWithScroll() throws InterruptedException {
        driver.get("https://habr.com/ru");
        WebElement webElement = driver.findElement(By.xpath("//a[@href='/ru/feedback/']"));
        webElement.click();
        /*Почему в данном тесте выбрали клик на кнопку техническая поддержка? Потому что, когда мы заходим
        на habr.com, нам нужно проскролить в самый низ, а только потом нажать на данную кнопку.
        Но к счастью селениум делает скролл за нас*/

    }

    @Test
    @DisplayName("Пример ОШИБКИ, если объект перегородил нужный нам элемент")
    public void testElementClickInterceptedException() throws InterruptedException {
        driver.get("https://www.labirint.ru/");
        WebElement webElement = driver.findElement(By.xpath("//p[text()='Все для успешного изучения английского со скидкой 50%.']"));
        webElement.click();
        /*Данный тест приведён в качестве примера, что можно поймать ошибку: .....element click intercepter........
         из-за того, что какой-то объект перегородил нужный нам элемент и клик не проходит,
         соответственно, тест упадёт. В консоли даже написано, что наш тег "p" не кликабелен, далее даже указан тег
         который нам мешает кликнуть. Мы можем спокойно его найти.
         ВАЖНО!!! Получается selenium, это по сути замена пользователя. И если пользователь не может кликнуть
         на перегороженную кнопку, соответственно и selenium тоже не сможет*/
    }

    @Test
    @DisplayName("Пример ОШИБКИ при клике на не кликабельные элементы")
    public void testElementNotInteractableException() throws InterruptedException {
        driver.get("https://habr.com/ru/feed/");
        WebElement webElement = driver.findElement(By.xpath("//div[@class='tm-articles-list__after-article'][3]"));
        webElement.click();
        /*Нужно быть готовыми, что иногда есть не кликабельные элементы, типо обычного текста. Данный автотест,
        это яркий пример этого. Выдаётся ошибка .....ElementNotInteractableException:element not interactable.

        Также бывает, что разработчик делает некоторые элементы не видимыми. Что с этим делать, мы узнаем позже.
        Кроме того, можно залесть в документацию на сайте https://www.w3.org/TR/webdriver2/#element-click, что
        почитать, какие ещё возможности есть у click и других*/
    }
}