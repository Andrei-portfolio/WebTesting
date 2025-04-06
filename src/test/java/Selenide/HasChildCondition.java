package Selenide;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

// В данном классе будем создавать свой Condition, чтобы использовать его в классе CollectionsTest

public class HasChildCondition extends WebElementCondition {
    private final String tagName;

    protected HasChildCondition(String tagName) {
        super(tagName);
        this.tagName = tagName;
    }

    // static factory method - паттерн
    public static HasChildCondition hasChild(String tagName) {
        return new HasChildCondition(tagName);
    }

    @Override
    public CheckResult check(Driver driver, WebElement element) {
        try {
            element.findElement(By.cssSelector(tagName));
            return CheckResult.accepted();
        }
        catch (NoSuchElementException e) {
            return CheckResult.rejected("Element has no any children", "");
        }
    }
}