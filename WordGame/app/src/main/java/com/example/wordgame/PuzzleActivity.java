package com.example.wordgame;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuzzleActivity extends AppCompatActivity implements View.OnClickListener {
    public static String[] words =new String[] {"squirrel", "chimpanzee", "rabbit","goldfish","mouse","kitten","parrot","turtle","shrimp","flamingo","seagull","goose","raven","woodpecker",
    "penguin","panda","walrus","koala","donkey","kangaroo","elephant","giraffe","sheep","crocodile","buffalo","zebra","rhinoceros","alligator","dinosaur","leopard","cheetah","tiger","lion","snake","tortoise",
    "chicken","monkey","antelope","gorilla","chipmunk","horse","camel","mongoose","turkey","lobster","pelican","jackal","jaguar","hyena","ostrich","vulture"};

    boolean run=true;
    int counter = 30,correct=0,wrong=0,unanswered=0,randomPosition;
    int globalmincounter=5;
    int globalseccounter=0;
    TextView c,question,remTime;
    Button next;
    EditText answer;
    String word;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        c = (TextView) findViewById(R.id.counter);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        question=(TextView)findViewById(R.id.question);
        answer=(EditText) findViewById(R.id.answer);
        remTime=(TextView)findViewById(R.id.remtime);
        ShuffleString s=new ShuffleString();
        randomPosition=random();
        word=s.shuffleJava8(words[randomPosition]);
        question.setText(word);
        new MyCounter().start();
        new Globalcounter().start();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        globalmincounter=0;
        this.finish();

    }
    public int random()
    {
        int r= new Random().nextInt(words.length);
        return r;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if(view.equals(next))
        {
            String userinput=answer.getText().toString();
            answer.setText("");
            if(userinput.equals(""))
            {
                Toast.makeText(getBaseContext(), "You Missed!", Toast.LENGTH_LONG).show();
                unanswered++;
            }
            else if(userinput.toLowerCase().equals(words[randomPosition]))
            {
                Toast.makeText(getBaseContext(), "Correct", Toast.LENGTH_LONG).show();
                correct++;
            }
            else
            {
                Toast.makeText(getBaseContext(), "Wrong", Toast.LENGTH_LONG).show();
                wrong++;
            }
            randomPosition=random();
            ShuffleString s=new ShuffleString();
            word=s.shuffleJava8(words[randomPosition]);
            question.setText(word);
            counter=30;
            run=true;
        }
    }

    class h extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            c.setText(String.valueOf(msg.what));
        }
    }
    class globalhandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            remTime.setText(String.valueOf(msg.what));
        }
    }
Handler handler= new h();
Handler ghandler=new globalhandler();
class MyCounter extends Thread
{
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void run()
    {
        while(run)
        {
            if(counter==0) {
                //Toast.makeText(getBaseContext(), "You Missed!", Toast.LENGTH_LONG).show();
                unanswered++;
                ShuffleString s=new ShuffleString();
                randomPosition=random();
                word=s.shuffleJava8(words[randomPosition]);
                question.setText(word);
                counter=30;
                run=true;
            }
            counter--;
            handler.sendEmptyMessage(counter);
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){}
        }
    }
}
class Globalcounter extends Thread
{
    public void run()
    {
        String m,s,message;
        int flag=0;
        while(globalmincounter!=0)
        {
            if(globalseccounter==0){
                globalmincounter--;
                globalseccounter=59;
            }
            globalseccounter--;
            ghandler.sendEmptyMessage(globalmincounter);
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e){}
        }
        if(globalmincounter==0)
        {
            Bundle bundle = new Bundle();
            bundle.putString("correct", String.valueOf(correct));
            bundle.putString("wrong", String.valueOf(wrong));
            bundle.putString("unanswered", String.valueOf(unanswered));
            Intent newActivity;
            newActivity = new Intent(PuzzleActivity.this,ScoreActivity.class);
            newActivity.putExtra("data", bundle);
            startActivity(newActivity);
            finish();
        }
    }
}
    class ShuffleString {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public String shuffleJava8(String text){
            List<Character> characters =  text.chars().mapToObj( c -> (char)c).collect(Collectors.toList());
            StringBuilder result = new StringBuilder();

            IntStream.range(0,text.length()).forEach((index) -> {
                int randomPosition = new Random().nextInt(characters.size());
                result.append(characters.get(randomPosition));
                characters.remove(randomPosition);
            });
            return result.toString();
        }
}
}