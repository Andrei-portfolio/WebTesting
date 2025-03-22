package Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
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
import org.openqa.selenium.interactions.Actions;

public class DragAndDropActions {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/test/resources/User-Agent-Switcher-for-Chrome-Chrome.crx"));
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
    @DisplayName("Тест на проверку количества элементов")
    public void testMultipleElements() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(driver);
        List<WebElement> columns = driver.findElements(By.cssSelector(".column"));
        // System.out.println(columns.get(0));
        //  System.out.println(columns.get(1));
        assertTrue(columns.get(0).isDisplayed());
        System.out.println(columns.size());
//        actions.
//
//            .perform();
    }

    @Test
    @DisplayName("Тест на перетаскивание элементов")
    public void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(driver);
        //  WebElement columnA = driver.findElement(By.cssSelector("#column-a"));
        //  WebElement columnB = driver.findElement(By.cssSelector("#column-b"));


        //ПЕРВЫЙ СПОСОБ НА ПЕРЕТАСКИВАНИЕ ЭЛЕМЕНТОВ
        actions
                .clickAndHold(driver.findElement(By.cssSelector("#column-a")))// Зажимаем колонку "a"
                .moveToElement(driver.findElement(By.cssSelector("#column-b")))// Двигаем колонку "a" к колонке "b"
                .pause(100)
                .release()// Отпускаем колонку "a"
                .pause(100)
                .perform();

        List<WebElement> columns = driver.findElements(By.cssSelector(".column"));
        assertEquals("B", columns.get(0).getText());// Проверяем, что один из элементов "B" стал впереди
        assertEquals("A", columns.get(1).getText());// Проверяем, что один из элементов "A" стал вторым

        driver.navigate().refresh();//После того, как мы обновили страницу, все элементы встали опять на своё место.
        //Т.е. A вернулось вперёд, В стало на втором месте. Поэтому во втором способе ниже, с помощью assertEquals
        // проверяем, что

        //ВТОРОЙ СПОСОБ НА ПЕРЕТАСКИВАНИЕ ЭЛЕМЕНТОВ
        actions
                .dragAndDrop(driver.findElement(By.cssSelector("#column-a")), driver.findElement(By.cssSelector("#column-b")))
                .pause(2000)
                .perform();
        columns = driver.findElements(By.cssSelector(".column"));
        assertEquals("B", columns.get(0).getText()); // Проверяем, что один из элементов "B" стал впереди
        assertEquals("A", columns.get(1).getText());// Проверяем, что один из элементов "A" стал вторым

    /*
    dragAndDrop это метод позволяющий взять что то и перетащить. Мы говорим, перетащи колонку "a" к колонке "b"
     */
    }
}