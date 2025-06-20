import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class SendPhotoTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://creaphoto.su/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.p-menuitem-text"))).getText().equals("Вход")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[data-cy='nickName']"))).sendKeys(Config.getProperty("right.login"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[data-cy='password']"))).sendKeys(Config.getProperty("right.password"));
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/main/div/div/form/div/div[4]/button"))).click();
        }

    }
    @Test
    public void testSendPhoto(){
        driver.get("https://creaphoto.su/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class, 'text-white-80') and contains(@class, 'group-hover') and text()='Отправить произведение']"))).click();
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Получить кешбэк']"))).getText();

        assertEquals(value, "Получить кешбэк");

        value = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Правила конкурса']"))).getText();

        assertEquals(value, "Правила конкурса");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
