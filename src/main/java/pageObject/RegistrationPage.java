package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;
    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By loginButton = By.xpath(".//a[text()='Войти']");
    private By passwordError = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void setUserInfo(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}
