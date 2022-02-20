package com.example.votecast;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Draft.class}, version = 2, exportSchema = false)
public abstract class ClientDatabase extends RoomDatabase {

    public abstract DraftDao drafts();
}
