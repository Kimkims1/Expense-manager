package brainee.hub.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email, password;
    private Button register;
    private TextView signin_here;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registration();
    }

    private void registration() {

        email = findViewById(R.id.email_id_reg);
        password = findViewById(R.id.password_id_reg);
        register = findViewById(R.id.button_register);
        signin_here = findViewById(R.id.sign_in_here);

        register.setOnClickListener(new View.OnClickListener() {
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

        signin_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

    }
}