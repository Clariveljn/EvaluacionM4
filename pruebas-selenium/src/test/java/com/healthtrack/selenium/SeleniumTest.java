package com.healthtrack.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Ruta al ChromeDriver
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe"); // Windows
        // System.setProperty("webdriver.chrome.driver", "driver/chromedriver"); // Linux/Mac

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Abrir la aplicación web (frontend React)
        driver.get("http://localhost:5173");
    }

    @Test
    public void testRegistroYActualizacionDeUsuario() throws InterruptedException {
        // Registrar usuario
        WebElement nombreInputRegistro = driver.findElement(By.xpath("(//input[@placeholder='Nombre'])[1]"));
        WebElement pesoInputRegistro = driver.findElement(By.xpath("//input[@placeholder='Peso']"));
        WebElement botonRegistrar = driver.findElement(By.xpath("//button[text()='Registrar']"));

        nombreInputRegistro.sendKeys("seleniumTest");
        pesoInputRegistro.sendKeys("70");
        botonRegistrar.click();

        Thread.sleep(1000); // Esperar el alert

        String alertRegistro = driver.switchTo().alert().getText();
        assertTrue(alertRegistro.contains("registrado"));
        driver.switchTo().alert().accept();

        // Actualizar peso
        WebElement nombreInputActualizar = driver.findElement(By.xpath("(//input[@placeholder='Nombre'])[2]"));
        WebElement nuevoPesoInput = driver.findElement(By.xpath("//input[@placeholder='Nuevo Peso']"));
        WebElement botonActualizar = driver.findElement(By.xpath("//button[text()='Actualizar']"));

        nombreInputActualizar.sendKeys("seleniumTest");
        nuevoPesoInput.sendKeys("75");
        botonActualizar.click();

        Thread.sleep(1000); // Esperar el alert

        String alertActualizar = driver.switchTo().alert().getText();
        assertTrue(alertActualizar.contains("actualizado"));
        driver.switchTo().alert().accept();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
