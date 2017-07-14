import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pavel on 13.07.2017.
 */
public class AppiumDriverBuilder {
    public static AndroidDriver driver = null;
    private static DesiredCapabilities capabilities = null;
    private static String errorMessage;


    public static AndroidDriver configureAppium(String app, String devicePlatform, String deviceName, String appPackage, String appActivity, String port ){

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, app);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, devicePlatform);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appActivity);

        String url = "http://0.0.0.0:";
        String minorUrl = "/wd/hub";
        try {
            driver = new AndroidDriver(new URL(url+port+minorUrl),capabilities);
        } catch (MalformedURLException e) {
            errorMessage = e.getMessage();
            e.printStackTrace();
        }

        return driver;
    }

}
