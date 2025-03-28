package LessonsPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchFieldTest {
    private WebDriver driver;

    private WebDriverWait wait;

    private By buttonXpath = By.xpath("//a[contains(@class, 'btn-tocart')]");

    private By searchFieldXpact = By.xpath("//input[@id='search-field']");

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test//ТЕСТ ПОЧЕМУ-ТО У МЕНЯ НЕ ОТРАБАТЫВАЕТ.
    public void testList() {
        driver.get("https://www.labirint.ru/");
        driver.findElement(searchFieldXpact).sendKeys("Java", Keys.RETURN);
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)

        String foundedHeader = driver.findElement(By.xpath("//h1")).getText();
        assertEquals("Все, что мы нашли в Лабиринте по запросу «Java»",foundedHeader);
        WebElement buttonToCart = driver.findElement(buttonXpath);//Находим нужную нам книгу
    }
    /*в данном тесте проверяем, что после того, как на сайте в поисковую строку вбили текст и нажали Enter,
    мы попадаем на другую страницу, где видим текст "Все, что мы нашли в Лабиринте по запросу «Java»"

    ВАЖНО!!!!!!! КАК И ГОВОРИЛИ НА ПРОТЯЖЕНИИ ВСЕГО КУРСА ОТ ОДИНАКОВОГО КОДА НУЖНО ИЗБАВЛЯТЬСЯ. ЧТОБЫ ВЫНЕСТИ
    ПОВТОРЯЮЩИЙСЯ КОД В ПРЕДЕЛА ОДНОГО КЛАССА, НУЖНО ВЫДЕЛИТЬ ЭТОТ КОД, НАПРИМЕР НАШ локатор xpath или CSS селектор
    в тесте By.xpath("//input[@id='search-field']"). Далее  кликаем ПКМ, выбираем Refactor -- Introduce Field
    (Ctrl + Alt + F) -- пишем название нашей переменной -- вверху нашего класса пишем
    private By searchFieldXpact = By.xpath("//input[@id='search-field']");
    private By назв. переменной = сама переменная.

    Если мы в процессе поняли, что хотим переименовать нашу же переменную, то клик на ПКМ -- Refactor -- Rename и
    IDE сама всё везде подправит
    Если же мы в процессе поняли, что хотим вынести наш повторяющийся код в отдельный метод, то клик на ПКМ -- Refactor --
    Extract Method

    Ну а если мы хотим вынести одинаковый код, повторяющийся в пределах нескольких классов. То для этого существует
    ПАТЕРН PAGE OBJECT MODEL (сокращенно POM, не путать с pom.xml). Более подробно про паттерн  PAGE OBJECT MODEL
    написано у меня в файле POJO.md
    */

}