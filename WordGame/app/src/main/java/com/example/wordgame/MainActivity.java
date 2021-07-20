package com.example.wordgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button play,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(Button)findViewById(R.id.play);
        exit=(Button)findViewById(R.id.exit);
        play.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(play))
        {
            Intent intent = new Intent(MainActivity.this,PuzzleActivity.class);
            startActivity(intent);
        }
        else if(view.equals(exit))
        {
                finish();
        }
    }
}