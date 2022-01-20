package com.ae.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ae.sampleapplication.adapter.MyNotesAdapter;
import com.ae.sampleapplication.model.MyNotes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView name;
    RecyclerView notesRecyclerView;
    ArrayList<MyNotes> myNotesList;
    FloatingActionButton addFabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.user_textView);
        notesRecyclerView = findViewById(R.id.notes_recyclerView);
        addFabButton = findViewById(R.id.add_note_fabButton);

        //Check if extras exists
        if(getIntent().hasExtra("username")) {
            name.setText(getIntent().getStringExtra("username"));
        } else {
            name.setText("No Username found");
        }

        prepareDummyDate();
        //ListView & RecyclerView
        //ListView - Orientation -> Vertical or Horizontal -> Contacts Application
        //GridView - Gallery in
        //RecyclerView ->

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);

        notesRecyclerView.setLayoutManager(manager);
        notesRecyclerView.setAdapter(new MyNotesAdapter(this, myNotesList));


        addFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent
                Intent intent = new Intent(HomeActivity.this, AddNotesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void prepareDummyDate() {
        MyNotes notes = new MyNotes("10th Jan 2022", "Monday", "Good morning!!");

        MyNotes notes1 = new MyNotes();
        notes1.setDate("11th Jan, 2022");
        notes1.setTitle("Tuesday");
        notes1.setDetails("Good Eveing!!!");

        MyNotes notes2 = new MyNotes("12th Jan, 2022");
        notes2.setTitle("Tuesday");
        notes2.setDetails("Good Eveing!!!");

        myNotesList = new ArrayList<MyNotes>();
        myNotesList.add(notes);
        myNotesList.add(notes1);
        myNotesList.add(notes2);
    }
}