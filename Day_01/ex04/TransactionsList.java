import java.util.UUID;

interface TransactionsList {

    void addTransaction(Transaction newTransaction);
    void removeTransactionById(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
}