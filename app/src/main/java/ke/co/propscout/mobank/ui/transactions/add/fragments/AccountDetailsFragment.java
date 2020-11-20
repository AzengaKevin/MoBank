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

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.User;
import ke.co.propscout.mobank.databinding.FragmentAccountDetailsBinding;

public class AccountDetailsFragment extends Fragment {

    private FragmentAccountDetailsBinding binding;
    private NavController navController;

    private User currentUser = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        navController = Navigation.findNavController(view);

        binding.nextButton.setOnClickListener(v -> {
            //Navigate to get the transactions
            navController.navigate(R.id.action_add_transaction_details);
        });
    }
}