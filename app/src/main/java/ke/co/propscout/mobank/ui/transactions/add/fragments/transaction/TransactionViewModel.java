package ke.co.propscout.mobank.ui.transactions.add.fragments.transaction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Transaction;
import ke.co.propscout.mobank.data.repositories.TransactionRepository;

public class TransactionViewModel extends ViewModel implements TransactionRepository.TransactionCrudTaskListener {

    private MutableLiveData<Transaction> _transaction = new MutableLiveData<>();
    private MutableLiveData<List<Transaction>> _transactions = new MutableLiveData<>();
    private MutableLiveData<Exception> _exception = new MutableLiveData<>();

    private TransactionRepository repository;
    private Account account;

    public TransactionViewModel(Account account) {
        this.account = account;

        repository = new TransactionRepository(this);
    }

    public void createTransaction(Transaction transaction) {
        transaction.setAccount(account);
        repository.createTransaction(transaction);
    }

    public LiveData<Transaction> getTransaction() {
        return _transaction;
    }

    public LiveData<List<Transaction>> getTransactions() {
        return _transactions;
    }

    public LiveData<Exception> getException() {
        return _exception;
    }

    @Override
    public void onTransactionRetrieved(Transaction transaction) {
        _transaction.postValue(transaction);
    }

    @Override
    public void onTransactionsRetrieved(List<Transaction> transactions) {
        _transactions.postValue(transactions);
    }

    @Override
    public void onException(Exception exception) {
        _exception.postValue(exception);
    }
}
