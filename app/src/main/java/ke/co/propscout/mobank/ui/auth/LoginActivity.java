package ke.co.propscout.mobank.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ke.co.propscout.mobank.ui.HomeActivity;
import ke.co.propscout.mobank.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        startActivity(new Intent(this, HomeActivity.class));
    }
}