package com.example.rcpittnp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    RadioButton option1,option2,option3,option4;
    TextView questionTxt;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_question, container, false);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);

        questionTxt = view.findViewById(R.id.questionTxt);

        questionTxt.setText("question");
        option1.setText("a");
        option2.setText("a");
        option3.setText("a");
        option4.setText("a");

        return view;
    }
}