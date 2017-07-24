import io.appium.java_client.android.Activity;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Delfi.ChromePage;
import pages.Delfi.DelfiPage;
import pages.MainPage;

/**
 * Created by Pavel on 21.07.2017.
 */
public class DelfiTest extends BaseTest {


    @Test
    public void delfiTest() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.checkWelcomeText());
        Activity chrome = new Activity("com.android.chrome", "com.google.android.apps.chrome.Main");
        chrome.setAppWaitPackage("com.android.chrome");
        chrome.setAppWaitActivity("org.chromium.chrome.browser.ChromeTabbedActivity");
        chrome.setStopApp(false);
        driver.startActivity(chrome);
        ChromePage chromePage = new ChromePage(driver);
        chromePage.typeUrlOnChromeMainPage(url);


        //deprecated
//        driver.startActivity("com.android.chrome","com.google.android.apps.chrome.Main",
//                "com.android.chrome","org.chromium.chrome.browser.ChromeTabbedActivity",false);


        DelfiPage delfiPage = new DelfiPage(driver);
        delfiPage.checkNewsFeed();
        Activity activity = new Activity(appPackage, appActivity);
        driver.startActivity(activity);
        Assert.assertTrue(mainPage.checkWelcomeText());


    }

}
