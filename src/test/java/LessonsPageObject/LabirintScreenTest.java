package LessonsPageObject;

import LessonsPageObject.component.BookCardComponent;
import LessonsPageObject.ext.CartPageResolver;
import LessonsPageObject.ext.ChromeDriverHelper;
import LessonsPageObject.ext.MainPageResolver;
import LessonsPageObject.ext.SearchResultPageResolver;
import LessonsPageObject.page_object.CartPage;
import LessonsPageObject.page_object.SearchResultPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MainPageResolver.class)
@ExtendWith(CartPageResolver.class)
@ExtendWith(SearchResultPageResolver.class)
@ExtendWith(ChromeDriverHelper.class)

@Owner("John")// автор автотеста
@Link(url = "https://ya.ru", name = "Яндекс")// если хотим прикрепить, например ссылку к задаче
@Epic("Онлайн магазин книг")// В jira есть Epic, это большая задача, к которой прикрепляются более маленькие
@Feature("Поисковая система")// аннотация фича

public class LabirintScreenTest {

    @Test
    @DisplayName("Скриншотный тест")//аннотация названия теста, в т.ч для Allure
    @Description("Скриншотим поисковую страницу")//аннотация описания теста, в т.ч для Allure
    @Tag("Позитивный")
    public void ScreenTest(SearchResultPage searchResultPage, CartPage cartPage) throws IOException {//Применили наши Resolver из пакета exp
        cartPage.open();// вынесли в MaimPage, объявили данный класс mainPage. Говорим открой главную страницу

        //cartPage.searchField.getScreenshotAs(OutputType.FILE).renameTo(Path.of("toBe.png").toFile());
        // При первом запуске, нам нужен эталон скрина. Будем его хранить в  cartPage. После первого запуска,
        // наш эталон комитим, чтобы эталоны не плодить
        byte[] screenshotExpected = Files.readAllBytes(Path.of("toBe.png"));  // берем скрин как ДОЛЖНО БЫТЬ, эталонный
        byte[] screenshotActual = cartPage.searchField.getScreenshotAs(OutputType.BYTES);// берём настоящий скрин из сайта
        assertArrayEquals(screenshotExpected, screenshotActual);// сравниваем
    }

    /*Далее рассмотрим ещё один вариант скриншотного тестирования. Это когда беруться 2 картинки и
    накладываются друг на друга. НО для этого нам нудно установить в pom.xml. Скачаем прежде всего фрэймворк
    Yandex QATools AShot WebDriver Utility » 1.5.4, перейдя по ссылке. Он уже не обновляется, но пока жив
    https://mvnrepository.com/artifact/ru.yandex.qatools.ashot/ashot/1.5.4.*/

    @Test
    @DisplayName("Скриншотный тест")
    @Description("Скриншотим поисковую строку")
    @Tag("Позитивный")
    public void screenTestAshot(SearchResultPage searchResultPage, CartPage cartPage) throws IOException, IOException {
        cartPage.open();
//        BufferedImage screenshot = new AShot().takeScreenshot(cartPage.driver, cartPage.searchField).getImage();//делаем скрин
//        ImageIO.write(screenshot, "png", Path.of("toBeAShot.png").toFile());
        // При первом запуске, нам нужен эталон скрина. Будем его хранить в  ImageIO.write. После первого запуска,
        // наш эталон комитим, чтобы эталоны не плодить

        //cartPage.searchField.sendKeys("aaa"); Можно раскомитить, только тогда, если хотим, чтобы тест упал и посмотреть
        BufferedImage actual = new AShot().takeScreenshot(cartPage.driver, cartPage.searchField).getImage();// берем скрин как ДОЛЖНО БЫТЬ, эталонный
        BufferedImage toBe = ImageIO.read(Path.of("toBeAShot.png").toFile());// берём настоящий скрин из сайта
        ImageDiff diff = new ImageDiffer().makeDiff(toBe, actual);//сравнение

        ImageIO.write(diff.getDiffImage(), "png", Path.of("diffImage.png").toFile());
        ImageIO.write(diff.getMarkedImage(), "png", Path.of("markedImage.png").toFile());
        ImageIO.write(diff.getTransparentMarkedImage(), "png", Path.of("transparentImage.png").toFile());
/*Как видно выше, в данном фрэймворке есть 3 метода для сравнения, каждый из которых по своему удобен:
getDiffImage - ???????????
getMarkedImage - если тест падает, показывает наложенные друг на друга скрины
getTransparentMarkedImage - если тест падает, показывает только то место, где несоответствие*/

        byte[] screenTransparent = Files.readAllBytes(Path.of("transparentImage.png"));
        cartPage.attachPng(screenTransparent);
        assertFalse(diff.hasDiff(), "Сравнение изображения поисковой строки");
    }

}





