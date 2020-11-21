package ke.co.propscout.mobank.data.models;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {
    @Exclude
    public static final String COLLECTION_NAME = "accounts";
    @Exclude
    public static final String ACCOUNT_TYPE_ARG = "platform";

    @DocumentId
    private String id;
    private String accountNumber;
    private String customerId;
    private String type;

    @Exclude
    private List<Transaction> transactions;

    @Exclude
    private Customer customer;

    public Account() {
    }

    public Account(String accountNumber, String type, String customerId) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.customerId = customerId;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
