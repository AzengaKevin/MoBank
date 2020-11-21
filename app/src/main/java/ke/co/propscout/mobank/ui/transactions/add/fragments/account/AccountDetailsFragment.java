package ke.co.propscout.mobank.ui.transactions.add.fragments.account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.databinding.FragmentAccountDetailsBinding;

public class AccountDetailsFragment extends Fragment {
    private static final String TAG = "AccountDetailsFragment";

    private FragmentAccountDetailsBinding binding;
    private AccountViewModel accountViewModel;
    private NavController navController;

    private Platform platform;
    private Customer customer;
    private TextInputEditText accountNumberField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get customer
        customer = AccountDetailsFragmentArgs.fromBundle(requireArguments()).getCustomer();
        //Get platform
        platform = AccountDetailsFragmentArgs.fromBundle(requireArguments()).getPlatform();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountNumberField = view.findViewById(R.id.account_number_field);

        navController = Navigation.findNavController(view);

        binding.nextButton.setOnClickListener(v -> {
            //Get account number
            String accountNumber = String.valueOf(accountNumberField.getText());

            Account account = new Account(accountNumber, platform.toString(), customer.getId());

            account.setCustomer(customer);

            accountViewModel.createAccount(account);

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AccountViewModelFactory factory = new AccountViewModelFactory(platform, customer);

        accountViewModel = new ViewModelProvider(this, factory).get(AccountViewModel.class);

        accountViewModel.getAccount().observe(getViewLifecycleOwner(), account -> {
            //Send to transactions
            AccountDetailsFragmentDirections.ActionAddTransactionDetails action =
                    AccountDetailsFragmentDirections.actionAddTransactionDetails(account);

            navController.navigate(action);
        });

        accountViewModel.getException().observe(getViewLifecycleOwner(), exception -> {
            Toast.makeText(getContext(), getString(R.string.warning_text), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onActivityCreated: ", exception);
        });
    }
}