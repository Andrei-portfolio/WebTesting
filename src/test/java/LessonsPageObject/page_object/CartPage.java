package LessonsPageObject.page_object;

/*Подробно про Page Object Model написано в классе MainPage*/

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage{

    //public final By cartTitle = By.xpath("//span[@class='cart-title']");// Здесь просто изменили на public
    // и не придётся создавать доп. методы, как делали в классе MainPage и SearchResultPage
    /*Закомитили выше, так как здесь в данном классе приведём пример использования аннотации FindBy
    Аннотация, благодаря которой можно раз и навсегда заменить метод findElement (поиск элементов) в автотестах.
    Что делает данная аннотация? Ничего не делает, пока мы в кострукторе не пропишем
    PageFactory.initElements(driver, this). Прописали в классе BasePage. Как результат, мы теперь не будем писать driver.findElement и заменим
    его */

    @FindBy(xpath ="//span[@class='cart-title']")
    public WebElement cartTitle;//данный код нужен при создании аннотации @FindBy

    public final By cartCounter = By.xpath("//span[contains(@class, 'j-cart-count')]");


    public CartPage(WebDriver driver) {
         super(driver);//это обращение к родительскому конструктору
        this.cartTitle = cartTitle; //Данная строка кода вызывает нашу аннотацию @FindBy
    }

    @Step("Открыть страница корзина")// Данная аннотация, позволяеть  увидеть данный комментарий в allure отчёте, если этот
    // метод используется в автотесте. И если автотест полетит, то будет видно, на каком шаге
    public void open() {
        driver.get("https://www.labirint.ru/cart/");
    }


}



