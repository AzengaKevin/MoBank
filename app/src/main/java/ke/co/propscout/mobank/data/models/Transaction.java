package ke.co.propscout.mobank.data.models;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

public class Transaction {
    @Exclude
    public static final String COLLECTION_NAME = "transactions";

    @DocumentId
    private String id;

    private String type;
    private double amount;
    private String transactionId;
    private String description;

    private Account account;

    public Transaction() {
    }

    public Transaction(String type, double amount, String transactionId, String description) {
        this.type = type;
        this.amount = amount;
        this.transactionId = transactionId;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Exclude
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
