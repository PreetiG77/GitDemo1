package org.genesistech;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.genesistech.testUtils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ecommerce_tc_1 extends BaseClass {



    @BeforeMethod
    public void preSetup(){
        ((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of("intent",
                "com.androidsample.generalstore/com.andoidsample.generalstore.MainActivity"));
    }
    @Test
    public void fillFormNegativeFlow() throws InterruptedException {
        //waitForElementToAppear(driver.findElement(By.id("com.androidsample.generalstore:id/nameField")), driver);
        Thread.sleep(3000);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Australia\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        waitForElementToAppear(driver.findElement(By.xpath("(//android.widget.Toast)[1]")), driver);

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name");
    }

    @Test
    public void fillFormPositiveFlow(){

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Australia\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //Assert.assertTrue( driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
             }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"src/test/java/org/genesistech/testData/eCommerce.json");
        return new Object[][]{  {data.get(0)}, {data.get(1)}  };
    }
}
