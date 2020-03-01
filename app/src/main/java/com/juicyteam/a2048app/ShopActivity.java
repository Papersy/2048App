package com.juicyteam.a2048app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ShopActivity extends AppCompatActivity {
    private Ads ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ads = new Ads(this);

        ((ImageView) findViewById(R.id.imgDeleteBonus)).setColorFilter(getResources().getColor(R.color.ads));
        ((ImageView) findViewById(R.id.imgDoubleBonus)).setColorFilter(getResources().getColor(R.color.ads));
        ((ImageView) findViewById(R.id.imgPositionBonus)).setColorFilter(getResources().getColor(R.color.ads));

        findViewById(R.id.linearDouble).setOnClickListener(v-> showAd(1));
        findViewById(R.id.linearPosition).setOnClickListener(v-> showAd(2));
        findViewById(R.id.linearDelete).setOnClickListener(v-> showAd(3));
    }

    void showAd(int index){
        ads.showAds(index);
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
