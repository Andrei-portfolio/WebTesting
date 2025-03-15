
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/*Для работы с фронтом скачаем в pom Selenium
https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.28.1 Кроме того, для начала нам
нужен chromedriver. Мы его должны скачать на компьютер и развернуть. И только после этого можно посылать запросы
ч/з API. Т.е. мы разворачиваем, чтобы была возможность посылать запросы. Но это было раньше, сейчас
достаточно скачать selenium и в созданном классе создать экземпляр
  */

public class FirstTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(); // 1. Запускается драйвер 2. Драйвер запускает браузер
        /* Вместо ChromeDriver можно писать другие драйвера, в зависимости от необходимого нам браузера
        ВЫШЕ мы создали экземпляр драйвера и у нас автоматически открылся БРАУЗЕР, т.е. появилось окно.
        Так устроена под капотом, эта команда, этот конструктор, которые при создании экземпляра
        шлёт POST /session запрос API, который уже реализует все драйверы.
        Получается java обращается к Selenium WebDriver, а selenium уже сам смотрит к какому драйверу обратиться
        (в зависимости от выбранного нами браузера).

        Проще говоря, selenium написал такой интерфейс и назвали его
        WebDriver, такую однотипную API для всех браузеров, которая принимает наш запрос и сама определяет
        (ч/з if и switch), с какого браузера он был отправлен. Ну а далее, каждый разработчик браузера
        (Chrome, Firefox и т.д.) был обязан написать свои драйвера (ChromeDriver, GeckoDriver и т.д.) так,
        чтобы они соответствовали перечню методов, который нужен для работы с браузером, представленный
        в п. 6.5 Endpoints https://www.w3.org/TR/webdriver2/. Получается у нас есть интерфейс WebDriver,
        который имплементирует экземпляры класса (ChromeDriver, GeckoDriver и т.д.). Т.е. они обязаны все эти
        методы реализовать*/

        //driver.manage().window().setPosition(new Point(2500, 50));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {//Если по ккой то причине драйвер не создался, т.к. что то пошло не так,
            // нужно проверять, что драйвер НЕ равен null
            driver.quit();// данная команда закрывает наш запущенный драйвер (завершает сессию), после выполнения теста.
            // Если её не будет то, сколь раз мы запустим наш тест, столько раз и будет запущен наш браузер.
            // Соответственно, в конце концов это будет занимать много памяти
            }
    }

    @Test
    public void testCloseOneWindow() {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.close();//данная команда закрывает окно/вкладку
        //Если проще сказать разница м/у close и quit, в том, что close закрывает текущее окно/вкладку,
        // quit - закрывает браузер и выгружает драйвер
    }

    @Test
    public void testInfo() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String title = driver.getTitle();//Проверяет, что в тесте отображается title
        String url = driver.getCurrentUrl();// Используется часто в тестах, чтобы убедиться, что страница открылась
        String pageSource = driver.getPageSource();
    }

    @Test
    public void testRedirect() {
        driver.get("http://yandex.ru/");
        assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }



}



/*





        @Test
        public void testNavigate() throws InterruptedException {
            driver.get("https://the-internet.herokuapp.com/windows");
            sleep(500);
            driver.navigate().refresh();
            sleep(500);
            driver.navigate().back();
            sleep(500);
            driver.navigate().forward();
        }

        @Test
        public void testWindow() throws InterruptedException {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/windows");
            Point point = driver.manage().window().getPosition();
            //driver.manage().window().setSize(new Dimension(300, 300));

            Dimension dimension = driver.manage().window().getSize();
        }

        @Test
        public void testAlert() {
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
            Alert alert = driver.switchTo().alert();
            assertEquals("I am a JS Alert", alert.getText());
            alert.accept();
        }

        @Test
        public void testCookie() {
            driver.get("https://www.labirint.ru/");
            driver.manage().addCookie(new Cookie("cookie_policy", "1"));
            Set<Cookie> cookieSet = driver.manage().getCookies();
            driver.navigate().refresh();
        }

        @Test
        public void takeScreen() {
            driver.get("https://www.labirint.ru/");
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            takesScreenshot.getScreenshotAs(OutputType.FILE).renameTo(Path.of("_output/result.png").toFile());
        }
    }

 */

