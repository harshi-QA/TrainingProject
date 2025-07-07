package Test;

import Base.BrowserOpen;
import PageObject.DashBoardPage;
import PageObject.LaunchPage;
import Utility.SeleniumMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BrowserOpen{
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setupReport() {
        extent = SeleniumMethods.getExtentReport();
    }


    @Test(priority = 1)
    public void verify_launch() {
        test = extent.createTest("Launch and Login Test");
        LaunchPage lp = new LaunchPage(driver, test);
        lp.clickLogin();
        test.pass("Login successful");
    }

    @Test(priority = 2, dependsOnMethods = "verify_launch" )
    public void verify_dashboard() {
        test = extent.createTest("Dashboard & Apply Leave Test");
        DashBoardPage dp = new DashBoardPage(driver, test);

        String title = dp.getDashboardTitle();
        try {
            Assert.assertNotNull(title);
            Assert.assertEquals(title.trim(), "Dashboard");
            test.pass("Dashboard title is correct: " + title);
        } catch (AssertionError e) {
            String path = SeleniumMethods.takeScreenshot(driver, "dashboard_title_failed");
            test.fail("Dashboard title validation failed: " + e.getMessage(),
            MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            throw e; // Re-throw so TestNG marks the test as failed
        }

        boolean isLeaveApplied = dp.applyLeave();
        try {
            Assert.assertTrue(isLeaveApplied, "Leave application failed or fields were missing");
            test.pass("Leave applied successfully.");
        } catch (AssertionError e) {
            String path = SeleniumMethods.takeScreenshot(driver, "Leave_Application_Apply_Failure");
            test.fail("Leave application failed: " + e.getMessage(),
            MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            throw e; // Rethrow to let TestNG mark it as failed
        }
    }

    @Test(priority =3)
    public void LogOut()
    {
        test = extent.createTest("logout successful");
        DashBoardPage dp = new DashBoardPage(driver, test);
        dp.clickLogOut();
        test.pass("Logout successfully");


    }




    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}







