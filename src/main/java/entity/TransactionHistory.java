package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing the transaction history of a user
 */
public class TransactionHistory {

    private final List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public TransactionHistory(List<Transaction> existingTransactions) {
        this.transactions = new ArrayList<>(existingTransactions);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * Get the most recent transactions by count
     *
     * @param count the number of transactions to get
     * @return the most recent transactions
     */
    public List<Transaction> getRecentTransactions(int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }

        int start = Math.max(transactions.size() - count, 0);
        return new ArrayList<>(transactions.subList(start, transactions.size()));
    }
}