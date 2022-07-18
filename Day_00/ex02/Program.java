import java.util.Scanner;

public class Program{
	
	public static int summDigit(int digit) {
		int ret = 0;
	
		while (digit > 0) {
			ret += digit % 10;
			digit /= 10;
		}
		return (ret);
		}

	public static boolean isPrime(int summ) {
			int prb = 2;

			while(prb * prb <= summ) {
				if (summ % prb == 0) {
					return (false);
				}
				prb++;
			}
			return (true);
		}
	
	public static void main(String arg[]) {
		int subject;
		int countQueries = 0;
		Scanner obj = new Scanner(System.in);

		do {
			if (!obj.hasNextLine()) {
				obj.close();
				System.exit(0);
			}
			subject = obj.nextInt();
			if (subject < 0){
				System.out.println("IllegalArgument");
				obj.close();
				System.exit(-1);
			}
			if (isPrime(summDigit(subject)))
				countQueries++;
		} while (subject != 42);
		System.out.println("Count of coffee - request - " + countQueries);
		obj.close();
	}
}
