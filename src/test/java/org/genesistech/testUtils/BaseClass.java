package org.genesistech.testUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.genesitech.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseClass extends AppiumUtils {
    public AppiumDriverLocalService service;
    public AndroidDriver driver;



    @BeforeClass(alwaysRun = true)
    public  void  configureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/genesistech/resources/data.properties");
       prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");

       service =  startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Redmi");
        options.setPlatformName("Android");
        options.setPlatformVersion("10");// Update this to your device's Android version
        options.setChromedriverExecutable("E:\\chrome-win64\\chromedriver-win64");
       // options.setApp("E:\\AppiumProjects\\AppiumProj\\src\\test\\java\\org\\genesistech\\resources\\ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir")+ "\\src\\test\\java\\org\\genesistech\\resources\\General-Store.apk");
 
         driver = new AndroidDriver(service.getUrl(), options);

    }


    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }
    @AfterClass(alwaysRun = true)
 public  void tearDown(){
     driver.quit();
     service.stop();
 }
}
