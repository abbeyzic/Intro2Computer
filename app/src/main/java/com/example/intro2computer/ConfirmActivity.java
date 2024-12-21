package com.example.intro2computer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    Button yes = findViewById(R.id.yes);
    Button no = findViewById(R.id.no);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.confirmation_activity);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ConfirmActivity.this, SubmitSuccessActivity.class);
                startActivity(k);
            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(ConfirmActivity.this, TestActivity.class);
                startActivity(l);
            }

        });
    }
}
