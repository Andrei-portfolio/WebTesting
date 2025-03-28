package LessonsPageObject.page_object;

/*Page Object Model -  это своего рода хелпер, но со своими правилами, в смысле оформления. Понятно, у нас были
API хелперы, хелперы БД, которые описывали повторяющеся поля. Это всё ясно. Но как быть с целым сайтом, где
большое количество страниц и если мы создадим общий хелпер и начнём туда кидать все наши элементы, получится
какая-то каша и мы запутаемся. А в Page Object Model можно делать хелперы, например для каждой страницы
тестируемого нами сайта.
Page Object Model - это даже не просто хелпер, а целый патерн описания интерфейса, или договорённость,
как создавать эти самые хелперы (помощники) для UI тестов.

ВАЖНО ОЧЕНЬ!!! ЕСТЬ УСЛОВНАЯ ДОГОВОРЁННОСТЬ, ЧТО В Page Object МЫ ПИШЕМ слово Page, иначе в коде не понятно,
что это такое.

Сначала начнём сюда выносить, всё что у нас в тестах находится на главной странице. ВАЖНО!!! @BeforeEach мы
сюда не выносим, мы не можем этого сделать, они должны храниться в тестовых классах
*/

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    private final By searchField = By.xpath("//input[@id='search-field']");

    private WebDriver driver;

    public MainPage() {
        driver = new ChromeDriver();
    }

    private void open() {//Т.к. класс изначально называется MainPage, то метод openMainPage изменили на open
        driver.get("https://www.labirint.ru/");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
    }

    private void findBook(String name) {
        driver.findElement(searchField).sendKeys(name, Keys.RETURN);
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)
    }

}



