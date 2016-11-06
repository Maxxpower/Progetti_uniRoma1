package francesco.imperioli.uniroma1.crawler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import francesco.imperioli.uniroma1.utils.FileUtils;

public class StatsMain {

	// classe main per l'utilizzo dell'oggetto cookiestatsgenerator

	public static void main(String[] args) {

		String XmlDirectoryPath = "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\cookiesXml";

		CookieStatsGenerator csg = new CookieStatsGenerator(XmlDirectoryPath);
		Map<String, Integer> occorrenze = csg.getCookieHostOccurrencesMap();
		Map<String, Integer> cookiePerSito = csg.getNumberOfCookiesPerSite();

		// Generating log files for stats

		String pathocc = "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\occorrenze.txt";
		FileUtils.writeMapOnTxt(occorrenze, "NUMERO DI OCCORRENZE PER COOKIE 3D PARTHY", pathocc);

		String pathNc = "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\cookiepersito.txt";
		FileUtils.writeMapOnTxt(cookiePerSito, "NUMERO DI COOKIE PER SITO", pathNc);
		
		String csvOcc="C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\csvoccorrenze.txt";
		FileUtils.generateCsvStatsFileFromMap(occorrenze, csvOcc);
		
		String csvNc="C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\csvnumcookie.txt";
		FileUtils.generateCsvStatsFileFromMap(cookiePerSito, csvNc);

	}
}
