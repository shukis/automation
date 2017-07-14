package pages.signUp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class SignUpFirstPage {

    @AndroidFindBy(id = "fdgdr")
    AndroidElement mainContainer;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.CHAIN)
    @AndroidFindBy(id = "fvfrfre")
    @AndroidFindBy(className = "android.widget.TextView")
    public List<AndroidElement> textViews;

    @AndroidFindBy(id="com.example.user.pocotest:id/signUpFirstContinue")
    AndroidElement gotItButton;

    public SignUpFirstPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 1, TimeUnit.SECONDS), this);
    }

    public void checkGotItButton(){
        System.out.println(" check 'GOT IT!' button");
        Assert.assertEquals("GOT IT!",gotItButton.getText());
    }

}
