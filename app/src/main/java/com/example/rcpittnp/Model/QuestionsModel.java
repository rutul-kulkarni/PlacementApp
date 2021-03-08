package com.example.rcpittnp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsModel {

    @SerializedName("questions")
    @Expose
    private List<com.example.rcpittnp.Model.Question> questions = null;

    public List<com.example.rcpittnp.Model.Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<com.example.rcpittnp.Model.Question> questions) {
        this.questions = questions;
    }

}

