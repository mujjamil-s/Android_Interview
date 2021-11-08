package com.example.androidinterview;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button bSimple, bTough, bSeeOtherApps, bRateApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout front_ll = findViewById(R.id.front_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

                bSimple = findViewById(R.id.bsq);
        bTough = findViewById(R.id.btq);
        bSeeOtherApps = findViewById(R.id.bseeotherapps);
        bRateApp = findViewById(R.id.brateapp);

        bSimple.setOnClickListener(this);
        bTough.setOnClickListener(this);
        bSeeOtherApps.setOnClickListener(this);
        bRateApp.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bsq:
                Intent isq = new Intent(this, SimpleQuestions.class);
                startActivity(isq);
              break;

            case R.id.btq:
                Intent itq = new Intent(this, ToughQuestions.class);
                startActivity(itq);
                break;

            case R.id.bseeotherapps:
                try {
                    Uri uri1 = Uri.parse("market://search?q=thehusnagamingstudio");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }catch (ActivityNotFoundException e){
                    Uri uri1 = Uri.parse("http://play.google.com/store/search?q=thehusnagamingstudio");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);

                }

                break;

            case R.id.brateapp:
                try {
                    Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }catch (ActivityNotFoundException e){
                    Uri uri1 = Uri.parse("http://play.google.com/store/apps/details?id="+ getPackageName());
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);

                }
                break;
        }

    }
}