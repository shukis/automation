package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class CongratulationPage {
    /*@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView("
            + "new UiSelector().resourceIdMatches(\".*id/congratulationButton\"))")*/
    @AndroidFindBy(id = "com.example.user.pocotest:id/congratulationButton1")
    private AndroidElement button1;
    @AndroidFindBy(id = "com.example.user.pocotest:id/linearWithButtons")
    private AndroidElement viewWithButtons;
    @AndroidFindBy(id = "com.example.user.pocotest:id/congratulationButton")
    private AndroidElement button;
    @AndroidFindBy(id = "com.example.user.pocotest:id/congratulationTextView")
    AndroidElement textView;
    @AndroidFindBy(id = "com.example.user.pocotest:id/imageView")
    AndroidElement image;
    @AndroidFindBy(id = "com.example.user.pocotest:id/congratulationTextView")
    AndroidElement congratulationtext;
    AndroidDriver driver = null;


    public CongratulationPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
        this.driver = driver;
    }

    public void scrollToCorrectButton() {
        TouchAction action = new TouchAction(driver);
        action.longPress(viewWithButtons).moveTo(congratulationtext).release().perform();
    }

    public void tapCongratulationButton() {
        ;
        int attempt = 1;
        while (attempt < 7) {
            try {
                System.out.println("attempt.. " + attempt);
                button.click();
                break;
            } catch (NoSuchElementException e) {
                scrollToCorrectButton();
                attempt++;
            }
        }
    }

}
