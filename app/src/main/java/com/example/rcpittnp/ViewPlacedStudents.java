package com.example.rcpittnp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Adapter.ViewPlacedStudentsAdapter;
import com.example.rcpittnp.Model.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPlacedStudents extends AppCompatActivity {
    RecyclerView placedStudentRv;
    DatabaseReference rootRef;
    List<StudentModel> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_placed_students);

        placedStudentRv =findViewById(R.id.viewPlacedStudentRv);

        rootRef = FirebaseDatabase.getInstance().getReference("users").child("student");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                students = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    StudentModel student = dataSnapshot.getValue(StudentModel.class);
                    students.add(student);
                }
                ViewPlacedStudentsAdapter adapter = new ViewPlacedStudentsAdapter(getBaseContext() , students);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext() , LinearLayoutManager.VERTICAL,false);
                placedStudentRv.setLayoutManager(layoutManager);
                placedStudentRv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}