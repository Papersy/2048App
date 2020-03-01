package com.juicyteam.a2048app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.juicyteam.a2048app.mainGameWindow.Statistic;

public class StatisticAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_all);

        Statistic statistic = new Statistic(this);

        ((TextView) findViewById(R.id.txtBestScore4)).setText(String.format(getString(R.string.string_string_int_format), getString(R.string.best_score2), "4x4:\n", statistic.getBestScr(4)));
        ((TextView) findViewById(R.id.txtBestScore5)).setText(String.format(getString(R.string.string_string_int_format), getString(R.string.best_score2), "5x5:\n", statistic.getBestScr(5)));
        ((TextView) findViewById(R.id.txtBestScore6)).setText(String.format(getString(R.string.string_string_int_format), getString(R.string.best_score2), "6x6:\n", statistic.getBestScr(6)));
        ((TextView) findViewById(R.id.txtBestScore7)).setText(String.format(getString(R.string.string_string_int_format), getString(R.string.best_score2), "7x7:\n", statistic.getBestScr(7)));
        ((TextView) findViewById(R.id.txtBestScore8)).setText(String.format(getString(R.string.string_string_int_format), getString(R.string.best_score2), "8x8:\n", statistic.getBestScr(8)));

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
