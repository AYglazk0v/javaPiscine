import java.util.Scanner;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import javax.lang.model.util.ElementScanner14;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;
public class Program {
	private static final int MAX_STUDENTS = 10;
	private static final int MAX_CLASSES_WEEK = 10;
	private static final String[][] CALENDAR = {{"SU", "MO", "TU", "WE", "TH", "FR", "SA"},
												{"", "", "1", "2", "3", "4", "5"},
												{"6", "7", "8", "9", "10", "11", "12"},
												{"13", "14", "15", "16", "17", "18", "19"},
												{"20", "21", "22", "23", "24", "25", "26"},
												{"27", "28", "29", "30", "", "", ""}};

	public static void checkNextLine(Scanner scanner){
		if (!scanner.hasNextLine()) {
			System.err.println("[Err] BAD ARRG");
			scanner.close();
			System.exit(-1);
		}
	}
	public static void errorExit(String msg) {
		System.err.println(msg);
		System.exit(-1);	
	}

	public static String[] readInput(Scanner scanner, int lenght) {
		int i;
		String[] excessArr = new String[lenght];
		checkNextLine(scanner);
		String line = scanner.nextLine();
		
		for (i = 0; i < 10 && !line.equals("."); i++) {
			excessArr[i] = line;
			checkNextLine(scanner);
			line = scanner.nextLine();
		}
		if (i == 10 && !line.equals(".")){
			errorExit("[Error] Bad arguments");
		}
		String[] retArr = new String[i];
		for(int j = 0; j < i; j++){
			retArr[j] = excessArr[j];
		}
		return (retArr);
	}
	private static String[] makeMonthTable(String[] timeClasses) {
		sortByNameDay(timeClasses);
		sortByTime(timeClasses);
		String headLine = "";
		for (int i = 0; i < timeClasses.length; i++) {
			String[] splittedElement = splitString(timeClasses[i], ' ');
			int j = 0;
			while (j < 7) {
				if (splittedElement[1].equals(CALENDAR[0][j])) {
					break;
				}
				j++;
			}
			if (j == 7){
				errorExit("[Error] Bad arguments");
			}
			for (int k = 1; k < 6; ++k) {
				if (!CALENDAR[k][j].equals("")) {
					headLine += splittedElement[0] + ":00 " + splittedElement[1] + " " + CALENDAR[k][j] + "|";
				}
			}
		}
		String[] result = splitString(headLine, '|');
		sortByDay(result);
		return (result);
	}

	private static int getNumberDay(String day) {
		for (int i = 0; i < 7; i++) {
			if (CALENDAR[0][i].equals(day)) {
				return (i);
			}
		}
		errorExit("[Error] Bad arguments");
		return (-1);
	}

	private static void sortByNameDay(String[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			for (int j = 0; j < (array.length - i - 1); ++j) {
				if (getNumberDay((splitString(array[j], ' '))[1])
						< getNumberDay((splitString(array[j + 1], ' '))[1])) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	private static void sortByDay(String[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			for (int j = 0; j < (array.length - i - 1); ++j) {
				int day1 = converStringDateToInt((splitString(array[j], ' '))[2]);
				int day2 = converStringDateToInt((splitString(array[j + 1], ' '))[2]);
				if (day1 > day2) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	private static int converStringDateToInt(String str) {
		char[] array = str.toCharArray();
		int day = 0;
		for (int i = 0; i < array.length; i++){
			day = day * 10 + (array[i] - '0');
		}
		return (day);
	}

	private static void sortByTime(String[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			for (int j = 0; j < (array.length - i - 1); ++j) {
				char[] arr1 = (splitString(array[j], ' '))[0].toCharArray();
				char[] arr2 = (splitString(array[j + 1], ' '))[0].toCharArray();
				int time1 = arr1[0] - '0';
				int time2 = arr2[0] - '0';
				if (time1 > time2) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	private static String[] splitString(String str, char delimiter) {
		int length = str.length();
		char[] array = str.toCharArray();
		int spaceCounter = 0;
		for (int i = 0; i < length; i++) {
			if (array[i] == delimiter) {
				++spaceCounter;
			}
		}
		int newLength = spaceCounter + 1;
		if (array[length - 1] == delimiter) {
			newLength = spaceCounter;
		}
		String[] result = new String[newLength];
		for (int k = 0; k < result.length; k++) {
			result[k] = "";
		}
		int j = 0;

		for (int i = 0; i < newLength; i++) {
			if (j < length &&  array[j] == delimiter) {
				j++;
			}
			while(j < length){
				if (array[j] == delimiter) {
					break;
				}
				result[i] += array[j];
				j++;
			}
		}
		return (result);
	}

	private static void fillInAttendance(String line, String[][] retTable) {
		String[] splittedLine = splitString(line, ' ');
		String[] splitted = null;
		int i = 1;
		while (i < retTable.length) {
			if (retTable[i][0].equals(splittedLine[0])) {
				break;
			}
			i++;
		}
		if (i == retTable.length) {
			errorExit("[ERROR] BAD ARG");
		}
		int j = 1;
		while (j < retTable[0].length) {
			splitted = splitString(retTable[0][j], ' ');
			if (splitted[0].equals(splittedLine[1] + ":00") && splitted[2].equals(splittedLine[2])) {
				break;
			}
			j++;
		}
		if (j == retTable[0].length) {
			errorExit("[ERROR] BAD ARG");
		}
		if (splittedLine[3].equals("HERE")){
			retTable[i][j] = "1";
		}
		else if (splittedLine[3].equals("NOT_HERE")) {
			retTable[i][j] = "-1";
		}
		else if (!splitted[0].equals(".")) {
			errorExit("[Error] Bad ARG");
		}
	}

	private static String[][] createTable(String[] students,  String[] timesClasses, Scanner scanner) {
		String[] monthTable = makeMonthTable(timesClasses);
		String[][] retVal = new String[students.length + 1][monthTable.length + 1];
		retVal[0][0] = "";
		for (int i = 0; i < monthTable.length; i++) {
			retVal[0][i + 1] = monthTable[i];
		}
		for (int i = 0; i < students.length; i++) {
			retVal[i + 1][0] = students[i];
		}
		checkNextLine(scanner);
		String line = scanner.nextLine();
		while (!line.equals(".")) {
			fillInAttendance(line, retVal);
			checkNextLine(scanner);
			line = scanner.nextLine();
		}
		return (retVal);
	}
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		String[] students = readInput(scanner, MAX_STUDENTS);
		String[] timesClasses = readInput(scanner, MAX_CLASSES_WEEK);
		String[][] table = createTable(students, timesClasses, scanner);
		for (int i = 0; i < table.length; ++i) {
			for (int j = 0; j < table[0].length; ++j) {
				if (i == 0 && j > 0) {
					String[] splitted = splitString(table[i][j], ' ');
					System.out.printf("%4s%3s%3s|", splitted[0], splitted[1], splitted[2]);
				} else if (i == 0 && j == 0) {
					System.out.printf("%10s", "");
				} else if (j == 0 && i > 0) {
					System.out.printf("%10s", table[i][j]);
				} else if (i > 0 && j > 0) {
					System.out.printf("%10s|", (table[i][j] != null) ? table[i][j] : "");
				}
			}
			System.out.println();
		}
		scanner.close();
	}
}	
