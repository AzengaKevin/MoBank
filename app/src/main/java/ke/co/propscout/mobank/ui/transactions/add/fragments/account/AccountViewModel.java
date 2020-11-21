package ke.co.propscout.mobank.ui.transactions.add.fragments.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.data.repositories.AccountRepository;

public class AccountViewModel extends ViewModel implements AccountRepository.AccountCrudTaskListener {

    private Customer customer;
    private AccountRepository accountRepository;

    public AccountViewModel(Platform platform, Customer customer) {
        this.customer = customer;
        accountRepository = new AccountRepository(this);

        accountRepository.readAccounts(platform, customer);
    }

    private MutableLiveData<List<Account>> _accounts = new MutableLiveData<>();
    private MutableLiveData<Account> _account = new MutableLiveData<>();
    private MutableLiveData<Exception> _exception = new MutableLiveData<>();

    public void createAccount(Account account) {
        accountRepository.createAccount(account);
    }

    public LiveData<Account> getAccount() {
        return _account;
    }

    public LiveData<List<Account>> getAccounts() {
        return _accounts;
    }

    public LiveData<Exception> getException() {
        return _exception;
    }

    @Override
    public void onAccountRetrieved(Account account) {
        _account.postValue(account);
    }

    @Override
    public void onAccountsRetrieved(List<Account> accounts) {
        _accounts.postValue(accounts);
    }

    @Override
    public void onException(Exception exception) {
        _exception.postValue(exception);
    }
}
