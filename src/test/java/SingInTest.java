import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CongratulationPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Pavel on 12.07.2017.
 */
public class SingInTest extends BaseTest {

    @Test(description = "Request Login")
    public void requestLogin() throws MalformedURLException, InterruptedException {
        System.out.println("Request Login test ");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.tapLoginButton();
        loginPage.checkToolbar();
        loginPage.fillEditTextFields("a@b.com","ababab");
        loginPage.taploginButton();
        CongratulationPage congratulationPage = new CongratulationPage(driver);
        congratulationPage.tapCongratulationButton();
        Assert.assertTrue(mainPage.checkWelcomeText());


    }

}
