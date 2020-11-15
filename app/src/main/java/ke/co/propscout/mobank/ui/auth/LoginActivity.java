package ke.co.propscout.mobank.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ke.co.propscout.mobank.databinding.ActivityLoginBinding;
import ke.co.propscout.mobank.ui.HomeActivity;
import ke.co.propscout.mobank.R;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}