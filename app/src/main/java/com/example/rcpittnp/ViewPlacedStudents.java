package com.example.rcpittnp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

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
    DatabaseReference feedbackRef;
    List<StudentModel> students;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_placed_students);

        placedStudentRv =findViewById(R.id.viewPlacedStudentRv);

        rootRef = FirebaseDatabase.getInstance().getReference("users").child("student");
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Loading Data");
        loadingBar.setMessage("Please wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                students = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    StudentModel student = dataSnapshot.getValue(StudentModel.class);
                    Log.d("Placed ", "onDataChange: "+student.getFirstName()+ "  "+student.isPlaced());
                    if (student.isPlaced()) {
                        students.add(student);
                        Log.d("Placed ", "onDataChange: "+student.getFirstName()+ "  "+student.isPlaced());
                    }
                }
                ViewPlacedStudentsAdapter adapter = new ViewPlacedStudentsAdapter(getBaseContext() , students);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext() , LinearLayoutManager.VERTICAL,false);
                placedStudentRv.setLayoutManager(layoutManager);
                placedStudentRv.setAdapter(adapter);
                loadingBar.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}