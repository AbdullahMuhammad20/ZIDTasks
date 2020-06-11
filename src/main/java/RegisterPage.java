import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterPage {

    String BASE_URL = "https://web.zid.sa/register";
    WebDriver driver = DriverFactory.getChromeDriver();
    @BeforeTest
    public void openBrow(){
        DriverFactory.getChromeDriver();
    }

    @Test
    public void validRegister() throws InterruptedException {
        driver.navigate().to(BASE_URL);

        // Step one
        WebElement full_name =driver.findElement(By.id("name"));
        WebElement mobile =driver.findElement(By.id("mobile"));
        WebElement Email =driver.findElement(By.id("registration_email"));
        WebElement password =driver.findElement(By.id("registration_password"));
        WebElement btnNext =driver.findElement(By.xpath("//button[contains(@class,'btn next-tab-btn')]"));


        // set two
        WebElement store_username_EN =driver.findElement(By.id("store_username"));
        WebElement store_name_AR =driver.findElement(By.id("store_name"));

        WebElement category_id =driver.findElement(By.id("store_category_id"));
        Select category_select = new Select(category_id);

        WebElement checkYes =driver.findElement(By.xpath("//input[@type='radio'][contains(@id,'yes')]"));
        WebElement checkNo =driver.findElement(By.xpath("//input[@type='radio'][contains(@id,'no')]"));

        WebElement registration_discount_code =driver.findElement(By.id("registration_discount_code"));

        WebElement btnRegister =driver.findElement(By.xpath("//button[contains(@class,'btn btn-primary login-btn')]"));

        full_name.sendKeys("New_Test1");
        mobile.sendKeys("126591544");
        Email.sendKeys("immi@avito-office.ru");
        password.sendKeys("123654789+++");
        Thread.sleep(5000);
        btnNext.click();
        Thread.sleep(5000);
        store_username_EN.sendKeys("Bla_Konafa_Test");
        store_name_AR.sendKeys("متجر كنافة");
        category_select.selectByVisibleText("مأكولات ومشروبات");
        if (checkYes.isSelected())
            checkNo.click();
        else
            checkYes.click();
        registration_discount_code.sendKeys("eeefff");

        btnRegister.click();
        Thread.sleep(5000);
        String home_URL =  "https://web.zid.sa/home";
        Assert.assertEquals(driver.getCurrentUrl(),home_URL);

    }


    @AfterTest
    public void closeBrow(){
        driver.close();
    }

}
