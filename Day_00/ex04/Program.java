import java.util.Scanner;

public class Program{
	static final int UNCICOD_MAX = 65536;
	static final int TOP = 10;

	public static int countUnique(int[] arrayUnicode) {
		int	uniq = 0;
		for (int i = 0; i < UNCICOD_MAX; i++) {
			if (arrayUnicode[i] != 0) {
				uniq++;
			}
		}
		return (uniq);
	}

	public static void printing(int[][] topTen, int uniq) {
		int countRows = TOP;

		if (uniq < countRows)
			countRows = uniq;

		char[][] arrayPrint = new char[TOP + 1][countRows];
		double curr = 0;

		for(int j = 0; j < countRows; j++){
			int i;
			curr = 1.0 * topTen[j][1] / topTen[0][1] * 10;
			for(i = TOP; i > 0 && curr >= TOP - i + 1; i--){
					arrayPrint[i][j] = '#';
			}
			arrayPrint[i][j] = 'M';
		}
		for(int i = 0; i < TOP + 1; i++){
			for(int j = 0; j < countRows; j++){	
				if (arrayPrint[i][j] == 'M'){
					System.out.printf("%3d",topTen[j][1]);
				}
				else{
				System.out.printf("%3c", arrayPrint[i][j]);
				}
			}
			System.out.printf("\n");
		}
		for(int i = 0; i < countRows; i++){
				System.out.printf("%3c", topTen[i][0]);
		}
	}

	public static void main(String arg[]) {
		int		lenStr;
		int		uniq;
		String	inStr;
		int[] arrayUnicode = new int[UNCICOD_MAX];
		Scanner obj = new Scanner(System.in);

		if (!obj.hasNextLine()){
			obj.close();
			System.exit(-1);
		}
		inStr = obj.nextLine();
		obj.close();
		lenStr = inStr.length();
		if (lenStr == 0)
			System.exit(0);
		char[] arrayStr = inStr.toCharArray();
		for(int i = 0; i < lenStr; i++) {
			arrayUnicode[arrayStr[i]]++;
		}
		uniq = countUnique(arrayUnicode);
		int[][] arrayCharAndCount = new int[uniq][2];
		for (int i = 0, j = 0; i < UNCICOD_MAX && j < uniq; i++) {
			if (arrayUnicode[i] != 0) {
				arrayCharAndCount[j][0] = i;
				arrayCharAndCount[j][1] = arrayUnicode[i];
				j++;
			}
		}	
		int[][] topTen = new int[TOP][2];
		for (int j = 0, pos_max, max; j < TOP; j++){
			pos_max = -1;
			max = -1;
			for (int i = 0; i < uniq; i++) {
				if (arrayCharAndCount[i][1] > max){
					pos_max = i;
					max = arrayCharAndCount[i][1];
				}
			}
			topTen[j][0] = arrayCharAndCount[pos_max][0];
			topTen[j][1] = arrayCharAndCount[pos_max][1];
			arrayCharAndCount[pos_max][1] = 0;
		}
		printing(topTen, uniq);
	}
}
