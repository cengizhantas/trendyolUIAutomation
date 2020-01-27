package test;

import base.BaseTest;
import org.junit.Test;
import page.LoginPage;

public class TestSuite extends BaseTest
{
    /**
     * Step 1
     */
    @Test
    public void login() {
        setUp("chrome");
        new LoginPage(driver).login(email, password);
    }

    /**
     * Step 2
     */
    @Test
    public void checkMainTabs() {
        setUp("chrome");
        new LoginPage(driver).login(email, password).checkMainPageTabs();
    }

    /**
     * Step 3
     */
//    @Test
//    public void clickAndCheckRandomBoutique() {
//        setUp("chrome");
//        new LoginPage(driver).login(email, password).clickAndCheckRandomBoutique();
//    }

    /**
     * Step 4 ve 5
     */
//    @Test
//    public void checkAndAddToBasket()
//    {
//        setUp("chrome");
//        new LoginPage(driver).login(email, password).clickAndCheckRandomBoutique().checkAndAddToBasket();
//    }
}
