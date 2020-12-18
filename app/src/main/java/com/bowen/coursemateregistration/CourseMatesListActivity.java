package com.bowen.coursemateregistration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.bowen.coursemateregistration.models.CourseMate;
import com.bowen.coursemateregistration.viewmodels.AddCourseMateViewModel;
import com.bowen.coursemateregistration.viewmodels.CourseMateViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class CourseMatesListActivity extends AppCompatActivity implements DialogCallback {

    private RecyclerView courseMateList;
    private CourseMateViewModel mCourseMateViewModel;
    private AddCourseMateViewModel mAddCourseMateViewModel;
    private CourseMateListAdapter mCourseMateListAdapter;
    private Context mContext;
    private static final String TAG = "CourseMatesListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_mates_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        courseMateList = findViewById(R.id.course_mates_recycler_view);
        mContext = this;
        mCourseMateViewModel = new ViewModelProvider(this).get(CourseMateViewModel.class);
        mAddCourseMateViewModel = new ViewModelProvider(this).get(AddCourseMateViewModel.class);
        mCourseMateViewModel.getCourseMates();
        mCourseMateViewModel.getCourseMates().observe(this, new Observer<List<CourseMate>>() {
            @Override
            public void onChanged(List<CourseMate> courseMates) {
                Log.d(TAG, "onChanged: ");
                mCourseMateListAdapter = new CourseMateListAdapter(courseMates,mContext);
                Log.d(TAG, "onChanged: "+ courseMates.size());
                courseMateList.setAdapter(mCourseMateListAdapter);
                courseMateList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        });
        FloatingActionButton fab = findViewById(R.id.add_course_mate_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddCourseMateActivity.class);
                intent.putExtra("fromList",false);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showDialog(CourseMate courseMate) {
        deleteCourse(courseMate);
    }

    private void deleteCourse(final CourseMate courseMate) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = this.getLayoutInflater().inflate(R.layout.confirmation_delete,null);
        builder.setView(view);
        Button No = view.findViewById(R.id.no_unmap);
        Button yes = view.findViewById(R.id.yesunmap);

        final AlertDialog alertDialog = builder.create();
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                mAddCourseMateViewModel.deleteCourseMate(courseMate);

            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.show();

    }

}