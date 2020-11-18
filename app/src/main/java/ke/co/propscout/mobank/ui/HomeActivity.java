package ke.co.propscout.mobank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ke.co.propscout.mobank.R;
import ke.co.propscout.mobank.ui.auth.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    private NavController navController;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        firebaseAuth = FirebaseAuth.getInstance();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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
        launchLoginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(launchLoginActivity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() == null) {
            sendToLogin();
        }
    }
}