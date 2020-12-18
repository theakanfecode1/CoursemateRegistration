package com.bowen.coursemateregistration.datasource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bowen.coursemateregistration.models.CourseMate;

@Database(entities = CourseMate.class, exportSchema = false, version = 1)
public abstract class CourseMateDatabase  extends RoomDatabase {
    public abstract CourseMateDao CourseMateDao();

    private static CourseMateDatabase instance;

    public static synchronized CourseMateDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CourseMateDatabase.class, "course_mate_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
