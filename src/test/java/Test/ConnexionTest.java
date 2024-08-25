package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//Connexion File2
public class ConnexionTest {

    private WebDriver driver;
      private String url;
      
      public ConnexionTest(String url) {
    	  
    	this.url = "https://dev.accorhotels.projets-en-cours.net/fr/user/login";
         }
        
    public void ouvrirNavigateur(String navigateur) {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Zrida Mohamed\\Desktop\\Jihene\\selenium\\gecko\\geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        // Vous pouvez ajouter d'autres options si nécessaire

        driver = new FirefoxDriver(options);
        driver.get(url);
    }
    
    public void seConnecter() {
        // Logique de connexion à la page web
        try {
    	driver.manage().window().maximize();
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement cookies = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")));        cookies.click();
            cookies.click();
            WebElement adresse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));

        adresse.sendKeys("imen.makni@symdrik.com");

        WebElement password = driver.findElement(By.id("edit-pass"));

        password.sendKeys("AdminSymdrik2020!");

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"edit-submit\"]"));

        loginButton.click();

    } catch (Exception e) {
        System.out.println("Une erreur est survenue lors de la connexion : " + e.getMessage());
    }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
