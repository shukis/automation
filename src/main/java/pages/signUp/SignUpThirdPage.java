package pages.signUp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 13.07.2017.
 */
public class SignUpThirdPage {
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpCity")
    AndroidElement city;
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpPostalCode")
    AndroidElement postalCode;
    @AndroidFindBy(id="com.example.user.pocotest:id/signUpThirdContinue")
    AndroidElement nextStepButton;

    public SignUpThirdPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, 1, TimeUnit.SECONDS), this);
    }

    public void fillEditTextFields(String emailValue, String postalCodeValue){
        fillEditTextField(city,emailValue);
        fillEditTextField(postalCode,postalCodeValue);

    }


    public void fillEditTextField(AndroidElement element, String value) {
        if (element == city) {
            city.sendKeys(value);
        } else if (element == postalCode) {
            postalCode.sendKeys(value);
        }
    }
    public void tapNextStepButton(){
        nextStepButton.click();
    }
}
