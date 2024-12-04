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
import pageObject.PasswordRecoveryPage;
import pageObject.RegistrationPage;
import user.User;
import user.UserApi;
import user.UserCreate;

import java.util.concurrent.TimeUnit;

public class LogInTest extends WebDriverCreator {
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInFromMainPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void logInFromProfilePageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickAccountButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void logInFromRegistrationPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickAccountButton();
        loginPage.clickRegisterButton();
        registrationPage.clickLoginButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void logInFromPasswordRecoveryPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        mainPage.clickAccountButton();
        loginPage.clickForgotPasswordButton();
        passwordRecoveryPage.clickLoginButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        String text = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", text);
    }

    @After
    public void tearDown(){
        driver.quit();
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }
}
