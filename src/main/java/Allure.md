*********************************Allure********************************

Allure это инструмент для формирования отчёта по запущенной сборке автотестов.

Документацию можно посмотреть на сайте http://allurereport.org/docs/frameworks. Там же есть очень много
фрэймворков, которые работают с Allure. Он работает с множеством библиотек на разных языках, например 
PHPUnit, Playwright. Так же там есть, подходящий для нас JUnit 5. 
Итак, чтобы установить Allure (https://allurereport.org/docs/junit5/) нужно следующее. Но здесь важно 
не запутаться, потому что важно в правильные места разложить.
Для этого, с данного сайта скопируем код для нашего pom.xml.

Сначала скопируем и вставим версию <allure.version>2.25.0</allure.version>. Обращу вниманее, что 
данную строку вставляем после вот этих строк в pom.xml.

<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

Далее, копируем с того же сайта dependencyManagement и вставляем, НО только не в  </properties> 
и не в </dependencies>, а отдельно. К примеру, перед </project>.

<dependencyManagement>
<dependencies>
<dependency>
<groupId>io.qameta.allure</groupId>
<artifactId>allure-bom</artifactId>
<version>${allure.version}</version>
<type>pom</type>
<scope>import</scope>
</dependency>
</dependencies>
</dependencyManagement>

   Ну и последнее, вставляем, куда обычно 
   
<dependency>
   <groupId>io.qameta.allure</groupId>
   <artifactId>allure-junit5</artifactId>
   <scope>test</scope>
</dependency>

ГОТОВО!!!!!!!!!!!!!!!!!!!!!!!!!!

Ну а далее запускаем любые наши автотесты, И У НАС ПОЯВЛЯЕТСЯ СЛЕВА НА ПАНЕЛИ ПАПКА, allure-results. 
Что в этих файликах, нам не важно. Но в них очень много информации. Это и есть отчёт, который мы видели,
но не читабельный, в формате json. Это всё отправилость в IDEA. Эти папки мы поместили в gitignore, чтобы
программа не предлагала сохранять их каждый раз. Чтобы открыть эти файлы используем непосредственно 
программу Allure. Ну а до этого мы просто установили зависимости, чтобы создались файлы в формате json. 
Но как было сказано выше, чтобы открыть их в нормальном html виде нужна программа. Установим по 
ссылке https://allurereport.org/docs/install-for-windows/.
Сначало необходимо установить Scoop (https://github.com/ScoopInstaller/Install#readme) и ХомРЮ. 
При установке Scoop обязательно необходим Windows PowerShell 5.1 и именно этой версии. В общем идём по
инструкции. Возможно будут ошибки, но ничего страшного. Продолжать идти по инструкции. После того, 
как установиться Scoop, в терминале напишем команду: scoop install allure. И всё.

Для сведения. Далее через Scoop можно установить всё что угодно, даже git, java и т.д.. После установки, 
сможем обращаться к нему через терминал в Idea. Нам нужна только одна команда: 
allure serve (но до команды нужно запустить тесты наши, и дождаться пока они отработают). 
Команда берёт нашу папку allure-results и рядом с ней создаёт папку где-то во временной директории и 
создаёт отчёт в формате html и запускается сервер, где видим отчёт по запущенным тестам.
Чтобы остановить сервер и закрыть его, нужно нажать в терминале idea - "Ctrl + C".
На сейчас, достаточно одной команды в терминал, allure serve. Но в будующем, на сложных проектах, нужно
знать, что данная команда состоит из 2х команд:
allure generate (генерирует отчет) и allure open (открывает сгенерированный отчёт). 
Если результаты автотестов нам не нужны, то пакеты allure-results и allure-report мы можем  удалить, 
т.к. они занимают много места. В следующий запуск они появятся 

Более подробно про аннотации для allure по ссылке https://allurereport.org/docs/junit5-reference/

Интересный момент, что делать, если хотим, чтобы в allure были рассписаны наши шаги теста и было видно,
на каком шаге он упал. Т.е. как же пошагово записать выполнение тестов? Для этого, смотрим наш тест, и
допустим, идём сверху вниз по его методам. Первый метод cartPage.open(). данный метод у нас в классе
CartPage. Над данным методом пишем аннотацию @Step и в скобках название шага @Step("Открыть страница корзина").
Это касается методов, которые мы писали сами. А что делать, есть это будет не наш метод, а интерфеса например.
Мы не сможем там ничего написать. Для этого есть второй вариант использования @Step. Мы пишем Step
в самом тесте


Но чтобы в тесте мы увидели данный шаг, НАМ НУЖНО в pom.xml кое что доустановить. Это AspectJ
по ссылке https://allurereport.org/docs/junit5/. Сначала копируем версию, и как при установке алюр, 
вставляем в pom.xml  <aspectj.version>1.9.21</aspectj.version>. Далее копируем и вместо точек, 
вставляем плагин в самом низу pom, 


 <build>
        <plugins>
 ............................................................   
        </plugins>
</build>

А это сам плагин с сайта:


<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.3</version>
    <configuration>
        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
        </argLine>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
    </dependencies>
</plugin>

