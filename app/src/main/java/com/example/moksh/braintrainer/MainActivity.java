package com.example.moksh.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView question, mcq1, mcq2, mcq3, mcq4, timer, scorecard, result;
    CountDownTimer countDown;
    int q1, q2, wrong1, wrong2, wrong3, ans, score=0, numberOfQuestions=1;
    Button reset;


    public void setTimer (){
        countDown = new CountDownTimer(30000, 1000){
            public void onTick(long milliseconds){
                timer.setText(String.valueOf((int)(milliseconds+100)/1000) + "s");
            }
            public void onFinish(){
                Toast.makeText(getApplicationContext(), "Over", Toast.LENGTH_SHORT).show();
                timer.setText("0s");
                mcq1.setEnabled(false);
                mcq2.setEnabled(false);
                mcq3.setEnabled(false);
                mcq4.setEnabled(false);
                result.setText("Your Score: " + score + "/" + numberOfQuestions);
                result.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void mcqConditions () {
        if (ans == 1){
            mcq1.setText(String.valueOf(q1 + q2));
            mcq2.setText(String.valueOf(wrong1));
            mcq3.setText(String.valueOf(wrong2));
            mcq4.setText(String.valueOf(wrong3));
        }
        else if (ans == 2) {
            mcq1.setText(String.valueOf(wrong1));
            mcq2.setText(String.valueOf(q1 + q2));
            mcq3.setText(String.valueOf(wrong2));
            mcq4.setText(String.valueOf(wrong3));
        }
        else if (ans == 3){
            mcq1.setText(String.valueOf(wrong1));
            mcq2.setText(String.valueOf(wrong2));
            mcq3.setText(String.valueOf(q1 + q2));
            mcq4.setText(String.valueOf(wrong3));
        }
        else if (ans == 4){
            mcq1.setText(String.valueOf(wrong1));
            mcq2.setText(String.valueOf(wrong2));
            mcq3.setText(String.valueOf(wrong3));
            mcq4.setText(String.valueOf(q1 + q2));
        }
    }


    public void textViewOnClick (final TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {  //Checks if the textView is clicked.
            @Override
            public void onClick(View v) {
                if (q1+q2 == Integer.parseInt(String.valueOf(textView.getText()))) {   //If the text at the given textView matches the answer.
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    score++;
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();

                scorecard.setText(score + "/" + numberOfQuestions);

                Random r = new Random();
                q1 = r.nextInt(20) + 1;
                q2 = r.nextInt(30) + 1;
                ans = r.nextInt(4) + 1;
                wrong1 = r.nextInt(50) + 1;
                wrong2 = r.nextInt(50) + 1;
                wrong3 = r.nextInt(50) + 1;

                question.setText(q1 + "+" + q2);
                numberOfQuestions++;

                mcqConditions();
            }
        });
    }

    public void playAgain(View view) {
        reset.setVisibility(View.GONE);
        result.setVisibility(View.GONE);
        mcq1.setEnabled(true);
        mcq2.setEnabled(true);
        mcq3.setEnabled(true);
        mcq4.setEnabled(true);
        setTimer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        reset = findViewById(R.id.playAgain);
        timer = findViewById(R.id.timer);
        setTimer();

        //Random numbers generated.
        Random r = new Random();
        q1 = r.nextInt(20) + 1;
        q2 = r.nextInt(30) + 1;
        ans = r.nextInt(4) + 1;
        wrong1 = r.nextInt(50) + 1;
        wrong2 = r.nextInt(50) + 1;
        wrong3 = r.nextInt(50) + 1;

        //Question initiated for the first time.
        question = findViewById(R.id.question);
        question.setText(q1 + "+" + q2);

        scorecard = findViewById(R.id.score);
        scorecard.setText(score + "/" + numberOfQuestions);

        //Finding the MCQ textViews.
        mcq1 = findViewById(R.id.mcq1); mcq2 = findViewById(R.id.mcq2); mcq3 = findViewById(R.id.mcq3); mcq4 = findViewById(R.id.mcq4);

        //Options to display based on the value of random variable 'ans' (1-4).
        mcqConditions();

        //TextViews are Clicked!
        textViewOnClick(mcq1);
        textViewOnClick(mcq2);
        textViewOnClick(mcq3);
        textViewOnClick(mcq4);

    }
}
