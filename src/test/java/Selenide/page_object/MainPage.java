package Selenide.page_object;


import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.refresh;

public class MainPage extends BasePage {

    public void open() {
        Selenide.open("https://www.labirint.ru/");
    }

    public void acceptCookies(){
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("cookie_policy", "1"));
        refresh();
        }
    }





