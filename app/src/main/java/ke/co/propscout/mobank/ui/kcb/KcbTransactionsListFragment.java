package ke.co.propscout.mobank.ui.kcb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.databinding.FragmentKcbTransactionsListBinding;

public class KcbTransactionsListFragment extends Fragment {

    private FragmentKcbTransactionsListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentKcbTransactionsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.addKcbTransactionFab.setOnClickListener(v -> navController.navigate(R.id.action_add_kcb_transaction));
    }
}