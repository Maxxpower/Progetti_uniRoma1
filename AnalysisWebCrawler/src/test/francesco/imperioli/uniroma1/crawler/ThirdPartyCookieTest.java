package test.francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ThirdPartyCookieTest {

	FirefoxDriver driver = null;

	@Before
	public void setUp() throws Exception {
		//File file = new File("/opt/geckodriver");
//		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//		caps.setCapability(
//		    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
//		    true);
		 
		
//		driver = new InternetExplorerDriver(caps);
		//System.setProperty("webdriver.firefox.driver",file.getAbsolutePath());
		
		//Caricamento dell'estensione, sembrerebbe non servire se l'estensione è installata.
		
		//FirefoxProfile fp= new FirefoxProfile();
		//Path percorsoEstensione=Paths.get("C:\\Users\\Francesco\\Downloads\\get_all_cookies_in_xml.xpi");
		//File extensionFile=new File(percorsoEstensione.toString());
		//fp.addExtension(extensionFile);
		
		
		driver= new FirefoxDriver();
		
		//Cancello tutti i cookies presenti (anche se non dovrebbero essercene);
		
		driver.manage().deleteAllCookies();
		driver.get("http://www.huffingtonpost.it/");
		
		
		
		//driver.wait(5000);
		//selenium = new WebDriverBackedSelenium(driver, "https://127.0.0.1:8843/bidding/secured/showLogin.action");
		//driver.navigate().to("javascript:document.getElementById('overridelink').click()");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		//driver.close();
	}
	
	@Test
	public void getCookiesTest(){
		//TODO verificare perchè non apre huffingtonpost.it e non aggiorna il file cookie.xml
		
		//driver.navigate().to("http://www.huffingtonpost.it/");
		//driver.get("chrome://getallcookies/content/getAllCookies.xul");
		
		//apro una nuova finestra e inserisco l'url trigger per l'estensione getAllcookiesXML TODO: verificarne il funzionamento corretto (sembra non funzionare)
		 WebElement body = driver.findElement(By.tagName("body"));
		 body.sendKeys(Keys.chord(Keys.CONTROL, "n"));
		 driver.get("chrome://getallcookies/content/getAllCookies.xul");

		
		
		
		Set<Cookie> allCookies = driver.manage().getCookies();
		for(Cookie c : allCookies){
			System.out.println("---------------");
			System.out.println("Domain:" + c.getDomain());
			System.out.println("Name:" + c.getName());
			System.out.println("Path:" + c.getPath());
			System.out.println("Value:" + c.getValue());
		}
		
	}
	

}
