package com.ae.sampleapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ae.sampleapplication.model.MyNotes;

import java.util.List;

@Dao
public interface MyNotesDao {
    @Query("SELECT * FROM MyNotes")
    List<MyNotes> getAll();

    @Insert
    void insert(MyNotes myNotes);

    @Delete
    void delete(MyNotes myNotes);

    @Update
    void update(MyNotes myNotes);
}
