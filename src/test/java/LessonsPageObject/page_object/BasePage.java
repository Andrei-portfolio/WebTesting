package LessonsPageObject.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*ВАЖНО!!! У Page Object Model есть ответвление Page Object and Companents Model, т.е. страницы и компаненты.
В чём суть. Если про плюсы Page Object Model, то
- с его помощью можно использовать код повторно;
- наглядно видно, что есть в нашем методе ещё.
ВАЖНО!!! Page Object and Companents Model это почти тоже что и Page Object Model, но его используют, когда какой-то
элемент встречается на нескольких страницах или вообще на всех. Например, поисковая строка. То здесь всё просто,
создаётся класс, BasePage, в переводе "базовая страница". И туда кидаем всё общее, что есть на всех страницах.

ОЧЕНЬ ВАЖНО!!! Делаем данный класс public ABSTRACT class BasePage. Для чего же делаем abstract. Этот класс BasePage
не должен быть, т.е. не должно быть экземпляров класса BasePage. Потому что BasePage, это "базовая страница.
Это БАЗА, ШАБЛОН, ОСНОВА, это не реальная страница, её не существует, поэтому делаем её абстрактной.

ТЕПЕРЬ ВСЕ КЛАССЫ НАСЛЕДУЮТСЯ ОТ BasePage. А для этого к каждому из классов page_object (их названию)
припишем extends BasePage*/

public abstract class BasePage {

    private final By searchField = By.xpath("//input[@id='search-field']");

    public final WebDriver driver;// обращу вниманее, сделали protected, чтобы можно было обратиться

    public final WebDriverWait wait;


    public BasePage (WebDriver driver) {
        //driver = new ChromeDriver();
        this.driver = driver;
        //Обратить вниманее, что к коду выше, где мы создавали driver = new ChromeDriver(), мы закомитили,
        //иначе таст падает, так как открывается два драйвера, один в данном классе из метода open, другой
        //из LabirintTest из метода setUp. Теперь, получается мы не ОБЪЯВЛЯЕМ драйвер, а ПРИНИМАЕМ его
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // явное ожидание
    }

    public void findBook(String name) {
        driver.findElement(searchField).sendKeys(name, Keys.RETURN);
        //Находим поисковую строку с помощью xpath, пишем там java и кликаем на RETURN (это enter на обычной клаве)
    }
}
