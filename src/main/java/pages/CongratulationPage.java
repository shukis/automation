package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class CongratulationPage {
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView("
            + "new UiSelector().resourceIdMatches(\".*id/congratulationButton\"))")
    private AndroidElement button;
    @AndroidFindBy(id = "com.example.user.pocotest:id/congratulationTextView")
    AndroidElement textView;


    public CongratulationPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
    }

    public boolean checkCongratulationPage() {
        if (textView.isDisplayed()){
            return true;
    }
        return false;
    }

    public void tapCongratulationButton(){
        button.click();
    }

}
