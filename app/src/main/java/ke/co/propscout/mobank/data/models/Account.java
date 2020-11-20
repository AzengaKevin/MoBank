package ke.co.propscout.mobank.data.models;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Account {
    public static final String COLLECTION_NAME = "accounts";

    @DocumentId
    private String id;
    private String accountNumber;
    private String type;
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(String accountNumber, String type) {
        this.accountNumber = accountNumber;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
