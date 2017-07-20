import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CongratulationPage;
import pages.Gmail.InsertEmailPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Pavel on 12.07.2017.
 */
public class SingInTest extends BaseTest {

    @Test(groups = "group3")
    public void test1() {
        System.out.println("Test1....");
    }

    @Test(groups = "group1")
    public void requestLogin() throws MalformedURLException, InterruptedException {

        System.out.println("Request Login test ");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.tapLoginButton();
        loginPage.checkToolbar();
        loginPage.fillEditTextFields("abb@com.com", "abcabc");
        loginPage.taploginButton();
        Thread.sleep(5000);
        System.out.println(driver.currentActivity());
        if (driver.currentActivity().contains("Login")) {
            driver.hideKeyboard();
            driver.navigate().back();
        } else {
            CongratulationPage congratulationPage = new CongratulationPage(driver);
            congratulationPage.tapCongratulationButton();
        }
        if (RetryAnalyzer.counter == 0) {
            Assert.assertFalse(mainPage.checkWelcomeText());
        } else {
            Assert.assertTrue(mainPage.checkWelcomeText());
        }

    }


}
