package francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import francesco.imperioli.uniroma1.utils.FileUtils;

public class CookieStatsGenerator {

	private Map<String, Integer> cookieHostOccurrencesMapIT = new HashMap<String, Integer>();
	private Map<String, Integer> cookieHostOccurrencesMapCOM = new HashMap<String, Integer>();
	private Map<String, Integer> cookieHostOccurrencesMapNET = new HashMap<String, Integer>();
	private Map<String, Integer> cookieHostOccurrencesMapMISC = new HashMap<String, Integer>();

	public Map<String, Integer> getCookieHostOccurrencesMapIT() {
		return cookieHostOccurrencesMapIT;
	}

	public void setCookieHostOccurrencesMapIT(Map<String, Integer> cookieHostOccurrencesMapIT) {
		this.cookieHostOccurrencesMapIT = cookieHostOccurrencesMapIT;
	}

	public Map<String, Integer> getCookieHostOccurrencesMapCOM() {
		return cookieHostOccurrencesMapCOM;
	}

	public void setCookieHostOccurrencesMapCOM(Map<String, Integer> cookieHostOccurrencesMapCOM) {
		this.cookieHostOccurrencesMapCOM = cookieHostOccurrencesMapCOM;
	}

	public Map<String, Integer> getCookieHostOccurrencesMapNET() {
		return cookieHostOccurrencesMapNET;
	}

	public void setCookieHostOccurrencesMapNET(Map<String, Integer> cookieHostOccurrencesMapNET) {
		this.cookieHostOccurrencesMapNET = cookieHostOccurrencesMapNET;
	}

	public Map<String, Integer> getCookieHostOccurrencesMapMISC() {
		return cookieHostOccurrencesMapMISC;
	}

	public void setCookieHostOccurrencesMapMISC(Map<String, Integer> cookieHostOccurrencesMapMISC) {
		this.cookieHostOccurrencesMapMISC = cookieHostOccurrencesMapMISC;
	}

	private Map<String, Integer> numberOfCookiesPerSite = new HashMap<String, Integer>();

	public Map<String, Integer> getNumberOfCookiesPerSite() {
		return numberOfCookiesPerSite;
	}

	public void setNumberOfCookiesPerSite(Map<String, Integer> numberOfCookiesPerSite) {
		this.numberOfCookiesPerSite = numberOfCookiesPerSite;
	}

	public CookieStatsGenerator(String directory) {

		readXmlFiles(directory);

	}

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
						// update sulla mappa delle occorrenze, devo fare alcune
						// distinzioni in base alla TLD dell'host, in modo da
						// avere più grafici
						String cookieDomainHost = cookie.getElementsByTagName("host").item(0).getTextContent();

						// controllo che i cookie siano di terze
						// parti,attraverso il nome del file escludo i cookie
						// che non sono cross-domain DA RIVEDERE
						// if
						// (!StringUtils.containsIgnoreCase(filename.split("\\.")[0],
						// cookieDomainHost.split("\\.")[1])) {

						String[] splittedDomainURL = cookieDomainHost.split("\\.");
						String domainTLD = splittedDomainURL[splittedDomainURL.length - 1];

						if (domainTLD.equalsIgnoreCase("it")) {

							String keyToUpdate = cookieDomainHost;

							for (String s : cookieHostOccurrencesMapIT.keySet()) {

								if (StringUtils.containsIgnoreCase(s, cookieDomainHost)) {

									keyToUpdate = s;

								}

							}

							updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapIT);

						} else if (domainTLD.equalsIgnoreCase("com")) {

							String keyToUpdate = cookieDomainHost;

							for (String s : cookieHostOccurrencesMapCOM.keySet()) {

								if (StringUtils.containsIgnoreCase(s, cookieDomainHost)) {

									keyToUpdate = s;

								}
							}

							updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapCOM);

						} else if (domainTLD.equalsIgnoreCase("net")) {

							String keyToUpdate = cookieDomainHost;

							for (String s : cookieHostOccurrencesMapNET.keySet()) {

								if (StringUtils.containsIgnoreCase(s, cookieDomainHost)) {

									keyToUpdate = s;

								}
							}

							updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapNET);

						} else {

							String keyToUpdate = cookieDomainHost;

							for (String s : cookieHostOccurrencesMapNET.keySet()) {

								if (StringUtils.containsIgnoreCase(s, cookieDomainHost)) {

									keyToUpdate = s;

								}
							}

							updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapMISC);

						}

						// }

					}

				}

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void updateOccurrencesMap(String key, Map<String, Integer> map) {

		if (!map.containsKey(key)) {

			map.put(key, 1);

		} else {

			Integer currentValue = map.get(key);
			map.put(key, currentValue + 1);

		}

	}

}
