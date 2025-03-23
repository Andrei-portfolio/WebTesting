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
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ImplicitlyWait {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        /*Здесь implicitlyWait неявное ожидание подразумевает, что в течении 20 секунд, в нашем случае
        будет происходить поиск элементов в DOM по кругу. Т.е. если не найдет, будет искать заново.
        Если в заданое время не найдет, то тест упадёт с эксепшеном. Ну а если
        сразу найдет, допустим ч/з 3 секунды, то программа выполняется дальше и автотест переходит
        к следующей строчке кода
        Есть одна особенность у данного неявного ожидания. Если вдруг элементы на страничке загрузяться
        не разом, а по одному (т.е. первый, затем второй...), то наш метод возьмет первый загрузившийся
        элемент, не дожидаясь загрузки остальных. Иметь это ввиду. Данная проблема решиться при
        изучении явных ожиданий*/
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест с применением метода неявного ожидания implicitlyWait")
    public void testList() {
        driver.get("https://sky-todo-list.herokuapp.com/");
        // sleep(20000);// антипаттерн - нежелательный подход
        List<WebElement> toDoElements = driver.findElements(By.xpath("//td"));// Говорим, иди поищи
        // элементы, эсли не найдёшь, поищи ещё раз. И так будет происходить поиск по кругу, в период времени,
        // который мы выставили выше в @BeforeEach в методе implicitlyWait
        assertTrue(toDoElements.size() > 0);
    /*В качестве примера выбрали сайт туду лист, не спроста. Т.к. в нем при открытии  список дел прогружается
    отдельным запросом get, и не так быстро. Соответственно здесь и применим наши ожидания. Раньше, мы использовали
    метод sleep, но это дурной тон его использовать. Т.к. мы можем поставить ожидание 5 сек, а тест мог бы
    отработать за 2 секунды. Т.е. на одном только тесте теряем 3 сек. А таких тестов может быть очень
    много. Поставим ожидание 2 сек, а задержка будет 3 сек и тест полетит. Поэтому использовать sleep
    не целесообразно (антипаттерн - нежелательный подход). Кроме того, на разных устройствах, код будет компилироваться разное время,
    т.е. время ожидания необходимо подбирать, менять и т.д. Да и менять его необходимо в общем для всех репозитории
    и данная задержка будет на всех устройствах

    Обратим вниманее, что в тесте используем не findElement, а findElements. Для этого,
    создадим список List. Через assert проверяем, что там больше 0 элементов.

    По патернам Кузнецов Алексей советовал Николая Алименкова  посмотреть (есть вроде с Гейзенбага доклады),
    хорошо расскажет всё
    */
    }

    @Test
    public void testText() {
        driver.get("http://uitestingplayground.com/ajax");
        // sleep(15000);
        driver.findElement(By.cssSelector("#ajaxButton")).click();
        String text = driver.findElement(By.cssSelector(".bg-success")).getText();
        assertEquals("Data loaded with AJAX get request.", text);//Ч/з assertEquals проверим ожид. текст
        System.out.println(text);
        /*В данном тесте у нас в поле текст загрузается с 15 сек задержкой. Поэтому рассмотрим его, с применением
    неявных ожиданий. Мы задали его в @BeforeEach. Далее переходим к явным ожиданиям. Кстати, в одном тесте можно
    использовать и те и те ожидания вместе*/
    }
}