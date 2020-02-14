package com.example.a2048app;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.a2048app.mainGameWindow.gameWindow;

public class MainActivity extends AppCompatActivity {
    private int temp = 4;
    private TextView gameSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameSize = findViewById(R.id.gameSize);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        temp = myPreferences.getInt("gameSize", 4);
        setSize(temp);

        findViewById(R.id.btnStart).setOnClickListener(v-> {
            Intent intent = new Intent(this, gameWindow.class);
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


//        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
//            ((TextView)findViewById(R.id.textView)).setText("Large");
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
//            ((TextView)findViewById(R.id.textView)).setText("Small");
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
//            ((TextView)findViewById(R.id.textView)).setText("Normal");
//        }


//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://showcase.linx.twenty57.net:8081/UnixTime/tounix?date=now";
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                response -> {
//                    long n = -1;
//                    try {
//                        n = Integer.parseInt(response);
//                    } catch (Exception ignore) { }
//                    System.out.println(n);
//                }, error -> System.out.println("That didn't work!" + error.toString()));
//
//        queue.add(stringRequest);
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

 }



