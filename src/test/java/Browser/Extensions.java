package Browser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Extensions { private WebDriver driver;

    /*В данном классе представлен тест с применением расширений браузера. Это расширение находится в браузере в
    верхнем правом углу. Но у нас его нет, т.к. мы запускаем его с чистого листа, в нём нет никаких расширений.
    Т.к. это не инкогнито, мы можем устанавливать туда расширения. Чтобы его установить, например, если мы хотим
    проверить как наша страница работает с расширением VPN. Для этого нужно скачать User Agent Switcher. Ну а чтобы
    была возможность скачивать расширения и прочее, нужно ОБЯЗАТЕЛЬНО сначала установить CRX Extractor/Downloader. Для этого
    перейдем по ссылке https://chromewebstore.google.com/detail/crx-extractordownloader/ajkhmmldknmfjnmeedkbkkojgobmljda.
    и установим.
    Далее, в том же хроме браузере вверху после строки с сылкой идет значок, что то типо квадратика. Нажимаем
    его и находим Download as CRX. Потом я так понял, найдем User Agent Switcher, или перейдём по ссылке
    https://chromewebstore.google.com/detail/user-agent-switcher/kchfmpdcejfkipopnolndinkeoipnoia и тоже
    установим.
     После чего, наш скачанный файлик User Agent Switcher мы перетаскиваем в IDEA. Для этого заранее создадим
     новый директорий resources в пакете test и перенесём его туда.*/


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();// Добавляем расширение
        options.addExtensions(new File("src/test/resources/User-Agent-Switcher-for-Chrome-Chrome.crx"));
        // Выше указываем путь к файлу с расширением User Agent Switcher
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
    public void testWindow()  {

        driver.get("https://the-internet.herokuapp.com/windows");

    }// здесь поставим дебаг, чтобы проверить. В открывшемся окне нажимаем на квадратик и там будет указано
    // наше расширение
}