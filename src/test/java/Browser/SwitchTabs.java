package Browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwitchTabs { /* В данном классе рассмотрим, как переключаться м/у вкладками. Т.е. научимся искать элементы на странице */

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
    public void testWindow() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.linkText("Click Here")).click();// Чтобы найти элемент, напишем по англ. findElement
        //By это указание, через что будем искать. Далее указываем нажать, click

        String currentWindow = driver.getWindowHandle();// Первая наша вкладка возвращает строку
        Set<String> windows = driver.getWindowHandles();// Вторая нужная нам вкладка, содержит множество строк

        String secondWindow = null;// Это наша вторая вкладка, но мы не знаем её название

        for (String window : windows) {// Возьми каждый элемент (у нас и х два
            if (!window.equals(currentWindow))// и сравни с открытым окном. Воскл. знак говорит, что оно должно быть не равно
            {
                secondWindow = window;
            }
        }
        driver.switchTo().window(secondWindow);
        String actualText = driver.findElement(By.cssSelector("h3")).getText();
        assertEquals("New Window", actualText);// Если у нас в тесте будет найдет вот этот элемент, значит мы
        // действительно перешли на данную вкладку
    }
    /*По факту, когда мы делаем switchTo - мы говорим драйверу, что у нас сменился контекст, в котором  надо искать,
    если этого не делать - мы останемся в прошлом контексте страницы и там элементов таких может и не существовать,
    поэтому мы всегда проверяем наличие текста или уникального элемента для надёжности
     */
}