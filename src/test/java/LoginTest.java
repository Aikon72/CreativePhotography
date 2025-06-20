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

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void testSuccessfulLogin(){
        driver.get("https://creaphoto.su/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-cy='nickName']"))).sendKeys(Config.getProperty("right.login"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-cy='password']"))).sendKeys(Config.getProperty("right.password"));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-cy=\"loginButton\"]"))).click();
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ml-5' and contains(text(), 'Тестировщик')]"))).getText();
        assertEquals(value, "Тестировщик");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("span.p-menuitem-icon.pi.pi-sign-out"))).click();
    }

    @Test
    public void testWrongPasswordLogin() {
        driver.get("https://creaphoto.su/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='nickName']"))).sendKeys(Config.getProperty("right.login"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='password']"))).sendKeys("1234");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[6]/div/span[2]"))).getText();
        assertEquals(value, "Псевдоним не корректен");
    }

    @Test
    public void testWrongNicknameLogin() {
        driver.get("https://creaphoto.su/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='nickName']"))).sendKeys("Tester");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='password']"))).sendKeys(Config.getProperty("right.password")+"2");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[6]/div/span[2]"))).getText();
        assertEquals(value, "Псевдоним не корректен");
    }

    @Test
    public void testEmptyNicknameLogin() {
        driver.get("https://creaphoto.su/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='nickName']"))).sendKeys("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='password']"))).sendKeys(Config.getProperty("right.password")+"2");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[6]/div/span[2]"))).getText();
        assertEquals(value, "Псевдоним не корректен");
    }

    @Test
    public void testEmptyPasswordLogin() {
                driver.get("https://creaphoto.su/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='nickName']"))).sendKeys(Config.getProperty("right.login"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-cy='password']"))).sendKeys("");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[6]/div/span[2]"))).getText();

        assertEquals(value, "Псевдоним или пароль - не корректны");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
