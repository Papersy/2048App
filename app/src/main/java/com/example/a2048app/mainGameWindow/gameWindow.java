package com.example.a2048app.mainGameWindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.a2048app.R;

public class gameWindow extends AppCompatActivity {
    private gameWindowInitialization gameWindowInitialization;
    private long backPressedTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_window);


        gameWindowInitialization = new gameWindowInitialization(this);
        gameWindowInitialization.Listener();
    }

    @Override
    protected void onDestroy() {
        gameWindowInitialization.saveArray();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        gameWindowInitialization.saveArray();
        super.onPause();
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();

            super.onBackPressed();

            return;
        }
        else{
            backToast = Toast.makeText(getBaseContext(), getString(R.string.onBackPressed), Toast.LENGTH_SHORT);

            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
        gameWindowInitialization.saveArray();
    }
}
