package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {
    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }
        switch (browser) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\WinUsers\\Acer\\Downloads\\chromedriver-win64(128)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe ");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\WinUsers\\Acer\\Downloads\\chromedriver-win64(128)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Acer\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        return new ChromeDriver(options);
    }
}
