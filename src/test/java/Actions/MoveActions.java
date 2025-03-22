package Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class MoveActions {

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

    @Test// что-то падает этот тест
    public void testMove() {
        driver.get("https://tholman.com/cursor-effects/");
        Actions actions = new Actions(driver);

        actions.
                moveToLocation(200, 800)
                .pause(100)
                .moveToLocation(100, 700)
                .pause(100)
                .moveToLocation(200, 800)
                .pause(100)
                .moveToLocation(300, 650)
                .pause(100)
                .perform();

        //moveToLocation это чисто координаты
    }
}