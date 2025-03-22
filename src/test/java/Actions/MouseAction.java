package Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class MouseAction {

    /*В данном классе приведём пример, как в автотестах применять мышь*/

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/test/resources/User-Agent-Switcher-for-Chrome-Chrome.crx"));
        // Выше указываем путь к файлу с расширением User Agent Switcher
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
    @DisplayName("Рисуем квадрат в paint")
    public void testMouse() {
        driver.get("https://jspaint.app/");// Зайдем на страничку граф. редактора paint прям в браузере
        /*Здесь правой клавишей мыши мы меняем цвет ластика, ну а зажатой левой КЛМ можем рисовать.
        Кроме того, есть выподающее меню, которое необходимо выбрать с помощью мыши. Есть элементы, которые
         подсвечиваются при наведении мыши*/

        Actions actions = new Actions(driver);

        /*В коде ниже canvas использ. для создания графики, рисовани. Проблема в том, что мы например нарисуем,
        оно не отобразиться в тегах и а соответственно нельзя проверить через селениум. Но для этого есть скриншотное
        тестирование, где можно автоматизировать на создание рисунка и вывода скриншота. Подробнее о данном
        виде тестирования в других лекциях. Ниже приведены примеры, что можем использовать */

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));
        WebElement tools = driver.findElement(By.cssSelector(".tools"));
        WebElement brush = tools.findElement(By.cssSelector("[title='Кисть']"));
        WebElement zoom = tools.findElement(By.cssSelector("[title='Масштаб']"));
        WebElement paint = tools.findElement(By.cssSelector("[title='Заливка']"));
        WebElement rect = tools.findElement(By.cssSelector("[title='Прямоугольник']"));
        WebElement color = driver.findElement(By.cssSelector("[data-color='rgb(128,128,255)']"));

        actions.click(canvas).pause(1000).perform();//actions.click() - ничего не писать.
                                                    // actions.click(canvas) - клик в середину, получаем точку.
        //Обратим вниманееЮ если водить мышкой по экрану во время выполнения данного теста, то можем упасть,
        // т.к. точка будет в другом месте

        actions.clickAndHold(canvas)// метод нажать и зажать
                .moveByOffset(100, 0)// обозначает сдвиг курсора, ну а в скобках координаты
                .pause(150)// пауза
                .moveByOffset(0, 100)//продолжаем двигать курсор, но в друг направлении
                .pause(150)
                .moveByOffset(-100, 0)
                .pause(150)
                .moveByOffset(0, -100)
                .pause(1500)
                .perform();// команда "закончить"
    }

    @Test
    @DisplayName("Тест на проверку срабатывания Button в paint")
    public void testHighlightButton() {
        driver.get("https://jspaint.app/");
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));

        actions.click(canvas).pause(1000).perform();

        WebElement fullScreenButton = driver.findElement(By.xpath("//tr[@aria-label='Fullscreen']"));
        actions
                .moveToElement(driver.findElement(By.xpath("//div[contains(@id, 'menu-button-Вид')]")))
                // говорим, что хотим обратиться к элементу, в скобках ч/з xpath обозначаем к какому - это кнопка "Вид" с выпадающим меню
                .pause(500)
                .click()
                .moveToElement(fullScreenButton, 5, 3)// в выпадающим меню выбираем fullScreen.
        /*Чтобы упростить код, выше создали отдельную переменную fullScreenButton Клик в автотестах осуществляется
        по геометрическому центру, но бывает проблема, что середина экрана не кликабельна. Поэтому нам нужно нажать
        немного левее или правее. Поэтому в данном коде пропишем на сколько сдвинуться от центра (на 5 и 3)*/
                .pause(2000)
                //.click()
                //.pause(1500)
                .perform();// команда "закончить"

        String buttonClass = fullScreenButton.getDomAttribute("class");
        System.out.println(buttonClass);
        assertEquals("menu-row menu-item highlight",buttonClass);
    }

    @Test
    @DisplayName("Тест на изменение цвета карандаша в paint")
    public void testColorPaint() {
        driver.get("https://jspaint.app/");
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.cssSelector(".main-canvas"));
        WebElement redColor = driver.findElement(By.xpath("//div[@data-color='rgb(255,0,0)']"));

        actions
                .contextClick(redColor)// Клик правой КЛМ. Заданная переменная redColor выше
                .pause(1500)
                .contextClick(canvas)
                .pause(1500)
                .doubleClick(canvas)
                .perform();// команда "закончить"
    }



}