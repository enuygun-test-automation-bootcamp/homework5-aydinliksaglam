package testng;

import devices.DeviceFarm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.HomePage;
import utility.DeviceFarmUtility;

import javax.swing.text.Utilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ContactManagerTestNG {



	/*
	{
  "platformName": "Android",
  "appium:deviceName": "oreo",
  "appium:automationName": "UiAutomator2",
  "appium:udid": "emulator-5554",
  "appium:avd": "Pixel 4 API 28",
  "appium:fastReset": true,
  "appium:newCommandTimeout": 5
}
	 */


    public static AppiumDriver<?> Driver;
    HomePage homePage;
    AddContactPage addContactPage;
    String oreo, kitkat;
    DesiredCapabilities capabilities;

    public ContactManagerTestNG(){
        oreo = DeviceFarm.ANDROID_OREO.path;
        kitkat = DeviceFarm.ANDROID_KITKAT.path;
    }

    @BeforeClass
    public void setup() throws MalformedURLException {

        capabilities = new DesiredCapabilities();
        capabilities = DeviceFarmUtility.pathToDesiredCapabilitites(this.oreo);
        capabilities.setCapability("app", new File("src/test/resources/apps/ContactManager.apk").getAbsolutePath());
        Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        homePage = new HomePage();
        addContactPage = new AddContactPage();
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "4.4.2");
		capabilities.setCapability("deviceName","Pixel_4_XL_API_24");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("app", new File("src/test/resources/apps/ContactManager.apk").getAbsolutePath());

		//capabilities.setCapability("appPackage", "com.android.calculator2");
// This package name of your app (you can get it from apk info app)
		//capabilities.setCapability("appActivity","com.android.calculator2.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
		//It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
		Driver = new AndroidDriver(new URL("http://127.0.0.1:8080/wd/hub"), capabilities);
		//*/



    }

    @Test(priority = 0)
    public void openAddContactOnOreo() throws NullPointerException, InterruptedException {

        homePage.getAddContactBtn().click();
        Thread.sleep(4000);
    }


    //User checks title whether it is "Add Contact"
    @Test(priority = 1)
    public void checkAddContactTitle(){

        Assert.assertEquals(addContactPage.getTitle().getText(),"Add Contact");
    }

    //User checks special characters limit for contact phone field
    //Kullanıcı, contact phone field için özel karakter sınırını kontrol eder
    @Test()
    public void checkContactPhoneField(String phone){

        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return Assert.assertEquals(addContactPage.getPhone().getText(),"Contact Phone Field");

    }

    //User checks speacial characters limit for contact name
    //Kullanıcı, contact name için özel karakter sınırını kontrol eder
    @Test()
    public void checkContactNameField(String name){

        if(!Pattern.matches("[a-zA-Z]+", name)) {
            return name.length() > 6 && name.length() <= 13;
        }
        return Assert.assertEquals(addContactPage.getName().getText(),"Contact Name Field");

    }


    //User checks number limit for contact phone field
    //Kullanıcı, telefon alanı için sayı sınırını kontrol eder
    @Test()
    public void checkPhoneNumberLimit(){

        Assert.assertEquals(addContactPage.getPhone().getText(),"Phone Number Limit");
    }

    //User checks email format for contact email field
    //Kullanıcı, e-posta alanı için e-posta biçimini kontrol eder
    @Test()
    public void checkContactEmail(){

        Assert.assertEquals(addContactPage.getEmail().getText(),"Contact Email");
    }



    @AfterClass
    public void waiter() throws InterruptedException {
        Thread.sleep(5000);
    }

}

/*@Test(enabled = true)
	public void openAddContactOnKitkat() throws MalformedURLException {
		capabilities = DeviceFarmUtility.pathToDesiredCapabilitites(this.kitkat);
		capabilities.setCapability("app", new File("src/test/resources/apps/ContactManager.apk").getAbsolutePath());
		Driver = new AndroidDriver(new URL("http://127.0.0.1:8080/wd/hub"), capabilities);
		homePage = new HomePage();
		homePage.getAddContactBtn().click();
	}*/