package com.example.intro2computer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Answers extends AppCompatActivity implements View.OnClickListener{
    Button restart;
    String _AllQuestions;
    int currentQuestionIndex = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answers_activity);

        _AllQuestions = get_AllQuestions();
        ((TextView)findViewById(R.id.answerstext)).setText(_AllQuestions);


        restart = findViewById(R.id.restart);
        AnswersText(get_AllQuestions());

        restart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.restart){
            restartQuiz();
        }
    }

    private void restartQuiz() {
        Intent i = new Intent(this, TestActivity.class);
        startActivity(i);
    }

    @NonNull
    public void AnswersText(String AllQuestions) {
        int CurrentQuestionIndex = 0;
        _AllQuestions = AllQuestions;
        while (CurrentQuestionIndex <= QuestionAnswer.question.length-1) {
            System.out.println(QuestionAnswer.question[currentQuestionIndex] + "\n" + QuestionAnswer.choices[CurrentQuestionIndex][0] + "\t" + QuestionAnswer.choices[CurrentQuestionIndex][1] + "\n" + QuestionAnswer.choices[CurrentQuestionIndex][2] + "\t" + QuestionAnswer.choices[CurrentQuestionIndex][3] + "\n" + QuestionAnswer.correctAnswers[CurrentQuestionIndex]);
            CurrentQuestionIndex++;
        }

    }
    String get_AllQuestions(){
        return _AllQuestions;
    }
}

