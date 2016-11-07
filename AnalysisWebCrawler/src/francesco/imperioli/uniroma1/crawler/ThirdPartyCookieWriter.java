/*
 * Copyright (c) 2016, Francesco Imperioli. All rights reserved.
 * Francesco Imperioli PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import francesco.imperioli.uniroma1.utils.FileUtils;

public class ThirdPartyCookieWriter implements Runnable {

	FirefoxDriver driver = null;
	File profileFile;
	String url;

	public ThirdPartyCookieWriter(String url) {

		this.url = url;

	}

	public void setUp() throws Exception {

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile ffprofile = profile.getProfile("default");


		// qui trovo il path del profilo
		this.profileFile = ffprofile.layoutOnDisk();
		System.out.println("CARTELLA PROFILO: " + profileFile.getAbsolutePath());


		driver = new FirefoxDriver(ffprofile);

		//forse � questo che mi crea problemi?
		driver.manage().deleteAllCookies();

	}

	public void tearDown() throws Exception {
		driver.quit();
	}

	public void getCookiesTest() {
		driver.get(url);

//		System.out.println("COOKIES FOUND" + driver.manage().getCookies());
		
		driver.get("chrome://getallcookies/content/getAllCookies.xul");

		// ora vado a copiare il file xml temporaneo in un altro file xml che
		// poi analizzer�

		String pathOftempXml = profileFile.getAbsolutePath() + "\\cookie.xml";
		String[] splittedUrl = url.split("\\.");
		String pathOfXml = "./result_files\\" + splittedUrl[1];// +"_"+System.currentTimeMillis()+"_"+".xml";
		File f = new File(pathOfXml);
		
		// se non esiste creo la nuova cartella dal dominio

		if (!f.exists()) {

			f.mkdirs();
		}

		try {

			//copio l'xml generato dall'estensione getAllCookiesXml 
			
			Path fileFrom = Paths.get(pathOftempXml);
			Path fileTo = Paths
					.get(pathOfXml + "\\" + splittedUrl[1] + "_" + System.currentTimeMillis() + "_" + ".xml");
			Files.copy(fileFrom, fileTo, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// wait di 3s per dare tempo a firefox di scrivere sul file
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//cancello i file temporanei creati dal driver firefox
		if (profileFile.exists()) {
			try {
				org.apache.commons.io.FileUtils.deleteDirectory(new File(profileFile.getAbsolutePath()));
				System.out.println("profilo " + profileFile.getAbsolutePath() + " cancellato");
			} catch (IOException e) {
			
				e.printStackTrace();
			}

		}

	}

	@Override
	public void run() {
		try {
			setUp();
			getCookiesTest();
			tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}