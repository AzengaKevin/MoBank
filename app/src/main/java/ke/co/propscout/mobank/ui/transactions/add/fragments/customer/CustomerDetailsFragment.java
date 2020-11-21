package ke.co.propscout.mobank.ui.transactions.add.fragments.customer;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.databinding.FragmentCustomerDetailsBinding;

public class CustomerDetailsFragment extends Fragment {
    private static final String TAG = "CustomerDetailsFragment";

    private FragmentCustomerDetailsBinding binding;
    private CustomerViewModel viewModel;
    private NavController navController;
    private Platform platform;

    private TextInputEditText customerNameField;
    private TextInputEditText customerPhoneField;
    private TextInputEditText customerIdNumberField;

    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        platform = (Platform) requireArguments().getSerializable(Account.ACCOUNT_TYPE_ARG);
        Log.i(TAG, "onCreate: platform = " + platform.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        customerNameField = view.findViewById(R.id.customer_name_field);
        customerPhoneField = view.findViewById(R.id.customer_phone_field);
        customerIdNumberField = view.findViewById(R.id.customer_id_number_field);

        binding.nextButton.setOnClickListener(v -> {

            //Get the data and validating the data
            String name = String.valueOf(customerNameField.getText());
            String phone = String.valueOf(customerPhoneField.getText());
            String idNumber = String.valueOf(customerIdNumberField.getText());

            //Phone
            if (!Patterns.PHONE.matcher(phone).matches()) {
                customerPhoneField.setError("A valid phone number is required");
                return;
            }

            //ID Number
            if (TextUtils.isEmpty(idNumber) || idNumber.length() != 8) {
                customerIdNumberField.setError("A valid id number is required");
                return;
            }

            Customer customer = new Customer(name, phone, idNumber);

            viewModel.createCustomer(customer);
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CustomerViewModelFactory factory = new CustomerViewModelFactory(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
        viewModel = new ViewModelProvider(this, factory).get(CustomerViewModel.class);

        viewModel.getCustomer().observe(getViewLifecycleOwner(), customer -> {

            CustomerDetailsFragmentDirections.ActionAddAccountDetails actionAddAccountDetails
                    = CustomerDetailsFragmentDirections.actionAddAccountDetails(platform, customer);

            //Get the account number
            navController.navigate(actionAddAccountDetails);
        });

        viewModel.getException().observe(getViewLifecycleOwner(), exception -> {
            Log.e(TAG, "onViewCreated: ", exception);
            Toast.makeText(getContext(), getString(R.string.warning_text), Toast.LENGTH_SHORT).show();
        });
    }
}