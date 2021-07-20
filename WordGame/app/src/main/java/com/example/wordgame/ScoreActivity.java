package com.example.wordgame;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Integer.*;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener{
    TextView c, w, a,res;
    Button replay,exit2;
    String correct, wrong, unasnswered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        c=(TextView)findViewById(R.id.correct);
        w=(TextView)findViewById(R.id.wrong);
        a=(TextView)findViewById(R.id.unanswered);
        res=(TextView)findViewById(R.id.result);
        replay=(Button)findViewById(R.id.replay);
        exit2=(Button)findViewById(R.id.exitscore);
        replay.setOnClickListener(this);
        exit2.setOnClickListener(this);

        Bundle bundle=getIntent().getBundleExtra("data");
        correct=bundle.getString("correct");
        wrong=bundle.getString("wrong");
        unasnswered=bundle.getString("unanswered");
        c.setText(c.getText()+correct);
        w.setText(w.getText()+wrong);
        a.setText(a.getText()+unasnswered);

        if(Integer.parseInt(correct)>Integer.parseInt(wrong) && Integer.parseInt(correct)>Integer.parseInt(unasnswered))
        {
            res.setText("Congratulations!! :)");
        }
        else
        {
            res.setText("Better luck next time :(");
        }
    }
    //@Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("EXIT", true);
//        startActivity(intent);
//        if (getIntent().getBooleanExtra("EXIT", false)) {
//            finish();
//        }
//    }

    @Override
    public void onClick(View view) {
        if(view.equals(replay))
        {
            Intent intent = new Intent(ScoreActivity.this,PuzzleActivity.class);
            startActivity(intent);
            finish();
        }
        else if(view.equals(exit2))
        {
            Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            if (getIntent().getBooleanExtra("EXIT", false)) {
                finish();
            }
        }
    }
}