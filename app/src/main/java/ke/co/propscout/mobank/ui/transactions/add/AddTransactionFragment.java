package ke.co.propscout.mobank.ui.transactions.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.Account;
import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.databinding.FragmentAddTransactionBinding;

public class AddTransactionFragment extends Fragment {
    private static final String TAG = "AddTransactionFragment";

    private Platform platform;
    private NavController navController;
    private FragmentAddTransactionBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        platform = AddTransactionFragmentArgs.fromBundle(requireArguments()).getPlatform();

        Log.i(TAG, "onCreate: platform = " + platform.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddTransactionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get the activity and cast it to AppCompatActivity
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        //Set the title on the action bar
        activity.getSupportActionBar().setTitle("Add Transaction");

        //Set subtitle on the action bar
        activity.getSupportActionBar().setSubtitle(platform.toString());

        navController = Navigation.findNavController(getActivity(), R.id.fragment);
        Bundle args = new Bundle();
        args.putSerializable(Account.ACCOUNT_TYPE_ARG, platform);
        navController.setGraph(R.navigation.add_transaction_navigation, args);

    }
}