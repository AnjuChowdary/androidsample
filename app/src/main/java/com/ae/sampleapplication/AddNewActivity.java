package com.ae.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ae.sampleapplication.database.DatabaseClient;
import com.ae.sampleapplication.model.MyNotes;

import java.util.Date;

public class AddNewActivity extends AppCompatActivity {
    EditText title, description;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        title = findViewById(R.id.task_editText);
        description = findViewById(R.id.description_editText);
        save = findViewById(R.id.save_button);

        save.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        final String sTitle = title.getText().toString().trim();
        final String sDesc = description.getText().toString().trim();
        final String sDate = new Date().toString();

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                MyNotes myNotes = new MyNotes();
                myNotes.setTitle(sTitle);
                myNotes.setDescription(sDesc);
                myNotes.setDate(sDate);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .routineDao()
                        .insert(myNotes);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}