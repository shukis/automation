package pages.Delfi;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel on 21.07.2017.
 */
public class DelfiPage {
    AndroidDriver driver = null;

    @FindBy(xpath = "//*[contains(@content-desc,'tiik')]")
    //          //*/@content-desc[contains(.,'Savisaar')]
    WebElement word;

    public DelfiPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
        this.driver = driver;

    }



    public void checkNewsFeed(){
        try {
            System.out.println("checkNewsFeed()...");
            System.out.println(word.getAttribute("contentDescription"));

       }catch (NoSuchElementException e){
            System.out.println("No news containing this word!");
        }
        try{Thread.sleep(100);}catch (Exception e){}
    }
}
