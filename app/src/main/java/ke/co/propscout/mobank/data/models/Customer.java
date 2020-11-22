package ke.co.propscout.mobank.data.models;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Customer extends CustomerWithOwner implements Serializable {

    @Exclude
    public static final String COLLECTION_NAME = "customers";

    @DocumentId
    private String id;

    private String name;
    private String phone;
    private String idNumber;
    private String ownerId;

    public Customer() {
    }

    public Customer(String name, String phone, String idNumber, String ownerId) {
        this.name = name;
        this.phone = phone;
        this.idNumber = idNumber;
        this.ownerId = ownerId;
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

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
