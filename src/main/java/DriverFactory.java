import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public  class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory(){
        // prevent instantiation
    }

    public static WebDriver getChromeDriver() {
        if (driver == null) {
            String chromePath = System.getProperty("user.dir") + "\\resources\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver",chromePath);

            driver = new ChromeDriver();
        }
        return driver;
    }
    public static WebDriver getFirefoxDriver(){
        if (driver == null){
            String foxPath = System.getProperty("user.dir") + "\\resources\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver",foxPath);

            driver = new FirefoxDriver();
        }
        return  driver;
    }

    public  static  WebDriver getIEDriver(){
        if (driver == null){
            String IEPath = System.getProperty("user.dir") + "\\resources\\IEDriverServer.exe";
            System.setProperty("webdriver.ie.driver", IEPath);

            driver = new InternetExplorerDriver();
        }
        return  driver;
    }

    public static WebDriverWait getWebDriverWait(){
        if (wait == null){
            wait = new WebDriverWait(getChromeDriver(),5);
        }
        return  wait;
    }


}
