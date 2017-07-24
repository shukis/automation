package pages.Gmail;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 18.07.2017.
 */
public class InsertPasswordPage {
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInsertView;
    @FindBy(xpath = "//div[@id='passwordNext']")
    WebElement nextButton;

    public InsertPasswordPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
    }

    public void insertPassword(String password) {
        System.out.println("Gmail InsertPassword page: password - "+password);
        int attempts = 0;
        do {
            attempts++;
            try {
                if (passwordInsertView.isDisplayed()) {
                    break;
                }

            } catch (StaleElementReferenceException e) {

            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        } while (attempts < 20);
        passwordInsertView.sendKeys(password);
        nextButton.click();

    }
}