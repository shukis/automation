package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class LoginPage {

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.CHAIN)
    @AndroidFindBy(id = "com.example.user.pocotest:id/toolbar")
    @AndroidFindBy(className = "android.widget.TextView")
    List<AndroidElement> toolbar;
    @AndroidFindBy(id="com.example.user.pocotest:id/email")
    AndroidElement email;
    @AndroidFindBy(id="com.example.user.pocotest:id/password")
    AndroidElement password;
    @AndroidFindBy(id="com.example.user.pocotest:id/email_sign_in_button")
    AndroidElement loginButton;

    public LoginPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 1, TimeUnit.SECONDS), this);

    }

    public boolean checkToolbar(){
        System.out.print("Checking toolbar: ");
        String toolbarText = toolbar.get(0).getText();
        System.out.println(toolbarText);
        if(toolbarText.equals("Login Activity")){
            return true;
        }
        return false;
    }

    public void fillEditTextFieldsAndGoToNextActivity(String emailValue, String passwordValue){
        fillEditTextField(email,emailValue);
        fillEditTextField(password,passwordValue);
        taploginButton();

    }

    public void fillEditTextField(AndroidElement element, String value){
        if(element==email){
            email.sendKeys(value);
        }else if(element == password){
            password.sendKeys(value);
        }
    }

    public void taploginButton(){
        System.out.println("tapLoginButton():");
        loginButton.click();
    }

}
