import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void openLoginPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

//    Login with valid username and password
    @Test(priority = 0)
    public void LoginWithValidData(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertEquals(expectedURL, actualURL, "Login Failed");
        System.out.println("Login Successful!");
    }

//    Login with invalid username
    @Test(priority = 1)
    public void LoginWithInvalidUsername(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("incorrect");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualURL1 = driver.getCurrentUrl();
        String expectedURL1 = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        Assert.assertEquals(expectedURL1, actualURL1, "Login Failed: Can not login with invalid username");
        System.out.println("Login Unsuccessful with Invalid Username!");
    }

//    Case sensitiveness testing with lowercase username
    @Test(priority = 2)
    public void LoginWithLowerCaseUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertEquals(actualURL, expectedURL, "Username should be case-sensitive, but login was allowed.");
        System.out.println("Username case sensitivity test passed!");
    }

//    Login with invalid password
    @Test(priority = 3)
    public void LoginWithInvalidPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin1234");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        Assert.assertEquals(expectedURL, actualURL, "Login Failed: Can not login with invalid password");
        System.out.println("Login Unsuccessful with Invalid Password!");
    }

//    Login with required fields empty
    @Test(priority = 4)
    public void LoginWithEmptyFields(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        Assert.assertEquals(expectedURL, actualURL, "Login Failed: Can not login with required fields empty");
        System.out.println("Login Unsuccessful with empty fields!");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}



