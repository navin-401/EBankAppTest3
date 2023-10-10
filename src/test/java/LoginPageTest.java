import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(EBankAppReport.class)
public class LoginPageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Getting started with Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testLoginWithEmptyInputs() {
        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        WebElement errorMessage = driver.findElement(By.className("error-message-text"));

        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, "Invalid user ID", "Error text with empty input fields does not match");
    }

    @Test(priority = 2)
    public void testLoginWithEmptyUserId() {
        WebElement pinInput = driver.findElement(By.id("pinInput"));
        pinInput.sendKeys("231225");

        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        WebElement errorMessage = driver.findElement(By.className("error-message-text"));

        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, "Invalid user ID", "Error text with empty User ID do not match");
    }

    @Test(priority = 3)
    public void testLoginWithEmptyPin() {
        WebElement userIdInput = driver.findElement(By.id("userIdInput"));
        userIdInput.sendKeys("142420");

        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        WebElement errorMessage = driver.findElement(By.className("error-message-text"));

        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, "Invalid PIN", "Error text with empty PIN do not match");
    }

    @Test(priority = 4)
    public void testLoginWithInvalidCreds() {
        WebElement userIdInput = driver.findElement(By.id("userIdInput"));
        userIdInput.sendKeys("142420");

        WebElement pinInput = driver.findElement(By.id("pinInput"));
        pinInput.sendKeys("123456");

        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        WebElement errorMessage = driver.findElement(By.className("error-message-text"));

        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, "User ID and PIN didn't match",
                "Error text with invalid PIN do not match");
    }

    @Test(priority = 5)
    public void testLoginWithValidCreds() {
        WebElement userIdInput = driver.findElement(By.id("userIdInput"));
        userIdInput.sendKeys("142420");

        WebElement pinInput = driver.findElement(By.id("pinInput"));
        pinInput.sendKeys("231225");

        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        String expectedUrl = "https://qaebank.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
    }
}
