package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("edit-name");
    private By passwordField = By.id("edit-pass");
    private By loginButton = By.id("edit-submit");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with elements
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys("imen.makni@symdrik.com");
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys("AdminSymdrik2020!");
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        String expectedUrl = "https://dev.accorhotels.projets-en-cours.net/fr"; // Remplacez par l'URL de votre page d'accueil
        return driver.getCurrentUrl().equals(expectedUrl);
    }
}

public boolean isLoginFailed() {
    // Sélecteur pour le message d'erreur
    By errorMessage = By.cssSelector(".form-item--error-message");
    // Texte attendu et lien attendu
    String expectedErrorMessage = "Nom d'utilisateur ou mot de passe non reconnu.";
    String expectedHref = "/fr/user/password?name=qd"; // Lien attendu (partie après le domaine)
    // Vérifier la présence du message d'erreur
    WebElement errorElement = driver.findElement(errorMessage);
    String actualErrorMessage = errorElement.getText();
    // Vérifier que le message d'erreur contient le texte attendu
    boolean isErrorMessageCorrect = actualErrorMessage.contains(expectedErrorMessage);
    // Vérifier la présence du lien (href) dans le message d'erreur
    WebElement errorLink = errorElement.findElement(By.tagName("a"));
    boolean isHrefCorrect = errorLink.getAttribute("href").endsWith(expectedHref);
    // Retourner vrai si le message d'erreur et le lien sont corrects
    return isErrorMessageCorrect && isHrefCorrect;
}
}