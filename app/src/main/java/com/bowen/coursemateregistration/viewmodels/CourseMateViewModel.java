package com.bowen.coursemateregistration.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bowen.coursemateregistration.models.CourseMate;
import com.bowen.coursemateregistration.repositories.CourseMateRepository;

import java.util.List;

public class CourseMateViewModel extends AndroidViewModel {
    public LiveData<List<CourseMate>> courseMates;
    public CourseMateRepository mCourseMateRepository;


    public CourseMateViewModel(@NonNull Application application) {
        super(application);
        mCourseMateRepository = new CourseMateRepository();

    }

    public LiveData<List<CourseMate>> getCourseMates(){
        courseMates = mCourseMateRepository.getCourseMates(getApplication().getApplicationContext());
        return courseMates;
    }

}
