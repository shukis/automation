import listeners.RetryAnalyzer;
import org.testng.annotations.Test;
import pages.Gmail.GmailPage;
import pages.Gmail.InsertEmailPage;
import pages.Gmail.InsertPasswordPage;

import java.net.MalformedURLException;

/**
 * Created by Pavel on 18.07.2017.
 */
public class GmailLoginTest extends BaseTest {

    private String email = "pavel.pavel090890@gmail.com";


    @Test
    public void requestLoginToGmail() throws MalformedURLException, InterruptedException {
        System.out.println("GmailLoginTest");
        System.err.println(driver.currentActivity());
        InsertEmailPage emailPage = new InsertEmailPage(driver);
        InsertPasswordPage passwordPage = new InsertPasswordPage(driver);
        GmailPage gmailPage = new GmailPage(driver);
        emailPage.insertEmail(email);
        passwordPage.insertPassword("pavel090890");

        gmailPage.tapMoreButton();
        gmailPage.checkCurrentUser(email);

    }
}
