package ke.co.propscout.mobank.ui.coop;

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
import ke.co.propscout.mobank.databinding.FragmentCoopTransactionsListBinding;

public class CoopTransactionsListFragment extends Fragment {

    private FragmentCoopTransactionsListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoopTransactionsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.addCoopTransactionFab.setOnClickListener(v -> navController.navigate(R.id.action_add_coop_transaction));
    }
}