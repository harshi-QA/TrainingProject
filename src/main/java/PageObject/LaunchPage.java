package PageObject;

import Utility.SeleniumMethods;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LaunchPage extends SeleniumMethods {
    public static WebDriver driver;
    public static ExtentTest test=null;
    String username = "username";
    String password = "password";
    String loginbutton = "//button[text()=' Login ']";


    public LaunchPage(WebDriver driver, ExtentTest test)
    {
        super(driver, test);
        this.driver=driver;
        this.test=test;

    }


    public void clickLogin()
    {
        sendkeysByName("username", "Admin");
        sendkeysByName("password", "admin123");
        click(driver, By.xpath(loginbutton));
    }

}
