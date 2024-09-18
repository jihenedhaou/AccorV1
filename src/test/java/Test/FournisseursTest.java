package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FournisseursTest{
    private String downloadDirectory = "C:\\Users\\Zrida Mohamed\\Téléchargements";
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
        System.err.println("Recherche: SUCCESS");
    }
    @Test(dependsOnMethods = "ouvrirPageFournisseur")
    public void exporterFournisseursEtVerifierCSV() throws InterruptedException, IOException {

        WebElement exportButton = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[2]/div[1]/div/a")); // Localiser le bouton d'export
        exportButton.click();
        // Nom du fichier à vérifier après téléchargement
        String fileName = "suppliers";

        // Attendre que le fichier soit téléchargé et vérifié
        FileDownloadWaiter waiter = new FileDownloadWaiter(driver, downloadDirectory);
        waiter.waitForFileDownload(fileName, Duration.ofMinutes(1));

        // Vérifier que le fichier est présent et a été téléchargé correctement
        File downloadedFile = new File(downloadDirectory, fileName);
        Assert.assertTrue(downloadedFile.exists(), "Le fichier téléchargé est introuvable.");
        Assert.assertTrue(downloadedFile.length() > 0, "Le fichier téléchargé est vide.");
    }
    //@AfterTest
   // public void tearDown() {
        // Actions de nettoyage après tous les tests
      //  driver.quit();
   // }
    }