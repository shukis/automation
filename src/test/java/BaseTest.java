import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

/**
 * Created by Pavel on 12.07.2017.
 */
public class BaseTest {

    private Process appium_Process;
    public static String app;
    public static String deviceName;
    public static String devicePlatform;
    public static String appPackage;
    public static String appActivity;
    public static String port;
    public static AndroidDriver driver = null;
    public static String userDir;
    public static String fileName;

    File appDir = null;

    @BeforeTest(alwaysRun = true)
    public void beforeTest() throws Exception {
        System.out.println("BeforeTest:");
        userDir = "/Users/Pavel/Desktop/Pocotest-master/app/build/outputs/apk/";
        fileName = "app-debug.apk";
        appDir = new File(userDir, fileName);
        app = appDir.getAbsolutePath();
        System.out.println("file location: " + app);
        deviceName = "Samsung";
        devicePlatform = MobilePlatform.ANDROID;
        appPackage = "com.example.user.pocotest";
        appActivity = "com.example.user.pocotest.*";
        startLocalAppiumServer("android");


    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
        System.out.println("Before method:");
        setupDriver();
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws InterruptedException {
        System.out.println("AfterSuite:");
        if (appium_Process != null) {
            try {
                System.out.println("closing Appium server 1");
                appium_Process.destroy();
            } catch (Exception e) {
                System.out.println("Appium server 1 FAILED to close");
                e.printStackTrace();
            }
            sleep(1);
            if (appium_Process.isAlive()) {
                System.out.println("closing Appium server 1 Forcibly");
                appium_Process.destroyForcibly();
            }
        }
    }

    private void setupDriver() {
        System.out.println("DRIVER start:");
        String app = appDir.getAbsolutePath();
        driver = AppiumDriverBuilder.configureAppium(app, devicePlatform, deviceName, appPackage, appActivity, port);

    }

    private void startLocalAppiumServer(final String devicePlatform) {
        if (port == null) {
            port = "4725";
        }

        //start server
        System.out.println("   start Appium server");

        // server 1
        List list = new ArrayList<String>();
        list.add("appium.cmd");
        list.add("--log-level");
        if (devicePlatform.contains("android")) {
            list.add("error");
        } else {
            list.add("error"); //iOS output FILTERS! check it below if need to see output correctly.
        }
        list.add("--port");
        list.add(port);
        list.add("--bootstrap-port");
        list.add(String.valueOf(Integer.parseInt(port) + 1000));
        list.add("--command-timeout");
        list.add("90");
        list.add("--session-override");
        list.add("--log-timestamp");

        System.out.println(" start appium as: " + list.toString());
        try {
            ProcessBuilder pb1 = new ProcessBuilder(list);

            pb1.redirectErrorStream(true);
            pb1.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            appium_Process = pb1.start();

            System.out.println("  appium server started");
        } catch (Exception e) {
            System.out.println("  appium server start FAILED");
            e.printStackTrace();
        }

    }


}
