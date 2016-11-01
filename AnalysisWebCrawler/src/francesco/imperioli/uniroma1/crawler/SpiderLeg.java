package francesco.imperioli.uniroma1.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {

	private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new ArrayList<String>();
	private Document doc;

	public boolean crawl(String url) {

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

	public List<String> getLinks() {
		return links;
	}

}
