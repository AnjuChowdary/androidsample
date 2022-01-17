package com.ae.sampleapplication.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context mContext;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.mContext = context;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "MyRoutine").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
