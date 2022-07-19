public class User {
	private Integer id;
	private Integer balance;
	private String name;
	private TransactionsList transactionsList;

	public User(Integer balance, String name){
		this.id = UserIdsGenerator.getInstance().generateId();
		if (balance > 0) {
			this.balance = balance;
		}
		else {
			this.balance = 0;
		}
		this.name = name;
		this.id = UserIdsGenerator.getInstance().generateId();
        this.transactionsList = new TransactionsLinkedList();
	}

	public User() {
		this.id = UserIdsGenerator.getInstance().generateId();
        this.balance = 0;
        this.transactionsList = new TransactionsLinkedList();
	}

	public void setBlance(Integer balance) {
		if (balance > 0) {
			this.balance = balance;
		}
		else {
			this.balance = 0;
		}
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return (balance);
    }

	public Integer getID() {
		return (id);
	}

    public String getName() {
        return (name);
	}

	@Override
	public String toString() {
		String str = "Name: " + this.name + " id: " + this.id + " balance: " + this.balance;
		return (str);
	}
}
