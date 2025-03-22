package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class StateDemo { private WebDriver driver;

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
    @DisplayName("Пример использования трёх методов: isSelected, isEnabled, isDisplayed")
    public void testStates() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        assertFalse(checkBox.isSelected());// проверим на то, что checkBox НЕвыбран, т.к. мы только зашли на страницу
        checkBox.click();
        assertTrue(checkBox.isSelected());// проверим на то, что checkBox выбран, т.к. кодом выше мы его нажали

        WebElement inputText = driver.findElement(By.xpath("//input[@type='text']"));
        assertFalse(inputText.isEnabled());// Проверяем, что поле (элемент) активно для ввода
        assertTrue(inputText.isDisplayed());// Проверка на отображение чего-либо, например что перешли с одной
        // страницы на другую. Т.е. позволяет проверить, когда элемент не отображается на странице, но есть в DOM в html

        /*В тесте приведен пример, использования трёх методов:
        Первый, isSelected - например, если чек бокс выбран, выдаст tru, если нет False
        Второй, isEnabled проверяет, активен ли элемент
        Третий, isDisplayed позволяет проверить, когда элемент не отображается на странице, но есть в DOM в html*/

    }
}