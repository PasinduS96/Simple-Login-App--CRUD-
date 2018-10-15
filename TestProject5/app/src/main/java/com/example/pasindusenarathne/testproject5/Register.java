package com.example.pasindusenarathne.testproject5;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText username,password,dateOfBirth;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.passWord);
        dateOfBirth = (EditText) findViewById(R.id.birthDay);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

        registerbtn = (Button) findViewById(R.id.register_button);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernames = username.getText().toString();
                String pass = password.getText().toString();
                String birthday = dateOfBirth.getText().toString();

                int selected = radioGroup.getCheckedRadioButtonId();
                radioButton= (RadioButton) findViewById(selected);

                String gender = radioButton.getText().toString();

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                dbHelper.insertData(genarateID(),usernames,pass,birthday,gender,sqLiteDatabase);

                Intent intent = new Intent(Register.this,Home.class);
                startActivity(intent);

            }
        });


    }

    public String genarateID(){

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        ArrayList<String> list = dbHelper.getIDList() ;

        int size = list.size();
        size++;
        String id = "User"+size;

        if(list.contains(id)){

            size++;
            id = "User0"+size;
        }


        return id;
    }
}
