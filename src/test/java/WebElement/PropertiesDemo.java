package WebElement;

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


public class PropertiesDemo {private WebDriver driver;

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
    @DisplayName("Пример работы со свойствами и атрибутами")
    public void testGetCssAndAttribute() {
        driver.get("http://uitestingplayground.com/textinput");

        WebElement container = driver.findElement(By.cssSelector(".container"));
        System.out.println(container.getCssValue("font-family"));// Указываем, какое значение Css свойства ожидаем
        System.out.println(container.getDomAttribute("class"));// Хотим узнать, какой у элемента класс
        System.out.println(container.getDomProperty("lastChild"));

    /*Работа со свойствами и атрибутами:
    getCssValue - указываем, какое значение Css свойства ожидаем
    getDomAttribute - это, если хотим узнать, какой у элемента класс. Это также может быть атрибут type, src и т.д.
    getDomProperty - смотрим в отдельной вкладке Property на вкладке Elements, там огромное количество свойств,
                     к которым можно обратиться*/

    }
}