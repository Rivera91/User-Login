package nyc.c4q.intentsreview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private EditText username;
    private EditText password;
    private Button registerButton;
    private Button submitButton;
    private CheckBox remember;
    private SharedPreferences login;
//    private TextView
//    private TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        registerButton = (Button) findViewById(R.id.register_button);
        submitButton = (Button) findViewById(R.id.submit_button);
        remember = (CheckBox) findViewById(R.id.checkBox);

        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);


        if (login.getBoolean("isChecked", false)) {
            username.setText(login.getString("username", null));
            password.setText(login.getString("password", null));
            remember.setChecked(login.getBoolean("isChecked", false));
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = login.edit();
                if (remember.isChecked()) {
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putBoolean("isChecked", remember.isChecked());
                    editor.commit();
                } else {
                    editor.putBoolean("isChecked", remember.isChecked());
                    editor.commit();
                }
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("currentUser", username.getText().toString());
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("testKey", SHARED_PREFS_KEY);
                startActivity(intent);

            }
        });

    }
}
