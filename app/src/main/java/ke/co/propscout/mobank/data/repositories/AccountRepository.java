package ke.co.propscout.mobank.data.repositories;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.data.models.User;

public class AccountRepository {

    private FirebaseFirestore firebaseFirestore;
    private AccountCrudTaskListener listener;

    public AccountRepository(AccountCrudTaskListener listener) {
        this.listener = listener;

        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    public void createAccount(Account account) {
        firebaseFirestore.collection(User.COLLECTION_NAME)
                .document(account.getCustomer().getOwnerId())
                .collection(Customer.COLLECTION_NAME)
                .document(account.getCustomer().getId())
                .collection(Account.COLLECTION_NAME)
                .add(account)
                .addOnCompleteListener(addAccountTask -> {
                    if (addAccountTask.isSuccessful()) {
                        account.setId(Objects.requireNonNull(addAccountTask.getResult()).getId());
                        if (listener != null) {
                            listener.onAccountRetrieved(account);
                        }
                    } else {

                        if (listener != null) {
                            listener.onException(addAccountTask.getException());
                        }
                    }
                });
    }

    public void readAccounts(Platform platform, Customer customer) {
        firebaseFirestore.collection(User.COLLECTION_NAME)
                .document(customer.getOwnerId()).collection(Customer.COLLECTION_NAME)
                .document(customer.getId()).collection(Account.COLLECTION_NAME)
                .whereEqualTo("type", platform.toString())
                .get()
                .addOnCompleteListener(readCustomersTask -> {
                    if (readCustomersTask.isSuccessful()) {

                        if (!Objects.requireNonNull(readCustomersTask.getResult()).isEmpty()) {
                            if (listener != null) {
                                listener.onAccountsRetrieved(readCustomersTask.getResult().toObjects(Account.class));
                            }
                        }

                    } else {
                        if (listener != null) {
                            listener.onException(readCustomersTask.getException());
                        }
                    }
                });
    }

    public interface AccountCrudTaskListener {

        void onAccountRetrieved(Account account);

        void onAccountsRetrieved(List<Account> accounts);

        void onException(Exception exception);
    }
}
