package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class MainPage {
    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.CHAIN)
    @AndroidFindBy(id = "android:id/content")
    @AndroidFindBy(className = "android.widget.TextView")
    AndroidElement welcomeText;
    @AndroidFindBy(id="com.example.user.pocotest:id/welcomeLogIn")
    private AndroidElement logInButton;
    @AndroidFindBy(id="com.example.user.pocotest:id/welcomeSignUp")
    private AndroidElement signUpButton;

    public MainPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
    }

    public void tapLoginButton(){
        logInButton.click();
    }

    public boolean tapSignUpButton() {
        try {

            System.out.println(" tap 'SignUp' button...");
            signUpButton.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean checkWelcomeText(){
        if(welcomeText.getText().equals("Welcome")) {
            System.out.println(welcomeText.getText());
            return true;
        }
        return false;
    }
}
