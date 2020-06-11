package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {

    WebDriver driver;
    String homeTitle = "https://web.zid.sa/home";
    public Home(WebDriver driver)
    {
        this.driver = driver;
    }

    // get current URL for home page login or register
    public String getHomeURL()
    {
        return driver.getCurrentUrl();
    }

    // get current title page for home page

    public String getHomeTitle()
    {
        return driver.getTitle();
    }

}
