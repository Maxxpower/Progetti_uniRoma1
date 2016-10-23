package test.francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		driver= new FirefoxDriver();
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
