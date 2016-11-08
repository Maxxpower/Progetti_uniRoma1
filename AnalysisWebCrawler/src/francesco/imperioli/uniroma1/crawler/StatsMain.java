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

		String XmlDirectoryPath = "./xmlCookieFiles";

		CookieStatsGenerator csg = new CookieStatsGenerator(XmlDirectoryPath);
		Map<String, Integer> occorrenzeIT = csg.getCookieHostOccurrencesMapIT();
		Map<String, Integer> occorrenzeCOM = csg.getCookieHostOccurrencesMapCOM();
		Map<String, Integer> occorrenzeNET = csg.getCookieHostOccurrencesMapNET();
		Map<String, Integer> occorrenzeMISC = csg.getCookieHostOccurrencesMapMISC();
		Map<String, Integer> cookieOrigin = csg.getCookieOriginMap();
		Map<String, Integer> cookiePerSito = csg.getNumberOfCookiesPerSite();

		// Generating log files for stats

		// String pathocc =
		// "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\occorrenzeIT.txt";
		// FileUtils.writeMapOnTxt(occorrenzeIT, "NUMERO DI OCCORRENZE PER
		// COOKIE 3D PARTHY", pathocc);
		//
		// String pathoccCOM =
		// "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\occorrenzeCOM.txt";
		// FileUtils.writeMapOnTxt(occorrenzeCOM, "NUMERO DI OCCORRENZE PER
		// COOKIE 3D PARTHY", pathoccCOM);
		//
		// String pathoccNET =
		// "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\occorrenzeNET.txt";
		// FileUtils.writeMapOnTxt(occorrenzeNET, "NUMERO DI OCCORRENZE PER
		// COOKIE 3D PARTHY", pathoccNET);
		//
		// String pathoccMISC =
		// "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\occorrenzeMISC.txt";
		// FileUtils.writeMapOnTxt(occorrenzeMISC, "NUMERO DI OCCORRENZE PER
		// COOKIE 3D PARTHY", pathoccMISC);
		//
		// String pathNc =
		// "C:\\Users\\Francesco\\Documents\\dev\\LinksCrawler\\stats\\cookiepersito.txt";
		// FileUtils.writeMapOnTxt(cookiePerSito, "NUMERO DI COOKIE PER SITO",
		// pathNc);

		// CSV FILES GENERATION
		String csvOcc = "./result_files/cookieStats/csvoccorrenzeIT.txt";
		FileUtils.generateCsvStatsFileFromMap(occorrenzeIT, csvOcc);

		String csvOccCOM = "./result_files/cookieStats/csvoccorrenzeCOM.txt";
		FileUtils.generateCsvStatsFileFromMap(occorrenzeCOM, csvOccCOM);

		String csvOccNET = "./result_files/cookieStats/csvoccorrenzeNET.txt";
		FileUtils.generateCsvStatsFileFromMap(occorrenzeNET, csvOccNET);

		String csvOccMISC = "./result_files/cookieStats/csvoccorrenzeMISC.txt";
		FileUtils.generateCsvStatsFileFromMap(occorrenzeMISC, csvOccMISC);

		String csvNc = "./result_files/cookieStats/csvnumcookie.txt";
		FileUtils.generateCsvStatsFileFromMap(cookiePerSito, csvNc);

		String csvCookieOrigin = "./result_files/cookieStats/csvCookieOrigin.txt";
		FileUtils.generateCsvStatsFileFromMap(cookieOrigin, csvCookieOrigin);

	}
}
