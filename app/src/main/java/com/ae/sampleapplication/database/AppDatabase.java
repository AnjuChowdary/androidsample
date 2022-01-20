package com.ae.sampleapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ae.sampleapplication.model.MyNotes;

@Database(entities = {MyNotes.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyNotesDao myNotesDao();
}
