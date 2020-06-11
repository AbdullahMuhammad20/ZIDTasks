package test;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Home;
import pages.Register;
import org.apache.commons.lang.ArrayUtils;
import sun.util.calendar.BaseCalendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestRegister
{
    WebDriver driver;
    Register  objRegister;
    Home      objHome;

    String BASE_URL = "https://web.zid.sa/register";

    @BeforeTest
    public void setup()
    {
        if (driver == null)
        {
            String chromePath = System.getProperty("user.dir") + "\\resources\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver",chromePath);

            driver = new ChromeDriver();
        }


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(BASE_URL);
    }


    @DataProvider(name = "excel_provider")
    public Object[][] getDataFromExcel() throws Exception
    {
        Object[][]  data =  new Object[][]{};

        File file =  new File("src\\main\\resources\\RegisterData.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook book = new XSSFWorkbook(fis);

        Sheet sheet = book.getSheet("ValidRegister");

        for (int i = 1; i<= sheet.getLastRowNum();i++)
        {
            Row row =  sheet.getRow(i);

            String full_name =row.getCell(0).getStringCellValue();
            String mobile =row.getCell(1).getStringCellValue();
            String Email = row.getCell(2).getStringCellValue();;
            String password =  row.getCell(3).getStringCellValue();
            String store_username_EN = row.getCell(4).getStringCellValue();;
            String store_name_AR   =  row.getCell(5).getStringCellValue();;
            String category_select = row.getCell(6).getStringCellValue();;

            data = (Object[][]) ArrayUtils.add(data,new Object[]{full_name,mobile,Email,password,store_username_EN,store_name_AR,category_select});
        }
        book.close();
        return data;
    }


    // test register valid all data
    @Test (dataProvider = "excel_provider")
    public void test_Register_Valid_Data(String full_name,String mobile,String Email,String password,String store_username_EN,String store_name_AR,String category_select) throws  InterruptedException {

        objRegister =  new Register(driver);

        // Verify current page is register page
        //String registerPageTitle = objRegister.getTitleOfPageReg();



        objRegister.RegisterZID(full_name,mobile,Email,password,store_username_EN,store_name_AR,category_select,"");
        Thread.sleep(10000);
        // go to with driver to home page
        objHome = new Home(driver);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // verify current page is home page and register operation is successfully
        assertTrue(objHome.getHomeTitle().contains("Zid | Home"));
        assertEquals(objHome.getHomeURL(),"https://web.zid.sa/home");

        driver.navigate().to(BASE_URL);
    }


    @Test
    public void test_Register_Invalid_Data() throws  InterruptedException
    {
        objRegister =  new Register(driver);
        objRegister.RegisterZID("eddw","","grgr","","newtest","","مأكولات ومشروبات","");

        Thread.sleep(5000);

        assertTrue(objRegister.getMobile_required().contains("رقم الجوال مطلوب"));
        assertEquals(objRegister.getEmail_Valid(),"يجب كتابة البريد الإلكتروني بشكل صحيح");
        assertTrue(objRegister.getPassword_required().contains("كلمة المرور مطلوبة"));

        // Verify the page doesn't redirect to another any page
        assertTrue(driver.getCurrentUrl().contains("register"));
    }


    @AfterTest
    public void close()
    {
        driver.close();
    }
}
