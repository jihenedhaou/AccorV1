package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class FileDownloadWaiter {
    private WebDriver driver;
    private String downloadDirectory;

    public FileDownloadWaiter(WebDriver driver, String downloadDirectory) {
        this.driver = driver;
        this.downloadDirectory = downloadDirectory;
    }

    public void waitForFileDownload(String fileName, Duration timeout) throws InterruptedException {
        File file = new File(downloadDirectory, fileName);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // Attendre que le fichier apparaisse dans le répertoire de téléchargement
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                File file = new File(downloadDirectory, fileName);
                return file.exists();
            }
        });

        // Attendre que le fichier soit complètement téléchargé
        long startTime = System.currentTimeMillis();
        boolean isFileComplete = false;

        while (System.currentTimeMillis() - startTime < timeout.toMillis()) {
            file = new File(downloadDirectory, fileName);
            if (file.exists()) {
                // Vérifier que le fichier ne change plus de taille
                long fileSize = file.length();
                long fileSizeAfterWait = file.length();
                if (fileSize == fileSizeAfterWait) { // Vérifie que la taille ne change pas
                    isFileComplete = true;
                    break;
                }
            }
            Thread.sleep(1000); // Attendre 1 seconde avant de vérifier à nouveau
        }

        if (!isFileComplete) {
            throw new AssertionError("Le téléchargement du fichier a échoué ou a pris trop de temps.");
        }
    }
}
