package TestListeners.ExtendReports;

import Utils.Tools;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public static ExtentReports extent = ExtentManager.createExtentReports();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static ExtentTest updateTest(ExtentTest test, WebDriver driver, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String base64Img = null;
            try {
                base64Img = java.util.Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(
                        new File(Tools.takeScreenshot(driver))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            test.log(Status.FAIL, "TEST FAILED !",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        } else {
            test.log(Status.SKIP, result.getTestName());
        }
        return test;
    }

}

