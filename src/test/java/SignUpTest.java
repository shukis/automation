import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CongratulationPage;
import pages.MainPage;
import pages.signUp.SignUpFirstPage;
import pages.signUp.SignUpSecondPage;
import pages.signUp.SignUpThirdPage;

import java.net.MalformedURLException;

/**
 * Created by Pavel on 17.07.2017.
 */
public class SignUpTest extends BaseTest {
    @Test
    public void requestSignUp() throws MalformedURLException, InterruptedException {
        System.out.println("Request SignUp test ");
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage2 = new MainPage(driver2);
        SignUpFirstPage signUpFirstPage = new SignUpFirstPage(driver);
        SignUpFirstPage signUpFirstPage2 = new SignUpFirstPage(driver2);
        SignUpSecondPage signUpSecondPage = new SignUpSecondPage(driver);
        SignUpSecondPage signUpSecondPage2 = new SignUpSecondPage(driver2);
        SignUpThirdPage signUpThirdPage = new SignUpThirdPage(driver);
        SignUpThirdPage signUpThirdPage2 = new SignUpThirdPage(driver2);
        CongratulationPage congratulationPage = new CongratulationPage(driver);
        CongratulationPage congratulationPage2 = new CongratulationPage(driver2);
        mainPage.tapSignUpButton();
        signUpFirstPage.tapGotItButton();

        //working on second device
        mainPage2.tapSignUpButton();
        signUpFirstPage2.tapGotItButton();
        signUpSecondPage2.fillEditTextFields(email,password);
        signUpSecondPage2.tapNextStepButton();
        signUpThirdPage2.fillEditTextFields("Tallinn","54321");
        signUpThirdPage2.tapNextStepButton();
        if(!congratulationPage2.checkCongratulationText()){
            driver2.navigate().back();
            driver2.navigate().back();
            driver2.navigate().back();
        }else {
            congratulationPage2.tapCongratulationButton();
        }
        //System.err.println(driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME));
        //signup finished on second device

        signUpSecondPage.fillEditTextFields(email, password);
        signUpSecondPage.tapNextStepButton();
        signUpThirdPage.fillEditTextFields("Tallinn", "12345");
        signUpThirdPage.tapNextStepButton();
        if(!congratulationPage.checkCongratulationText()){
            driver.navigate().back();
            driver.navigate().back();
            driver.navigate().back();
        }else{
            congratulationPage.tapCongratulationButton();
        }
        Assert.assertTrue(mainPage.checkWelcomeText());

    }
}
