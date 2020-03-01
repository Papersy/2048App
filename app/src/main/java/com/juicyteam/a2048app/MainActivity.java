package com.juicyteam.a2048app;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.juicyteam.a2048app.mainGameWindow.gameWindow;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    private int temp = 4;
    private TextView gameSize;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changeLanguage();
        setContentView(R.layout.activity_main);
        gameSize = findViewById(R.id.gameSize);

        ((ImageView) findViewById(R.id.imgLeft)).setColorFilter(Color.parseColor("#574A40"));
        ((ImageView) findViewById(R.id.imgRight)).setColorFilter(Color.parseColor("#574A40"));

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        temp = myPreferences.getInt("gameSize", 4);
        setSize(temp);

        findViewById(R.id.btnStart).setOnClickListener(v-> {
            Intent intent = new Intent(this, gameWindow.class);
            startActivity(intent);
        });

        findViewById(R.id.btnStatistic).setOnClickListener(v->{
            Intent intent = new Intent(this, StatisticAll.class);
            startActivity(intent);
        });

        findViewById(R.id.btnShop).setOnClickListener(v->{
            Intent intent = new Intent(this, ShopActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnAbout).setOnClickListener(v ->{
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.imgLeft).setOnClickListener(v-> {
            if(temp == 4)
                temp = 8;
            else
                temp--;

            setSize(temp);

            myEditor.putInt("gameSize", temp);
            myEditor.apply();
        });

        findViewById(R.id.imgRight).setOnClickListener(v-> {
            if(temp == 8)
                temp = 4;
            else
                temp++;

            setSize(temp);

            myEditor.putInt("gameSize", temp);
            myEditor.apply();
        });
    }

    @SuppressLint("SetTextI18n")
    void setSize(int temp){
        switch (temp){
            case 4:
                gameSize.setText("4x4");
                break;
            case 5:
                gameSize.setText("5x5");
                break;
            case 6:
                gameSize.setText("6x6");
                break;
            case 7:
                gameSize.setText("7x7");
                break;
            case 8:
                gameSize.setText("8x8");
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    void changeLanguage(){
        Locale locale = new Locale("uk");

        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources()
                .updateConfiguration(configuration,
                        getBaseContext()
                                .getResources()
                                .getDisplayMetrics());
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            finishAffinity();
            return;
        }
        else{
            backToast = Toast.makeText(getBaseContext(), getString(R.string.onBackPressed), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}