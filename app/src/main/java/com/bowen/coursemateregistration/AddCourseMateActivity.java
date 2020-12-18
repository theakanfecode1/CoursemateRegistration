package com.bowen.coursemateregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bowen.coursemateregistration.models.CourseMate;
import com.bowen.coursemateregistration.viewmodels.AddCourseMateViewModel;

import es.dmoral.toasty.Toasty;

public class AddCourseMateActivity extends AppCompatActivity {

    private EditText nameOfStudent;
    private EditText matricNumberOfStudent;
    private EditText fieldOfInterestOfStudent;
    private Button submitButton;
    private CourseMate mCourseMate;
    private AddCourseMateViewModel mAddCourseMateViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameOfStudent = findViewById(R.id.name_of_student);
        matricNumberOfStudent = findViewById(R.id.matric_number_of_student);
        fieldOfInterestOfStudent = findViewById(R.id.field_of_interest);
        submitButton = findViewById(R.id.submit_button);
        mAddCourseMateViewModel = new ViewModelProvider(this).get(AddCourseMateViewModel.class);
        Intent intent = getIntent();
        final boolean fromListScreen = intent.getBooleanExtra("fromList", false);
        if (fromListScreen) {
            submitButton.setText("Update");
            mCourseMate = intent.getParcelableExtra("student");
            nameOfStudent.setText(mCourseMate.getName());
            matricNumberOfStudent.setText(mCourseMate.getMatricNumber());
            fieldOfInterestOfStudent.setText(mCourseMate.getFieldOfInterest());

        } else {
            submitButton.setText("Register");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameOfStudent.getText().toString().isEmpty() || matricNumberOfStudent.getText().toString().isEmpty() || fieldOfInterestOfStudent.getText().toString().isEmpty()) {
                    Toasty.error(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT, true).show();

                } else {

                    if (fromListScreen) {
                        mCourseMate.setName(nameOfStudent.getText().toString());
                        mCourseMate.setMatricNumber(matricNumberOfStudent.getText().toString());
                        mCourseMate.setFieldOfInterest(fieldOfInterestOfStudent.getText().toString());
                        mAddCourseMateViewModel.updateCourseMate(mCourseMate);
                        Toasty.success(getApplicationContext(), "Course-mate Updated!", Toast.LENGTH_SHORT, true).show();
                    } else {
                        CourseMate courseMate = new CourseMate(nameOfStudent.getText().toString(), matricNumberOfStudent.getText().toString(), fieldOfInterestOfStudent.getText().toString());
                        mAddCourseMateViewModel.addCourseMate(courseMate);
                        Toasty.success(getApplicationContext(), "Course-mate Registered!", Toast.LENGTH_SHORT, true).show();
                    }

                    finish();
                }
            }
        });

    }
}