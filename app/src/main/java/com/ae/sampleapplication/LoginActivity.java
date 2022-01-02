package com.ae.sampleapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_editText);
        password = findViewById(R.id.password_editText);
        forgotPassword = findViewById(R.id.forgotPassword_textView);
        createAccount = findViewById(R.id.createAccount_textView);
        login = findViewById(R.id.login_button);

        //Programatically handling click of Button
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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

            //Lambda
            alert.setNegativeButton("Cancel", (dialog, which) -> {

            });

            alert.show();
        } else {
            //Login
            //Go to home Screen -> Explicit Intent
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("username", username.getText().toString()); //Passing values to another screen
            startActivity(intent);
            finish();

            //Drawable
            //Empty input fields
            username.setText("");
            password.setText("");

//            Toast.makeText(this, "Login is Successful", Toast.LENGTH_LONG).show();
        }
    }
}