package WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*ВАЖНО!!! Еще один сайт, где можно оттачить свои навыки для создания автотестов. Там множество разных кейсов
 http://www.uitestingplayground.com/textinput*/


public class SendKeysDemo {//Тесты связанные с вводом данных

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options); // 1. Запускается драйвер 2. Драйвер запускает браузер
        driver.manage().window().setPosition(new Point(2500, 50));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Применение метода sendKeys")
    public void sendKeys() {// Пример теста, где изменяем наименование кнопки и проверяем потом, что оно изменилось
        driver.get("http://www.uitestingplayground.com/textinput");// 1. мы заходим на страничку
        System.out.println(driver.findElement(By.tagName("body")).getText());// выводит в консоль полное содержимое ,
                                         // нашего body.  Возможно и инфо, как кнопка называлась, до нашего переименования
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));// 2. находим поле, куда вводим текст "Knopka" для кнопки
        inputName.sendKeys(buttonName);// как ввести значение?
        WebElement button = driver.findElement(By.cssSelector(".btn"));// 3. а это собственно, сама кнопка. Вводим текст "Knopka"
        button.click(); // 4. находим эту кнопку
        String newButtonName = button.getText();// 5.Кнопка должна переименоваться в то, что мы написали
        /*Метод getText() мы из кнопки получает всё её текстовое содержимое*/
        assertEquals(buttonName, newButtonName);


    }

    @Test
    @DisplayName("Применение метода sendKeys 2")
    public void sendKeysAndBackSpace() {//удаление названия нашей кнопки с помощью кнопки BackSpace на клавиатуре
        driver.get("http://www.uitestingplayground.com/textinput");
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);
        inputName.sendKeys(Keys.BACK_SPACE);// в Keys храняться все доступные нам кнопки на клавиатуре, в т.ч. BACK_SPACE,
         // т.к. Keys имплементирует метод CharSequence, в котором перечисляются все кнопки клавиатуры.
        // С помощью BACK_SPACE удалим одну из букв названия кнопки.
        inputName.sendKeys(Keys.BACK_SPACE);

        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        String newButtonName = button.getText();
        assertEquals("Knop", newButtonName);// Ожидаем "Knop", т.к. две последние буквы удалили
    }

    @Test
    @DisplayName("Пример использования метода chord")
    public void copyPaste() {
        driver.get("http://www.uitestingplayground.com/textinput");
        String buttonName = "Knopka";
        WebElement inputName = driver.findElement(By.cssSelector(".form-control"));
        inputName.sendKeys(buttonName);
        inputName.sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_UP));// SHIFT + ARROW_UP выделяем наш текст
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "c"));// CONTROL + С копируем текст
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "v"));//  CONTROL + V вставляем текст
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "v"));//  CONTROL + V вставляем текст ещё раз
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "v"));//  CONTROL + V вставляем текст ещё раз

        /* В данном тесте пример использования метода chord, который позволяет нажать на клавиатуре две клавиши
        одновременно */

        WebElement button = driver.findElement(By.cssSelector(".btn"));
        button.click();
        String newButtonName = button.getText();
        assertEquals("KnopkaKnopkaKnopka", newButtonName);
    }


    @Test
    @DisplayName("Пример полноценной загрузки файла")
    @Disabled("Тест не рабочий, т.к. нужно указывать собственный путь к собственному html файлу")
    public void uploadFile() {
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement inputFile = driver.findElement(By.cssSelector("#file-upload"));//поиск по id
        inputFile.sendKeys("C:\\Users\\Sammy\\Desktop\\работа\\4 модуль\\JavaLessons4Module\\src\\test\\resources\\file.html");//указываем путь

        WebElement button = driver.findElement(By.cssSelector("#file-submit"));// Кнопочка загрузить файл/картинку
        button.click();
        String uploadedName = driver.findElement(By.cssSelector("#uploaded-files")).getText();
        assertEquals("file.html", uploadedName);// ожидаемое название нашего файла. НО дополнительно надо проверять через API,
        // через БД
    }
}