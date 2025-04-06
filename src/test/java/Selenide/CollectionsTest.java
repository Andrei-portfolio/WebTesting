package Selenide;

import Selenide.component.BookCardComponent;
import Selenide.page_object.CartPage;
import Selenide.page_object.MainPage;
import Selenide.page_object.SearchResultPage;
import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.util.List;

//import static Selenide.HasChildCondition.hasChild;
import static Selenide.HasChildCondition.hasChild;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class CollectionsTest {

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserPosition = "2500x50";
        Configuration.browserSize = "1024x768";
        Configuration.timeout = 20000;
        Configuration.headless = false;
    }

    @Test
    public void testList() {
        open("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        SelenideElement button = $("#my-dropdown-1");
        button.click();
        SelenideElement elementUl = button.sibling(0);
        ElementsCollection liList = elementUl.findAll("li");//найди все элементы, содержащие "li"

        liList.shouldHave(size(5)).filter(hasChild("a")).shouldHave(size(4));// отфильтруй список,
        //оставь только те у которых есть тег "a". Существующих Condition для этого нет, поэтому для
        // hasChild создадим свой Condition (кондишн). Для этого создадим отдельный класс HasChildCondition
        //Condition - это проверки состояния элементов
    }

    @Test
    public void testList2() {
        open("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        SelenideElement button = $("#my-dropdown-1");
        button.click();
        SelenideElement elementUl = button.sibling(0);
        ElementsCollection liList = elementUl.findAll("li");
        SelenideElement firstLi = liList.shouldHave(size(5)).first();//Верни мне первый элемент "li"

        firstLi.shouldBe(hasChild("a"));
    }

}

