package francesco.imperioli.uniroma1.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WormSon {

	private List<String> links = new LinkedList<String>();
	private Document htmlDoc;
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> objectsOnPage = new ArrayList<String>();
	private Map<String, String> cookiesOnpage = new HashMap<String, String>();

	// the css selector must be an attribute selector
	public boolean crawl(String url, String cssSelector) {
		try {
			Connection c = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument;

			htmlDocument = c.get();
			this.htmlDoc = htmlDocument;
			System.out.println("Pagina ricevuta: " + url);
			// qui mi ricavo i link dal dom, in modo da ottenere altre pagine da
			// visitare
			Elements linksOnPage = htmlDocument.select("a[href]");
			// ora ricavo dal dom i tag che mi interessano grazie al selettore
			// css passato in input
			Elements thirdPartyTags = htmlDocument.select(cssSelector);

			if (c.response().statusCode() == 200) {

				System.out.println("Trovati:" + linksOnPage.size() + " link");

			}
			if (!c.response().contentType().contains("text/html")) {

				return false;

			}

			//primo controllo sui cookie nella response
			cookiesOnpage = c.response().cookies();

			// aggiungo i link trovati sulla pagina alla linklist
			for (Element l : linksOnPage) {

				links.add(l.absUrl("href"));

			}

			// aggiungo gli oggetti di interesse trovati sulla pagina alla lista
			// objectOnPage:

			for (Element obj : thirdPartyTags) {
				
				if(obj.tag().equals("img") && obj.attr(cssSelector).contains("cookie")){
					
					objectsOnPage.add(obj.toString());
					
				}
				

				

			}

			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}

	public Map<String, String> getCookiesOnpage() {
		return cookiesOnpage;
	}

	public List<String> getObjectsOnPage() {
		return objectsOnPage;
	}

	public List<String> getLinks() {
		return links;
	}

	public boolean ricercaParola(String searchKey) {
		System.out.println("Cerco la parola " + searchKey + "...");
		String bodyText = this.htmlDoc.body().text();
		return bodyText.toLowerCase().contains(searchKey.toLowerCase());
	}

}
