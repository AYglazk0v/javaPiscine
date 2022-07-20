import java.util.Scanner;

public class Program{

	public static int searchMinInFiveDigits(Scanner obj) {
		int	subject;
		int	min = 10;
		int	i = 0;
	
		while (i++ < 5) {
			subject = obj.nextInt();
			if (subject < 1 || subject > 9) {
				System.err.println("[ERROR] BAD ARG!");
				System.exit(-1);
			}
			if (subject < min) {
				min = subject;
			}
		}
		return (min);
	}

	public static void printResault(long storage, int week)
	{
		long	countSymb;

		if (storage > 10)
			printResault(storage / 10, week - 1);
		System.out.print("Week " + week + " ");
		countSymb = storage % 10;
		for (int i = 0; i < countSymb; i++) {
			System.out.print("=");
		}
		System.out.println(">");
	}

	public static void main(String arg[]) {
		int		prevWeek = 0;
		int		currWeek = 0;
		long	storage = 0;
		String	subjectStr;
		Scanner obj = new Scanner(System.in);

		for(int i = 0; i < 18; i++) {
			if (!obj.hasNextLine()){
				obj.close();
				System.exit(0);
			}
			subjectStr = obj.next();
			if (subjectStr.equals("42")) {
				break ;
			}
			if (!subjectStr.equals("Week")) {
				System.err.println("[ERROR] BAD ARG!");
				System.exit(-1);
			}
			if (!obj.hasNextLine()) {
				obj.close();
				System.exit(0);
			}
			currWeek = obj.nextInt();
			if (currWeek < prevWeek) {
				System.err.println("[ERROR] BAD ARG!");
				System.exit(-1);
			}
			prevWeek = currWeek;
			storage = storage * 10 + searchMinInFiveDigits(obj);
		}
		if (currWeek > 0) {
			printResault(storage, currWeek);
		}
		obj.close();
	}
}

