package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class News {
    Random random;
    private ConnexionTest connexion;
    private WebDriver driver;
    private WebDriverWait wait;


    public News(String url) {
        this.connexion = new ConnexionTest(url);
        connexion.ouvrirNavigateur("firefox"); // Utilisation de Firefox ici
        connexion.seConnecter();
        this.driver = connexion.getDriver();
        // Initialisation de WebDriverWait pour l'ensemble de la classe avec un temps d'attente de 10 secondes
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.random = new Random(); // Initialisation de l'objet Random ici

    }

    @Test
    public void ouvrirPageNews() {
        WebElement elementnews = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div/div/div[2]/nav/ul/li[5]/a")));
        elementnews.click();
        System.err.println("ouverture PageNews: SUCCESS");
    }


    @Test(dependsOnMethods = "ouvrirPageNews")
    public void FiltreParCategorie() {

        WebElement boutonFiltreCategorie = driver.findElement(By.id("edit-subcategory-collapsible"));
        boutonFiltreCategorie.click();
        System.err.println("Le bouton pour sélectionner la catégorie a été cliqué.");

        // Étape 1 : Attendre que la liste des catégories soit visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-subcategory")));

        List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@id='edit-subcategory']//input[@type='checkbox']"));
        System.err.println("La liste des catégories est maintenant visible.");

        // Étape 2 : Sélectionner une ou plusieurs cases à cocher de manière aléatoire
        int nombreDeSelections = random.nextInt(checkboxes.size()) + 1; // Sélectionne au moins une case à cocher
        for (int i = 0; i < nombreDeSelections; i++) {
            WebElement checkbox = checkboxes.get(i);
            checkbox.click();
        }
        System.err.println("clic un elementLe nombre de cases à cocher sélectionnées aléatoirement est : ");


        // Étape 3 : Cliquer sur le bouton pour appliquer le filtre
        WebElement boutonFiltrer = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/div/div/form/button"));
        boutonFiltrer.click();
        System.err.println("Le filtre a été appliqué en cliquant sur le bouton approprié.");

        // Étape 4 : Vérifier la présence de la card "Choix de catégorie"
        WebElement choixCategorieCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".news-filters-selected")));
        Assert.assertTrue(choixCategorieCard.isDisplayed(), "La card 'Choix de catégorie' n'est pas présente après avoir appliqué le filtre.");
        System.err.println("La section 'Choix de catégorie' est bien visible après l'application du filtre.");
        String cardText = choixCategorieCard.getText(); // Récupère le texte contenu dans l'élément

// Affichage dans System.err
        System.err.println("Carte trouvée : " + cardText);

        // Étape 5 : Vérifier les résultats affichés (présence de blocs avec titre, catégorie et image)
        //List<WebElement> resultats = driver.findElements(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/div/div/ul"));
        //System.err.println("verifier resultat");

        // Étape 6 : Vérifier le nombre des choix , est ce qu'ils sont les meme dans le résultat ou non"
        List<WebElement> cartesAffichees = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".news-filters-selected li")));

        // Étape 5 : Vérifier que le nombre d'éléments affichés est correct
        int nombreDeCartesAffichees = cartesAffichees.size();
        if (nombreDeSelections == nombreDeCartesAffichees) {
            System.err.println("Vérification réussie : Le nombre de sélections (" + nombreDeSelections + ") correspond au nombre de cartes affichées (" + nombreDeCartesAffichees + ").");
        } else {
            System.err.println("Échec de la vérification : Le nombre de sélections (" + nombreDeSelections + ") ne correspond pas au nombre de cartes affichées (" + nombreDeCartesAffichees + ").");
        }

    }
    @AfterTest
    public void tearDown() {
        try {
            Thread.sleep(60000); // Pause de 60 secondes
        } catch (InterruptedException e) {
            e.printStackTrace(); // Gérer l'exception si l'attente est interrompue
        }

        // Actions de nettoyage après tous les tests
        driver.quit();
    }
}

