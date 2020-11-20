package ke.co.propscout.mobank.data.models;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Account {

    @DocumentId
    private String id;
    private String accountNumber;
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(String accountNumber, List<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
