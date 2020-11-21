package ke.co.propscout.mobank.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ke.co.propscout.mobank.databinding.ActivityRegisterBinding;
import ke.co.propscout.mobank.ui.HomeActivity;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private ActivityRegisterBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signInTextView.setOnClickListener(v -> finish());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.registerButton.setOnClickListener(v -> {

            String email = String.valueOf(binding.emailField.getText());
            String password = String.valueOf(binding.passwordField.getText());
            String confirmPassword = String.valueOf(binding.confirmPasswordField.getText());

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

            //Passwords Match
            if (!password.equals(confirmPassword)) {
                binding.passwordField.setError("Passwords did not match");
                binding.passwordField.requestFocus();
                return;
            }

            registerUser(email, password);
        });

    }

    private void registerUser(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        sendHome();
                    } else {
                        Toast.makeText(this, "Registration Failed, Check Logs", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "registerUser: ", task.getException());
                    }
                });
    }


    private void sendHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            sendHome();
        }
    }
}