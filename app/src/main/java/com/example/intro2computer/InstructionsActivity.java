package com.example.intro2computer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_activity);
        Intent i = getIntent();
        String message = i.getStringExtra("Name");
        ((TextView) findViewById(R.id.welcome)).setText("Welcome, " + message);

        TextView instructions = findViewById(R.id.bullets);
        String htmText = "<b>Instructions</b><br><br>";
                instructions.setText(Html.fromHtml(getString(R.string.instructs), Html.FROM_HTML_MODE_LEGACY));
    }

    public void TestActivity(View view) {
        Intent i = new Intent(this, TestActivity.class);
        i.putExtra("Name", getIntent().getStringExtra("Name"));
        startActivity(i);
    }
}