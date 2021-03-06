package com.example.androidinterview;

import android.app.ActionBar;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class SimpleQuestions extends AppCompatActivity implements View.OnClickListener {

   String[] simpleQuestions, simpleAnswers;
   TextView tvQuestion, tvAnswer, tvTotalLengthYY, tvPresentIndexXX;
   Button bLeft, bShow, bRight;
//variables texttospeech
   TextToSpeech ttsObject;
   int result;

   int index;
    public static final String defaultAnswer = "Press \"A\" Button for the Answer)";

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
        headQuestion.setText("Simple Questions");

        index = 0;
        simpleQuestions = getResources().getStringArray(R.array.simple_ques);
        simpleAnswers = getResources().getStringArray(R.array.simple_ans);

        tvQuestion= findViewById(R.id.tvQuestion);
        tvAnswer= findViewById(R.id.tvAnswer);
        tvTotalLengthYY = findViewById(R.id.tvYY);
        tvPresentIndexXX= findViewById(R.id.tvXX);

        bLeft= findViewById(R.id.bleft);
        bShow= findViewById(R.id.bshowanswer);
        bRight= findViewById(R.id.bright);

        bLeft.setOnClickListener(this);
        bShow.setOnClickListener(this);
        bRight.setOnClickListener(this);
        bSpeak.setOnClickListener(this);
        bStopMusic.setOnClickListener(this);

        //Setting Values to our Variable and 4 textViews
        index = 0;
        tvQuestion.setText(simpleQuestions[index]);
        tvAnswer.setText(defaultAnswer);
        tvPresentIndexXX.setText(String.valueOf(index+1));
        tvTotalLengthYY.setText("/"+String.valueOf(simpleQuestions.length));


        //TTSObject lstener initialisation
        ttsObject = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status== TextToSpeech.SUCCESS){
                    result = ttsObject.setLanguage(Locale.ENGLISH);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Feature not supported by your device", Toast.LENGTH_SHORT).show();
}
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

                case R.id.bleft:
                tvAnswer.setText(defaultAnswer);
                index--;
                if(index == -1)
                {index = simpleQuestions.length-1;
                    tvQuestion.setText(simpleQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }else {
                    tvQuestion.setText(simpleQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }
                break;

                case R.id.bshowanswer:
                tvAnswer.setText(simpleAnswers[index]);
                break;


                case R.id.bright:
                tvAnswer.setText(defaultAnswer);
                index++;
                if(index ==simpleQuestions.length){
                    index =0;
                    tvQuestion.setText(simpleQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }
                else {
                    tvQuestion.setText(simpleQuestions[index]);
                    tvPresentIndexXX.setText(String.valueOf(index+1));
                }
                break;

                case  R.id.bspeak:
                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(),"feature not supported in your device",Toast.LENGTH_SHORT).show();
                } else{

                    if(tvAnswer.getText().toString().equals(defaultAnswer))
                    {

                    } else{
                    ttsObject.speak(simpleAnswers[index], TextToSpeech.QUEUE_FLUSH, null);
                }
                }
                break;

                case  R.id.bstop_mute:
                    if(ttsObject!=null){
                        ttsObject.stop();
                    }

                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(ttsObject != null){
            ttsObject.stop();
            ttsObject.shutdown();
        }
    }
}
