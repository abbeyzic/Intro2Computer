package com.example.intro2computer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Answers extends AppCompatActivity implements View.OnClickListener {
    Button restart; TextView answers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answers_activity);

        answers = findViewById(R.id.answers);
        String questionsText = getFormattedQuestionsText(); // Get your formatted questions string
        answers.setText(questionsText);

        restart = findViewById(R.id.restart);

        restart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.restart) {
            restartQuiz();
        }
    }

    private void restartQuiz() {
        Intent i = new Intent(this, TestActivity.class);
        startActivity(i);
    }
    private String getFormattedQuestionsText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < QuestionAnswer.question.length; i++) {
            sb.append("Question ").append(i + 1).append(": ").append(QuestionAnswer.question[i]).append("\n");
            for (int j = 0; j < QuestionAnswer.choices[i].length; j++) {
                sb.append("  ").append((char) ('A' + j)).append(". ").append(QuestionAnswer.choices[i][j]).append("\n");
            }
            sb.append("Correct Answer: ").append(QuestionAnswer.correctAnswers[i]).append("\n\n");
        }
        return sb.toString();
    }
}



