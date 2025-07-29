package Base;


import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BrowserOpen {
    public WebDriver driver;
    public Properties p;
   // public String browserName="chrome";
   @BeforeClass
   @Parameters({"browser"})


    public void openBrowser(@Optional("chrome") String br) throws IOException {

        //loading config.properties file
        FileReader file= new FileReader("./src/main/resources/config.properties");
        p=new Properties();
        p.load(file);

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid browser name! Launching Chrome by default.");
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(p.getProperty("appURL"));
        System.out.println("Browser launched successfully");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }






}
