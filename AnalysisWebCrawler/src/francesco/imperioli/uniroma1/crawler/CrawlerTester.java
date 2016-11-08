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
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrawlerTester {

	public static void main(String[] args) {

		// implementazione multithread del crawler con threadpoolexecutor
		// lettura dei link di partenza da file txt, utilizzo java nio
		List<String> baseLinks = null;
		Path linkFile = Paths.get("./inputFiles/listOfurl");
		try {
			baseLinks = Files.readAllLines(linkFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// multithread sul desktop 5-6 thread sul portatile max 2 thread
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (int i = 0; i < baseLinks.size(); i++) {

			String currentBase = baseLinks.get(i);
			Runnable crawler = new Spider(currentBase);
			executor.execute(crawler);

		}

	}

}
