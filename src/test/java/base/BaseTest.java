package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest
{
    protected static final Logger logTest = Logger.getLogger(BaseTest.class);
    protected WebDriver driver;
    public static final String BASEURL = "https://trendyol.com";

    protected String email = System.getProperty("email", "cengizgiresun@gmail.com");
    protected String password = System.getProperty("password", "cengizdemosifre");

    public void setUp(String driverName)
    {
        PropertyConfigurator.configure("properties/log4j.properties");
        logTest.info("Driver:" + driverName.toUpperCase());

        if ("CHROME".equals(driverName.toUpperCase()))
        {
            System.setProperty("webdriver.chrome.driver", "properties/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if ("FİREFOX".equals(driverName.toUpperCase()))
        {
            System.setProperty("webdriver.gecko.driver", "properties/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get(BASEURL);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
