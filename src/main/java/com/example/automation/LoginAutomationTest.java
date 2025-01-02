package com.example.automation; 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.chrome.ChromeDriver; 
public class LoginAutomationTest { 
    public void testLogin() { 
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver-win64/chromedriver.exe"); 
        WebDriver driver = new ChromeDriver(); 
 
        try { 
            driver.get("https://example.com/login"); 
 
            WebElement usernameField = driver.findElement(By.id("username")); 
            WebElement passwordField = driver.findElement(By.id("password")); 
            WebElement loginButton = driver.findElement(By.id("loginButton")); 
 
            // Perform login 
            usernameField.sendKeys("testUser"); 
            passwordField.sendKeys("testPassword"); 
            loginButton.click(); 
 
            // Validate successful login 
            String expectedTitle = "Dashboard"; 
            String actualTitle = driver.getTitle(); 
 
        } finally { 
            // Close the browser 
            driver.quit(); 
        } 
} 
} 
