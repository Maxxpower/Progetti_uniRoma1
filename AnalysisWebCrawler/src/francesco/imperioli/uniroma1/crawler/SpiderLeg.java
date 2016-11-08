/*
 * Copyright (c) 2016, Francesco Imperioli. All rights reserved.
 * Francesco Imperioli PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {

	private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new ArrayList<String>();
	private Document doc;
	private List<String> cookieTagsFound = new ArrayList<String>();
	private String baseUrl;


	public boolean crawl(String url) {

		this.baseUrl = url;
		Connection c;

		try {
			c = Jsoup.connect(url).userAgent(userAgent);
			Document htmlDoc;
			htmlDoc = c.get();
			this.doc = htmlDoc;

			if (c.response().statusCode() == 200) {

				System.out.println("Connessione accettata: " + url);

			}
			if (!c.response().contentType().contains("text/html")) {

				System.out.println("Problemi con l'indirizzo" + url);
				return false;

			}

			Elements linkDom = htmlDoc.select("a[href]");

			// looking for cookie-setting tags into the DOM
			//leggo i selettori css da un file txt
			List<String> selectorList=readSelectors();
			
			for (String selector : selectorList) {

				Elements cookieTag = htmlDoc.select(selector);
				addTags(cookieTag);

			}

			// writing these tags in a txt

			writeTagsToFile();

			System.out.println("Found (" + linkDom.size() + ") links");
			for (Element link : linkDom) {
				this.links.add(link.absUrl("href"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	public List<String> getCookieTagsFound() {
		return cookieTagsFound;
	}

	private List<String> readSelectors() {

		List<String> selectorList=null;
		String filePath = new File("").getAbsolutePath();
		Path selectorFilePath=Paths.get(filePath+"\\selectorFiles\\selectors.txt");
		try {
			selectorList=Files.readAllLines(selectorFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return selectorList;

	}

	private void addTags(Elements list) {

		for (Element e : list) {

			cookieTagsFound.add(e.toString());

		}

	}

	public List<String> getLinks() {
		return links;
	}

	private void writeTagsToFile() {

		String[] baseUrlSplit = baseUrl.split("\\.");

		Path tagFile = Paths.get("C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\cookieTags\\" + baseUrlSplit[1]+ ".txt");
		try {
//			
			if (Files.exists(tagFile)){
				
				Files.write(tagFile, cookieTagsFound, StandardOpenOption.APPEND);
				
			}else{
				
				
				Files.write(tagFile, cookieTagsFound, StandardOpenOption.CREATE_NEW);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
