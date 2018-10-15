package com.example.pasindusenarathne.testproject5;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private EditText id,username,password,dateOfBirth;
    private RadioButton radioButton,malebutton,femalebutton;
    private RadioGroup radioGroup;
    private Button update,remove,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id = (EditText) findViewById(R.id.search_userid);
        username =(EditText) findViewById(R.id.usernamefeild);
        password =(EditText) findViewById(R.id.passwordfeild);
        dateOfBirth =(EditText) findViewById(R.id.birthdayfeild);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        malebutton = (RadioButton) findViewById(R.id.maleButton);
        femalebutton = (RadioButton) findViewById(R.id.femaleButton);

        update = (Button) findViewById(R.id.update_button);
        remove = (Button)findViewById(R.id.remove_button);
        search = (Button) findViewById(R.id.search_button1);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = id.getText().toString();
                String usernames = username.getText().toString();
                String pass = password.getText().toString();
                String birthday = dateOfBirth.getText().toString();

                int selected = radioGroup.getCheckedRadioButtonId();
                radioButton= (RadioButton) findViewById(selected);

                String gender = radioButton.getText().toString();

                DBHelper dbHelper =new DBHelper(getApplicationContext());
                dbHelper.updateData(uid,usernames,pass,birthday,gender);
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
                id.setText("");
                username.setText("");
                password.setText("");
                dateOfBirth.setText("");
                radioGroup.clearCheck();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = setCustomDialog();
                alertDialog.show();
                id.setText("");

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = id.getText().toString();

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

                ArrayList<User> result = dbHelper.getDetails(uid);

                for(int i=0; i < result.size(); i++){

                    username.setText(result.get(i).getName());
                    password.setText(result.get(i).getPassword());
                    dateOfBirth.setText(result.get(i).getDateofbirth());
                    String gender = result.get(i).getGender();

                    if(gender.equals("Male")){

                        malebutton.setChecked(true);
                    }
                    if(gender.equals("Female")){

                        femalebutton.setChecked(true);
                    }
                }
            }
        });
    }

    public AlertDialog setCustomDialog(){

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Delete Record")
                .setMessage("Please Confirm The Action")
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DBHelper dbHelper = new DBHelper(getApplicationContext());
                        dbHelper.deleteData(id.getText().toString());
                        username.setText("");
                        password.setText("");
                        dateOfBirth.setText("");
                        radioGroup.clearCheck();
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                }).create();

        return alertDialog;
    }
}
