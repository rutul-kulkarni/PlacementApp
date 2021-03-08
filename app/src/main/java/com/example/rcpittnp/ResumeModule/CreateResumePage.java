package com.example.rcpittnp.ResumeModule;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcpittnp.R;

public class CreateResumePage extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resume_page);
        Intent intent = getIntent();

        Button button1 = (Button) findViewById(R.id.b1);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), PersonalInfoActivity.class);
                startActivity(intent);


            }
        });

        Button button2 = (Button) findViewById(R.id.b2);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), EducationActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.b3);

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), TechnicalSkillsActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.b4);

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), InterestsActivity.class);
                startActivity(intent);
            }
        });

        Button button5 = (Button) findViewById(R.id.b5);

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), ProjectsActivity.class);
                startActivity(intent);
            }
        });

        Button button6 = (Button) findViewById(R.id.b6);

        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), AchivementsActivity.class);
                startActivity(intent);
            }
        });

        Button button7 = (Button) findViewById(R.id.b7);

        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), ObjectiveActivity.class);
                startActivity(intent);
            }
        });


        Button button8 = (Button) findViewById(R.id.b8);

        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), ReferencesActivity.class);
                startActivity(intent);
            }
        });

        Button button9 = (Button) findViewById(R.id.b9);

        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), GenerateResumeActivity.class);
                startActivity(intent);
            }
        });

    }
}