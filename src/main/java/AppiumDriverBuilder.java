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
    public static AndroidDriver androidDriver = null;
    private static DesiredCapabilities capabilities = null;
    private static String errorMessage;


    public static AndroidDriver configureAppiumAndroid(String app, String devicePlatform, String deviceName, String appPackage, String appActivity, String port, String url) {

        capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, devicePlatform);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appActivity);

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, deviceName);

        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        if (url == null) {
            System.out.println("file location: " + app);
            capabilities.setCapability(MobileCapabilityType.APP, app);
            createAndroidDriver(port, capabilities);
        } else {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            createAndroidDriver(port, capabilities);
            androidDriver.get(url);

        }
        return androidDriver;
    }

    public static void createAndroidDriver(String port, DesiredCapabilities capabilities) {
        String appiumUrl = "http://0.0.0.0:";
        String minorUrl = "/wd/hub";
        try {
            androidDriver = new AndroidDriver(new URL(appiumUrl + port + minorUrl), capabilities);
        } catch (MalformedURLException e) {
            errorMessage = e.getMessage();
            e.printStackTrace();
        }

    }


}
