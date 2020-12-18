package com.bowen.coursemateregistration.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bowen.coursemateregistration.models.CourseMate;
import com.bowen.coursemateregistration.repositories.CourseMateRepository;

import java.util.List;

public class AddCourseMateViewModel extends AndroidViewModel {

    public CourseMateRepository mCourseMateRepository;


    public AddCourseMateViewModel(@NonNull Application application) {
        super(application);
        mCourseMateRepository = new CourseMateRepository();

    }

    public void addCourseMate(CourseMate courseMate){
        mCourseMateRepository.addCourseMate(courseMate,getApplication().getApplicationContext());

    }

    public void updateCourseMate(CourseMate courseMate){
        mCourseMateRepository.updateCourseMate(courseMate,getApplication().getApplicationContext());
    }

    public void deleteCourseMate(CourseMate courseMate){
        mCourseMateRepository.deleteCourseMate(courseMate,getApplication().getApplicationContext());
    }

}
