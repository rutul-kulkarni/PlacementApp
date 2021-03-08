package com.example.rcpittnp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Model.Question;
import com.example.rcpittnp.R;

import java.util.HashMap;
import java.util.List;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Question> questions;
    LayoutInflater inflater;
    public QuestionRecyclerViewAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.que_rec_view_layout ,null , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        String questionStr = question.getQuestion();
        String op1 =question.getOptions().get(0);
        String op2 = question.getOptions().get(1);
        String op3 = question.getOptions().get(2);
        String op4 = question.getOptions().get(3);
        holder.questionTV.setText(questionStr);
        holder.option1.setText(op1);
        holder.option2.setText(op2);
        holder.option3.setText(op3);
        holder.option4.setText(op4);
        holder.ansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ansId = holder.ansGroup.getCheckedRadioButtonId();
                RadioButton selectedBtn = holder.itemView.findViewById(ansId);
                String selectedAns = selectedBtn.getText().toString();
                String ans = question.getAns();
                if(ans.equals(selectedAns)) {
                    holder.ansTV.setText("Correct Answer");
                    holder.ansTV.setTextColor(holder.itemView.getResources().getColor(R.color.green));
                }
                else {
                    holder.ansTV.setText("Wrong Answer");
                    holder.ansTV.setTextColor(holder.itemView.getResources().getColor(R.color.red));
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTV,ansTV;
        RadioButton option1 ,option2,option3,option4;
        Button ansBtn;
        RadioGroup ansGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ansGroup = itemView.findViewById(R.id.ans_group);
            ansBtn = itemView.findViewById(R.id.submitBtn);
            questionTV = itemView.findViewById(R.id.questionTxt);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
            option3 = itemView.findViewById(R.id.option3);
            option4 = itemView.findViewById(R.id.option4);
            ansTV = itemView.findViewById(R.id.ansTxt);
        }
    }
}
