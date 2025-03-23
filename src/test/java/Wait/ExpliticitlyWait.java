package Wait;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*Явные же ожидания используются в более утончённых случаях. Это когда мы ждём явного события или действия.
В качестве примера, где сможем использовать явные ожидания, например на сайте есть полоса загрузки страницы
в процентах. И нам надо поймать, именно тот момент, когда страница загрузится на 75%. Т.е данный процент
будет виден на шкале загрузки

*/

public class ExpliticitlyWait {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Пример явного ожидания по загрузке страницы до 75% - КАК ДОЛЖНО БЫТЬ")
    // В ДАННОМ ТЕСТЕ ПОКАЗАНО КАК ДОЛЖНО БЫЛО БЫТЬ, НО ЗА НАС УЖЕ ВСЁ СДЕЛАНО, ЧТО МОЖЕМ УВИДЕТЬ В СЛЕД. ТЕСТЕ testReal
    public void testOld() throws InterruptedException {
        driver.get("http://uitestingplayground.com/progressbar");

        driver.findElement(By.cssSelector("#startButton")).click();// Кликаем на кнопку start
        // когда нашли элемент с 75% жмем кнопку
        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']"));
        String value = null;
        long startTime = System.currentTimeMillis();
        long finishTime = startTime + 20000;//Говорим, что задаём максимальное время в 20 сек для того чтобы поймать 75%
        // а код строч. ниже говорит, выполняйся, пока текущее время меньше, чем finishTime
        while (System.currentTimeMillis() < finishTime) {// В цикле ниже, говорим, проверяй %загрузки, пока не будет достигнуто 75%
            sleep(100);// Говорим, слишком часто ходишь проверяешь значение текста. ходи медленее, ходи реже.
            // ВРОДЕ БЫ с помощью данного sleep, в терминале будет отображаться %загрузки с интервалом 1 сек,
            // т.е. в терминале будет меньше значений. Но если мы промахнеёмся и в терминале пропуститься значение 75%,
            // то упадёт тест наверное
            value = progressBar.getText();//создали бесконечный цикл
            System.out.println(value);//постоянно этот текст выводим в терминал
            if (value.equals("75%")) {// Говорим, если значение достигло 75%, то выходи из цикла и нажми "#stopButton"
                break;
            }
        }
        driver.findElement(By.cssSelector("#stopButton")).click();// Здесь говорим, когда будет достигнуто
                                                                 // нужное нам значение, нажми stop
        assertEquals("75%", value);//Проверим, что ожидаемое значение в 75% достигнуто
    }


    @Test
    @DisplayName("Пример явного ожидания по загрузке страницы до 75% - УПРОЩЕННЫЙ ВАРИАНТ")

    public void testReal() {
        driver.get("http://uitestingplayground.com/progressbar");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ZERO);
    /* ПРИВЕДЁН ТОТ ЖЕ ПРИМЕР ПО ЗАГРУЗКЕ СТРАНИЦЫ, НО С ПРИМЕНЕНИЕМ МЕТОДА WebDriverWait. ЗДЕСЬ БУДЕТ ВИДНО,
    ЧТО ЗА НАС УЖЕ ВСЁ СДЕЛАНО, ЧТОБЫ УПРОСТИТЬ КОД. МЕТОД WebDriverWait принимает на вход ТРИ параметра,
    это driver, максимальное время ожидания Duration.ofSeconds и константа ziro, которая говорит ходи максимально часто.
    Последнюю константу, можно и не задавать.
    КРОМЕ ТОГО, В МЕТОДЕ WebDriverWait по умолчанию уже задан sleep продолжительностью пол секунды*/
        driver.findElement(By.cssSelector("#startButton")).click();
        // когда нашли элемент с 75% жмем кнопку
        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']"));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='progressBar']"), "75%"));
        //Указываем, какое событие ждать, на что должно повлиять. Например, когда наш текст будет равен 75%.
        // Тем самым, одной строчкой заменим цикл, приведенный в тесте выше testOld
        String value = progressBar.getText();
        driver.findElement(By.cssSelector("#stopButton")).click();
        assertEquals("75%", value);
    }

    @Test
    public void testActiveMenuLi() {
        driver.get("https://www.labirint.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        WebElement bookMenuElement = driver.findElement(By.xpath("//li[@data-event-content='Книги']"));
        actions.moveToElement(bookMenuElement).perform();

        wait.until(ExpectedConditions.attributeContains(
                By.xpath("//li[@data-event-content='Книги']"),
                "class",
                "b-toggle-active")
        );
    }
}