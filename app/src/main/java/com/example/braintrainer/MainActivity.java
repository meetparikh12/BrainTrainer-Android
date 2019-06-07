package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView question,countDown,viewScore;
    private Button bt1,bt2,bt3,bt4,goButton,resetButton;
    private ConstraintLayout constraintLayout;
    int correctAnswer;
    int questionsAnswered;
    int score=0;

    public void generateRandom(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        int number1 = random.nextInt(25)+1;
        int number2 = random.nextInt(25)+1;
        int result = number1+number2;
        question.setText(number1 + " + " +number2);
        Button[] buttons = new Button[4];
        buttons[0] = bt1;
        buttons[1] = bt2;
        buttons[2] = bt3;
        buttons[3] = bt4;
        int generateRandom;
        correctAnswer = random.nextInt(4);
        arrayList.clear();
        for(int i=0; i<4; i++){
            if(i==correctAnswer){
                arrayList.add(number1+number2);
            }else{
                generateRandom = random.nextInt(50)+1;
                while(generateRandom==(number1+number2)){
                    generateRandom = random.nextInt(50)+1;
                }
                arrayList.add(generateRandom);
            }
        }
        for(int i=0; i<4; i++){
            buttons[i].setText(String.valueOf(arrayList.get(i)));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraintLayout);
        goButton = findViewById(R.id.goButton);
        resetButton = findViewById(R.id.resetButton);
        question = findViewById(R.id.question);
        countDown = findViewById(R.id.countDown);
        viewScore = findViewById(R.id.viewScore);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
    }
    public void buttonPressed(View buttonView){
        int tagID = Integer.parseInt(buttonView.getTag().toString());
        String answer="";
        questionsAnswered++;
        if(tagID == correctAnswer){
            score++;
            answer="Correct Answer";
        }else{
            answer="Wrong Answer";

        }
        Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        generateRandom();
    }
    public void startGame(View view){
        goButton.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        generateRandom();
        questionsAnswered=0;
        setTimer();

    }
    public void resetGame(View view){
        resetButton.setVisibility(View.INVISIBLE);
        score=0;
        generateRandom();
        questionsAnswered=0;
        viewScore.setVisibility(View.INVISIBLE);
        countDown.setVisibility(View.VISIBLE);
        setTimer();
        allowButtonToClick(true);

    }
    private void setTimer(){
        new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000<10){
                    countDown.setText("Time left: 0:0" +millisUntilFinished/1000 +"s");
                }else{
                    countDown.setText("Time left: 0:" +millisUntilFinished/1000 +"s");
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "OOPS! TIME'S UP", Toast.LENGTH_SHORT).show();
                viewScore.setText("Score: " +score +"/" +questionsAnswered );
                viewScore.setVisibility(View.VISIBLE);
                countDown.setVisibility(View.INVISIBLE);
                allowButtonToClick(false);
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    private void allowButtonToClick(boolean allow){
        bt1.setClickable(allow);
        bt2.setClickable(allow);
        bt3.setClickable(allow);
        bt4.setClickable(allow);
    }
}




