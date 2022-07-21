import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
	
public class Program {

	private static final String undefined = "UNDEFINED";
	private static final String PROCESSED = "PROCESSED";

	private static Map<String, String[]> signatureMap;
	private static LinkedList<String> resultList;
	private static String signaturesTxt = "signatures.txt";
	private static String resaultTxt = "result.txt";
	private static int maxLength = 0;
	
	private static void getFullPath() {
		File fullPathToSignature = new File(new File(signaturesTxt).getAbsolutePath());
		signaturesTxt = "" + fullPathToSignature.getAbsolutePath();

		File fullPathToResault = new File(new File("").getAbsolutePath());
		resaultTxt = "" + fullPathToResault + "/" + resaultTxt;
	}

	private static void setSignatureMap() {
		try (FileInputStream fileInput = new FileInputStream(signaturesTxt)) {
			signatureMap = new HashMap<>();
			int length = (int)new File(signaturesTxt).length();
			byte[] bytes = new byte[length];
			fileInput.read(bytes);
			String signatureLines = new String(bytes);
			String[] splitedLines = signatureLines.split("\n");
			for (String line : splitedLines) {
				String[] keyVal = line.split(",");
				String[] values = keyVal[1].trim().split(" ");
				signatureMap.put(keyVal[0], values); 
			}
		} catch (IOException e) {
			System.err.println("File not found!");
		}
	}

	public static void setResultList() {
		resultList = new LinkedList<>();
		Scanner input = new Scanner(System.in);
		String filePath;
		while (true) {
			if (!input.hasNext()) {
				input.close();
			}
			filePath = input.next();
			if (filePath.equals("42")) {
				break;
			}
			try (FileInputStream fileInput = new FileInputStream(filePath)) {
				byte[] bytes = new byte[maxLength];
				for (int i = 0; i < maxLength; i++) {
					bytes[i] = (byte)fileInput.read();
				}
				String[] lineBytes = new String[maxLength];
				for (int i = 0; i < maxLength; i++) {
					lineBytes[i] = Integer.toHexString(bytes[i] & 0xff);
					if (lineBytes[i].length() == 1) {
						lineBytes[i] = "0" + lineBytes[i].toUpperCase();
					}
					else {
						lineBytes[i] = lineBytes[i].toUpperCase();
					}
				}
				boolean validKey = false;
				for (Map.Entry<String, String[]> entry : signatureMap.entrySet()) {
					validKey = true;
					for (int i = 0; i < entry.getValue().length; i++) {
						if (!entry.getValue()[i].equals(lineBytes[i])) {
							validKey = false;
							break;
						} 
					}
					if (validKey) {
						System.out.println("PROCESSED");
						resultList.add(entry.getKey());
						break;
					}
				}
				if (!validKey) {
					System.out.println("UNDEFINED");
				}	
			} catch (IOException e) {
				System.err.println("File not found!");
			}
		}
		input.close();

	}
	public static void main(String[] args) {

		getFullPath();
		setSignatureMap();
		for (String[] values : signatureMap.values()) {
			if (values.length > maxLength) {
				maxLength = values.length;
			}
		}
		setResultList();
		try (FileOutputStream fileOut = new FileOutputStream(resaultTxt)) {
			for(String string : resultList) {
				byte[] bytes = string.getBytes();
				fileOut.write(bytes, 0, bytes.length);
				byte[] nl = "\n".getBytes();
				fileOut.write(nl);
			}
		} catch (IOException e) {
			System.err.println("Permition denaid!");
		}
	}
}
