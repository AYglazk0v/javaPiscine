public class Program{
		public static void main(String arg[]) {
		{
			User u0 = new User(1, 500, "Ivan");
			User u1 = new User(2, 1500, "Dmittriy");
			System.out.println(u0);
			System.out.println(u1);
			Transaction t1 = new Transaction(u0, u1, 250, Transaction.Category.DEBIT);
			System.out.println(t1);
		}
	}
}
