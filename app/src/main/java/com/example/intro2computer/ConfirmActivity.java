package com.example.intro2computer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {
    Button no; Button yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_activity);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle no button click
                goBack();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle yes button click
                openScore();
            }
        });
    }
        void goBack(){
            Intent i = new Intent(this, TestActivity.class);
            startActivity(i);
        }
        void openScore(){
            Intent j = new Intent(this, SubmitSuccessActivity.class);
            startActivity(j);
        }
    }

