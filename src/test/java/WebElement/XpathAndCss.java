package WebElement;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class XpathAndCss { private WebDriver driver;

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

    @Test// Тест не отработает, нужно указывать собственный путь к собственному html файлу
    public void testText() throws InterruptedException {
        driver.get("file:///C:/Users/Sammy/Desktop/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0/4%20%D0%BC%D0%BE%D0%B4%D1%83%D0%BB%D1%8C/JavaLessons4Module/src/test/resources/file.html");
        WebElement webElement = driver.findElement(By.xpath("//p[text() = 'Очень вкусно.']"));
        /* Получаем id элемента, чтобы с ним взаимодействовать далее. В коде выше, мы преминили надёжной способ поиска,
        тем что привязали поиск по тексту "//p[text() = 'Очень вкусно.']"
         При поиске лучше использовать ординарные кавычки, т.к. если поставим двойные, то idea преобразит
         немного и добавит обратный слэшь. //p[text() = \"Очень вкусно.\"]" Да и ординарные, визуально лучше выглядят*/

        String actualTitle = webElement.getText();
        //                     //body/p    //p     //html/body/p    //html//p  - перечисляем способы поиска.

        /* Самый быстрый способ поиска для нас будет //p. Кроме того, чем хорош данный способ поиска //p? Допустим
        мы указали путь //html/body/p. А разработчик взял и добавил новый тег div и наш указанный нами путь
        //html/body/p ничего не найдёт */


        assertEquals("Очень вкусно.", actualTitle);
    }


    @Test// Тест не отработает, нужно указывать собственный путь к собственному html файлу
    public void testButton() throws InterruptedException {
        driver.get("file:///C:/Users/Sammy/Desktop/%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0/4%20%D0%BC%D0%BE%D0%B4%D1%83%D0%BB%D1%8C/JavaLessons4Module/src/test/resources/file.html");
        driver.findElement(By.xpath("//button[text() = 'Заказать']")).click();
        //                                         //button[@class = "red"]// ч/з символ @ мы можем указать любой атрибут
        // ВАЖНО!!! Вмессто любого тэга мы можем в поисковой строке указать * и получится //*[@class = "red"]. Опасность
        // в том, что в html может быть несколько одинаковых классов, и в автотесте мы будем искать не тот тэг
    }

    //     //b[contains(.,'Привет')]
    /*НО БЫВАЮТ СЛУЧАИ КОГДА [@class = "**********"] МЫ ПРИМЕНИТЬ НЕ МОЖЕМ, Т.К. ФУНКЦИЯ contains ПРИНИМАЕТ ДВА ЭЛЕМЕНТА. ПЕРВЫЙ */
}

/*ОЧЕНЬ ВАЖНО!!! ПОИСК ПО Xpath ПОЗВАЛЯЕТ НАМ УКАЗАТЬ ПУТЬ НЕ ТОЛЬКО СВЕРХУ В НИЗ В НИСХОДЯЩИЕ ТЕГИ , НО И
ОБРАТИТЬСЯ С НИЗУ ВВЕРХ В РОДИТЕЛЬСКИЙ. ДЛЯ ЭТОГО В НАЙДЕНОМ НИСХОДЯЩЕМ ТЕГЕ ПОСЛЕ СЛЭША ПИШЕМ ДВЕ ТОЧКИ
 И УКАЗЫВАЕМ РОДИТЕЛЬСКИЙ (Т.Е. ВЫШЕСТОЯШИЙ ТЭГ). И ТАК МОЖЕМ ДЕЛАТЬ ДО БЕСКОНЕЧНОСТИ, ПОКА НЕ ДАЙДЕМ ДО
  ТЭГА HTML. НО ТАК ДЕЛАТЬ НЕ НУЖНО*/