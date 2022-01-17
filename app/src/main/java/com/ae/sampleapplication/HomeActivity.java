package com.ae.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.ae.sampleapplication.adapter.MyNotesAdapter;
import com.ae.sampleapplication.database.DatabaseClient;
import com.ae.sampleapplication.model.MyNotes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView name;
    RecyclerView notesRecyclerView;
    ArrayList<MyNotes> myNotesList;
    FloatingActionButton addFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.user_textView);
        notesRecyclerView = findViewById(R.id.notes_recyclerView);
        addFab = findViewById(R.id.add_fab);

        //Check if extras exists
        if(getIntent().hasExtra("username")) {
            name.setText(getIntent().getStringExtra("username"));
        } else {
            name.setText("No Username found");
        }

//        prepareDummyDate();
        //ListView & RecyclerView
        //ListView - Orientation -> Vertical or Horizontal -> Contacts Application
        //GridView - Gallery in
        //RecyclerView ->

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);

        notesRecyclerView.setLayoutManager(manager);
//        notesRecyclerView.setAdapter(new MyNotesAdapter(this, myNotesList));

        addFab.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddNewActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getTasks();
    }

    private void prepareDummyDate() {
        com.ae.sampleapplication.model.MyNotes notes = new com.ae.sampleapplication.model.MyNotes("10th Jan 2022", "Monday", "Good morning!!");

        com.ae.sampleapplication.model.MyNotes notes1 = new com.ae.sampleapplication.model.MyNotes();
        notes1.setDate("11th Jan, 2022");
        notes1.setTitle("Tuesday");
        notes1.setTitle("Good Eveing!!!");

        com.ae.sampleapplication.model.MyNotes notes2 = new com.ae.sampleapplication.model.MyNotes("12th Jan, 2022");
        notes2.setTitle("Tuesday");
        notes2.setTitle("Good Eveing!!!");

        myNotesList = new ArrayList<com.ae.sampleapplication.model.MyNotes>();
        myNotesList.add(notes);
        myNotesList.add(notes1);
        myNotesList.add(notes2);
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<MyNotes>> {

            @Override
            protected List<MyNotes> doInBackground(Void... voids) {
                List<MyNotes> myNotesList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .routineDao()
                        .getAll();
                return myNotesList;
            }

            @Override
            protected void onPostExecute(List<MyNotes> myNotes) {
                super.onPostExecute(myNotes);
                notesRecyclerView.setAdapter(new MyNotesAdapter(HomeActivity.this, myNotes));
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}