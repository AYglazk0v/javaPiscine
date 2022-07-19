import java.util.UUID;

public class Transaction {
	private final UUID id;
	private User recipient;
	private User sender;
	private Integer transferAmount;
	private Category transferCategory;
	private Transaction next;

	enum Category {
		DEBIT, CREDITS
	}

	public Transaction(User recipient, User sender, Integer transferAmount, Category transferCategory) {
		this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
		this.setTransferAmount(transferAmount);
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setNext(Transaction next){
		this.next = next;
	}

	public void setTransferAmount(Integer transferAmount) {
		if (this.transferCategory == Category.CREDITS && transferAmount > 0) {
			this.transferAmount = 0;
		}
		if (this.transferCategory == Category.DEBIT && transferAmount < 0) {
			this.transferAmount = 0;
		}
		else {
			this.transferAmount = transferAmount;
		}
	}
	public void setTransferCategory(Category transferCategory) {
        this.transferCategory = transferCategory;
    }

	public User getRecipient() {
		return(recipient);
	}
	
	public User getSender() {
		return(sender);
	}

	public Integer getTransferAmount() {
		return (transferAmount);
	}

	public Category getTransferCategory() {
        return (transferCategory);
    }
	
	public UUID getIdentifier() {
        return id;
	}

	public Transaction getNext() {
        return next;
    }

	@Override
	public String toString() {
		String str = "Sender: " + this.sender.getName() + " Recipient: " + this.recipient.getName() +
		 " Category: " + this.transferCategory + " Amount: " + this.transferAmount + " UUID: " + this.id;
		 return str;
    }
}
