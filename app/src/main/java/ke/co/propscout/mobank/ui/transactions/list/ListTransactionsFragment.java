package ke.co.propscout.mobank.ui.transactions.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ke.co.propscout.mobank.data.models.Platform;
import ke.co.propscout.mobank.databinding.FragmentListTransactionsBinding;

public class ListTransactionsFragment extends Fragment {
    private static final String TAG = "ListTransactionsFrag";

    private FragmentListTransactionsBinding binding;
    private NavController navController;

    private Platform platform;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        platform = ListTransactionsFragmentArgs.fromBundle(requireArguments()).getPlatform();

        Log.i(TAG, "onCreate: platform = " + platform.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListTransactionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get the activity and cast it to AppCompatActivity
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        //Set the title on the action bar
        activity.getSupportActionBar().setTitle("Transactions");

        //Set subtitle on the action bar
        activity.getSupportActionBar().setSubtitle(platform.toString());

        navController = Navigation.findNavController(view);

        binding.addTransactionFab.setOnClickListener(v -> {
            ListTransactionsFragmentDirections.ActionAddTransaction
                    actionAddTransaction = ListTransactionsFragmentDirections.actionAddTransaction(platform);
            navController.navigate(actionAddTransaction);
        });

    }
}