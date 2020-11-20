package ke.co.propscout.mobank.data.repositories;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.User;

public class CustomerRepository {

    private CustomerCrudTaskLister listener;
    private FirebaseFirestore firebaseFirestore;

    public CustomerRepository(CustomerCrudTaskLister listener) {
        this.listener = listener;

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void createCustomer(Customer customer) {
        firebaseFirestore
                .collection(User.COLLECTION_NAME)
                .document(customer.getOwnerId())
                .collection(Customer.COLLECTION_NAME)
                .add(customer)
                .addOnCompleteListener(addCustomerTask -> {
                    if (addCustomerTask.isSuccessful()) {
                        customer.setId(addCustomerTask.getResult().getId());
                        if (listener != null) {
                            listener.onCustomerRetrieved(customer);
                        }
                    } else {

                        if (listener != null) {
                            listener.onException(addCustomerTask.getException());
                        }
                    }
                });
    }

    public void readCustomers(String uid) {
        firebaseFirestore.collection(User.COLLECTION_NAME).document(uid).collection(Customer.COLLECTION_NAME)
                .get()
                .addOnCompleteListener(readCustomersTask -> {
                    if (readCustomersTask.isSuccessful()) {

                        if (!readCustomersTask.getResult().isEmpty()) {
                            if (listener != null) {
                                listener.onCustomersRetrieved(readCustomersTask.getResult().toObjects(Customer.class));
                            }
                        }

                    } else {
                        if (listener != null) {
                            listener.onException(readCustomersTask.getException());
                        }
                    }
                });
    }

    public interface CustomerCrudTaskLister {

        void onCustomerRetrieved(Customer customer);

        void onCustomersRetrieved(List<Customer> customers);

        void onException(Exception exception);
    }
}
