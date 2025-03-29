package LessonsPageObject.page_object;

/*Подробно про Page Object Model написано в классе MainPage*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage{

    public final By cartTitle = By.xpath("//span[@class='cart-title']");// Здесь просто изменили на public
    // и не придётся создавать доп. методы, как делали в классе MainPage и SearchResultPage

    public final By cartCounter = By.xpath("//span[contains(@class, 'j-cart-count')]");


    public CartPage(WebDriver driver) {
         super(driver);//это обращение к родительскому конструктору
    }

    public void open() {
        driver.get("https://www.labirint.ru/cart/");
    }


}



