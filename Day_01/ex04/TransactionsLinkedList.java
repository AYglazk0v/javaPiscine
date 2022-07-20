import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
	private Transaction start;
	private Transaction end;
	private Integer size = 0;

	@Override
	public void addTransaction(Transaction newTransaction) {
		if (this.start == null) {
			this.start = newTransaction;
		}
		else {
			this.end.setNext(newTransaction);
		}
		this.end = newTransaction;
		size++;
	}

	@Override
	public void removeTransactionById(UUID id) throws TransactionNotFoundException {
		Transaction tmp;
		Transaction prev;

		if (this.start != null) {
			tmp = this.start.getNext();
			prev = this.start;

			if (prev.getIdentifier() == id) {
				this.start = tmp;
				size--;
				return ;
			}

			while (tmp != null) {
				if (tmp.getIdentifier() == id) {
					prev.setNext(tmp.getNext());
					size--;
					return ;
				}
				prev = prev.getNext();
				tmp = prev.getNext();
			}
		}
		throw new TransactionNotFoundException("Transaction " + id + " not found");
	}

	@Override
	public Transaction[] toArray() {
		Transaction[] transArr = new Transaction[size];
		Transaction tmp = this.start;
		for (int i = 0; i < size; i++) {
			transArr[i] = tmp;
			tmp = tmp.getNext();   
		}
		return transArr;
	}

	public int getSizeLst()
	{
		int i = 0;
		Transaction tmp;
		tmp = this.start;
		while(tmp != null){
			i++;
			tmp = tmp.getNext();
		}	
		return (i);
	}

	public void printLst(){
		Transaction tmp;
		tmp = this.start;
		while(tmp != null){
			System.out.println(tmp);
			tmp = tmp.getNext();
		}
	}
}
