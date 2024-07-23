package org.genesistech;

import io.appium.java_client.android.AndroidDriver;
import org.genesistech.PageObjects.android.CartPage;
import org.genesistech.PageObjects.android.FormPage;
import org.genesistech.PageObjects.android.ProductCatalogue;
import org.genesistech.testUtils.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class generalstoreappAutomation extends BaseClass  {
    FormPage fillformDriver;




    @Test(dataProvider = "getData")
    public void fillform(HashMap<String,String> input) throws InterruptedException {


        fillformDriver = new FormPage(driver);
            fillformDriver.setNameField(input.get("name"));
        fillformDriver.setGender(input.get("gender"));
       fillformDriver.selectCountry(input.get("country"));
       ProductCatalogue productCatalogue = fillformDriver.clickOnShop();
            /*String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name");
        */
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogue.clickOnCartBtn();


        double totalSum = cartPage.getProductSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();

        Assert.assertEquals(totalSum, displayFormattedSum);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();
        }
        @BeforeMethod
         public void preSetup(){
        fillformDriver.setActivity();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"src/test/java/org/genesistech/testData/eCommerce.json");
        return new Object[][]{  {data.get(0)}, {data.get(1)}  };
    }
}
