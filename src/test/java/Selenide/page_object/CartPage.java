package Selenide.page_object;

/*Подробно про Page Object Model написано в классе MainPage*/

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {

    public final SelenideElement cartTitle = $x("//span[@class='cart-title']");

    public final SelenideElement cartCounter = $x("//span[contains(@class, 'j-cart-count')]");

    @Step("Открыть страница корзина")// Данная аннотация, позволяеть  увидеть данный комментарий в allure отчёте, если этот
    // метод используется в автотесте. И если автотест полетит, то будет видно, на каком шаге
    public void open() {
        Selenide.open("https://www.labirint.ru/cart/");
    }


}



