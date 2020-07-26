package brainee.hub.expensemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email, password;
    private Button register;
    private TextView signin_here;

    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.register_progress);

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

                progressBar.setVisibility(View.VISIBLE);

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

                firebaseAuth.createUserWithEmailAndPassword(m_email, m_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(RegistrationActivity.this, "Account created..", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "Account creation failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

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