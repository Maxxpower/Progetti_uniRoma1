package test.francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ThirdPartyCookieTest {

	FirefoxDriver driver = null;

	@Before
	public void setUp() throws Exception {
		// File file = new File("/opt/geckodriver");
		// System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		// caps.setCapability(
		// InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		// true);

		// driver = new InternetExplorerDriver(caps);
		// System.setProperty("webdriver.firefox.driver",file.getAbsolutePath());

		// Caricamento dell'estensione, sembrerebbe non funzionare, problemi col path?

		FirefoxProfile fp = new FirefoxProfile();
		Path percorsoEstensione = Paths.get("C:\\Users\\Francesco\\Downloads\\get_all_cookies_in_xml.xpi");
		File extensionFile = new File(percorsoEstensione.toString());
		fp.addExtension(extensionFile);


		driver = new FirefoxDriver();

		// Cancello tutti i cookies presenti (anche se non dovrebbero
		// essercene);

		driver.manage().deleteAllCookies();
		driver.get("http://www.huffingtonpost.it/");

		// driver.wait(5000);
		// selenium = new WebDriverBackedSelenium(driver,
		// "https://127.0.0.1:8843/bidding/secured/showLogin.action");
		// driver.navigate().to("javascript:document.getElementById('overridelink').click()");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		// driver.close();
	}

	@Test
	public void getCookiesTest() {
		// TODO verificare perchè non aggiorna il file cookie.xml, firefox a quanto sembra non riesce a caricare l'estensione

		driver.get("chrome://getallcookies/content/getAllCookies.xul");

		// wait di 3s per dare tempo a firefox di scrivere sul file
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set<Cookie> allCookies = driver.manage().getCookies();
		for (Cookie c : allCookies) {
			System.out.println("---------------");
			System.out.println("Domain:" + c.getDomain());
			System.out.println("Name:" + c.getName());
			System.out.println("Path:" + c.getPath());
			System.out.println("Value:" + c.getValue());
		}

	}

}
