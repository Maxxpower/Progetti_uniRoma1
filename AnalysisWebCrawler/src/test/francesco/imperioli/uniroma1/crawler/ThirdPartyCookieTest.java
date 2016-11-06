package test.francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import francesco.imperioli.uniroma1.utils.FileUtils;

public class ThirdPartyCookieTest {

	FirefoxDriver driver = null;
	File profileFile;

	@Before
	public void setUp() throws Exception {

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile ffprofile = profile.getProfile("default");

		// qui trovo il path del profilo!!finalmente!!!
		this.profileFile = ffprofile.layoutOnDisk();
		System.out.println("CARTELLA PROFILO: " + profileFile.getAbsolutePath());

		driver = new FirefoxDriver(ffprofile);

		driver.manage().deleteAllCookies();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		// driver.close();
	}

	@Test
	public void getCookiesTest() {
		driver.get("http://hdblog.it");

		driver.get("chrome://getallcookies/content/getAllCookies.xul");

		// ora vado a copiare il file xml temporaneo in un altro file xml che
		// poi analizzerò

		String pathOftempXml = profileFile.getAbsolutePath() + "\\cookie.xml";
		String pathOfXml = "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\cookiesXml\\" + "hpostit1.xml";
		try {

			Path fileFrom = Paths.get(pathOftempXml);
			Path fileTo = Paths.get(pathOfXml);
			Files.copy(fileFrom, fileTo, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// wait di 3s per dare tempo a firefox di scrivere sul file
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (profileFile.exists()) {
			try {
				org.apache.commons.io.FileUtils.deleteDirectory(new File(profileFile.getAbsolutePath()));
				System.out.println("profilo " + profileFile.getAbsolutePath() + " cancellato");
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}
}
