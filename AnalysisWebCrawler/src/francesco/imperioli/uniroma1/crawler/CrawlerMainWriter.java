package francesco.imperioli.uniroma1.crawler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.file.Files;

public class CrawlerMainWriter {

	public static void main(String[] args) {

		if (args[0] == null) {
			return;
		}

		Path file = Paths.get(args[0]);
		try {
			
			//implementazione multithread
			
			List<String> lineeFile = Files.readAllLines(file);
			ExecutorService executor = Executors.newFixedThreadPool(1);

			for (int i = 0; i < lineeFile.size(); i++) {

				String lineaCurr = lineeFile.get(i);
				System.out.println(lineaCurr);
				Runnable cookieWriter = new ThirdPartyCookieWriter(lineaCurr);
				executor.execute(cookieWriter);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
