package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

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

    public void checkToolbar(){
        System.out.println(toolbar.get(0).getText());
    }

    public void fillEditTextFields(String emailValue, String passwordValue){
        fillEditTextField(email,emailValue);
        fillEditTextField(password,passwordValue);

    }

    public void fillEditTextField(AndroidElement element, String value){
        if(element==email){
            email.sendKeys(value);
        }else if(element == password){
            password.sendKeys(value);
        }
    }

    public void taploginButton(){
        loginButton.click();
    }

}
