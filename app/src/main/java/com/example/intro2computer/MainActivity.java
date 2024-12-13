package com.example.intro2computer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle("Login");
        ((EditText)findViewById(R.id.username)).getText().toString();
    }
    public void launchInstructions(View v){
        //launch instructions page
        Intent i = new Intent(this, InstructionsActivity.class);
        String message = ((EditText)findViewById(R.id.username)).getText().toString();
        i.putExtra("Name", message);
        startActivity(i);
    }

}
