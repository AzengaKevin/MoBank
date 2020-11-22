package ke.co.propscout.mobank.data.repositories;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

import ke.co.propscout.mobank.data.models.Transaction;

public class TransactionRepository {

    private final TransactionCrudTaskListener listener;
    private final FirebaseFirestore firebaseFirestore;

    public TransactionRepository(TransactionCrudTaskListener listener) {
        this.listener = listener;

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void createTransaction(Transaction transaction) {
        firebaseFirestore
                .collection(Transaction.COLLECTION_NAME)
                .add(transaction)
                .addOnCompleteListener(addAccountTask -> {
                    if (addAccountTask.isSuccessful()) {
                        transaction.setId(Objects.requireNonNull(addAccountTask.getResult()).getId());
                        if (listener != null) {
                            listener.onTransactionRetrieved(transaction);
                        }
                    } else {
                        if (listener != null) {
                            listener.onException(addAccountTask.getException());
                        }
                    }
                });

    }

    public interface TransactionCrudTaskListener {

        void onTransactionRetrieved(Transaction transaction);

        void onTransactionsRetrieved(List<Transaction> transactions);

        void onException(Exception exception);
    }
}
