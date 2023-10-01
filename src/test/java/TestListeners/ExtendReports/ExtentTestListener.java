package TestListeners.ExtendReports;


import Tests.BaseTest;
import Utils.Tools;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ExtentTestListener extends BaseTest implements ITestListener {

    static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
        //return iTestResult.getTestName();
    }

    @Override
    public void onStart(ITestContext context) {
        context.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "TEST PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver drv = ((BaseTest) testClass).driver;
        String base64Img = null;
        try {
            base64Img = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File(Tools.takeScreenshot(drv))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExtentTestManager.getTest().log(Status.FAIL, "TEST FAILED !",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "TEST SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

}
