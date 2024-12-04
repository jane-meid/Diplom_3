import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.MainPage;
import user.UserApi;

import java.util.concurrent.TimeUnit;

public class ConstructorTest extends WebDriverCreator {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        driver.get(UserApi.BASE_URL);
        RestAssured.baseURI = UserApi.BASE_URL;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    @DisplayName("Переход к разделу Булки")
    public void bunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Булки", text);
    }
    @Test
    @DisplayName("Переход к разделу Соусы")
    public void sauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Соусы", text);
    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void fillingSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Начинки", text);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
