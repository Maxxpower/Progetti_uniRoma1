/*
 * Copyright (c) 2016, Francesco Imperioli. All rights reserved.
 * Francesco Imperioli PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package francesco.imperioli.uniroma1.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class FileUtils {

	public static void salvaLinksSuFile(String nomeFile, List<String> linee) {

		Path file = Paths.get(nomeFile);
		try {
			Files.write(file, linee, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void copyXmlFile(String file1, String file2) {

		// copia linea a linea

		Path fileToRead = Paths.get(file1);
		Path fileToWrite = Paths.get(file2);

		try {
			List<String> linee = Files.readAllLines(fileToRead);
			Files.write(fileToWrite, linee);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Path> getAllXmlfromDirectory(String dirPath) {

		// ritorna la lista dei files in una directory usando java.nio

		List<Path> listaFiles = new ArrayList<Path>();

		try {
			Stream<Path> paths = Files.walk(Paths.get(dirPath));
			paths.forEach(filepath -> {
				if (Files.isRegularFile(filepath)) {

					listaFiles.add(filepath);

				}

			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaFiles;

	}

	public static void writeMapOnTxt(Map<? extends Object, ? extends Object> map, String title, String filename) {

		List<String> linee = new ArrayList<String>();
		linee.add("===========================" + title + "===========================");
		linee.add("KEY:===================================================VALUE:");
		linee.add("");

		for (Object o : map.keySet()) {

			String line = o.toString() + "-----------------------------------" + map.get(o).toString();
			linee.add(line);
			linee.add("");

		}

		// writing on txt file

		Path fileToWrite = Paths.get(filename);
		try {

			Files.deleteIfExists(fileToWrite);
			Files.write(fileToWrite, linee, StandardOpenOption.CREATE_NEW);
			System.out.println(" STATS FILE: " + filename + " CREATED");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void generateCsvStatsFileFromMap(Map<? extends Object, ? extends Object> map, String filename) {

		List<String[]> listOfCsv = new ArrayList<String[]>();

		// now we populate the list
		for (Object o : map.keySet()) {

			String keyString = o.toString();
			String valueString = map.get(o).toString();

			listOfCsv.add(new String[] { keyString, valueString });

		}

		// write to a file

		CSVWriter writer = null;

		try {
			writer = new CSVWriter(new FileWriter(new File(filename)));
			writer.writeAll(listOfCsv);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
