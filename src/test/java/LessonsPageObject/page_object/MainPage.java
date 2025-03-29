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

Здесь представлен Page Object на главную страницу. Подробно про Page Object Model написано в классе MainPage

ВАЖНО!!! Можно ли ассерты выносить в отдельные методы в Page Object? На этот счёт есть два мнения. Преподаватель за то,
чтобы не выносить. Но есть и другая группа, которые считают, что нужно выносить. Лучше их оставлять в тестах

ВАЖНО!!! У Page Object Model есть ответвление Page Object and Companents Model, т.е. страницы и компаненты.
В чём суть. Если про плюсы Page Object Model, то
- с его помощью можно использовать код повторно;
- наглядно видно, что есть в нашем методе ещё.

Page Object and Companents Model это почти тоже что и Page Object Model, но его используют, когда какой-то
элемент встречается на нескольких страницах или вообще на всех. Например, поисковая строка. То здесь всё просто,
создаётся класс, BasePage, в переводе базовая страница.*/

import LessonsPageObject.component.BookCardComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    public BookCardComponent bookCardComponent;

    public MainPage(WebDriver driver) {
        //driver = new ChromeDriver();
        //this.driver = driver;
        //Обратить вниманее, что к коду выше, где мы создавали driver = new ChromeDriver(), мы закомитили,
        //иначе таст падает, так как открывается два драйвера, один в данном классе из метода open, другой
        //из LabirintTest из метода setUp. Теперь, получается мы не ОБЪЯВЛЯЕМ драйвер, а ПРИНИМАЕМ его
        super(driver);//это обращение к родительскому конструктору
        bookCardComponent = new BookCardComponent(driver, wait);
    }

    public void open() {//Т.к. класс изначально называется MainPage, то метод openMainPage изменили на open
        driver.get("https://www.labirint.ru/");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));// Добавляем Cookie
        driver.navigate().refresh();// Обновление страницы, после чего окно "Принять" пропадает
    }
}



