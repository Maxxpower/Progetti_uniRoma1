package francesco.imperioli.uniroma1.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WormCrawler {

	private static final int MAX_PAGES = 100;
	private Set<String> pagineVisitate = new HashSet<String>();
	private List<String> pagineDaVisitare = new LinkedList<String>();

	private String prossimoUrl() {

		// Scorro gli url da visitare cancellandoli dalla linkedlist ed
		// aggiungendoli al set dei visitati

		String prossimoUrl;

		do {

			prossimoUrl = pagineDaVisitare.remove(0);
		} while (pagineVisitate.contains(prossimoUrl));

		pagineVisitate.add(prossimoUrl);
		return prossimoUrl;

	}

	/*
	public void ricerca(String url, String cssSelector) {

		while (pagineVisitate.size() < MAX_PAGES) {

			String currentUrl;
			WormSon ws = new WormSon();
			if (pagineDaVisitare.isEmpty()) {

				currentUrl = url;
				pagineVisitate.add(url);

			} else {

				currentUrl = prossimoUrl();

			}

			// richiamo il metodo crawl da WormSon
			boolean success = ws.crawl(currentUrl, cssSelector);

			if (success) {

				// System.out.println(ws.getLinks());
				System.out.println(ws.getObjectsOnPage());

			}

			pagineDaVisitare.addAll(ws.getLinks());

		}

	}*/

}
