package com.example.androidinterview;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ToughQuestions extends AppCompatActivity implements View.OnClickListener {

    String[] toughQuestions, toughAnswers;
    TextView tvQuestion, tvAnswer, tvTotalLengthYY, tvPresentIndexXX;
    Button bLeft, bShow, bRight;
int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        LinearLayout questions_ll = findViewById(R.id.questions_titlebar);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.question_title_bar);

        Button bSpeak = findViewById(R.id.bspeak);
        Button bStopMusic = findViewById(R.id.bstop_mute);
        TextView headQuestion = findViewById(R.id.tv_questions_titlebar);
        headQuestion.setText("Tough Questions");

        toughQuestions = getResources().getStringArray(R.array.difficult_ques);
        toughAnswers = getResources().getStringArray(R.array.difficult_ans);


        tvQuestion= findViewById(R.id.tvQuestion);
        tvAnswer= findViewById(R.id.tvAnswer);
        tvTotalLengthYY= findViewById(R.id.tvYY);
        tvPresentIndexXX= findViewById(R.id.tvXX);

        bLeft= findViewById(R.id.bleft);
        bShow= findViewById(R.id.bshowanswer);
        bRight= findViewById(R.id.bright);

        bLeft.setOnClickListener(this);
        bShow.setOnClickListener(this);
        bRight.setOnClickListener(this);

        //Setting Values to our Variable and 4 textViews
        index = 0;
        tvQuestion.setText(toughQuestions[index]);
        tvAnswer.setText("Press \"A\" Button for the Answer");
        tvPresentIndexXX.setText(String.valueOf(index+1));
        tvTotalLengthYY.setText("/"+String.valueOf(toughQuestions.length));



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bleft:
                tvAnswer.setText("Press \"A\" Button for the Answer");
                index--;
                if(index == -1)
                {index = toughQuestions.length-1;
                    tvQuestion.setText(toughQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }else {
                    tvQuestion.setText(toughQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }
                break;

            case R.id.bshowanswer:
                tvAnswer.setText(toughAnswers[index]);
                break;

            case R.id.bright:
                tvAnswer.setText("Press \"A\" Button for the Answer");
                index++;
                if(index ==toughQuestions.length){
                    index =0;
                    tvQuestion.setText(toughQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }
                else {
                    tvQuestion.setText(toughQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }
                break;
    }
}
}
