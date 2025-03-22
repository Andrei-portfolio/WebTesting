package Actions;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

     /* Сегодняшняя тема лекции от 6.03.25 это действия, или работа с классом Actions. На прошлой лекции учились
        простым действиям, типо клика и получения текста. Но встречаются и более сложные задачи, например,
        когда нужно зажать элемент и перетащить на другое место и отпустить. Обычным кликом здесь не обойтись.
        На прошлой лекции рассматривали метод SendKeys по работе с клавиатурой, который вводит какие-то данные
        в отдельные поля. Но это касается полей.
        В данном классе рассмотрим класс Action, который принимает на вход driver. Если после actions ставим
        точку, то нам представляется большое количество методов. Которые, обратить вниманее, что все эти методы
         возвращают тип Action. А это значит, наш старый приятель ПАТЕРН БИЛДЕР. Это когда мы ч/з точку записываем
         кучу действий (пример использования из пред. блока API - РЕСТ-АШУР). В самом низу класса поступим также*/


public class KeyboardAction {
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
    public void testKeyboard()  {
        driver.get("http://www.uitestingplayground.com/textinput");
        Actions actions = new Actions(driver);// Класс Actions принимает на вход driver
        //String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));

//        Keys command;
//        if (Platform.getCurrent().is(Platform.WINDOWS)) {
//            command = Keys.CONTROL;
//        }
//        else {
//            command = Keys.COMMAND;
//        }

        /*Код выше мы хотели применить, для того, чтобы данный тест отработал не только на WINDOWS, но и например
        на Линоксе. НО т.к. код получился объемным, мы применим  тернарные градиенты TernGrad в коде всего одной
        строчкой ниже. Данный код приведёт к тому же, но занимать будет всего одну строчку.
        Если посмотреть лекцию от 6.03 до 31 минуты, то там можно посмотреть код без того, который у нас выше
        и без TernGrad.*/

        Keys command = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;// что обозначает
        // рассказывается в лекции  от 6.03 на 33 минуте

        /*Ниже обратить вниманее, что если после actions ставим то нам возможно применить много действий */

        actions
                .keyDown(command)// метод зажимает кнопку. Будет зажата, пока мы её не отпустим методом keyUp
                .sendKeys(driver.findElement(By.cssSelector("body")), "a")// Обращ к body ч/з cssSelector
                .sendKeys("c")//Копируем ч/з клавишу ctrl + "c"
                .sendKeys(inputName,"v")// Вставляем ctrl + "v" ч/з input
                .keyUp(command)// Отпускаем зажатую кнопку
                .pause(2000)//Возможно сделать паузу, чтобы не дебажить
                .perform();// Т.к. это билдер, ожидается данное волшебное слово, которое говорит, что
                          // мы закончили. Выполняй действия. Обяз. пишется в конце
    }
}