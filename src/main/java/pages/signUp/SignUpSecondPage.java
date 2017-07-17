package pages.signUp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class SignUpSecondPage {
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpEmail")
    AndroidElement email;
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpPassword")
    AndroidElement password;
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpConfirmPassword")
    AndroidElement confirmPassword;
    @AndroidFindBy(id="com.example.user.pocotest:id/terms")
    AndroidElement terms;
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpSecondContinue")
    AndroidElement nextStepButton;

    public SignUpSecondPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, 1, TimeUnit.SECONDS), this);
    }

    public void fillEditTextFields(String emailValue, String passwordValue){
        fillEditTextField(email,emailValue);
        fillEditTextField(password,passwordValue);

    }

    public void fillEditTextField(AndroidElement element, String value) {
        if (element == email) {
            email.sendKeys(value);
        } else if (element == password) {
            password.sendKeys(value);
            confirmPassword.sendKeys(value);
        }
    }
    public void tapNextStepButton(){
        terms.click();
        nextStepButton.click();
    }

}
