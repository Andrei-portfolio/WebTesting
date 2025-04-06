package Selenide.page_object;


import Selenide.component.BookCardComponent;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage extends BasePage {
    private final ElementsCollection bookCardElements = $$(".product-card");

    private final SelenideElement header = $x("//h1");

    public BookCardComponent getBookCardComponent() {
        SelenideElement bookCardElement = bookCardElements.get(0);
        return new BookCardComponent(bookCardElement);
    }

    public List<BookCardComponent> getBookCardComponents() {
        List<BookCardComponent> bookCardComponents = new ArrayList<>();

        bookCardElements.forEach(bookCardElement -> bookCardComponents.add(new BookCardComponent(bookCardElement)));

        return bookCardComponents;
    }

    public String getHeaderText() {
        return header.getText();
    }
}


