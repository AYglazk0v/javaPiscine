public class Program {
	private static int count;

	static void egg() {
		for (int i = 0; i < count; i++) {
			System.out.println("Egg");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.err.println("Egg interrupted");
			}
		}
	}

	static void hen() {
		for (int i = 0; i < count; i++ ) {
			System.out.println("Hen");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.err.println("Hen interrupted");
			}
		}
	}

	public static void main(String[] args) {
		if(args.length == 1){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--count")) {
				count = Integer.parseInt(tmp[1]);
				Thread taskEgg = new Thread(() -> egg());
				Thread taskHen = new Thread(()->hen());
				taskEgg.start();
				taskHen.start();
				try {
					taskEgg.join();
					taskHen.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < count; i++) {
					System.out.println("Human");
				}
			} else {
				System.err.println("Invalid argument");
			}
		} else {
			System.err.println("Invalid argument");
		}
	}
}