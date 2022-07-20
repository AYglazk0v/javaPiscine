public class Program {
	
	static final int MAX_INTERVAL = 1000000;
	static final int MIN_INTERVAL = 99999;

	public static void main(String[] args) {
		int number = 479598;
		int res = 0;

		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		res += number % 10;
		number /= 10;
		System.out.println(res);
	}
}
