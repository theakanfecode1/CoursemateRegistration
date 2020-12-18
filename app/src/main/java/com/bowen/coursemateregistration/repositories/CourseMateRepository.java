package com.bowen.coursemateregistration.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bowen.coursemateregistration.datasource.CourseMateDatabase;
import com.bowen.coursemateregistration.models.CourseMate;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CourseMateRepository {

    public LiveData<List<CourseMate>> getCourseMates(Context context){
        CourseMateDatabase courseMateDatabase = CourseMateDatabase.getInstance(context);
        LiveData<List<CourseMate>> courseMatesLiveData = courseMateDatabase.CourseMateDao().getCourseMates();
        return courseMatesLiveData;
    }

    public void addCourseMate(final CourseMate courseMate,Context context){
        final CourseMateDatabase courseMateDatabase = CourseMateDatabase.getInstance(context);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                courseMateDatabase.CourseMateDao().insertCourseMate(courseMate);
            }
        });
        thread.start();

    }

    public void updateCourseMate(final CourseMate courseMate,Context context){
        final CourseMateDatabase courseMateDatabase = CourseMateDatabase.getInstance(context);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                courseMateDatabase.CourseMateDao().updateCourseMate(courseMate);
            }
        });
        thread.start();

    }

    public void deleteCourseMate(final CourseMate courseMate,Context context){
        final CourseMateDatabase courseMateDatabase = CourseMateDatabase.getInstance(context);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                courseMateDatabase.CourseMateDao().deleteCourseMate(courseMate);
            }
        });
        thread.start();

    }


}
