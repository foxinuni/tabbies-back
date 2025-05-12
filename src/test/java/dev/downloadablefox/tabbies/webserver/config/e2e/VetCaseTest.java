package dev.downloadablefox.tabbies.webserver.config.e2e;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VetCaseTest {

    private final String BASE_URL = "http://localhost:4200/";

    private WebDriver driver;
    private WebDriverWait wait;

    private final String newUserName = "Papu Gómez";

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void runAllVetTests() {
        vetTest_LoginTest_Login();
        vetTest_CreateOwner_Owner();
        vetTest_VetCreatePet_Pet();
        vetTest_OwnerAssign_Pet();
    }


    private void vetTest_LoginTest_Login() {
        driver.get(BASE_URL + "/login");
        String xPath = "/html/body/app-root/app-login/section/form/div[1]/select";
        String xPath2 = "/html/body/app-root/app-veterinarian/panel/section/sidebar/aside/nav/ul/li[2]/a";

        WebElement liCampo = driver.findElement(By.xpath(xPath));
        String expectedCampo = "Veterinario";
        Select select = new Select(liCampo);
        select.selectByVisibleText(expectedCampo);
        driver.findElement(By.name("email")).sendKeys("vet@tabbies.com");
        driver.findElement(By.name("password")).sendKeys("vet1");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("vet");
        driver.findElement(By.id("submit")).click();
        wait.until(ExpectedConditions.urlContains("/veterinarian/pets"));

        driver.findElement(By.xpath(xPath2)).click();
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("/veterinarian/users");
    }
    private void vetTest_CreateOwner_Owner() {
        String aniadirPath = "/html/body/app-root/app-veterinarian/panel/section/main/user-listing/section/a";
        String crearOwnerPath = "//*[@id=\"crear\"]";
        driver.get(BASE_URL + "/veterinarian/users");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(aniadirPath)));
        WebElement btnElement = driver.findElement(By.xpath(aniadirPath));
        btnElement.click();

        
        long unique = System.currentTimeMillis();

        WebElement inputName = driver.findElement(By.id("name"));
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputHash = driver.findElement(By.id("hash"));
        WebElement inputDocument = driver.findElement(By.id("document"));
        WebElement inputNumber = driver.findElement(By.id("number"));

        inputName.sendKeys("Papu Gómez");
        inputEmail.sendKeys("papugomez@gmail.com");
        inputDocument.sendKeys(Keys.BACK_SPACE);
        inputDocument.sendKeys(Long.toString(100000 + (unique % 900000)));
        inputHash.sendKeys("papu");
        inputNumber.sendKeys(Long.toString(3000000000L + (unique % 1000000000)));

        driver.findElement(By.xpath(crearOwnerPath)).click();

        wait.until(ExpectedConditions.urlContains("/veterinarian/users"));
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("/veterinarian/users");
    }


    private void vetTest_VetCreatePet_Pet() {
        driver.get(BASE_URL + "/veterinarian/users");
        String petPath = "/html/body/app-root/app-veterinarian/panel/section/sidebar/aside/nav/ul/li[1]/a";
        String createPetPath = "/html/body/app-root/app-veterinarian/panel/section/main/pet-listing/section/a";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(petPath)));
        WebElement btnElement = driver.findElement(By.xpath(petPath));
        btnElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(createPetPath)));
        btnElement = driver.findElement(By.xpath(createPetPath));
        btnElement.click();

        WebElement inputName = driver.findElement(By.id("name"));
        WebElement inputBreed = driver.findElement(By.id("breed"));
        WebElement inputWeight = driver.findElement(By.id("weight"));
        WebElement inputDate = driver.findElement(By.id("birthDate"));
        WebElement inputPicture = driver.findElement(By.id("picture"));
        WebElement inputOwner = driver.findElement(By.id("owner"));

        inputName.sendKeys("papu");
        inputBreed.sendKeys("Esfinge");
        inputWeight.sendKeys(Keys.BACK_SPACE);
        inputWeight.sendKeys("hola");  // error

        inputDate.sendKeys(LocalDate.parse("2022-12-19").toString());
        inputPicture.sendKeys("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQ2eFBnx0wwcyGIS4PcXFYa2iHlrtnd03zOsYZL8NTk4BWZnzo8VKN17XUEl35qyC7Ld6O1K8bJGtcDD5ohoVLDDw");
        inputOwner.sendKeys(newUserName);

        inputWeight.clear();
        inputWeight.sendKeys("5");

        driver.findElement(By.id("crear")).click();

        String currentUrl2 = driver.getCurrentUrl();
        assert currentUrl2.contains("/veterinarian/pets");
    }

    private void vetTest_OwnerAssign_Pet() {
        driver.get(BASE_URL + "/login");
        String xPath = "/html/body/app-root/app-login/section/form/div[1]/select";

        WebElement liCampo = driver.findElement(By.xpath(xPath));
        String expectedCampo = "Cliente";
        Select select = new Select(liCampo);
        select.selectByVisibleText(expectedCampo);
        driver.findElement(By.name("email")).sendKeys("papugomez@gmail.com");
        driver.findElement(By.name("password")).sendKeys("papu");
        driver.findElement(By.id("submit")).click();

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("/client/pets");
    }

    @AfterEach
    public void tearDown() {
        //if (driver != null) {
        //    driver.quit();
        //}
    }
}
