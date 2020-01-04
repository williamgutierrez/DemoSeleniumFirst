import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GoogleSearchTest {
    private WebDriver driver;
    By videLinkLocator= By.cssSelector("a[href='https://www.youtube.com/watch?v=RbSlW8jZFe8']");
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }
    @Test
    public void testGooglePage() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("QA Automation video");
        searchBox.submit();
        Thread.sleep(3000);
        assertTrue(driver.findElement(videLinkLocator).isDisplayed());

    }
    @Test
    public void testGooglePageSeleniumIDE(){
        //driver.get("https://www.google.com/");
        //driver.manage().window().setSize(new Dimension(944, 802));
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).sendKeys("qa automation video");
        driver.findElement(By.name("q")).submit();
        // driver.findElement(By.name("btnK")).click();
        assertThat(driver.findElement(By.cssSelector(".bkWMgd > .g > div > .rc .LC20lb")).getText(), is("Automation Testing Tutorial for Beginners - YouTube"));
        //driver.close();

    }

    @After
    public void teardDown(){
        driver.quit();
    }

}
