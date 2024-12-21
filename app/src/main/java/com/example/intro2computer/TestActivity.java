package com.example.intro2computer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;



public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totalQuestionTextView;
    TextView questionTextView;
    RadioButton ansA; RadioButton ansB; RadioButton ansC; RadioButton ansD;
    Button nextBtn; Button prevBtn; Button submitBtn;

    int score= 0;
    int totalQuestions = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    int currentQuestion= currentQuestionIndex+1;

    String selectedAnswer = "";



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent i = getIntent();
        String message= i.getStringExtra("Name");
        ((TextView)findViewById(R.id.username)).setText(message);

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
                String passStatus = "Time's up!";
                new AlertDialog.Builder(TestActivity.this)
                        .setTitle(passStatus)
                        .setMessage("Score is " + score+" out of"+ totalQuestions)
                        .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                        .setCancelable(false)
                        .show();
            }
        }.start();


        totalQuestionTextView = findViewById(R.id.questionId);
        questionTextView = findViewById(R.id.Question);
        ansA = findViewById(R.id. option1);
        ansB = findViewById(R.id. option2);
        ansC = findViewById(R.id. option3);
        ansD = findViewById(R.id. option4);
        nextBtn = findViewById(R.id.nextbtn);
        prevBtn = findViewById(R.id.prevbtn);
        submitBtn = findViewById(R.id.submitbtn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);



        //Buttons

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle next button click
                if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }
                currentQuestion+=1;
                currentQuestionIndex+=1;
                loadNewQuestion();
                totalQuestionTextView.setText("Question : "+ currentQuestion +"/" + totalQuestions);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle previous button click
                loadPreviousQuestion();
                totalQuestionTextView.setText("Question : "+ currentQuestion +"/" + totalQuestions);
            }
        });
        //Question Number
        totalQuestionTextView.setText("Question : "+ currentQuestion +"/" + totalQuestions);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishQuiz();
            }
        });



    }

    //Radio buttons
    @Override
    public void onClick(View view){
        RadioButton clickedButton = (RadioButton) view;
        selectedAnswer = clickedButton.getText().toString();
    }
    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;

        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    void loadPreviousQuestion(){
        if (currentQuestionIndex > 0) {
            currentQuestion-=1 ;// Decrement the question number
            currentQuestionIndex-=1 ;// Decrement the question index
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);// Update UI with the previous question and options
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        } else {
            Toast.makeText(this, "No previous question available", Toast.LENGTH_SHORT).show();
        }
    }


    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
    void finishQuiz(){

        Intent i = new Intent(this, ConfirmActivity.class);
        startActivity(i);
        }
    }

