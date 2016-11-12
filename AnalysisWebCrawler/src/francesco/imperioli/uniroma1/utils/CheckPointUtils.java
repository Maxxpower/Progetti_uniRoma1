package francesco.imperioli.uniroma1.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckPointUtils {

	public static final String CHECKPOINTFILE = "./result_files/checkpoint.txt";

	public synchronized static void writeUrl(String url) {
		if (checkVisited(url)) {

			return;

		}

		Path file = Paths.get(CHECKPOINTFILE);
		List<String> linea = new ArrayList<String>();
		linea.add(url);

		try {
			Files.write(file, linea, StandardOpenOption.APPEND);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized static void writeUrl(Set<String> visitedUrls) {
		if (checkVisited(visitedUrls)) {

			return;

		}

		Path file = Paths.get(CHECKPOINTFILE);

		try {
			Files.write(file, visitedUrls, StandardOpenOption.APPEND);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized static boolean checkVisited(String url) {

		List<String> lines = null;
		Path file = Paths.get(CHECKPOINTFILE);
		try {

			return Files.readAllLines(file).contains(url);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public synchronized static boolean checkVisited(Set<String> visitedUrls) {

		List<String> lines = null;
		Path file = Paths.get(CHECKPOINTFILE);
		try {

			return Files.readAllLines(file).containsAll(visitedUrls);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
