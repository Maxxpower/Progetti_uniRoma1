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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import francesco.imperioli.uniroma1.utils.FileUtils;

public class CookieStatsGenerator {

	private Map<String, Integer> cookieHostOccurrencesMap = new HashMap<String, Integer>();
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

	public Map<String, Integer> getCookieHostOccurrencesMap() {
		return cookieHostOccurrencesMap;
	}

	public void setCookieHostOccurrencesMap(Map<String, Integer> cookieHostOccurrencesMap) {
		this.cookieHostOccurrencesMap = cookieHostOccurrencesMap;
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
						// update sulla mappa delle occorrenze
						updateOccurrencesMap(cookie.getElementsByTagName("host").item(0).getTextContent());

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

	private void updateOccurrencesMap(String key) {

		if (!cookieHostOccurrencesMap.containsKey(key)) {

			cookieHostOccurrencesMap.put(key, 1);

		} else {

			Integer currentValue = cookieHostOccurrencesMap.get(key);
			cookieHostOccurrencesMap.put(key, currentValue + 1);

		}

	}

}
