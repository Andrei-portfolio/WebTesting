package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClearDemo {private WebDriver driver;

    /*
    Здесь приведён пример, как можно работать с clear
    */

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
    @DisplayName("Пример, что можно ввести текст, стереть его и ввести заново")
    public void sendKeys() {
        driver.get("http://www.uitestingplayground.com/textinput");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);

        inputName.clear();//удаляем текст. clear также работает с полем для загрузки файла
        inputName.sendKeys("abc");//вводим новый текст


        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        String newButtonName = button.getText();
        assertEquals("abc", newButtonName);
    }
}