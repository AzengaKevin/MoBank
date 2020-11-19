package ke.co.propscout.mobank.ui.home;

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
import ke.co.propscout.mobank.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.mpesaCard.setOnClickListener(
                v -> navController.navigate(R.id.action_view_transactions)
        );

        binding.kcbCard.setOnClickListener(
                v -> navController.navigate(R.id.action_view_transactions)
        );

        binding.airtelCard.setOnClickListener(
                v -> navController.navigate(R.id.action_view_transactions)
        );

        binding.equityCard.setOnClickListener(
                v -> navController.navigate(R.id.action_view_transactions)
        );

        binding.coopCard.setOnClickListener(
                v -> navController.navigate(R.id.action_view_transactions)
        );
    }
}