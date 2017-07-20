package pages.Gmail;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 18.07.2017.
 */
public class InsertEmailPage {
    @FindBy(xpath = "//input[@type='email']")
    WebElement emailInsertView;
    @FindBy(xpath = "//div[@id='identifierNext']")
    WebElement nextButton;

    public InsertEmailPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
    }


    public void insertEmail(String email) {
        emailInsertView.sendKeys(email);
        nextButton.click();
    }
}
