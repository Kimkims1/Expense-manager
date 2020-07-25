package brainee.hub.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private TextView forgotPassword, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registration();
    }

    private void registration() {

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        login = findViewById(R.id.button_login);
        forgotPassword = findViewById(R.id.password_forgot);
        sign_up = findViewById(R.id.sign_up);
    }
}