package page;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseUtil;

public class LoginPage extends BaseUtil
{
    protected final Logger log = Logger.getLogger(getClass());

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public MainPage login(String email, String password)
    {
        closedPopup();
        hoverElement(getElementBy(By.className("login-register-button-container")));
        clickObjectBy(By.className("login"));
        boolean validateEmail = isElementPresent(By.id("email"));
        boolean validatePassword = isElementPresent(By.id("password"));
        boolean validateLoginButton = isElementPresent(By.id("loginSubmit"));
        Assert.assertTrue("Email ile giriş sayfa yapısında bozulma var.",
                validateEmail && validatePassword && validateLoginButton);
        sendKeysBy(By.id("email"), email);
        sendKeysBy(By.id("password"), password);
        clickObjectBy(By.id("loginSubmit"));
        boolean validateLogin = isElementPresent(By.className("user-name"));
        Assert.assertTrue("Username alanı bulunamadı!", validateLogin);
//        hoverElement(getElementBy(By.className("icon-user")));
        log.info("user-name:" + elementGetText(By.className("user-name")));
//        Assert.assertFalse("Giriş yapılamadı!", StringUtils.isEmpty(elementGetText(By.className("user-name"))));
        log.info("Giriş Başarılı!");
        closedNotifications();
        return new MainPage(driver);
    }

    public LoginPage closedPopup()
    {
        if (isElementPresent(By.className("fancybox-close")))
        {
            clickObjectBy(By.className("fancybox-close"));
        }
        return new LoginPage(driver);
    }

    public LoginPage closedNotifications()
    {
        if (isElementPresent(By.xpath("//div[@class=\"modal-close\"]")));
        {
            clickObjectBy(By.xpath("//div[@class=\"modal-close\"]"));
        }
        return new LoginPage(driver);
    }
}
