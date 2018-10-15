package com.example.pasindusenarathne.testproject5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private EditText username,password;
    private Button register,signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (EditText) findViewById(R.id.USERNAME);
        password = (EditText) findViewById(R.id.PASSWORD);

        register = (Button) findViewById(R.id.REGISTER_button);
        signin = (Button) findViewById(R.id.LOGIN_button);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernames = username.getText().toString();
                String pass = password.getText().toString();

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                boolean result = dbHelper.isLogin(usernames,pass);

                if(result == true){

                    Intent intent = new Intent(Home.this,Profile.class);
                    Toast.makeText(getApplicationContext(),"User Signed In",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplicationContext(),"User Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
