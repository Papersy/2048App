package com.example.a2048app.mainGameWindow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2048app.Enum.Bonus;
import com.example.a2048app.MainActivity;
import com.example.a2048app.R;
import com.example.a2048app.onSwipe.onSwipe;

import java.util.ArrayList;

class gameWindowInitialization {
    private Statistic statistic;
    private GameController gameController;
    private gameWindow activity;
    private int index = 0;
    private int boxCount;
    private TextView txtDesc;
    private LinearLayout swipeReader;
    private ArrayList<LinearLayout> linearList = new ArrayList<>();
    private ArrayList<TextView> textList = new ArrayList<>();
    private ArrayList<DataCon> arrayList = new ArrayList<>();
    private LinearLayout linearDelete, linearDouble, linearPosition;


    gameWindowInitialization(gameWindow activity) {
        this.activity = activity;

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        boxCount = myPreferences.getInt("gameSize", 4);

        createGameBox();

        statistic = new Statistic(activity);
        gameController = new GameController(activity, textList, boxCount, statistic);

        txtDesc = activity.findViewById(R.id.textDesc);
        swipeReader = activity.findViewById(R.id.swipeReader);

        linearDelete = activity.findViewById(R.id.linearDelete);
        linearDouble = activity.findViewById(R.id.linearDouble);
        linearPosition = activity.findViewById(R.id.linearPosition);

        ((ImageView) activity.findViewById(R.id.imgDelete)).setColorFilter(Color.parseColor("#535c68"));
        ((ImageView) activity.findViewById(R.id.imgPosition)).setColorFilter(Color.parseColor("#ffffff"));
        ((TextView) activity.findViewById(R.id.textDelteCount)).setText(String.valueOf(gameController.bonusCount(Bonus.DELETE)));
        ((TextView) activity.findViewById(R.id.textPositionCount)).setText(String.valueOf(gameController.bonusCount(Bonus.POSITION)));
        ((TextView) activity.findViewById(R.id.textDoubleCount)).setText(String.valueOf(gameController.bonusCount(Bonus.DOUBLE)));
    }

    @SuppressLint("ClickableViewAccessibility")
    void Listener(){
        linearDelete.setOnClickListener(v-> {
            if(gameController.getBonus().equals(Bonus.DELETE)){
                txtDesc.setVisibility(View.INVISIBLE);
                gameController.setBonus(Bonus.NONE);
                swipeReader.setVisibility(View.VISIBLE);
                activity.findViewById(R.id.linearDelete).setBackgroundResource(R.drawable.bonus_style);
            }
            else {
                if (gameController.getBonusCount(Bonus.DELETE)) {
                    txtDesc.setText(activity.getString(R.string.deleteDesc));
                    txtDesc.setVisibility(View.VISIBLE);
                    gameController.setBonus(Bonus.DELETE);
                    swipeReader.setVisibility(View.GONE);
                    activity.findViewById(R.id.linearDelete).setBackgroundResource(R.drawable.bonus_style_activated);
                    activity.findViewById(R.id.linearDouble).setBackgroundResource(R.drawable.bonus_style);
                    activity.findViewById(R.id.linearPosition).setBackgroundResource(R.drawable.bonus_style);
                } else
                    Toast.makeText(activity, "Not enough bonuses", Toast.LENGTH_SHORT).show();
            }
        });

        linearDouble.setOnClickListener(v-> {
            if(gameController.getBonus().equals(Bonus.DOUBLE)){
                txtDesc.setVisibility(View.INVISIBLE);
                gameController.setBonus(Bonus.NONE);
                swipeReader.setVisibility(View.VISIBLE);
                activity.findViewById(R.id.linearDouble).setBackgroundResource(R.drawable.bonus_style);
            }
            else {
                if (gameController.getBonusCount(Bonus.DOUBLE)) {
                    txtDesc.setText(activity.getString(R.string.doubleDesc));
                    txtDesc.setVisibility(View.VISIBLE);
                    gameController.setBonus(Bonus.DOUBLE);
                    swipeReader.setVisibility(View.GONE);
                    activity.findViewById(R.id.linearDelete).setBackgroundResource(R.drawable.bonus_style);
                    activity.findViewById(R.id.linearDouble).setBackgroundResource(R.drawable.bonus_style_activated);
                    activity.findViewById(R.id.linearPosition).setBackgroundResource(R.drawable.bonus_style);
                } else
                    Toast.makeText(activity, "Not enough bonuses", Toast.LENGTH_SHORT).show();
            }
        });

        linearPosition.setOnClickListener(v-> {
            if(gameController.getBonus().equals(Bonus.POSITION) && gameController.getIndex() == 0){
                txtDesc.setVisibility(View.INVISIBLE);
                gameController.setBonus(Bonus.NONE);
                swipeReader.setVisibility(View.VISIBLE);
                activity.findViewById(R.id.linearPosition).setBackgroundResource(R.drawable.bonus_style);
            }
            else {
                if (gameController.getBonusCount(Bonus.POSITION)) {
                    txtDesc.setText(activity.getString(R.string.positionDesc));
                    txtDesc.setVisibility(View.VISIBLE);
                    gameController.setBonus(Bonus.POSITION);
                    swipeReader.setVisibility(View.GONE);
                    activity.findViewById(R.id.linearDelete).setBackgroundResource(R.drawable.bonus_style);
                    activity.findViewById(R.id.linearDouble).setBackgroundResource(R.drawable.bonus_style);
                    activity.findViewById(R.id.linearPosition).setBackgroundResource(R.drawable.bonus_style_activated);
                } else
                    Toast.makeText(activity, "Not enough bonuses", Toast.LENGTH_SHORT).show();
            }
        });

        swipeReader.setOnTouchListener(new onSwipe(activity){
            public void onSwipeTop() {
                gameController.swipeTop();
            }
            public void onSwipeRight() {
                gameController.swipeRight();
            }
            public void onSwipeLeft() {
                gameController.swipeLeft();
            }
            public void onSwipeBottom() {
                gameController.swipeBot();
            }
        });

        activity.findViewById(R.id.goHome).setOnClickListener(v->{
            gameController.saveArray();
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        });

        activity.findViewById(R.id.restartGame).setOnClickListener(v-> restartGame());

        activity.findViewById(R.id.prevArray).setOnClickListener(v-> gameController.setPrevArray());
    }


    private void createGameBox(){
        //creating game background
        LinearLayout mainLinear = activity.findViewById(R.id.mainLinear);
        mainLinear.setOrientation(LinearLayout.VERTICAL);

        for(int j = 0; j < boxCount; j++){
            LinearLayout linearLine = new LinearLayout(activity);
            linearLine.setOrientation(LinearLayout.HORIZONTAL);

            for(int i = 0; i < boxCount; i++, index++){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5, 5, 5, 5);

                layoutParams.height = 800/ boxCount;
                layoutParams.width = 800/ boxCount;

                LinearLayout linearBox = new LinearLayout(activity);

                linearBox.setBackgroundResource(R.drawable.play_btn_style);
                linearBox.setId(index);

                linearLine.addView(linearBox, layoutParams);

                linearList.add(linearBox);
                arrayList.add(0, new DataCon(index, i, j));
            }
            mainLinear.addView(linearLine);
        }

        //creating game front size with numbers
        GridLayout gridText = activity.findViewById(R.id.gridText);
        gridText.setColumnCount(boxCount);

        for (int i = 0; i < boxCount; i++){
            for(int j = 0; j < boxCount; j++) {
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParam.setMargins(5, 5, 5, 5);


                layoutParam.height = 800/ boxCount;
                layoutParam.width = 800/ boxCount;

                TextView textView = new TextView(activity);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                }

                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setMaxLines(1);
                textView.setBackgroundResource(R.drawable.play_btn_txt);
                gridText.addView(textView, layoutParam);
                textList.add(textView);
            }
        }

        for (DataCon data: arrayList) {
            activity.findViewById(data.index).
                    setOnClickListener(v-> gameController.checkBonus(data.y, data.x));
        }

        activity.findViewById(R.id.linearInfo).setMinimumWidth(boxCount*(800/ boxCount + 10));
        System.out.println(String.valueOf(boxCount*(800/ boxCount + 10)));
    }

    private void restartGame(){
        gameController.restartGame();
    }

    public void saveArray(){
        gameController.saveArray();
    }

    private class DataCon{
        private int  index, x, y;

        DataCon(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }
}

