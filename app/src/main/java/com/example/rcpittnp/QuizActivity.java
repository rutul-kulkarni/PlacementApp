package com.example.rcpittnp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rcpittnp.Adapter.QuestionRecyclerViewAdapter;
import com.example.rcpittnp.Model.Question;
import com.example.rcpittnp.Model.QuestionsModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    List<HashMap<String,String>> hashMaps = new ArrayList<>();
    RecyclerView questionRecyclerView;

    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions = new ArrayList<>();
        String questionJSonString = Utils.getJsonFromAssets(getApplicationContext(),"Questions.json");
        Gson gson =new Gson();
        QuestionsModel questionsModel =gson.fromJson(questionJSonString,QuestionsModel.class);
        questions = questionsModel.getQuestions();

        questionRecyclerView = findViewById(R.id.quest_rec_view);
        QuestionRecyclerViewAdapter adapter = new QuestionRecyclerViewAdapter(this,questions);

        LinearLayoutManager layoutManager =new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL,false);
        questionRecyclerView.setLayoutManager(layoutManager);
        questionRecyclerView.setAdapter(adapter);
    }
}