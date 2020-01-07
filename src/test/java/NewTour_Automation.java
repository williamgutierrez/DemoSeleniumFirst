import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NewTour_Automation {

    private WebDriver driver;
    By registerLocation = By.linkText("REGISTER");
    By registerPageLocator=By.xpath("//img[@src='/images/masts/mast_register.gif']");
    By usernameLocator=By.id("email") ;
    By passwordLocator=By.name("password");
    By confirmPasswordLocator= By.cssSelector("input[name='confirmPassword']");//By.name("confirmPassword");//input[name='confirmPassword']
    By register=By.name("register");
    By fontLocator = By.tagName("font");

    By linkSignOn= By.linkText("SIGN-ON");
    By inputUserName= By.name("userName");
    By inputPassw = By.name("password");
    By buttonSubmit= By.name("login");
    By imgSingON = By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']");
    By imgNoValidaSingON = By.xpath("//img[@src='/images/masts/mast_signon.gif']");

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://newtours.demoaut.com/");
    }

    @Test
    public void registroUsuario() throws InterruptedException {
        driver.findElement(registerLocation).click();
        Thread.sleep(2000);
        if(driver.findElement(registerPageLocator).isDisplayed()){
            driver.findElement(usernameLocator).sendKeys("wallas20");
            driver.findElement(passwordLocator).sendKeys("123456");
            driver.findElement(confirmPasswordLocator).sendKeys("123456");
            driver.findElement(register).click();
        }else{
            System.out.println("Register Page was not found");
        }
        List<WebElement>fonts= driver.findElements(fontLocator);
        assertEquals("Note: Your user name is william.",fonts.get(5).getText() );
    }
    //verificar SIGNON con password valido
    // 1 - Automatizar el Sign-On Con credenciales validas
    @Test
    public void singnOnUser(){
       driver.findElement(linkSignOn).click();
       driver.findElement(inputUserName).sendKeys("wallas20");
       driver.findElement(inputPassw).sendKeys("123456");
       driver.findElement(buttonSubmit).click();
       assertNotNull(imgSingON);
    }

    // 2 - Automatizar el Sign-On negative case con credenciales incorrectas
    //verifica el SINGON con passwor invalidos
    @Test
    public void OffSingOnUser(){
        driver.findElement(linkSignOn).click();
        driver.findElement(inputUserName).sendKeys("wallas20");
        driver.findElement(inputPassw).sendKeys("5456451");
        driver.findElement(buttonSubmit).click();
        assertNotNull(imgNoValidaSingON);
    }

    @After
    public void teardDown(){
        driver.quit();
    }



}
