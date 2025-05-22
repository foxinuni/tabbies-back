package dev.downloadablefox.tabbies.webserver.config.e2e;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TreatmentCaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        //options.addArguments("--headless");

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void AsignTreatmentCase() {
        driver.get("http://localhost:4200/login");
       
        String xPath = "/html/body/app-root/app-login/section/form/div[1]/select";

        WebElement liCampo = driver.findElement(By.xpath(xPath));
        String expectedCampo = "Veterinario";
        Select select = new Select(liCampo);
        select.selectByVisibleText(expectedCampo);
        driver.findElement(By.name("email")).sendKeys("vet@tabbies.com");
        driver.findElement(By.name("password")).sendKeys("vet1");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("vet");
        //driver.findElement(By.id("submit")).click();
        driver.get("http://localhost:4200/veterinarian/pets");
        wait.until(ExpectedConditions.urlContains("/veterinarian/pets"));

        String xPath2 = "/html/body/app-root/app-veterinarian/panel/section/main/pet-listing/section/input";
        String Shadow = "/html/body/app-root/app-veterinarian/panel/section/main/pet-listing/section/table/tbody/tr/td[5]/a[2]";
        String notes = "Test";
        String medicine = "ACOLAN";

        WebElement barraBusqueda = driver.findElement(By.xpath(xPath2));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        barraBusqueda.sendKeys("Shadow");
        driver.findElement(By.xpath(Shadow)).click();
        wait.until(ExpectedConditions.urlContains("/5/procedure"));

        driver.findElement(By.name("notes")).sendKeys(notes);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("3");
        Select medicineSelect = new Select(driver.findElement(By.xpath("//*[@id=\"medicineId\"]")));
        medicineSelect.selectByVisibleText(medicine);
        Select vetSelect = new Select(driver.findElement(By.xpath("//*[@id=\"veterinaryId\"]")));
        vetSelect.selectByVisibleText("Vet");
        driver.findElement(By.xpath("/html/body/app-root/app-veterinarian/panel/section/main/procedure-create/section/div/form/span[5]/button")).click();
        wait.until(ExpectedConditions.urlContains("/veterinarian/pets/5"));

        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//table[contains(@class, 'procedures-table')]//td[@name='notas']")
        ));
        String actualText = resultElement.getText();
        wait.until(driver -> actualText.equals(notes));
        driver.get("http://localhost:4200/login");
        ///html/body/app-root/app-login/section/form/div[1]/select/option[4]
       

        WebElement liCampo2 = driver.findElement(By.xpath(xPath));
        String expectedCampo2 = "Administrador";
        Select select2 = new Select(liCampo2);
        select2.selectByVisibleText(expectedCampo2);
        driver.findElement(By.name("email")).sendKeys("admin@tabbies.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.id("submit")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("/html/body/app-root/app-admin/panel/section/sidebar/aside/nav/ul/li[1]")).click();

        // Obtén el texto de las ganancias y elimina el símbolo "$"
        String gananciasTexto = driver.findElement(By.xpath("/html/body/app-root/app-admin/panel/section/main/dashboard/section/div[2]/div/div[1]/p")).getText();
        gananciasTexto = gananciasTexto.replace("$", "").replace(",", "").trim(); // Elimina "$" y posibles comas

        // Convierte el texto a un número decimal
        double ganancias = Double.parseDouble(gananciasTexto);


        String tratamientos = driver.findElement(By.xpath("/html/body/app-root/app-admin/panel/section/main/dashboard/section/div[1]/div/div[4]/p")).getText();

        assert ganancias > 0 && Integer.parseInt(tratamientos) > 10 : "Las ganancias deberían ser mayores a 0, pero fueron: " + ganancias + " y los tratamientos: " + tratamientos;        
    }
}
