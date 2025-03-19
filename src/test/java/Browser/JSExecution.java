package Browser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*Если коротко, то страничка состоит, не только из CSS, но и из JavaScript. Мы в тестировании тое можем
 вызывать JavaScript код и менять саму страницу. Зачем это пригодиться:
 Плюсы:

 Минусы:


 Яркий пример. Допустим нам нужно сделать скриншот страницы, как это мы делали в классе FirstTest. Но допустим
 на нужном нам сайте (ya.ru) есть место для рекламы. А реклама сегодня может быть одна, а завтра другая
 и у нас тест будет падать. Так как мы же сравниваем скрин по результатам теста с скрином, котрый сделан ранее.
 И соответственно они будут не совпадать. А для этого удалим рекламу с сайта. И только после будем делать скрин
 с помощью автотеста.

 Как удалить рекламу. Находим данный тег с рекламой в консоли разработчика во вкладке "Elements". Копируем тег
 (или только его класс/название) и переходим  во вкладку Console. Пишем там следующее document.querySelector(.......).
 Вместо точек вставляем, например класс/название нашего тега с рекламой. Поиск нам по названию выдаёт весь тег с
 рекламой. Его и нужно удалить. А для этого ниже напишем document.querySelector(.......).remove()
 Вместо точек будет тоже самое. После удаления можно делать скриншот. Далее рассмотрим, как выполнить команду
 в автотесте.
 Вспомним, что RemoteWebDriver (подробнее в классе FirstTest внизу) имплементирует не только WebDriver,
 TakesScreenshot, но и нужный нам JavascriptExecutor. Далее используем приведение типов (изменение типов переменной).
 То есть, для приведения к другому типу надо указать в скобках нужный тип. Поэтому в коде нашего теста
 напишем ((JavascriptExecutor) driver).executeScript(JS_REMOVE); Ну а ниже  используем приведение TakesScreenshot

  */

public class JSExecution { private WebDriver driver;

    private static final String JS_REMOVE = "document.querySelector(\".main-home-banner\").remove()";

    public static final String JS_ADD_SCORE = "localStorage.setItem(\"bestScore\",\"HELLO!!!\")\n";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
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
    public void screenWithoutAd() {
        driver.get("https://ya.ru/");
        ((JavascriptExecutor) driver).executeScript(JS_REMOVE);
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)
                .renameTo(Path.of("_output/screenshotJS.png").toFile());
    }

    @Test
    public void changeScore() {
        driver.get("https://2048game.com/");
        ((JavascriptExecutor) driver).executeScript(JS_ADD_SCORE);
        driver.navigate().refresh();// обновляем страницу
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)
                .renameTo(Path.of("_output/addScore.png").toFile());
    }
}