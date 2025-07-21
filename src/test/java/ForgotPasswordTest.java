import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class ForgotPasswordTest {

    WebDriver driver;
    @BeforeMethod
    public void openLoginPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void forgotPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement forgotPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.orangehrm-login-forgot-header")));
        forgotPassword.click();

        String actualURL1 = driver.getCurrentUrl();
        String expected_URL1 = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
        Assert.assertEquals(expected_URL1,actualURL1, "Failed: Can not navigate to the password reset code page");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualURL2 = driver.getCurrentUrl();
        String expected_URL2 = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";
        Assert.assertEquals(expected_URL2,actualURL2, "Failed: Can not reset the password ");

        System.out.println("Forgot password test passed!");

    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}
