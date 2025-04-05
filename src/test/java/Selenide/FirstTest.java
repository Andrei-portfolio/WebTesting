package Selenide;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class FirstTest {

    @BeforeAll
public static void beforeAll() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());//Этот код нужен для формирования отчета в allure

    Configuration.pageLoadStrategy = "eager";// Способ загрузки, что мы ждём, загрузку DOM, или полной прогрузки
        // страницы с полной статикой, стилями и картинками
    Configuration.browser = "firefox";//браузер
    Configuration.browserPosition = "2500x50";// Параметры браузера - позиция
    Configuration.browserSize = "1024x768";// Параметры браузера - размер
    Configuration.timeout = 20000;//задержка 20 сек. В Selenide нужно задать timeout, а он сам разберётся какое
        // ожидание использовать, явное или не явное. Но вроде в коде теста нужно использовать shouldHave,
        // как мы это делали в testText даного класса
}

    @Test
    public void testYa() {
        step("Открыть страницу", () -> open("https://www.ya.ru"));// Чтобы настроить allure отчёты,
        // нужно в pom загрузить библиотеку allure selenide
        System.out.println(WebDriverRunner.url());
        System.out.println(WebDriverRunner.source());
        System.out.println(WebDriverRunner.getWebDriver().getTitle());
    }

//    @Test// Закомиченный тест, т.к. скопировали его для примера, для сравнения, как делали тест ч/з selenium
//    public void testText() {
//        driver.get("http://uitestingplayground.com/ajax");
//        // sleep(15000);
//        driver.findElement(By.cssSelector("#ajaxButton")).click();
//        String text = driver.findElement(By.cssSelector(".bg-success")).getText();
//        assertEquals("Data loaded with AJAX get request.", text);
//        System.out.println(text);
//    }

    @Test
    public void testText() {
        open("http://uitestingplayground.com/ajax");
        // sleep(15000);
        //SelenideElement selenideElement = $("#ajaxButton");// Поиск по css селектору
        //SelenideElement selenideElement = $(By.xpath("//button[@id='ajaxButton']"));// Поиск того же по xpath - 1 вариант
        SelenideElement selenideElement = $x("//button[@id='ajaxButton']");//Поиск того же по xpath - 2 вариант
        selenideElement.click();
        $(".bg-success").shouldHave(text("Data loaded with AJAX get request."));// найди и проверь, данный элемент должен иметь текст
        $(".bg-success").shouldHave(Condition.text("Data loaded with AJAX get request"));// Этот и верхний код касаются одиночных селекторов
    }
//
    @Test// Тест спец. падает, т.к. в ту-ду лист нет 100 задач, поэтому падает
    public void testToDo() {
        step("Открыть страницу", () -> open("https://sky-todo-list.herokuapp.com/"));
        $$("tr").shouldHave(CollectionCondition.sizeGreaterThan(100), Duration.ofSeconds(3));
    }
}