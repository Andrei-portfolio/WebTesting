package Browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

        /*На любом сайте, если перейдём в консоль разработчика во вкладку network, то снизу будут 2 события.
        Первый, это DOMContentLoaded - показывает время за которое считался html документ
        Второй, это Load - показывает произошла ли загрузка всех вложенных файлов (шрифтов, картинок, стилей и т.д.).
        Получается, наши тест будут запускаться только тогда, когда загрузятся все вложенные файлы
        (шрифты, картинки, стили и т.д.) - Load.

        Так вот, чтобы не приходилось ждать, пока произойдет загрузка Load (от 8 сек. и более), нам нужно произвести
        настройку браузера. В нём есть много разных настроек. Для того, чтобы с ними работать в коде пропишем
         следующее ChromeOptions options = new ChromeOptions();

         enum это класс, который хранит экземпляры себя самого, экземпляры самого класса. Но это не всё так важно,
         а важно то зачем здесь enum. Ну а затем, что есть несколько видов стратегий загрузки страницы,
         т.е. стратегии работы метода get:
         первую стратегию мы уже знаем. Это когда код выполняется по умолчанию, после полной загрузки
         шрифтов, картинок, стилей (т.е. Load)
         вторая стратегия - это метод PageLoadStrategy с тремя стратегиями NONE, NORMAL, EAGER

         - NORMAL - это значение по умолчание, это значит, что мы ничего не меняем. Т.е. тест отработает
                    после загрузки Load
         - EAGER - означает, что тест запуститься не после загрузки Load, а после DOMContentLoaded. Т.е.
                   после считывания html, значительно быстрее
          Отмечено, что при использовании EAGER, тест может упасть,т.к. в DOMContentLoaded не успел
          загрузиться нужный нам элемент (кнопка, стиль и т.д.). На этот случай есть специальные методы
          ожидания, но это отдельная тема и мы будем изучать их позже. Суть их в том, что тест ожидает
          загрузку DOMContentLoaded и если нужный нам элемент прогрузился, то тест запускается. Если нет,
          то ждёт пока загрузится.
          - NONE - тест ничего не ждёт, ни DOMContentLoaded, ни Load. Это очень опасный элемент, т.к.
            запускаем тест без прогрузки всего*/

public class PageLoadStrategy {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();// Подробно расписано выше
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);// Подробно расписано выше.
        // Здесь PageLoadStrategy содержит enum
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
    public void testInfo() {
        driver.get("https://www.aviasales.ru/");
        System.out.println("Эта строчка выполнится после метода get");// нажать кнопку
    }
}

