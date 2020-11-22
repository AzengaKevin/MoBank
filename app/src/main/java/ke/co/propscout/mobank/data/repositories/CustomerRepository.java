package ke.co.propscout.mobank.data.repositories;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

import ke.co.propscout.mobank.data.models.Customer;

public class CustomerRepository {

    private final CustomerCrudTaskLister listener;
    private final FirebaseFirestore firebaseFirestore;

    public CustomerRepository(CustomerCrudTaskLister listener) {
        this.listener = listener;

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void createCustomer(Customer customer) {
        firebaseFirestore
                .collection(Customer.COLLECTION_NAME)
                .add(customer)
                .addOnCompleteListener(addCustomerTask -> {
                    if (addCustomerTask.isSuccessful()) {
                        customer.setId(Objects.requireNonNull(addCustomerTask.getResult()).getId());
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
        firebaseFirestore
                .collection(Customer.COLLECTION_NAME)
                .whereEqualTo("ownerId", uid)
                .get()
                .addOnCompleteListener(readCustomersTask -> {
                    if (readCustomersTask.isSuccessful()) {

                        if (!Objects.requireNonNull(readCustomersTask.getResult()).isEmpty()) {
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
