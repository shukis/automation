package pages.Gmail;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 18.07.2017.
 */
public class GmailPage {
    @FindBy(xpath = "//div[@class='og_t']")
    List<WebElement> moreButton;
    @FindBy(xpath = "//div[@id='og_t']")
    WebElement currentUser;
    AndroidDriver driver = null;


    public GmailPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
        this.driver = driver;
    }

    public void tapMoreButton() {
        System.out.println("tapMoreButton()");
        int attempts = 0;
        do {
            attempts++;
            if (!moreButton.isEmpty() && moreButton.get(0).isDisplayed()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                break;
            } else {
                System.out.println("button is not displayed, attempt: " + attempts);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        } while (attempts < 20);
        System.out.println("tapMoreButon(): click");
        moreButton.get(0).click();
        //new TouchAction(driver).tap(moreButton.get(0)).perform();
    }

    public void checkCurrentUser(String user) {
        System.out.println("Check current user: " + user);
        int attempts = 0;
        do {
            attempts++;
            if (currentUser.getText().length() > 0) {
                break;
            } else {
                System.out.println("currentUser is not displayed, attempt: " + attempts);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }

        } while (attempts < 20);
        Assert.assertEquals(user, currentUser.getText());
    }
}
