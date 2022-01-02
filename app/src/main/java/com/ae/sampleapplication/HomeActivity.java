package com.ae.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.user_textView);

        //Check if extras exists
        if(getIntent().hasExtra("username")) {
            name.setText(getIntent().getStringExtra("username"));
        } else {
            name.setText("No Username found");
        }
    }
}