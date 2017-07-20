import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Parameters;
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
        SignUpFirstPage signUpFirstPage = new SignUpFirstPage(driver);
        SignUpSecondPage signUpSecondPage = new SignUpSecondPage(driver);
        SignUpThirdPage signUpThirdPage = new SignUpThirdPage(driver);
        CongratulationPage congratulationPage = new CongratulationPage(driver);
        mainPage.tapSignUpButton();
        signUpFirstPage.tapGotItButton();

        signUpSecondPage.fillEditTextFields(email, password);
        signUpSecondPage.tapNextStepButton();
        signUpThirdPage.fillEditTextFields("Tallinn", "12345");
        signUpThirdPage.tapNextStepButton();
        Thread.sleep(10000);
        if (driver.currentActivity().contains("SecondActivity")) {
            driver.hideKeyboard();
            driver.navigate().back();
            driver.navigate().back();
        } else {
            congratulationPage.tapCongratulationButton();
        }

        Assert.assertTrue(mainPage.checkWelcomeText());

    }
}
