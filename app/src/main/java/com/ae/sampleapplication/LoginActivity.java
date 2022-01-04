package com.ae.sampleapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView forgotPassword, createAccount;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("MYDATA", Context.MODE_PRIVATE);

        username = findViewById(R.id.username_editText);
        password = findViewById(R.id.password_editText);
        forgotPassword = findViewById(R.id.forgotPassword_textView);
        createAccount = findViewById(R.id.createAccount_textView);
        login = findViewById(R.id.login_button);

        //Programatically handling click of Button
        createAccount.setOnClickListener(v -> {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        //Check if user already exists then go to Home Screen
        if (sharedPreferences.contains("username")) {
            //User already exists
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("username", sharedPreferences.getString("username","")); //Passing values to another screen
            startActivity(intent);
            finish();
        }
    }

    public void UserLogin(View view) {
        //Validate User Inputs -> Username and Password
        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            //Alert -> USername and Password cannot be empty
            //Context -> Screen Reference this -> .java -> .class
            //Instance of any class, object this = new LoginActivity()
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Login Error");
            alert.setMessage("Username and Password should not be empty");

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //No implementation
                }
            });

            //Lambda expression - Functional Interface -> Single unimplemented method
            alert.setNegativeButton("Cancel", (dialog, which) -> {

            });

            alert.show();
        } else {
            //Login
            //Go to home Screen -> Explicit Intent
            String savedUsername = sharedPreferences.getString("username","");
            String savePassword = sharedPreferences.getString("password","");

            if (username.getText().toString().equals(savedUsername) && password.getText().toString().equals(savePassword)) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("username", username.getText().toString()); //Passing values to another screen
                startActivity(intent);
                finish();

                //Empty input fields
                username.setText("");
                password.setText("");
            } else {
                Toast.makeText(this, "Invalid Credentilas", Toast.LENGTH_LONG).show();
            }
        }
    }
}