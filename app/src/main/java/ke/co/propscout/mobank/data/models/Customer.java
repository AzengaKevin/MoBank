package ke.co.propscout.mobank.data.models;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Customer {

    @DocumentId
    private String id;

    private String name;
    private String phone;
    private String idNumber;
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(String name, String phone, String idNumber) {
        this.name = name;
        this.phone = phone;
        this.idNumber = idNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
