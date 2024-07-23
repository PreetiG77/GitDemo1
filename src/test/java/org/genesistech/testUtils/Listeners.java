package org.genesistech.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.genesitech.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {
    public ExtentReports extent = ExtentReporterNG.getReportrerObject();
    public ExtentTest test;
    public AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("LIsterner start method");
        test = extent.createTest(iTestResult.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS,"Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("LIsterner Fail method");
        test.fail(iTestResult.getThrowable());
        try {
            driver = (AppiumDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance()                  );
            test.addScreenCaptureFromPath(getScreenShotPath(iTestResult.getMethod().getMethodName(),driver), iTestResult.getMethod().getMethodName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }


}
