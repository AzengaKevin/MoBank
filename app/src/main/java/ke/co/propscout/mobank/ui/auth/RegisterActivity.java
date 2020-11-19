package ke.co.propscout.mobank.ui.auth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ke.co.propscout.mobank.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signInTextView.setOnClickListener(v -> finish());

    }
}