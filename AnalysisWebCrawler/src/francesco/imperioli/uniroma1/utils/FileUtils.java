package francesco.imperioli.uniroma1.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

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

}
