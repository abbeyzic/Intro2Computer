package com.example.intro2computer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionTextView;
    TextView questionTextView;
    RadioButton ansA;
    RadioButton ansB;
    RadioButton ansC;
    RadioButton ansD;
    Button nextBtn;
    Button prevBtn;
    Button submitBtn;

    int score = 0;
    int totalQuestions = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    int currentQuestion = currentQuestionIndex + 1;

    String selectedAnswer = "";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ((TextView)findViewById(R.id.questionId)).setText("Question : " + currentQuestion + "/" + totalQuestions);

        Intent i = getIntent();
        String message = i.getStringExtra("Name");
        ((TextView) findViewById(R.id.username)).setText(message);

        new CountDownTimer(1801000, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                ((TextView) findViewById(R.id.countdown)).setText(String.format("%d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                showTimeUpDialog();
            }
        }.start();

        totalQuestionTextView = findViewById(R.id.questionId);
        questionTextView = findViewById(R.id.Question);
        ansA = findViewById(R.id.option1);
        ansB = findViewById(R.id.option2);
        ansC = findViewById(R.id.option3);
        ansD = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        submitBtn = findViewById(R.id.submitBtn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


    }

    // Methods Declaration
    public void loadNewQuestion() {
        if (currentQuestionIndex < totalQuestions-1) {
            currentQuestion++;
            currentQuestionIndex++;
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        }
        else
            Toast.makeText(TestActivity.this, "This is the last question!!", Toast.LENGTH_SHORT).show();
    }

    public void loadPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestion--;
            currentQuestionIndex--;
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        }
        else
            Toast.makeText(this, "No previous question available", Toast.LENGTH_SHORT).show();
    }

    public void finishQuiz() {
        Intent j = new Intent(this, SubmitSuccessActivity.class);
        j.putExtra("Score", score);
        startActivity(j);
    }
    private void showTimeUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Time's Up!");
        builder.setMessage("Your time is up. Please proceed to the results page.");

        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishQuiz();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }


//Button Clicks

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.option1) {
            selectedAnswer = ansA.getText().toString();
        } else if (v.getId() == R.id.option2) {
            selectedAnswer = ansB.getText().toString();
        } else if (v.getId() == R.id.option3) {
            selectedAnswer = ansC.getText().toString();
        } else if (v.getId() == R.id.option4) {
            selectedAnswer = ansD.getText().toString();
        } else if (v.getId() == R.id.nextBtn) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score++;
            }
            loadNewQuestion();
            ((TextView) findViewById(R.id.questionId)).setText("Question : " + currentQuestion + "/" + totalQuestions );
        } else if (v.getId() == R.id.prevBtn) {
            loadPreviousQuestion();
            ((TextView)findViewById(R.id.questionId)).setText("Question : " + currentQuestion + "/" + totalQuestions );
        } else if (v.getId() == R.id.submitBtn) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score++;
            }
            // Confirm activity to pop up
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.confirmation_activity, null);
            builder.setView(dialogView);

            AlertDialog dialog = builder.create();
            dialog.show();

            // Buttons on the alertdialog
            Button noButton = dialogView.findViewById(R.id.no);
            Button yesButton = dialogView.findViewById(R.id.yes);

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finishQuiz();
                }
            });
        }
    }
}