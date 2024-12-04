package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By bunButton = By.xpath(".//span[text()='Булки']");
    private By sauceButton = By.xpath(".//span[text()='Соусы']");
    private By fillingButton = By.xpath(".//span[text()='Начинки']");
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By menuTabLocator = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickAccountButton() {
        driver.findElement(profileButton).click();
    }

    public void clickBunButton() {
        driver.findElement(bunButton).click();
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public String getCreateOrderButtonText(){
        return driver.findElement(createOrderButton).getText();
    }

    public String getMenuTabLocator(){
        return driver.findElement(menuTabLocator).getText();
    }
}
