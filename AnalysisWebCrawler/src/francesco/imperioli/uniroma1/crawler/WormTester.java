package francesco.imperioli.uniroma1.crawler;



public class WormTester {

	public static void main(String[] args) {
	
		WormCrawler wc= new WormCrawler();
		wc.ricerca("http://www.myprotein.com", "[src]");
	}

}
