package WebElement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class RectDemo { private WebDriver driver;

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
    @DisplayName("Пример использования метода Rect")
    public void testGetRect() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement inputText = driver.findElement(By.xpath("//input[@type='text']"));

        System.out.println(inputText.getRect().getWidth());
        System.out.println(inputText.getRect().getHeight());
        System.out.println(inputText.getRect().getX());
        System.out.println(inputText.getRect().getY());

//В данном тесте мы с помощью getRect хотим получить координаты текста по иксу, игрику, высота, ширина

    }
}