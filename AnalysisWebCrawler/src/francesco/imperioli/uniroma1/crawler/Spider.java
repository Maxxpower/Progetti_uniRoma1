package francesco.imperioli.uniroma1.crawler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class Spider implements Runnable, Callable<Set<String>> {

	private static final int MAX_PG = 50;
	private Set<String> visitate = new HashSet<String>();
	private List<String> daVisitare = new ArrayList<String>();
	private String startUrl;

	public Spider(String url) {

		this.startUrl = url;

	}

	private String nextUrl() {

		String nextUrl;

		do {

			nextUrl = this.daVisitare.remove(0);
		} while (this.visitate.contains(nextUrl));

		this.visitate.add(nextUrl);
		return nextUrl;

	}

	public void navigaLink() {

		while (this.visitate.size() < MAX_PG) {

			String urlCorrente;

			SpiderLeg leg = new SpiderLeg();
			if (this.daVisitare.isEmpty()) {

				urlCorrente = startUrl;
				this.visitate.add(startUrl);

			} else {

				urlCorrente = nextUrl();

			}

			leg.crawl(urlCorrente);
			this.daVisitare.addAll(leg.getLinks());
			System.out.println("Visitate: " + visitate.size() + " pagine");

		}

	}

	public synchronized void salvaLinksSuFile(String nomeFile) {

		Path file = Paths.get(nomeFile);
		try {
			Files.write(file, this.visitate, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		navigaLink();
		salvaLinksSuFile("C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\links.txt");

	}

	@Override
	public Set<String> call() throws Exception {
		
		navigaLink();
		return this.visitate;
	}

}
