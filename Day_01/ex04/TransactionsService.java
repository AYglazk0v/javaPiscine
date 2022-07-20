import java.util.UUID;

public class TransactionsService {

	TransactionsList transactionLst = new TransactionsLinkedList();
	UserList userList = new UsersArrayList();

	public void addUser(User newUser){
		this.userList.addUser(newUser);
	}

	public Integer getUserBalance(Integer id) throws TransactionNotFoundException{
		return (userList.getUserById(id).getBalance());
	}

	public Integer getUserBalance(User user) throws UserNotFoundException{
		return (user.getBalance());
	}

	public void executeTransaction(Integer senderId, Integer recipientId, Integer amount) throws IllegalTransactionException {
		try {
			User sender = userList.getUserById(senderId);
			User recipient = userList.getUserById(recipientId);

			if (senderId == recipientId || amount < 0 || sender.getBalance() < amount) {
				throw new IllegalTransactionException("Illegal Transaction");
			}
			Transaction credit = new Transaction(sender, recipient, -amount, Transaction.Category.CREDITS);
			Transaction debit = new Transaction(recipient, sender, amount, Transaction.Category.DEBIT);
	
			debit.setIdentifier(credit.getIdentifier());
			recipient.getTransactionsList().addTransaction(debit);
			sender.getTransactionsList().addTransaction(credit);
			recipient.setBlance(recipient.getBalance() + amount);
			sender.setBlance(sender.getBalance() - amount);
		} catch (UserNotFoundException | IllegalTransactionException e) {
			System.err.println(e.getMessage());
		}
	}

	public Transaction[] getTransactionsArray(Integer userId) {
		try {
			return (userList.getUserById(userId).getTransactionsList().toArray());
		} catch (UserNotFoundException e) {
			System.err.println(e);
		}
		return (null);
	}

	public void removeTransactionById(UUID uuid, Integer userId) {
		try {
			userList.getUserById(userId).getTransactionsList().removeTransactionById(uuid);
		} catch (UserNotFoundException | TransactionNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}

	public Transaction[] getInvalidTransaction() {
		int cntUser = userList.getUserCount();
		int cntInvalid = 0;
		Transaction[][] allUserTransactions = new Transaction[cntUser][];
		Transaction[] invalidTrans = new Transaction[cntInvalid];
		for (int i = 0; i < cntUser; i++){
			allUserTransactions[i] = userList.getUserByIndex(i).getTransactionsList().toArray();
		}
		for (int i = 0; i < cntUser; i++) {
			for (int j = 0; j < allUserTransactions[i].length; j++) {
				int cnt = 0;
				for(int k = 0; k < cntUser; k++) {
					for(int g = 0; g < allUserTransactions[k].length; g++){
						if (allUserTransactions[i][j].getIdentifier() == allUserTransactions[k][g].getIdentifier()) {
							cnt++;
						}
					}
				}
				if (cnt != 2){
					Transaction[] newInvalid = new Transaction[cntInvalid + 1];
					for(int p = 0; p < cntInvalid; p++){
						newInvalid[p] = invalidTrans[p];
					}
					newInvalid[cntInvalid] = allUserTransactions[i][j];
					cntInvalid++;
					invalidTrans = newInvalid;
				}
			}
		}
		return (invalidTrans);
	}
}
