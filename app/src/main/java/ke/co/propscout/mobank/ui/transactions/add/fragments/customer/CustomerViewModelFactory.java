package ke.co.propscout.mobank.ui.transactions.add.fragments.customer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

public class CustomerViewModelFactory implements ViewModelProvider.Factory {

    private String uid;

    public CustomerViewModelFactory(String uid) {
        this.uid = uid;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(String.class).newInstance(uid);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
