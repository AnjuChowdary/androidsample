package com.ae.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ae.sampleapplication.database.AppDatabase;
import com.ae.sampleapplication.model.MyNotes;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {
    TextInputEditText title, notes;
    Boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        title = findViewById(R.id.title_editText);
        notes = findViewById(R.id.notes_editText);

        if (getIntent().hasExtra("tittle") && getIntent().hasExtra("details")) {
            //Edit the content
            title.setText(getIntent().getStringExtra("tittle"));
            notes.setText(getIntent().getStringExtra("details"));
            isUpdate = true;
        }
    }

    public void SaveNotes(View view) {
        //validation
        //Save into DB
        //Close this Activity

        if (title.getText().toString().isEmpty() || notes.getText().toString().isEmpty()) {
            Toast.makeText(this, "Title and Notes are required", Toast.LENGTH_SHORT).show();
        } else {
            MyNotes myNotes = new MyNotes();
            myNotes.setTitle(title.getText().toString());
            myNotes.setDetails(notes.getText().toString());

            if (isUpdate)
                myNotes.setDate(getIntent().getStringExtra("date"));
            else
                myNotes.setDate(new Date().toString());

            MyTask task = new MyTask();
            task.execute(myNotes);

        }
    }


    //AsyncTask - Non UI thread

    class MyTask extends AsyncTask<MyNotes, Void, Void> {
        AppDatabase db;
        @Override
        protected void onPreExecute() { //UI thread
            super.onPreExecute();
            //Loader on screen
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mynotes").build();
        }

        @Override
        protected Void doInBackground(MyNotes... myNotes) { //Not UI thread
            if (isUpdate) {
                db.myNotesDao().updateNotes(myNotes[0]);
            } else {
                db.myNotesDao().insert(myNotes[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) { //UI thread
            super.onPostExecute(unused);
            //Difsmiss the loader
            //finish
            Toast.makeText(AddNotesActivity.this, "Your notes saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Scope - public , private, protected
}