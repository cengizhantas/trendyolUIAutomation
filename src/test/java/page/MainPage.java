package page;

import base.BaseUtil;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MainPage extends BaseUtil
{
    protected final Logger log = Logger.getLogger(getClass());

    public MainPage(WebDriver driver)
    {
        super(driver);
    }

    public void checkMainPageTabs()
    {
        List<String> linkList = new ArrayList<String>();
        // tab header url listesi oluşturuldu.
        for (WebElement element : getElementsBy(By.cssSelector(".main-nav .tab-header"))) {
            linkList.add(element.getAttribute("href"));
            log.info("href=" + element.getAttribute("href"));
        }

        // tüm tab headerlar gezilerek daha önce oluşturduğumuz linkler kontrol
        // edilecek
        // her sayfada butik altında img tagi yoksa assert verecek

        boolean isPresentButik = false;
        for (int i = 0; i < getElementsBy(By.cssSelector(".main-nav .tab-header")).size(); i++)
        {
            clickByWebElement(getElementsBy(By.cssSelector(".main-nav .tab-header")).get(i));
            Assert.assertTrue("'" + linkList.get(i) + "' sayfa hatası!", checkUrlContains(linkList.get(i)));

            for (int j = 0; j < getElementsBy(By.className("butik")).size(); j++)
            {
                isPresentButik = isElementPresent(getElementsBy(By.className("butik")).get(j), By.tagName("img"));
                if (!isPresentButik)
                {
                    log.info("'" + getElementsBy(By.className("butik")).get(j).findElement(By.tagName("a"))
                            .getAttribute("title") + "' isimli butikte img tagi bulunamadı!");
                }
                // Assert.assertTrue("Butik img hatası!!",isPresentButik);
                // //hata fırtlatmak istersek bu assert ü açabiliriz
            }
        }
    }

    public boolean checkUrlContains(String urlpart)
    {
        try
        {
            return driver.getCurrentUrl().contains(urlpart);
        } catch (NoSuchElementException e)
        {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

}
