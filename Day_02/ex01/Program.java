import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {

	private static final String OUTPUT_FILE = "dictionary.txt";

	private static Set<String> dictionary;
	private static String[] dictArray;
	private static ArrayList<String> fileA;
	private static ArrayList<String> fileB;
	private static ArrayList<Integer> vectorA;
	private static ArrayList<Integer> vectorB;

	private static void readArgToDictionary(String filePath, ArrayList<String> fileArray){
		try (FileReader input = new FileReader(filePath)) {
			Scanner scan = new Scanner(input);
			while (scan.hasNext()) {
				String tmp = scan.next();
				dictionary.add(tmp);
				fileArray.add(tmp);
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("Couldn't read a file!" + filePath);
		}
	} 

	private static boolean validationArgs(String[] args) {
		int countValid = 0;
		for (int i = 0; i < 2; i++){
			File file = new File(args[i]);
			if (file.exists()) {
				System.out.print("Size file " + (i + 1)  + " = ");
				System.out.printf("%f Mb\n", (1.0 * file.length()/(1024 * 1024)));
				if ((1.0 * file.length()/(1024 * 1024)) < 10.0){
					countValid++;
				}
			}
			else {
				System.err.println("Couldn't read a file!");	
				System.exit(-1);
			}
		}
		if (countValid == 2) {
			return (true);
		}
		return (false);
	}

	public static void fillVector(ArrayList<Integer> vector, ArrayList<String> fileEntrance) {
		for (int i = 0; i < dictArray.length; i++) {
			Integer count = 0;
			for (String string : fileEntrance) {
				if (dictArray[i].equals(string)) {
					count++;
				}
			}
			vector.add(count);
		}
	}

	public static Double analyzeSimilarity() {
		Double modA, modB, multi, tmpSumm;

		tmpSumm = 0.0;
		for (Integer val : vectorA) {
			tmpSumm += val * val;
		}
		modA = Math.sqrt(tmpSumm);
		tmpSumm = 0.0;
		for (Integer val : vectorB) {
			tmpSumm += val * val;
		}
		modB = Math.sqrt(tmpSumm);
		multi = 0.0;
		for (int i = 0; i < vectorA.size(); i++) {
			multi += vectorA.get(i) * vectorB.get(i);
		}
		if (modA == 0.0 || modB == 0.0) {
			return (0.0);
		}
		return (multi / (modA * modB));
	}

	public static void writeDictFile() {
		try (FileWriter outfile = new FileWriter(OUTPUT_FILE)) {
			for (int i = 0; i < dictArray.length; i++) {
				outfile.write(dictArray[i]);
				outfile.write(" ");
			}
		} catch (IOException e) {
			System.err.println("Permition denaid!");
		}
	}

	public static void main(String[] args) {
		if (args.length == 2 && validationArgs(args)) {

			dictionary = new HashSet<>();
			fileA = new ArrayList<>();
			fileB = new ArrayList<>();
			vectorA = new ArrayList<>();
			vectorB = new ArrayList<>();

			readArgToDictionary(args[0], fileA);
			readArgToDictionary(args[1], fileB);
			dictArray = dictionary.toString().split(", ");
			if (dictArray.length > 1) {
				dictArray[0] = dictArray[0].substring(1, dictArray[0].length());
				dictArray[dictArray.length - 1] = dictArray[dictArray.length - 1].substring(0, dictArray[dictArray.length - 1].length() - 1);
			}
			else if (dictArray.length == 1){
				dictArray[0] = dictArray[0].substring(1, dictArray[0].length() - 1);
			}
			Arrays.sort(dictArray, Comparator.naturalOrder());
			fillVector(vectorA, fileA);
			fillVector(vectorB, fileB);
			System.out.printf("Similarity = %.2f\n", analyzeSimilarity());
			writeDictFile();
		} else {
			System.err.println("Illegal Argument!");
			System.exit(-1);
		}
	}
}
