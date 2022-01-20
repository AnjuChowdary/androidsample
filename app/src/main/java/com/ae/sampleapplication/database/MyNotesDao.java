package com.ae.sampleapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ae.sampleapplication.model.MyNotes;

import java.util.ArrayList;

@Dao
public interface MyNotesDao {
    @Insert
    void insert(MyNotes myNotes);

//    @Query("SELECT * from MyNotes")
//    ArrayList<MyNotes> getAll();
}
