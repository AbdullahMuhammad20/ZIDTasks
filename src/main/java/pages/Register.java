package pages;

import org.apache.poi.ss.usermodel.RichTextString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Register
{
    WebDriver driver ;

    public Register(WebDriver driver)
    {
        this.driver = driver;
    }
    // Step one
    By full_name = By.id("name");

    By mobile =  By.id("mobile");

    By Email = By.id("registration_email");
    By password = By.id("registration_password");
    By btnNext = By.xpath("//button[contains(@class,'btn next-tab-btn')]");

    // set two

    By store_username_EN =  By.id("store_username");
    By store_name_AR     =  By.id("store_name");
    By category_id       =  By.id("store_category_id");

    By checkYes =   By.xpath("//input[@type='radio'][contains(@id,'yes')]");
    By checkNo  =   By.xpath("//input[@type='radio'][contains(@id,'no')]");

    By registration_discount_code =By.id("registration_discount_code");

    By btnRegister =By.xpath("//button[contains(@class,'btn btn-primary login-btn')]");




    // Validation Mobile Item
    By mobile_less8        = By.xpath("//strong[contains(.,'رقم الجوال يجب ان يحتوي على 8 أرقام على الأقل')]");
    By mobile_numbers_only = By.xpath("//strong[contains(.,'رقم الجوال يمكن ان يحتوي فقط على ارقام.')]");
    By mobile_required     = By.xpath("//span[@class='help-block'][contains(.,'رقم الجوال مطلوب.')]");

    // Validation Email Item
    By Email_Valid        = By.xpath("//strong[contains(.,'يجب كتابة البريد الإلكتروني بشكل صحيح')]");
    By Email_required     = By.xpath("//strong[contains(.,'البريد الالكتروني مطلوب.')]");

    // Validation Password Item
    By Password_required = By.xpath("//strong[contains(.,'كلمة المرور مطلوبة')]");
    By Password_less8    = By.xpath("//strong[contains(.,'كلمة المرور يجب ان تحتوي على 8 حروف أو ارقام على الأقل')]");

    // Validation StoreEn Item
    By Store_En_UnAvailable = By.xpath("//strong[contains(.,'رابط المتجر غير متوفر')]");
    By Store_En_required = By.xpath("//strong[contains(.,'رابط المتجر مطلوب.')]");

    // Validation StoreAR Item
    By Store_AR_required = By.xpath("//strong[contains(.,'اسم المتجر مطلوب.')]");

    // Validation Check-box
    By Check_required = By.xpath("//strong[contains(.,'الحقل مطلوب.')]");




    // Set Register Validation
    public String getMobile_required() {
        return driver.findElement(mobile_required).getText();
    }
    public String getMobile_less8() {return driver.findElement(mobile_less8).getText(); }
    public String getMobile_numbers_only(){return driver.findElement(mobile_numbers_only).getText();}

    public String getEmail_required() {return driver.findElement(Email_required).getText();}
    public String getEmail_Valid() {return driver.findElement(Email_Valid).getText();}

    public String getPassword_required() {return driver.findElement(Password_required).getText();}
    public String getPassword_less8() {return driver.findElement(Password_less8).getText();}

    public String getStore_En_UnAvailable() {return driver.findElement(Store_En_UnAvailable).getText();}
    public String getStore_En_required() {return driver.findElement(Store_En_required).getText();}

    public String getStore_Ar_required() {return driver.findElement(Store_AR_required).getText();}

    public String getCheck_required() {return driver.findElement(Check_required).getText();}


    // set Data into page items
    public void setFull_name(String strFull_name)
    {
        driver.findElement(full_name).sendKeys(strFull_name);

    }
    public void setMobile(String Mobile)
    {
        driver.findElement(mobile).sendKeys(Mobile);
    }

    public void setEmail(String strEmail)
    {
        driver.findElement(Email).sendKeys(strEmail);
    }

    public void setPassword(String strPassword)
    {
        driver.findElement(password).sendKeys(strPassword);
    }

    public void clickNext()
    {
        driver.findElement(btnNext).click();
    }

    public void setStore_username_EN(String strStoreEn)
    {
        driver.findElement(store_username_EN).sendKeys(strStoreEn);
    }

    public void setStore_name_AR(String strStoreAr)
    {
        driver.findElement(store_name_AR).sendKeys(strStoreAr);
    }

    public void setCategoryName(String strCategoryName)
    {
        WebElement category_ids = driver.findElement(category_id);

        Select category_select = new Select(category_ids);

        category_select.selectByVisibleText(strCategoryName);
    }
    public void setCheck()
    {
        if (driver.findElement(checkYes).isSelected())
            driver.findElement(checkNo).click();
        else
        driver.findElement(checkYes).click();

    }

    public void setRegistration_discount_code(String registration_discount)
    {
        driver.findElement(registration_discount_code).sendKeys(registration_discount);
    }

    public void clickRegister()
    {
        driver.findElement(btnRegister).click();
    }





    // get Title of page Register
    public String getTitleOfPageReg()
    {
        return driver.getTitle();
    }

    public void RegisterZID(String full_name, String mobile, String email, String password, String storeEn, String storeAr, String category, String regDiscount)
    {
        // step One
        this.setFull_name(full_name);
        this.setMobile(mobile);
        this.setEmail(email);
        this.setPassword(password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.clickNext();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // step two
        this.setStore_username_EN(storeEn);
        this.setStore_name_AR(storeAr);
        this.setCategoryName(category);
        this.setCheck();
        this.setRegistration_discount_code(regDiscount);
        this.clickRegister();
    }


}
