package com.example.rcpittnp.ResumeModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcpittnp.R;

public class ResumeBuilderActivity extends AppCompatActivity {

    private float x = 0;
    private float y = 0;
    private Button button5 = null;
    private TextView tv1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_builder);

        RelativeLayout r1 = findViewById(R.id.r1);
        tv1 = findViewById(R.id.tv1);

        Button createResumeBtn = (Button) findViewById(R.id.createResumeBtn);

        createResumeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), CreateResumePage.class);
                startActivity(intent);
            }
        });


        Button viewInterviewQueBtn = (Button) findViewById(R.id.viewInterviewQueBtn);

        viewInterviewQueBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), InterviewActivity.class);
                startActivity(intent);
            }
        });

    }
}