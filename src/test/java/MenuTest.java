import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import static org.testng.Assert.*;

public class MenuTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @Test
    public void testRules(){
        driver.get("https://creaphoto.su/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='p-menuitem-text' and text()='Правила конкурса']"))).click();
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Получить кешбэк']"))).getText();

        assertEquals(value, "Получить кешбэк");

        value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Правила конкурса']"))).getText();

        assertEquals(value, "Правила конкурса");
    }

    @Test
    public void testAboutUs(){
        driver.get("https://creaphoto.su/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='p-menuitem-text' and text()='О нас']"))).click();
        String value = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//strong[text()='Ассоциации фотографов «Евразия»']"))).getText();

        assertEquals(value, "Ассоциации фотографов «Евразия»");
    }

    @Test
    public void testJoury() {
        driver.get("https://creaphoto.su/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='p-menuitem-text' and text()='Жюри']"))).click();
        String value = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//strong[text()='Сергей Майоров']"))).getText();

        assertEquals(value, "Сергей Майоров");
    }

    @Test
    public void testNews(){
        driver.get("https://creaphoto.su/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='p-menuitem-text' and text()='Новости']"))).click();
        String value = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[text()='Новости конкурса']"))).getText();

        assertEquals(value, "Новости конкурса");
    }

    @Test
    public void testGalery()     {
        driver.get("https://creaphoto.su/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='p-menuitem-text' and text()='Галерея']"))).click();
        String value = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.uppercase.text-semi-bright.mb-5.text-4xl.text-center.mt-10"))).getText();

        assertEquals(value, "2025");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
