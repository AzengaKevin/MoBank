package ke.co.propscout.mobank.ui.transactions.add.fragments.account;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.Platform;

public class AccountViewModelFactory implements ViewModelProvider.Factory {

    private final Platform platform;
    private final Customer customer;

    public AccountViewModelFactory(Platform platform, Customer customer) {

        this.platform = platform;
        this.customer = customer;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        try {
            return modelClass.getConstructor(Platform.class, Customer.class).newInstance(platform, customer);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
