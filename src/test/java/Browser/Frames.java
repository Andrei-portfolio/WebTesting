package Browser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*Frames - если утрированно, это сайт внутри сайта. Самое частое встраивание, это плэер ютуба, чтобы мы показывали
 какое то своё видео. Достаточно взять этот фрэйм на ютубе, скопировать и вставить себе на страницу.
 Второй пример, где встречается, это система оплаты. Т.е. никто же будет в данном случае заново писать код
 не будет. А можно скачать уже готовый, который будет перекидывать на систему оплаты, например яндекс пэй.
 Важный момент, фрэймы ничего не знают друг о друге. И второй момент, если осуществлять поиск фрэймов,
 также, как мы искали элемент в классе SwitchTabs, то тест упадет. Так как браузер ищет элементы везде,
 кроме фрэймов. Т.к. было уже сказано выше, фрэйм это же отдельная страница. Поэтому на странице нужно найти
 нужный нам фрэйм (внутри него могут быть ещё фрэймы).
 ОЧЕНЬ ВАЖНО!!! Быть внимательным, когда мы ищем элемент на странице, а автотест падает и не находит его. ЭТО МОЖЕТ
 БЫТЬ ФРЭЙМ. ПОЭТОМУ АВТОТЕСТ НЕ НАХОДИТ И ПАДАЕТ. А нужно найти фрэйм по имени с помощью switchTo

 */




public class Frames { private WebDriver driver;

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
    public void testInfo() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-bottom");//находим фрэйм по имени. Теперь браузер будет видеть все элементы
                                                //в рамках данного фрэйма
        String actualText = driver.findElement(By.cssSelector("body")).getText();// говорим, найди его "body", получи его текст и
        assertEquals("BOTTOM", actualText);// и мы ожидаем, что этот текст равен "BOTTOM"
        System.out.println(actualText);// выведем на экран этот текс

        driver.switchTo().defaultContent();// Выходим из фрэйма, в который заходили выше. И только после этого можем зайти в другой фрэйм.
        driver.switchTo().frame("frame-top"); // Заходим в другой фрэйм
        driver.switchTo().frame("frame-middle");

        actualText = driver.findElement(By.cssSelector("body")).getText();//говорим, найди его "body", получи его текст и
        assertEquals("MIDDLE", actualText);//и мы ожидаем, что этот текст равен "MIDDLE"
        System.out.println(actualText);//выведем на экран этот текс
    }

}