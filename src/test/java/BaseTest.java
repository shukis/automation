import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;
import listeners.RetryAnalyzer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


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
    public static String email;
    public static String password;

    public static File appDir = null;

    public static ArrayList<Integer> passedtests = new ArrayList<>();
    public static ArrayList<Integer> failedtests = new ArrayList<>();
    public static ArrayList<Integer> skippedtests = new ArrayList<>();

    @BeforeSuite(alwaysRun = true)
    @Parameters()
    public void beforeSuite() throws Exception {
        System.out.println("BeforeSuite:");
        userDir = "/Users/" +
                System.getProperty("user.name") +
                "/Downloads/test_clients/";
        userDir = userDir.replace("\"", "");
        fileName = "pavel.apk";
        appDir = new File(userDir, fileName);
        app = appDir.getAbsolutePath();
        devicePlatform = MobilePlatform.ANDROID;
        appPackage = "com.example.user.pocotest";
        appActivity = "com.example.user.pocotest.*";
        startLocalAppiumServer("android");

        System.out.println("BeforeSuite: end");

    }

    @BeforeTest
    public void beforeTest() {

        System.out.println("BeforeTest()");
        RetryAnalyzer.resetRetryCount();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"myEmail", "myPassword", "url", "deviceName"})
    public void beforeMethod(@Optional String myEmailValue, @Optional String myPasswordValue, @Optional String url, @Optional String myDeviceName) throws Exception {
        System.out.println("Before method:");
        System.out.println("retryAnalyzer counter: " + RetryAnalyzer.counter);
        Thread.sleep(1000);
        email = myEmailValue;
        password = myPasswordValue;
        if (System.getProperty("email") != null && email != null) {
            email = System.getProperty("email");
        }

        deviceName = myDeviceName;
        if (myDeviceName == null) {
            deviceName = "Android";
        }
        setupDriver(url);
        System.out.println("Before method: end");
    }

    @AfterMethod(alwaysRun = true)
    public void AfterMethod(ITestContext context, ITestResult iTestResult) throws Exception {
        iTestResult.getMethod().getMethodName();
        if (iTestResult.getStatus() == 1) {
            passedtests.add(1);
            RetryAnalyzer.resetRetryCount();
        } else if (iTestResult.getStatus() == 2) {
            failedtests.add(1);
            getScreenshot("C:\\Users\\Pavel\\Downloads\\test_clients\\" + iTestResult.getMethod().getMethodName() + "_" + deviceName + "_" + "_Failed.png");
        } else if (iTestResult.getStatus() == 3) {
            skippedtests.add(1);
            getScreenshot("C:\\Users\\Pavel\\Downloads\\test_clients\\" + iTestResult.getMethod().getMethodName() + "_" + deviceName + "_" + "_Skipped.png");
        }
        System.out.println("-----------");
        System.out.println("-- Passed tests: " + passedtests.size());
        System.out.println("-- Failed tests: " + failedtests.size());
        System.out.println("-- Skipped tests: " + skippedtests.size());
        System.out.println("-----------");
        if (iTestResult.getStatus() == 2 || iTestResult.getStatus() == 3) {

        }


        System.out.println("AfterMethod method:");

        System.out.println("AfterMethod method: end");
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

    private void setupDriver(String url) {
        System.out.println("DRIVER start:");
        driver = AppiumDriverBuilder.configureAppiumAndroid(app, devicePlatform, deviceName, appPackage, appActivity, port, url);
    }

    private void startLocalAppiumServer(final String devicePlatform) {
        String[] cmd;
        System.out.println("   kill Appium server");
        if (port == null) {
            port = "4725";
        }
        if (System.getProperty("os.name").contains("Windows")) {
            cmd = new String[]{"cmd.exe", "/c", "FOR /F \"tokens=5 delims= \" %P IN ('netstat -a -n -o ^| findstr :" + port + "') DO TaskKill.exe /F /PID %P"};
        } else {
            cmd = new String[]{"sh", "-c", "lsof -P | grep ':" + port + "' | awk '{print $2}' | xargs kill -9"};
        }
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        try {
            final Process p = pb.start();
            p.waitFor();
            p.destroy();
            if (p.isAlive()) {
                System.out.println("  executeShell(): closing Forcibly");
                p.destroyForcibly();
            }
            Thread.sleep(10000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("   start Appium server");
        List list = new ArrayList<String>();
        System.out.println(System.getProperty("os.name"));
        if (System.getProperty("os.name").contains("Windows")) {
            list.add("appium.cmd");
        } else {
            list.add("appium");

        }
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

            Thread.sleep(10000);
            System.out.println("  appium server started");
        } catch (Exception e) {
            System.out.println("  appium server start FAILED");
            e.printStackTrace();
        }

    }

    public static void getScreenshot(String outputlocation) throws IOException {
        System.out.println("Capturing the snapshot of the page ");
        File srcFiler = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFiler, new File(outputlocation));
    }

//    private void startEmulator(String deviceName) {
//        String emulatorPort = deviceName.substring(deviceName.length() - 4);
//        //String[] cmd = new String[]{"cmd.exe", "/c", "emulator","-avd Pixel_API_25"};
//
//
//
//        ProcessBuilder pb = new ProcessBuilder(cmd);
//        pb.redirectErrorStream(true);
//        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//        try{
//            final Process p = pb.start();
//            p.waitFor();
//            p.destroy();
//            //Runtime.getRuntime().exec("cmd.exe /c emulator  -port "+emulatorPort+" -no-boot-anim -netdelay none -netspeed full -avd Pixel_API_25");
//            //ProcessBuilder pb1 = new ProcessBuilder(list);
////
////            pb1.redirectErrorStream(true);
////            pb1.redirectOutput(ProcessBuilder.Redirect.INHERIT);
////            pb1.start();
//            Thread.sleep(20000);
//            System.out.println("emulator started");
//        }catch (Exception e){
//            System.out.println("emulator start failed");
//            e.printStackTrace();
//        }
//    }


}
