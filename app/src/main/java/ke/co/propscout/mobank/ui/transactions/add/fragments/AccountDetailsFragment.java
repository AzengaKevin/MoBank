package ke.co.propscout.mobank.ui.transactions.add.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.data.models.User;
import ke.co.propscout.mobank.databinding.FragmentAccountDetailsBinding;

public class AccountDetailsFragment extends Fragment {
    private static final String TAG = "AccountDetailsFragment";

    private FragmentAccountDetailsBinding binding;
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

            //Navigate to get the transactions
            navController.navigate(R.id.action_add_transaction_details);
        });
    }
}