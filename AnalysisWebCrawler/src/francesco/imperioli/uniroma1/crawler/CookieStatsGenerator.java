/*
 * Copyright (c) 2016, Francesco Imperioli. All rights reserved.
 * Francesco Imperioli PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import francesco.imperioli.uniroma1.utils.FileUtils;

/**
 * Class {@code CookieStatsGenerator} is the main class for statistical information generation. <br/>
 * This class produces information like number of cookies for each site and so on.
 *
 * @author  Francesco Imperioli
 * @version 1.0
 */
public class CookieStatsGenerator {

	private Map<String, Integer> cookieHostOccurrencesMap = new HashMap<String, Integer>();
	private Map<String, Integer> numberOfCookiesPerSite = new HashMap<String, Integer>();
	
	/**
	 * Creates a new instance of  {@code CookieStatsGenerator} by the given directory.
	 * @param directory the folder which contains the xml file(s) to analyze.
	 */
	public CookieStatsGenerator(String directory) {
		readXmlFiles(directory);
	}

	/**
	 * This method returns a {@code Map} containing the number of cookies per site. <br/>
	 * The key is the site name and the value is the over all sum of the cookies
	 * for the current site.
	 * @return the number of cookies per site
	 */
	public Map<String, Integer> getNumberOfCookiesPerSite() {
		return numberOfCookiesPerSite;
	}
	
	/**
	 * This method sets a {@code Map} containing the number of cookies per site. <br/>
	 * The key is the site name and the value is the over all sum of the cookies
	 * for the current site.
	 * @param numberOfCookiesPerSite
	 */
	public void setNumberOfCookiesPerSite(Map<String, Integer> numberOfCookiesPerSite) {
		this.numberOfCookiesPerSite = numberOfCookiesPerSite;
	}

	/**
	 * This method returns a {@code Map} containing the number of occurrences per site of the current cookie. <br/>
	 * The key is the cookie name and the value is the over all sum of the occurrences
	 * for the current cookie.
	 * @return
	 */
	public Map<String, Integer> getCookieHostOccurrencesMap() {
		return cookieHostOccurrencesMap;
	}

	/**
	 * This method sets a {@code Map} containing the number of occurrences of the current cookie. <br/>
	 * The key is the cookie name and the value is the over all sum of the occurrences
	 * for the current cookie.
	 * @param cookieHostOccurrencesMap
	 */
	public void setCookieHostOccurrencesMap(Map<String, Integer> cookieHostOccurrencesMap) {
		this.cookieHostOccurrencesMap = cookieHostOccurrencesMap;
	}
	
	/*
	 * Executes the business logic loading all xml files inside the given directory if any exists.
	 */
	private void readXmlFiles(String directory) {
		// tutti i file xml generati manualmente
		List<Path> files = FileUtils.getAllXmlfromDirectory(directory);
		for (Path p : files) {
			try {
				// lettura dei file xml della cartella
				File xmlFIle = p.toFile();
				String filename = p.getFileName().toString();
				System.out.println("FILE ANALIZZATO: " + filename);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(xmlFIle);
				NodeList cookieTagList = doc.getElementsByTagName("cookie");
				numberOfCookiesPerSite.put(filename.split("\\.")[0], cookieTagList.getLength());
				for (int i = 0; i < cookieTagList.getLength(); i++) {
					Node currentCookieNode = cookieTagList.item(i);
					if (currentCookieNode.getNodeType() == Node.ELEMENT_NODE) {
						Element cookie = (Element) currentCookieNode;
						// update sulla mappa delle occorrenze
						updateOccurrencesMap(cookie.getElementsByTagName("host").item(0).getTextContent());
					}
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Updates the number of occurrences by the given key.
	 * If the given key exists then increases the value, otherwise adds
	 * the new values to the map and put the initial size to 1
	 */
	private void updateOccurrencesMap(String key) {
		if (!cookieHostOccurrencesMap.containsKey(key)) {
			cookieHostOccurrencesMap.put(key, 1);
		} else {
			Integer currentValue = cookieHostOccurrencesMap.get(key);
			cookieHostOccurrencesMap.put(key, currentValue + 1);
		}
	}

}
