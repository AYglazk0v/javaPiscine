import java.util.concurrent.ThreadLocalRandom;

public class Program {
	private static int[] array;
	private static int arraySize;
	private static int threadsCount;
	private static int result;
	private static int MAX_NUM = 1000;
	private static int MIN_NUM = -1000;
	private static SubThread[] threads;

	
	public static class SubThread extends Thread {
		private int start;
		private int end;
		private int id;
		
		public SubThread (int id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += array[i];
			}
			if (end < 0) {
				end = 0;
			}
			System.out.printf("Thread %d: from %d to %d sum is %d\n", this.id, this.start, this.end, sum);
			result += sum;
		}
	}

	public static void getArray() {
		array = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(MIN_NUM, MAX_NUM + 1);
		}
	}

	public static int getFactSumm() {
		int sum = 0;
		for (int i = 0; i < arraySize; i++) {
			sum += array[i];
		}
		return sum;
	}

	public static void subThreadsCreate() {
		threads = new SubThread[threadsCount];

		int	extrime;
		int	stanartDiff;
		int index_start = 0;
		
		if (arraySize % threadsCount == 0){
			stanartDiff = arraySize / threadsCount;
			extrime = stanartDiff;
		} else {
			int tmp = arraySize;
			while (tmp % (threadsCount) != 0) {
				tmp--;
			}
			stanartDiff = tmp / (threadsCount);
			extrime = arraySize - (stanartDiff * (threadsCount - 1)) ;
		}
		for (int j = 0; j < threadsCount; j++) {
			if (j != threadsCount - 1) {
				threads[j] = new SubThread(j + 1, index_start, index_start + stanartDiff - 1);
				index_start = index_start + stanartDiff;
			} else {
				threads[j] = new SubThread(j + 1, index_start, index_start + extrime - 1);
				index_start = index_start + extrime;
			}
		}
	}

	public static void main(String[] args) {
		if(args.length == 2){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--arraySize")) {
				arraySize = Integer.parseInt(tmp[1]);
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
			tmp = args[1].split("=");
			if (tmp[0].equals("--threadsCount")) {
				threadsCount = Integer.parseInt(tmp[1]);
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
			getArray();
			System.out.printf("Sum: %d\n", getFactSumm());
			subThreadsCreate();
			for (int i = 0; i < threadsCount; i++) {
				threads[i].start();
			}
			for (int i = 0; i < threadsCount; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			}
			System.out.printf("Sum by threads %d\n", result);
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
	}
}