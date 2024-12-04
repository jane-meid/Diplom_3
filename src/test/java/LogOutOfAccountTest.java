import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;
import user.User;
import user.UserApi;
import user.UserCreate;

import java.util.concurrent.TimeUnit;

public class LogOutOfAccountTest extends WebDriverCreator {
    private User user;
    private WebDriver driver;
    private String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        driver.get(UserApi.BASE_URL);
        RestAssured.baseURI = UserApi.BASE_URL;
        user = UserCreate.getRandomUser();
        accessToken = UserApi.createNewUser(user).then().extract().path("accessToken");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void logOutOfAccountTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        profilePage.clickLogoutButton();
        String text = loginPage.getEnterLabelText();
        Assert.assertEquals("Вход", text);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }
}
