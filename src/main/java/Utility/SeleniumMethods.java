package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SeleniumMethods {
    public static ExtentReports extent;
    public WebDriver driver = null;
    public static ExtentTest test = null;

    public SeleniumMethods(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }


    //sendkeys by name,id,xpath
    public void sendkeysByName(String nameValue, String textToSend) {
        driver.findElement(By.name(nameValue)).sendKeys(textToSend);
    }

    public void sendKeysById(WebDriver driver, String idValue, String textToSend) {
        driver.findElement(By.id(idValue)).sendKeys(textToSend);
    }

    public void sendKeysByXpath(WebDriver driver, String xpathValue, String textToSend) {
        driver.findElement(By.xpath(xpathValue)).sendKeys(textToSend);
    }

    // button click
    public void clickButton(WebDriver driver, By locator) {
        WebElement button = driver.findElement(locator);
        button.click();
    }

    public void click(WebDriver driver, By locator) {

        driver.findElement(locator).click();
    }

    //explicitWait
    public WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    //getText
    public String getText(WebDriver driver, By locator) {
        test.info("Text retrieved successfully");
        return driver.findElement(locator).getText();

    }

    //getAttribute
    public String getAttribute(WebDriver driver, By locator, String attributeName) {
        return driver.findElement(locator).getAttribute(attributeName);
    }

    // Extent Report
    public static ExtentReports getExtentReport() {
        if (extent == null) { //To create it only once and reuse it everywhere — just like you wouldn’t want a new TV remote every time you switch channels.
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = "reports/ExtentReport_" + timestamp + ".html";


            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Harshitha");
        }
        return extent;
    }

    //screenshot

   /* public static String takeScreenshot(WebDriver driver, String name) {
        try {
            String folderPath = "reports/screenshots";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            String fileName = name + "_" + System.currentTimeMillis() + ".png";
            String relativePath = "screenshots/" + fileName;
            String fullPath = folderPath + "/" + fileName;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(fullPath);
            FileUtils.copyFile(src, dest);

            return relativePath; // ✅ this will work with MediaEntityBuilder
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }*/
   public static String takeScreenshot(WebDriver driver, String screenshotName) {
       try {
           // Path to "reports/screenshots" folder
           String folderPath = System.getProperty("user.dir") + "/reports/screenshots";
           File screenshotDir = new File(folderPath);
           if (!screenshotDir.exists()) {
               screenshotDir.mkdirs(); // Create folder if not present
           }

           // Timestamped filename
           String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
           String filename = screenshotName + "_" + timestamp + ".png";

           // Full path to save the file
           String fullPath = folderPath + "/" + filename;
           File destination = new File(fullPath);

           // Take screenshot and save it
           File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
           FileUtils.copyFile(src, destination);

           // Return path relative to reports/index.html
           return "screenshots/" + filename;

       } catch (Exception e) {
           System.out.println("Screenshot capture failed: " + e.getMessage());
           return null;
       }
   }
}
