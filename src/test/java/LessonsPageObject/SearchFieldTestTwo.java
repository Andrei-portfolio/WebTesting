package LessonsPageObject;

import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.MainPage;
import LessonsPageObject.page_object.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchFieldTestTwo {
    private WebDriver driver;

    private WebDriverWait wait;

    private MainPage mainPage;

    private SearchResultPage searchResultPage;//объявляли, создаем экземпляр класса для
    // SearchResultPage, после того, как перенесли в него всё необходимое с данного класса

    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
        mainPage = new MainPage(driver);// прописали после того, как объявили данный класс выше и приняли драйвер
        //в классе MainPage. Объявили свой драйвер
        searchResultPage = new SearchResultPage(driver);//прописали аналогично, как и в строке выше, но только
        // для класса SearchResultPage
        cartPage = new CartPage(driver);//прописали аналогично, как и в 2-х строках выше
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test//ТЕСТ ПОЧЕМУ-ТО У МЕНЯ НЕ ОТРАБАТЫВАЕТ.
    public void testSearchField() {
        mainPage.open();// заходим на главную страницу. Очень удобно, т.к обращаемся к одному из нашего page_object
        mainPage.findBook("Java");
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)

        String foundedHeader = searchResultPage.getHeaderText();
        assertEquals("Все, что мы нашли в Лабиринте по запросу «Java»",foundedHeader);
    }

       /*в данном тесте проверяем, что после того, как на сайте в поисковую строку вбили текст и нажали Enter,
    мы попадаем на другую страницу, где видим текст "Все, что мы нашли в Лабиринте по запросу «Java»*/

    @Test
    public void testSearchFieldInCart() {
        cartPage.open();// заходим на страницу корзины. Очень удобно, т.к обращаемся к одному из нашего page_object
        cartPage.findBook("Selenium");
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)

        String foundedHeader = searchResultPage.getHeaderText();
        assertEquals("Все, что мы нашли в Лабиринте по запросу «Selenium»",foundedHeader);

        /*В данном тесте, заходим в корзину и ищем в ней товар с "selenium", с помощью поисковой строки*/

    }



    /*ВАЖНО!!!!!!! КАК И ГОВОРИЛИ НА ПРОТЯЖЕНИИ ВСЕГО КУРСА ОТ ОДИНАКОВОГО КОДА НУЖНО ИЗБАВЛЯТЬСЯ. ЧТОБЫ ВЫНЕСТИ
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
    написано у меня в файле POJO.md*/
}