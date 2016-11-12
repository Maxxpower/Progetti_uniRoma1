/*
 * Copyright (c) 2016, Francesco Imperioli. All rights reserved.
 * Francesco Imperioli PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
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
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import francesco.imperioli.uniroma1.utils.CheckPointUtils;
import francesco.imperioli.uniroma1.utils.FileUtils;

public class MainCrawlerRunner {

	public static void main(String[] args) {

		/**** RUNNING STATS GENERATOR FOR COOKIE OBJECTS IN XML FILES ****/

		// generating stats from xml files containing cookie objects

		String XmlDirectoryPath = "./xmlCookieFiles";

		CookieStatsGenerator csg = new CookieStatsGenerator(XmlDirectoryPath);
		Map<String, Integer> occorrenzeIT = csg.getCookieHostOccurrencesMapIT();
		Map<String, Integer> occorrenzeCOM = csg.getCookieHostOccurrencesMapCOM();
		Map<String, Integer> occorrenzeNET = csg.getCookieHostOccurrencesMapNET();
		Map<String, Integer> occorrenzeMISC = csg.getCookieHostOccurrencesMapMISC();
		Map<String, Integer> cookieOrigin = csg.getCookieOriginMap();
		Map<String, Integer> cookiePerSito = csg.getNumberOfCookiesPerSite();

		// Generating log files for stats
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

		/************ RUNNING THE CRAWLER GENERATIING TAGS FILE **************/

		// implementazione multithread del crawler con threadpoolexecutor
		// lettura dei link di partenza da file txt, utilizzo java nio
//		List<String> baseLinks = null;
//		Path linkFile = Paths.get("./inputFiles/listOfurl");
//		try {
//			baseLinks = Files.readAllLines(linkFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// multithread sul desktop 5-6 thread sul portatile max 2 thread
//		ExecutorService executor = Executors.newFixedThreadPool(6);
//
//		for (int i = 0; i < baseLinks.size(); i++) {
//
//			String currentBase = baseLinks.get(i);
//			Runnable crawler = new Spider(currentBase);
//			executor.execute(crawler);
//
//		}

	}

}
