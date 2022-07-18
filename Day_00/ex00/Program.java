public class Program {
	
	static final int MAX_INTERVAL = 1000000;
	static final int MIN_INTERVAL = 99999;

	public static void main(String[] args) {
		int number = 4905908;
		int res = 0;

		if (x > MIN_INTERVAL && x < MAX_INTERVAL) {
			while (x > 0) {
				res += x % 10;
				x /= 10;
			}
			System.out.println(res);
		}
		else {
			System.out.println("[Error] Bad arg!");
		}
	}
}
