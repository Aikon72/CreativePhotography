import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import static org.testng.Assert.*;

public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void testSuccessfulLogin(){
        driver.get("https://creaphoto.su");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/div/div/div[4]/button[1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("input"))).sendKeys(Config.getProperty("right.login"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("password"))).sendKeys(Config.getProperty("right.password"));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[6]/div/span[2]"))).getText();
        assertEquals(value, "Псевдоним или пароль - не корректны");
    }

    @Test
    public void testUnsuccessfulLogin() {
        driver.get("https://creaphoto.su");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/div/div/div[4]/button[1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("input"))).sendKeys("Aikon");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[6]/div/span[2]"))).getText();
        assertEquals(value, "Псевдоним или пароль - не корректны");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
