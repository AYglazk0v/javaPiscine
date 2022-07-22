public class Program {
	
	private static int count;

	public static class Printer {
		public static boolean isPrinted = true;
		public synchronized void print(String message) {
			if (!isPrinted) {
				try {
					isPrinted = true;
					wait();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			}
			notify();
			isPrinted = false;
			System.out.println(message);
		}
	}


	static void egg(String message, Printer printer) {
		for (int i = 0; i < count; i++) {
			printer.print(message);
		}
	}

	static void hen(String message, Printer printer) {
		for (int i = 0; i < count; i++) {
			printer.print(message);
		}
	}
	public static void main(String[] args) {
		if(args.length == 1){
			Printer printer = new Printer();
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--count")) {
				count = Integer.parseInt(tmp[1]);
				Thread taskEgg = new Thread(()->egg("Egg", printer));
				Thread taskHen = new Thread(()->hen("Hen", printer));
				taskEgg.start();
				try {
					Thread.sleep(0,5);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				taskHen.start();
				try {
					taskEgg.join();
					Thread.sleep(0,5);
					taskHen.join();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
	}
}