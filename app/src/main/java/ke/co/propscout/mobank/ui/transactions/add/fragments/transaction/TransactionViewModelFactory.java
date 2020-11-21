package ke.co.propscout.mobank.ui.transactions.add.fragments.transaction;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

import ke.co.propscout.mobank.data.models.Account;

public class TransactionViewModelFactory implements ViewModelProvider.Factory {

    private Account account;

    public TransactionViewModelFactory(Account account) {
        this.account = account;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(Account.class).newInstance(account);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
