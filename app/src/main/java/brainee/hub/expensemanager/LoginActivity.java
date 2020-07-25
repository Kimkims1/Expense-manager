package brainee.hub.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private TextView forgotPassword, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();
    }

    private void login() {

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        login = findViewById(R.id.button_login);
        forgotPassword = findViewById(R.id.password_forgot);
        sign_up = findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String m_email = email.getText().toString().trim();
                String m_password = password.getText().toString().trim();

                if (TextUtils.isEmpty(m_email)) {
                    email.setError("Email is required..");
                    return;
                }

                if (TextUtils.isEmpty(m_password)) {
                    password.setError("Password is required");
                    return;
                }

            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetActivity.class));
            }
        });
    }
}