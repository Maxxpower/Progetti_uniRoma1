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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrawlerTester {



	public static void main(String[] args) {

		// implementazione multithread del crawler con threadpoolexecutor

		ExecutorService executor = Executors.newFixedThreadPool(5);
		ArrayList<String> baseLinks = new ArrayList<String>();
		ArrayList<Set<String>> linkVisited = new ArrayList<Set<String>>();

		baseLinks.add("http://www.lastampa.it");
		baseLinks.add("http://www.hwupgrade.it");
		baseLinks.add("http://www.wired.it");
		baseLinks.add("http://www.hdblog.it");
		baseLinks.add("http://www.gamemag.it");
		baseLinks.add("http://www.ilmanifesto.info");
		baseLinks.add("http://www.tuttosport.com");
		baseLinks.add("http://www.ilmessaggero.it");
		baseLinks.add("http://www.repubblica.it");
		baseLinks.add("http://www.corriere.it");
		baseLinks.add("http://www.huffingtonpost.it");
		baseLinks.add("http://www.gazzetta.it");
		baseLinks.add("http://www.ilfattoquotidiano.it");
		baseLinks.add("http://www.ilgiornale.it");
		baseLinks.add("http://www.nexthardware.com");
		baseLinks.add("http://www.amazon.it");
		baseLinks.add("http://www.liberoquotidiano.it");
		baseLinks.add("http://www.avvenire.it");
		baseLinks.add("http://www.lanazione.it");

		for (int i = 0; i < baseLinks.size(); i++) {

			String currentBase = baseLinks.get(i);
			 Runnable crawler= new Spider(currentBase);
			 executor.execute(crawler);

//			try {
//				Callable<Set<String>> sp = new Spider(currentBase);
//				Future<Set<String>> crawlerFuture = executor.submit(sp);
//				linkVisited.add(crawlerFuture.get());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}

//		ArrayList<String> fileLines = new ArrayList<String>();
//
//		for (Set<String> links : linkVisited) {
//
//			for (String s : links) {
//
//				fileLines.add(s);
//
//			}
//
//		}
//		
//		//scrivo su file
//		
//		salvaLinksSuFile("C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\links.txt", fileLines);

	}

}
