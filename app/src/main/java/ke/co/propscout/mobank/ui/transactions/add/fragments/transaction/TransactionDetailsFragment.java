package ke.co.propscout.mobank.ui.transactions.add.fragments.transaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Transaction;
import ke.co.propscout.mobank.data.models.TransactionType;
import ke.co.propscout.mobank.databinding.FragmentTransactionDetailsBinding;

public class TransactionDetailsFragment extends Fragment {
    private static final String TAG = "TransactionDetailsFrag";

    private FragmentTransactionDetailsBinding binding;
    private TransactionViewModel viewModel;
    private NavController navController;
    private Account account;

    private TextInputEditText transactionAmountField;
    private TextInputEditText transactionIdField;
    private TextInputEditText transactionDescriptionField;
    private RadioGroup transactionTypeRadioGroup;
    private RadioButton depositRadioButton;
    private RadioButton withdrawRadioButton;

    private TransactionType transactionType;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        account = TransactionDetailsFragmentArgs.fromBundle(requireArguments()).getAccount();

        Log.i(TAG, "onCreate: account = " + account.getAccountNumber());
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        initExtraWidgets(view);

        binding.nextButton.setOnClickListener(v -> {

            try {
                double amount = Double.parseDouble(String.valueOf(transactionAmountField.getText()));
                String description = String.valueOf(transactionDescriptionField.getText());
                String transactionId = String.valueOf(transactionIdField.getText());
                //Get all the necessary data
                if (transactionType == null) {
                    Toast.makeText(getContext(), getString(R.string.transaction_type_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                //Validate transaction id
                if (TextUtils.isEmpty(transactionId)) {
                    transactionIdField.setText(getString(R.string.transaction_id_error));
                    return;
                }

                Transaction transaction = new Transaction(transactionType.toString(), amount, transactionId, description);

                viewModel.createTransaction(transaction);

            } catch (NumberFormatException exception) {
                transactionAmountField.setError(getString(R.string.transaction_amount_error));
            }

        });
    }

    private void initExtraWidgets(View view) {
        transactionTypeRadioGroup = view.findViewById(R.id.transaction_type_radio_group);

        transactionTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == withdrawRadioButton.getId()) {
                transactionType = TransactionType.Withdraw;
            }

            if (checkedId == depositRadioButton.getId()) {
                transactionType = TransactionType.Deposit;
            }
        });

        withdrawRadioButton = view.findViewById(R.id.withdraw_radio_button);
        depositRadioButton = view.findViewById(R.id.deposit_radio_button);

        transactionAmountField = view.findViewById(R.id.transaction_amount_field);
        transactionIdField = view.findViewById(R.id.transaction_id_field);
        transactionDescriptionField = view.findViewById(R.id.transaction_description_field);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TransactionViewModelFactory factory = new TransactionViewModelFactory(account);
        viewModel = new ViewModelProvider(this, factory).get(TransactionViewModel.class);

        viewModel.getTransaction().observe(getViewLifecycleOwner(), transaction -> {
            Log.i(TAG, "onActivityCreated: transaction id = " + transaction.getTransactionId());
            Toast.makeText(getContext(), "Transaction Added Successfully", Toast.LENGTH_SHORT).show();
        });

        viewModel.getException().observe(getViewLifecycleOwner(), exception -> {
            Log.e(TAG, "onActivityCreated: ", exception);
            Toast.makeText(getContext(), getString(R.string.warning_text), Toast.LENGTH_SHORT).show();
        });
    }
}