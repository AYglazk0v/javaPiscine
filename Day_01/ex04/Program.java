public class Program {
	public static void main(String arg[]) {
		try{
			User u1 = new User(7500, "Ivan");
			User u2 = new User(5500, "Petya");
			TransactionsService transService = new TransactionsService();
			transService.addUser(u1);
			transService.addUser(u2);
			transService.executeTransaction(u1.getID(), u2.getID(), 400);
			transService.executeTransaction(u2.getID(), u1.getID(), 150);
			transService.executeTransaction(u1.getID(), u2.getID(), 100);
			System.out.println(u1);
			System.out.println("_______________________________________________________");
			System.out.println(u2);
			System.out.println("_______________________________________________________");
			u1.getTransactionsList().printLst();
			System.out.println("_______________________________________________________");
			Transaction t1 = new Transaction(u1, u2, 75, Transaction.Category.DEBIT);
			u1.getTransactionsList().addTransaction(t1);
			u1.getTransactionsList().printLst();
			System.out.println("_______________________________________________________");
			Transaction[] err = transService.getInvalidTransaction();
			u1.getTransactionsList().printLst();
			System.out.println("_______________________________________________________");

			for (int i = 0; i < err.length; i++){
				System.out.println("ERROR_ARRAY: " + err[i]);
			}
		}
		catch (UserNotFoundException | IllegalTransactionException | TransactionNotFoundException e ) {
				System.out.println(e.getMessage());
			}
		}
}


