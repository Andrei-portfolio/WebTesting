package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class XpathAndCssHabr {private WebDriver driver;

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
    public void testLink() throws InterruptedException {
        driver.get("https://habr.com/ru/articles/");
        driver.findElement(By.xpath("//a[contains(@class, 'header__logo')]")).click();
    }

    @Test
    public void testLinkByCSS() throws InterruptedException {
        driver.get("https://habr.com/ru/articles/");
        driver.findElement(By.cssSelector("span[class *= 'logo-wrap'] > a[href='/ru/']")).click();
    }

    @Test
    public void testNotRealElement() {
        driver.get("https://habr.com/ru/articles/");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.xpath("//div[text()='Привет мир!']")));
    }


    //     //button[@class='tm-navigation-filters-spoiler__button' and contains(text(),'Настройки')]
}