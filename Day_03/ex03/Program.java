import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
	private static String URLS_FILE = "files_urls.txt";
	private static Map<Integer, String> fileUrl;
	private static int threadsCount;

	public static void readFileFromURL(Integer number, String url) {
		try (BufferedInputStream internetStreem = new BufferedInputStream(new URL(url).openStream()); FileOutputStream localFile = new FileOutputStream(number.toString())) {
			
			byte data[] = new byte[1024];
			int byteContent;
			String threadName = "Thread-" + Thread.currentThread().getName().split("-")[3];

			System.out.printf("%s start download file number %d\n", threadName, number);

			while ((byteContent = internetStreem.read(data, 0, 1024)) != -1) {
				localFile.write(data, 0, byteContent);
			}
			
			System.out.printf("%s finish download file number %d\n", threadName, number);

		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	private static void readURLFile() {
		fileUrl = new HashMap<>();
		
		try (FileReader fileReader = new FileReader(URLS_FILE); Scanner scan = new Scanner(fileReader);) {
			while (scan.hasNext()) {
				if (!scan.hasNextInt()) {
					throw new IOException();
				}
				Integer number = scan.nextInt();
				if (!scan.hasNext()) {
					throw new IOException();
				}
				String url = scan.next();
				fileUrl.put(number, url);
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("Couldn't read " + URLS_FILE);
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		if(args.length == 1){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--threadsCount")) {
				threadsCount = Integer.parseInt(tmp[1]);
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
			readURLFile();
			ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
			for (Map.Entry<Integer, String> entry : fileUrl.entrySet()) {
				executor.submit(() -> readFileFromURL(entry.getKey(), entry.getValue()));
			}
			executor.shutdown();
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
	}
}