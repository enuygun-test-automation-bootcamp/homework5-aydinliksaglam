import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class testAppium {

    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
    //Elements
    String secondNewJob = "//android.widget.FrameLayout[2]/android.widget.LinearLayout/" +
            "android.widget.RelativeLayout/android.widget.ImageView";
    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 4 API 28");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("skipUnlock","true");
        caps.setCapability("appPackage", "com.isinolsun.app");
        caps.setCapability("appActivity","com.isinolsun.app.activities.SplashActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://localhost/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void basicTest () throws InterruptedException {
        //Click and pass "İşin Olsun"
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/animation_view"))).click();
        //Click and pass Tutorial
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/btn_skip"))).click();
        //Click to "Is Ariyorum" Button
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/bluecollar_type_button"))).click();
        //Notification Allow
        if (driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size()>0) {
            driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
        }
        //Click second job
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(secondNewJob))).click();

        //NoSuchWindowException: This is thrown when WebDriver tries to switch to an invalid window.
        //NoSuchWindowException: Bu, WebDriver geçersiz bir pencereye geçmeye çalıştığında atılır.
        for (String handle : driver.getWindowHandles()) {
            try {
                driver.switchTo().window(handle);
            } catch (NoSuchWindowException e) {
                System.out.println(“An exceptional case”);
            }
        }


        //ElementNotVisibleException exception is thrown if WebDriver tries to find an element on the web page before the page is fully loaded.
        //WebDriver, sayfa tamamen yüklenmeden önce web sayfasında bir öğe bulmaya çalışırsa, ElementNotVisibleException istisnası atılır.
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        driver.get(“http://http://localhost/wd/hub” );


/*
        WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
        driver.get("http://localhost/wd/hub");
*/
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
