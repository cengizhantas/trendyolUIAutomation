package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseUtil
{
    protected final Logger log = Logger.getLogger(getClass());
    protected WebDriver driver;
    static JavascriptExecutor jsDriver = null;

    public BaseUtil(WebDriver driver)
    {
        this.driver = driver;
    }

    public void pageLoadComplete()
    {
        jsDriver = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver driver)
            {
                return jsDriver.executeScript("return document.readyState", true).toString().equals("complete");
            }
        };
        try {
            sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 200);
            wait.until(expectation);
        } catch (Throwable error) {
        }
    }

    public WebElement getElementBy(By by)
    {
        pageLoadComplete();
        return driver.findElement(by);
    }

    public WebElement sendKeysBy(By by, String value)
    {
        WebElement element = getElementBy(by);
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public void clickByWebElement(WebElement el)
    {
        if (!(el.isDisplayed()))
        {
            scrollToElement(el);
        }
        el.click();
    }

    public String elementGetText(By by)
    {
        return getElementBy(by).getText().trim();
    }

    public WebElement clickObjectBy(By by)
    {
        WebElement element = getElementBy(by);
        element.click();
        return element;
    }

    public List<WebElement> getElementsBy(By by)
    {
        pageLoadComplete();
        return driver.findElements(by);
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.debug(e.getMessage(), e);
        }
    }

    public boolean isElementPresent(By by)
    {
        try
        {
            getElementBy(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

    public boolean isElementPresent(WebElement element, By by)
    {
        try
        {
            element.findElement(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

    public void hoverElement(WebElement element)
    {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void scrollToElement(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(1000);
    }
}
