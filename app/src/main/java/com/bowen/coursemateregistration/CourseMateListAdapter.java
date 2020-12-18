package com.bowen.coursemateregistration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bowen.coursemateregistration.models.CourseMate;

import java.util.List;

public class CourseMateListAdapter extends RecyclerView.Adapter<CourseMateListAdapter.CourseMateListViewHolder> {

    private List<CourseMate> mCourseMateList;
    private Context mContext;
    private DialogCallback mDialogCallback;

    public CourseMateListAdapter(List<CourseMate> courseMateList, Context context) {
        mCourseMateList = courseMateList;
        mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mDialogCallback = (DialogCallback) mContext;
    }

    @NonNull
    @Override
    public CourseMateListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_list_item,parent,false);
        return new CourseMateListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseMateListViewHolder holder, final int position) {
        final int positionAt = position;
        holder.studentName.setText(mCourseMateList.get(position).getName());
        holder.studentMatricNumber.setText(mCourseMateList.get(position).getMatricNumber());
        holder.studentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,AddCourseMateActivity.class);
                intent.putExtra("fromList",true);
                intent.putExtra("student",mCourseMateList.get(positionAt));
                mContext.startActivity(intent);
            }
        });
        holder.studentCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mDialogCallback.showDialog(mCourseMateList.get(position));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCourseMateList.size();
    }

    class CourseMateListViewHolder extends RecyclerView.ViewHolder{
        CardView studentCard;
        TextView studentName;
        TextView studentMatricNumber;
        public CourseMateListViewHolder(@NonNull View itemView) {
            super(itemView);
            studentCard = itemView.findViewById(R.id.student_card);
            studentName = itemView.findViewById(R.id.student_name);
            studentMatricNumber = itemView.findViewById(R.id.student_matric_number);
        }
    }
}
