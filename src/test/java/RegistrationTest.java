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
import pageObject.RegistrationPage;
import user.User;
import user.UserApi;
import user.UserCreate;
import user.UserLogin;

import java.util.concurrent.TimeUnit;

public class RegistrationTest extends WebDriverCreator {
    private User user;
    private WebDriver driver;
    private String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        driver.get(UserApi.BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registrationUserTest() {
        user = UserCreate.getRandomUser();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registrationPage.setUserInfo(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        String text = loginPage.getEnterLabelText();
        Assert.assertEquals("Вход", text);
        UserLogin login = new UserLogin(user.getEmail(), user.getPassword());
        RestAssured.baseURI = UserApi.BASE_URL;
        accessToken = UserApi.loginUser(login).then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registrationUserWithWrongPasswordTest() {
        user = UserCreate.getRandomUserWithWrongPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registrationPage.setUserInfo(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        String text = registrationPage.getPasswordErrorText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }
}
