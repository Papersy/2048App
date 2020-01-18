package com.example.a2048app.mainGameWindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a2048app.R;

public class gameWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_window);

        new gameWindowInitialization(this).Listener();

    }
}
