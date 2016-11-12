package francesco.imperioli.uniroma1.crawler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
	private Map<String, Integer> cookieOriginMap = new HashMap<String, Integer>();
	private Map<String, Integer> numberOfCookiesPerSite = new HashMap<String, Integer>();

	public Map<String, Integer> getCookieHostOccurrencesMapIT() {
		return cookieHostOccurrencesMapIT;
	}

	public Map<String, Integer> getCookieOriginMap() {
		return cookieOriginMap;
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

						// la fonte del cookie, il dominio di provenienza è
						// registrato nel campo host del nostro xml
						String cookieDomainHost = cookie.getElementsByTagName("host").item(0).getTextContent();

						// update sulla mappa delle occorrenze, devo fare alcune
						// distinzioni in base alla TLD dell'host, in modo da
						// avere più grafici
						String domainTLD = cookieDomainHost.substring(cookieDomainHost.lastIndexOf('.') + ".".length(),
								cookieDomainHost.length()); // it, com, net etc
						// esempio {www.alvolante.it=3, immagini.alvolante.it=1}
						// String regExpDomainWithoutDot =
						// cookieDomainHost.substring(1,
						// cookieDomainHost.length()).substring(0,
						// cookieDomainHost.lastIndexOf('.'));

						// ***** creazione reg ex dinamica sulla base del
						// dominio *************
						String host = cookieDomainHost.substring(0, cookieDomainHost.lastIndexOf('.'));
						host = host.substring(host.lastIndexOf('.') + ".".length(), host.length());
						String mainDomainRegExp = "([a-z0-9]+[.])*" + host + "[.]" + domainTLD;
						// ***** fine creazione reg ex dinamica sulla base del
						// dominio ********
						// poiche la chiave è dinamica la inizializzo qui invece
						// di ripeterla, paradigma DRY.
						String keyToUpdate = "www." + host + "." + domainTLD;

						/*
						 * GENERAZIONE DELLE STATISTICHE SUGLI HOST DEL COOKIE*
						 * DA FILE XML
						 */

						// controllo che i cookie siano di terze
						// parti,attraverso il nome del file escludo i cookie
						// che non sono cross-domain (ovvero, se il cookie
						// corrente ha un dominio coerente con quello del nome
						// del file xml non è da considerare) DA RIVEDERE
						String fileFirstDomain = filename.split("\\.")[0];
						// regex da rivedere
						if (!fileFirstDomain.matches("([a-z0-9]?+[.])*" + host)) {

							// System.out.println("FILENAME: " + filename +"
							// HOST PRESENTI: " + host);

							if (domainTLD.equalsIgnoreCase("it")) {
								// se ho già un cookie di un sotto-sovra dominio
								// nella mappa, lo uso come chiave, eliminando
								// ridondanze
								for (String s : cookieHostOccurrencesMapIT.keySet()) {
									// verifico se il cookie esiste gia sulla
									// base
									// della regex
									if (s.matches(mainDomainRegExp)) {
										keyToUpdate = s;
										// se ho trovato cosa cercavo esco per
										// evitare cicli inutili
										break;
									}
								}
								updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapIT);
							} else if (domainTLD.equalsIgnoreCase("com")) {
								// se ho già un cookie di un sotto/sovra dominio
								// nella mappa, lo uso come chiave, eliminando
								// ridondanze
								for (String s : cookieHostOccurrencesMapIT.keySet()) {
									if (s.matches(mainDomainRegExp)) {
										keyToUpdate = s;
										// se ho trovato cosa cercavo esco per
										// evitare cicli inutili
										break;
									}
								}
								updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapCOM);

							} else if (domainTLD.equalsIgnoreCase("net")) {
								// se ho già un cookie di un sotto-sovra dominio
								// nella mappa, lo uso come chiave, eliminando
								// ridondanze
								for (String s : cookieHostOccurrencesMapIT.keySet()) {
									if (s.matches(mainDomainRegExp)) {
										keyToUpdate = s;
										// se ho trovato cosa cercavo esco per
										// evitare cicli inutili
										break;
									}
								}
								updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapNET);
							} else {
								// se ho già un cookie di un sotto-sovra dominio
								// nella mappa, lo uso come chiave, eliminando
								// ridondanze
								for (String s : cookieHostOccurrencesMapIT.keySet()) {
									if (s.matches(mainDomainRegExp)) {
										keyToUpdate = s;
										// se ho trovato cosa cercavo esco per
										// evitare cicli inutili
										break;
									}
								}
								updateOccurrencesMap(keyToUpdate, cookieHostOccurrencesMapMISC);
							}

							// statistiche sul tld di origine dei cookie
							updateOccurrencesMap(domainTLD, cookieOriginMap);

							/*
							 * GENERAZIONE DEI SELECTORS PER L'ANALISI DELLE
							 * PAGINE
							 */
							// utilizzo la variabile host, contenente il dominio
							// di provenienza del cookie corrente, per ricercare
							// nella pagina i tags in cui il cookie corrente
							// viene "richiamato", scrivo così i cookie sul file
							// selectors.txt

							String selectorsFilePath = "./selectorFiles/selectors.txt";
							generateSelectorsByHost(host, selectorsFilePath);

						}

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

	// write css selectors generated by cookie host on the xml files. The
	// selectors list will be written on the selectors file
	private void generateSelectorsByHost(String host, String selectorFile) {

		Path selectorsFilePath = Paths.get(selectorFile);
		List<String> currentSelectors = null;
		try {
			currentSelectors = Files.readAllLines(selectorsFilePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String> selectorsToWrite = new ArrayList<String>();

		// generating selectors strings TODO: add more selectors
		selectorsToWrite.add("iframe[src*=" + host + "]");
		selectorsToWrite.add("script[src*=" + host + "]");
		selectorsToWrite.add("img[src*=" + host + "]");
		selectorsToWrite.add("video[src*=" + host + "]");
		selectorsToWrite.add("object[data*=" + host + "]");
		selectorsToWrite.add("form[action*=" + host + "]");

		// write the list to selectorFile

		if (!currentSelectors.containsAll(selectorsToWrite)) {
			try {
				Files.write(selectorsFilePath, selectorsToWrite, StandardOpenOption.APPEND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
