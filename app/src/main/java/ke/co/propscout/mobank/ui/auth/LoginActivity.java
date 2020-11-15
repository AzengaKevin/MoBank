package ke.co.propscout.mobank.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ke.co.propscout.mobank.databinding.ActivityLoginBinding;
import ke.co.propscout.mobank.ui.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(v -> {

            String email = String.valueOf(binding.emailField.getText());
            String password = String.valueOf(binding.passwordField.getText());

            //Validation
            //Email
            if (TextUtils.isEmpty(email)) {
                binding.emailField.setError("Email is required");
                binding.emailField.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailField.setError("Valid email is required");
                binding.emailField.requestFocus();
                return;
            }

            //Password
            if (TextUtils.isEmpty(password) || password.length() < 6) {
                binding.passwordField.setError("At least a 6 chars password required");
                binding.passwordField.requestFocus();
                return;
            }

            loginUser(email, password);

        });
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        sendHome();
                    } else {
                        Toast.makeText(this, "User Login Failed, Check Logs", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "loginUser: ", task.getException());
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            sendHome();
        }
    }

    private void sendHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}