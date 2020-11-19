package ke.co.propscout.mobank.ui.transactions.add.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.databinding.FragmentTransactionDetailsBinding;

public class TransactionDetailsFragment extends Fragment {

    private FragmentTransactionDetailsBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.nextButton.setOnClickListener(v -> {
            //Should persist the transaction to the server
            Toast.makeText(getContext(), "Finish adding the transaction", Toast.LENGTH_SHORT).show();
        });

    }
}