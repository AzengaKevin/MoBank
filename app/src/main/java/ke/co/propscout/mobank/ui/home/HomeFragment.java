package ke.co.propscout.mobank.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ke.co.propscout.mobank.data.models.Platform;
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

        //Remove the subtitle
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(null);

        binding.mpesaCard.setOnClickListener(v -> viewTransactions(Platform.Mpesa));
        binding.kcbCard.setOnClickListener(v -> viewTransactions(Platform.KCB));
        binding.airtelCard.setOnClickListener(v -> viewTransactions(Platform.Airtel));
        binding.equityCard.setOnClickListener(v -> viewTransactions(Platform.Equity));
        binding.coopCard.setOnClickListener(v -> viewTransactions(Platform.Coop));
    }

    private void viewTransactions(Platform platform) {
        HomeFragmentDirections.ActionViewTransactions actionViewTransactions = HomeFragmentDirections.actionViewTransactions(platform);
        navController.navigate(actionViewTransactions);
    }
}