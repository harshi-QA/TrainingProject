package PageObject;

import Utility.SeleniumMethods;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashBoardPage extends SeleniumMethods {
    WebDriver driver;

    String DashBoardicon = "//span[@class='oxd-topbar-header-breadcrumb']//h6";
    String ApplyLeave = "//button[@title='Apply Leave']";
    String AssignLeave = "//a[text()='Assign Leave']";
    String SubmitButton = "//button[text()=' Assign ']";
    String UserDropDown = "//li[@class='oxd-userdropdown']";
    String LogOut = "//a[text()='Logout']";

    public DashBoardPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
        this.driver = driver;
    }

    public String getDashboardTitle() {
        try {
            WebElement element = driver.findElement(By.xpath(DashBoardicon));
            return element.getText();
        } catch (Exception e) {
            System.out.println("Error while getting dashboard title: " + e.getMessage());
            return null;
        }
    }


    public boolean applyLeave() {
        try {
            click(driver, By.xpath(ApplyLeave));
            click(driver, By.xpath(AssignLeave));

            String emp = getAttribute(driver, By.xpath("//input[@placeholder='Type for hints...']"), "value");
            String leave = getText(driver, By.xpath("//div[@class='oxd-select-text-input']"));
            String from = getAttribute(driver, By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]"), "value");
            String to = getAttribute(driver, By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]"), "value");

            boolean allFieldsFilled = !emp.isEmpty() && !leave.equals("-- Select --") && !from.isEmpty() && !to.isEmpty();

            if (allFieldsFilled) {
                click(driver, By.xpath(SubmitButton));
                System.out.println("Leave application submitted successfully.");
                return true;
            } else {
                System.out.println("Some leave fields are missing.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error during leave application: " + e.getMessage());
            return false;
        }
    }

    public void clickLogOut()
    {
       click(driver,By.xpath(UserDropDown));

       click(driver, By.xpath(LogOut));



    }
}











