package pages.Delfi;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 21.07.2017.
 */

public class ChromePage {
    AndroidDriver driver = null;
    @FindBy(xpath = "//*[contains(@text,'Search')]")
    WebElement searchBox;
    @FindBy(id = "com.android.chrome:id/url_bar")
    WebElement urlBar;
    @FindBy(id = "com.android.chrome:id/new_tab_button")
    WebElement newTab;

    public ChromePage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
        this.driver = driver;

    }

    public void openNewTab() {
        try {
            newTab.click();
            System.out.println("Chrome page: No tabs were open");
            Thread.sleep(100);
        } catch (Exception e) {
        }

    }

    public void typeUrlOnChromeMainPage(String url) {
        int attempt = 0;
        while (attempt < 2) {
            System.out.println("typeUrlOnChromeMainPage : attempt - " + attempt);
            attempt++;
            openNewTab();
            try {
                searchBox.sendKeys(url);
                System.out.println("Chrome page: No pages were open");
                break;
            } catch (NoSuchElementException e) {
                try {
                    driver.navigate().back();
                    urlBar.sendKeys(url);
                    System.out.println("Chrome page: Some page was open");
                    break;
                } catch (NoSuchElementException ne) {
                }
            }
        }
        driver.pressKeyCode(AndroidKeyCode.ENTER);
    }
}
