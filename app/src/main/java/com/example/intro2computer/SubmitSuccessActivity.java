package com.example.intro2computer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    Button restart ; Button answers;
    TextView percentScore;
    int totalQuestions = QuestionAnswer.question.length; //int percentageScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submitsuccess_activity);

        Intent j = getIntent();
        int score = j.getIntExtra("Score", 0);
        score+=1;
        //int percentageScore = (score/totalQuestions) *100 ;
        ((TextView)findViewById(R.id.percentScore)).setText(score +"/"+ totalQuestions);

        restart = findViewById(R.id.restart);
        percentScore = findViewById(R.id.percentScore);
        answers = findViewById(R.id.answers);

        restart.setOnClickListener(this);
        answers.setOnClickListener(this);
    }
    public void restartQuiz() {
        Intent i = new Intent(this, TestActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.restart){
            restartQuiz();
        } else if (v.getId() == R.id.answers ) {
            Intent i = new Intent(this, Answers.class);
            startActivity(i);
        }
    }
}