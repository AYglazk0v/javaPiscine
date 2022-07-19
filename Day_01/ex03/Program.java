public class Program {
	public static void main(String arg[]) {

		User u1 = new User(500, "Ivan");
		User u2 = new User(500, "Petya");

		Transaction t1 = new Transaction(u1, u2, 250, Transaction.Category.DEBIT);
		Transaction t2 = new Transaction(u2, u1, 250, Transaction.Category.CREDITS);
		Transaction t3 = new Transaction(u1, u2, 250, Transaction.Category.DEBIT);
		Transaction t4 = new Transaction(u2, u1, 250, Transaction.Category.CREDITS);
		Transaction t5 = new Transaction(u1, u2, 250, Transaction.Category.DEBIT);
		Transaction t6 = new Transaction(u2, u1, 250, Transaction.Category.CREDITS);
		Transaction t7 = new Transaction(u1, u2, 250, Transaction.Category.DEBIT);
		Transaction t8 = new Transaction(u2, u1, 250, Transaction.Category.CREDITS);
	
		TransactionsLinkedList lstTrans = new TransactionsLinkedList();

		lstTrans.addTransaction(t2);
		lstTrans.addTransaction(t3);
		lstTrans.addTransaction(t4);
		lstTrans.addTransaction(t5);
		lstTrans.addTransaction(t7);
		lstTrans.addTransaction(t1);
		lstTrans.addTransaction(t6);
		lstTrans.addTransaction(t8);

		try {
			lstTrans.removeTransactionById(t1.getIdentifier());
			lstTrans.removeTransactionById(t2.getIdentifier());
			lstTrans.removeTransactionById(t3.getIdentifier());
			lstTrans.removeTransactionById(t4.getIdentifier());
			lstTrans.removeTransactionById(t5.getIdentifier());
			lstTrans.removeTransactionById(t6.getIdentifier());
			lstTrans.removeTransactionById(t7.getIdentifier());
			lstTrans.removeTransactionById(t8.getIdentifier());
			lstTrans.printLst();
		} catch (TransactionNotFoundException err) {
			System.out.println(err.getMessage());
		}
	}
}


