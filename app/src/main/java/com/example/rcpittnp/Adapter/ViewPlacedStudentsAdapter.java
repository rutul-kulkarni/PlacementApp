package com.example.rcpittnp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Model.StudentModel;
import com.example.rcpittnp.R;

import java.util.List;

public class ViewPlacedStudentsAdapter extends RecyclerView.Adapter<ViewPlacedStudentsAdapter.ViewHolder> {

    Context context;
    List<StudentModel> students;
    LayoutInflater inflater;
    public ViewPlacedStudentsAdapter(Context context, List<StudentModel> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewPlacedStudentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.placed_student_view ,parent , false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPlacedStudentsAdapter.ViewHolder holder, int position) {
        StudentModel student = students.get(position);
        String studentName = capitalizeName(student.getFirstName()) + " "+capitalizeName(student.getLastName());
        holder.studentNameTv.setText(studentName);
        if (student.isAllFeedbackGiven())
            holder.placedIv.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameTv;
        ImageView placedIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameTv = itemView.findViewById(R.id.studNameTv);
            placedIv = itemView.findViewById(R.id.check);
        }
    }

    public String capitalizeName(String name)
    {
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1, name.length());
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + remainingLetters;
        return name;
    }
}
