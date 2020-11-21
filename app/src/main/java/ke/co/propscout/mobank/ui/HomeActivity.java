package ke.co.propscout.mobank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.data.models.User;
import ke.co.propscout.mobank.ui.auth.LoginActivity;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private NavController navController;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navView = findViewById(R.id.nav_view);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        setUpDestinationChangeListener();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void setUpDestinationChangeListener() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.addTransactionFragment || destination.getId() == R.id.listTransactionsFragment) {
                if (navView != null) {
                    navView.setVisibility(View.GONE);
                }
            } else {
                if (navView != null) {
                    if (navView.getVisibility() != View.VISIBLE)
                        navView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (navController != null)
            return navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.change_password_menu_option:
                Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sign_out_menu_option:
                initiateLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initiateLogout() {
        AlertDialog.Builder signOutDialog = new AlertDialog.Builder(this);
        signOutDialog.setTitle("Signing Out");
        signOutDialog.setMessage("Are sure you want to sign out of the application");

        signOutDialog.setPositiveButton("Sure", (dialog, position) -> {
            firebaseAuth.signOut();
            sendToLogin();
        });

        signOutDialog.setNegativeButton("Cancel", (dialog, position) -> {
        });

        signOutDialog.show();
    }

    private void sendToLogin() {
        Intent launchLoginActivity = new Intent(this, LoginActivity.class);
        launchLoginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(launchLoginActivity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() == null) {
            sendToLogin();
        } else {
            checkOrSetupUser(firebaseAuth.getCurrentUser());
        }
    }

    private void checkOrSetupUser(FirebaseUser currentUser) {

        firebaseFirestore.collection(User.COLLECTION_NAME).document(currentUser.getUid())
                .get()
                .addOnCompleteListener(getUserDocumentTask -> {
                    if (getUserDocumentTask.isSuccessful()) {
                        //Check is the user document is setup
                        if (!Objects.requireNonNull(getUserDocumentTask.getResult()).exists()) {
                            //Get the auth provider and setup the document

                            String email = currentUser.getEmail();
                            String displayName = currentUser.getDisplayName();
                            String phoneNumber = currentUser.getPhoneNumber();
                            String photoUrl = currentUser.getPhoneNumber();

                            User user = new User(phoneNumber, photoUrl, email, displayName);
                            firebaseFirestore.collection(User.COLLECTION_NAME).document(currentUser.getUid())
                                    .set(user)
                                    .addOnCompleteListener(setUserDocumentTask -> {
                                        if (setUserDocumentTask.isSuccessful()) {
                                            Toast.makeText(this, "User document initialized", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(this, getString(R.string.warning_text), Toast.LENGTH_SHORT).show();
                                            Log.e(TAG, "checkOrSetupUser: error initialing user document", getUserDocumentTask.getException());
                                        }
                                    });

                        } else {
                            Log.i(TAG, "checkOrSetupUser: user document already setup");
                        }
                    } else {
                        Toast.makeText(this, getString(R.string.warning_text), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "checkOrSetupUser: error getting user document", getUserDocumentTask.getException());
                    }
                });
    }
}