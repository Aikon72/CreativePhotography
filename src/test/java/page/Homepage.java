package page;

import common.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BaseTest {

    @FindBy(xpath = "//span[@class='p-menuitem-text']")
    private WebElement contestRules;

    @FindBy(xpath = "//a[span[text()='О нас']]")
    private WebElement aboutUs;

    @FindBy(xpath = "//a[span[text()='Жюри']]")
    private WebElement joury;

    @FindBy(xpath = "//a[span[text()='Новости']]")
    private WebElement news;

    @FindBy(xpath = "//a[span[text()='Галерея']]")
    private WebElement gallery;

}
