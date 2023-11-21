package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        driver.findElement(By.id("username")).sendKeys("tomsmith"); //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Enter password
        driver.findElement(By.xpath("//button[@type='submit']")).click(); //Click on login button
        String expectedTest = "Secure Area"; //Verify the text
        String actualTest = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualTest, expectedTest);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith1"); //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Enter password
        driver.findElement(By.xpath("//button[@type='submit']")).click(); //Click on login button
        String expectedErrorMessage = "Your username is invalid!\n×"; //Verify the text
        String actualErrorMsg = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("Your username is invalid!\n×", actualErrorMsg, expectedErrorMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith"); //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword"); //Enter password
        driver.findElement(By.xpath("//button[@type='submit']")).click(); //Click on login button
        String expectedErrorMessage = "Your password is invalid!\n×"; //Verify the error message
        String actualErrorMsg = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("Your password is invalid!\n×", actualErrorMsg, expectedErrorMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
