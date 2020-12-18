package com.bowen.coursemateregistration.datasource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.bowen.coursemateregistration.models.CourseMate;

import java.util.List;

@Dao
public interface CourseMateDao {

    @Query("SELECT * FROM course_mate")
    LiveData<List<CourseMate>> getCourseMates();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourseMate(CourseMate courseMate);

    @Update
    void updateCourseMate(CourseMate courseMate);

    @Delete
    void deleteCourseMate(CourseMate courseMate);
}
