
import static java.lang.Thread.sleep;
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

        driver.manage().window().setPosition(new Point(2500, 50));// Здесь прописаны координаты, с указанием места
        // где будет выгружаться наше окно, после запуска автотеста
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
        String title = driver.getTitle();//Проверяет, что в тесте отображается нормальное название title
        String url = driver.getCurrentUrl();// Используется часто в самих тестах, чтобы убедиться, что страница открылась.
                                            // Подробнее ниже
        String pageSource = driver.getPageSource();// Нужен, чтобы в алюр формировать отчёт. Ещё может быть нужен,
        // когда мы хотим найти какой-то невидимый элемент и через селениум это сделать трудно или не возможно.
        // Скрытые элементы невидимы, но нужны

        /*url используется часто в самих тестах, чтобы убедиться, что страница открылась. Так как иногда не достаточно
        открыть страницу, а нужно дождаться пока url будет тот который ожидаем. Потому что бывает очень много
        разных редиректов, которые перенаправляют на другую страницу. Яркий пример это http://yandex.ru,
        который перекидывает нас на https://dzen.ru. Ниже в тесте мы это и проверим. Скажем зайди на
        http://yandex.ru/ и с помощью assertEquals проверим, что текущий url - https://dzen.ru*/
}

    @Test
    public void testRedirect() {// В данном тесте мы проверяем редирект, который перенаправляет на другую страницу
        driver.get("https://yandex.ru/");
        assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }

    @Test
    public void testNavigate() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/windows");
        sleep(500);// Самый страшный грех в тестировании, это использ. sleep. Мы только в обучении применяем его. В дальнейшем рассмтрим другой способ
        driver.navigate().refresh();
        sleep(500);
        driver.navigate().back();
        sleep(500);
        driver.navigate().forward();
    /*В navigate можем использовать три метода, это refresh - обновление страницы браузера, back - назад,
     forward - вперёд. Позволяют обновление Эти кнопки находятся в левом углу нашей страницы*/

    }

    @Test
    public void testWindow() throws InterruptedException {
        //driver.manage().window().maximize();// После запуска, manage().window() позволяет запускать окно браузера,
        // на одном экране с автотестом. Ну а maximize() позволяет загрузить наше окно на полный размер экрана
        driver.get("https://the-internet.herokuapp.com/windows");
        Point point = driver.manage().window().getPosition();
        //driver.manage().window().setSize(new Dimension(300, 300));// Указан размер нашего окна браузера, но
        // можно maximize() поставить на весь экран

        Dimension dimension = driver.manage().window().getSize();
    }

    @Test
    public void testAlert() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Alert alert = driver.switchTo().alert();// С помощью switchTo()
        assertEquals("I am a JS Alert", alert.getText());
        alert.accept();
    }
    /* Alert это из java script. У неё нет дома в html. Это часть сайта, а не часть браузера*/

    @Test
    public void testCookie() {
        driver.get("https://www.labirint.ru/");// Заходим на страницу
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        Set<Cookie> cookieSet = driver.manage().getCookies();// При дебаже в cookieSet можно посмотреть большинство из cookies
        driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
    }

    /* Комментарий к тесту testCookie.
     Cookie это набор пары ключ:значение, т.е. текстовые данные, которые сервер отправляет нам (браузеру),
    а браузер сохраняет их на компьютере. Мы об этом заговорили, т.к. много сайтов при загрузке просят
     разрешить (принять) отправку Cookie. Если не принимать работу с Cookie в начале автотеста, то кейс упадёт.
     Т.к. может при выборе нужного нам элемента (например книги на сайте), случайно нажмем на всплывающее
     окно с Cookie. Преподаватель напомнил, что каждый раз, как мы запускаем новый драйвер, у нас инкогнито режим.
     И у нас нет никаких куки, пока мы их не примем. Как с этим бороться???
     1-й вариант. Написать в коде и каждый раз нажимать кнопку принять. НО это будет занимать в каждом тесте время
     2-й вариант. ЭТО ПРОКИДЫВАТЬ КУКИ на прямую. ОН круче. Можно их посмотреть в девтулс во вкладке нетворк.
     Вообще изначально, Cookie это заголовки. В ответе в нетворк приходит заголовок set-Cookie от сервера.
     И мы должны их установить себе. Ещё это дело можно посмотреть в разделе Application в разделе Cookies
     (ключ:cookie_policy, значение:1). И мы примем данные Cookie с помощью Selenium*/

    @Test
    public void takeScreen() {
        driver.get("https://www.labirint.ru/");
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;// Мы сказали преврати наш драйвер в TakesScreenshot
        takesScreenshot.getScreenshotAs(OutputType.FILE).renameTo(Path.of("_output/result.png").toFile());
    }

    /*Комментарий к тесту takeScreen.
    Одним из главных отчетов в UI тестах будет скриншот, который будем делать конкретно перед падением.
    Но мы не будем заморачиваться и будем делать скрин в тесте, не зависимо, упал ли он, или отработал успешно.

     У нас получается, что помимо того, что WebDriver имплементирует 7 разных драйверов
     (FirefoxDriver, ChromeDriver .....), каждый из 7 этих драйверов наследуется от RemoteWebDriver.
     Т.е. являются наследниками данного класса, у которого есть какие то общие поля и методы. Получается, что
     м/у WebDriver и каждым из 7 этих драйверов стоит RemoteWebDriver

                WebDriver

     FirefoxDriver ChromeDriver ..........

              RemoteWebDriver

     К чему это велось? Откуда же брать скриншоты? Оказывается RemoteWebDriver имплементирует не только WebDriver,
     но и WebDriver, JavascriptExecutor, HasCapabilities, HasDownloads, HasFederatedCredentialManagement,
     HasVirtualAuthenticator, Interactive, PrintsPage, и необходимый для нас TakesScreenshot, который делает скрин.
     А как же нам быть? Получается, если мы в самом начале данного класса изменим переменную private WebDriver driver
     на private TakesScreenshot driver, то все наше автотесты в данном классе не будут работать.
     Т.е. у нас получается гибкость, в плане использования разных браузеров, но в тоже время мы не можем использовать
     TakesScreenshot. А для этого мы используем приведение типов, это просто изменение типов переменной.
      То есть, для приведения к другому типу надо указать в скобках нужный тип. Поэтому в коде нашего теста
     takeScreen напишем TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
     Мы сказали: преврати наш драйвер в TakesScreenshot.

     Кроме того, мы в WebTesting создали отдельный дирректорий, куда будут сохраняться наши скрины и
     прописали данный путь в тесте "_output/result.png".
     Чтобы данные скрины не сохранять в репозитории гитхаб, мы заходим в слева в файл .gitignore и прописываем
     наименование данного дирректория _output. Теперь idea не будет предложено сохранить данный файл в гитхаб


     На любой странице, если перейдём на консоль разработчика во вкладку network, то снизу будут 2 события.
     Первый, это DOMContentLoaded - показывает время за которое считался html документ
     Второй, это Load - показывает произошла ли загрузка всех вложенных файлов (шрифтов, картинок, стилей и т.д.)
     */





}




