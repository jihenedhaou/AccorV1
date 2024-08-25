package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FournisseursTest{

	private ConnexionTest connexion;
    private WebDriver driver;

	public  FournisseursTest(String url) {

        this.connexion = new ConnexionTest(url);
        connexion.ouvrirNavigateur("firefox"); // Utilisation de Firefox ici
        connexion.seConnecter();
        this.driver = connexion.getDriver();
    }
    @Test
    public void ouvrirPageFournisseur() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementfournisseur = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div/div/div[2]/nav/ul/li[7]/a")));
        elementfournisseur.click();
    }
	@Test(dependsOnMethods = "ouvrirPageFournisseur")
    public void Recherche() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementfournisseur = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div/div/div[1]/div[1]/div/div[1]/div[1]/div[2]")));
        elementfournisseur.click();
     }
    }