package Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = com.Test.utils.WebDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        driver.get(ConfigReader.getProperty("https://dev.accorhotels.projets-en-cours.net/fr/user/login"));
    }

    @Test
    public void testValidLogin() {
        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPassword");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid credentials");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
