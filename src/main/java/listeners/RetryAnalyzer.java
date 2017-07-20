package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by Pavel on 19.07.2017.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    public static int counter = 0;
    int retrylimit = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < retrylimit) {
            counter++;
            return true;
        }
        return false;
    }

    public static void resetRetryCount() {
        counter = 0;
    }
}