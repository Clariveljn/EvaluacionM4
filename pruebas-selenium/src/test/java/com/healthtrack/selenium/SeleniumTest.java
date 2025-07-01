package com.healthtrack.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://127.0.0.1:5173");
    }

    @Test
    public void testRegistroYActualizacionDeUsuario() {

        // Registrar usuario
        WebElement nombreInputRegistro = driver.findElement(By.id("nombre-registro"));
        WebElement pesoInputRegistro = driver.findElement(By.id("peso-registro"));
        WebElement botonRegistrar = driver.findElement(By.id("boton-registrar"));

        nombreInputRegistro.sendKeys("seleniumTest");
        pesoInputRegistro.sendKeys("70");
        botonRegistrar.click();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertRegistro = driver.switchTo().alert().getText();
        System.out.println("Alerta Registro: " + alertRegistro);
        assertTrue(alertRegistro.contains("registrado"));
        driver.switchTo().alert().accept();

        // Actualizar peso
        WebElement nombreInputActualizar = driver.findElement(By.id("nombre-actualizar"));
        WebElement nuevoPesoInput = driver.findElement(By.id("nuevo-peso"));
        WebElement botonActualizar = driver.findElement(By.id("boton-actualizar"));

        nombreInputActualizar.sendKeys("seleniumTest");
        nuevoPesoInput.sendKeys("75");
        botonActualizar.click();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertActualizar = driver.switchTo().alert().getText();
        System.out.println("Alerta Actualizar: " + alertActualizar);
        assertTrue(alertActualizar.contains("actualizado"));
        driver.switchTo().alert().accept();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


