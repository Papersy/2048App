package com.juicyteam.a2048app.Animation;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.juicyteam.a2048app.R;
import com.juicyteam.a2048app.mainGameWindow.gameWindow;

import java.util.ArrayList;

public class Anim {
    private gameWindow activity;
    private long time = 350;
    private int animTransition;
    private int boxSize;
    private int[][] array, prevArray;
    private boolean spawnNew = false, isPrev = false, isMove = true;

    private TranslateAnimation translateAnimation;
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;

    private ArrayList textArray;


    public Anim(int boxSize, int[][] array, ArrayList textArray, gameWindow activity){
        this.boxSize = boxSize;
        this.array = array;
        this.textArray = textArray;
        this.activity = activity;

        myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        myEditor = myPreferences.edit();

        animTransition = 800 / boxSize + 10;
        prevArray = new int[boxSize][boxSize];
    }

    public void animFunc(int index){

        savePrevArray();

        switch (index) {
            case 1: //top
                new Thread(this::top).start();
                break;
            case 2: //bot
                new Thread(this::bot).start();
                break;
            case 3: //left
                new Thread(this::left).start();
                break;
            case 4: //right
                new Thread(this::right).start();
                break;
        }
    }

    private void top(){
        for (int i = 0; i < boxSize; i++) {
            for (int g = 0; g < boxSize; g++) {
                for (int j = g + 1; j < boxSize; j++) {
                    if (array[j][i] != 0 && (array[g][i] == array[j][i] || array[g][i] == 0)) {
                        spawnNew = true;
                        int temp = getVerticalIndex(i, j);

                        View view = (View) textArray.get(temp);
                        translateAnimation = new TranslateAnimation(0, 0, 0, (g - j) * animTransition);
                        time = System.currentTimeMillis();
                        translateAnimation.setDuration(320);

                        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) { }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (isSpawnNew()) {
                                    addNewNumber();
                                }
                                setSpawnNew(false);

                                printArray();
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) { }
                        });

                        view.startAnimation(translateAnimation);

                        array[g][i] += array[j][i];
                        array[j][i] = 0;
                    } else if (array[j][i] != array[g][i] && array[j][i] != 0)
                        break;
                }
            }
        }
    }

    private void bot(){
        for (int i = 0; i < boxSize; i++) {
            for (int g = boxSize - 1; g > 0; g--) {
                for (int j = g - 1; j >= 0; j--) {
                    if (array[j][i] != 0 && (array[g][i] == array[j][i] || array[g][i] == 0)) {
                        spawnNew = true;
                        int temp = getVerticalIndex(i, j);

                        View view = (View) textArray.get(temp);
                        translateAnimation = new TranslateAnimation(0, 0, 0, (g - j) * animTransition);
                        time = System.currentTimeMillis();
                        translateAnimation.setDuration(320);

                        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) { }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (isSpawnNew()) {
                                    addNewNumber();
                                }
                                setSpawnNew(false);

                                printArray();
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) { }
                        });

                        view.startAnimation(translateAnimation);

                        array[g][i] += array[j][i];
                        array[j][i] = 0;
                    } else if (array[j][i] != array[g][i] && array[j][i] != 0)
                        break;
                }
            }
        }
    }

    private void left(){
        for (int i = 0; i < boxSize; i++) {
            for (int g = 0; g < boxSize - 1; g++) {
                for (int j = g + 1; j < boxSize; j++) {
                    if (array[i][j] != 0 && (array[i][g] == array[i][j] || array[i][g] == 0)) {
                        spawnNew = true;
                        int temp = getHorizontalIndex(i, j);

                        View view = (View) textArray.get(temp);
                        translateAnimation = new TranslateAnimation(0, (j - g) * -1 * animTransition, 0, 0);
                        time = System.currentTimeMillis();
                        translateAnimation.setDuration(320);

                        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) { }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (isSpawnNew()) {
                                    addNewNumber();
                                }
                                setSpawnNew(false);

                                printArray();
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) { }
                        });

                        view.startAnimation(translateAnimation);

                        array[i][g] += array[i][j];
                        array[i][j] = 0;
                    } else if (array[i][j] != array[i][g] && array[i][j] != 0)
                        break;
                }
            }
        }
    }

    private void right(){
        for (int i = 0; i < boxSize; i++) {
            for (int g = boxSize - 1; g > 0; g--) {
                for (int j = g - 1; j >= 0; j--) {
                    if (array[i][j] != 0 && (array[i][g] == array[i][j] || array[i][g] == 0)) {
                        spawnNew = true;
                        int temp = getHorizontalIndex(i, j);

                        View view = (View) textArray.get(temp);
                        translateAnimation = new TranslateAnimation(0, (g - j) * animTransition, 0, 0);
                        time = System.currentTimeMillis();
                        translateAnimation.setDuration(320);

                        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) { }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (isSpawnNew()) {
                                    addNewNumber();
                                }
                                setSpawnNew(false);

                                printArray();
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) { }
                        });

                        view.startAnimation(translateAnimation);

                        array[i][g] += array[i][j];
                        array[i][j] = 0;
                    } else if (array[i][j] != array[i][g] && array[i][j] != 0)
                        break;
                }
            }
        }
    }





    private int getVerticalIndex(int i, int index)
    {
        int temp = 0;
        switch(i){
            case 0:
                temp = index * boxSize;
                break;
            case 1:
                temp = index * boxSize + 1;
                break;
            case 2:
                temp = index * boxSize + 2;
                break;
            case 3:
                temp = index * boxSize + 3;
                break;
            case 4:
                temp = index * boxSize + 4;
                break;
            case 5:
                temp = index * boxSize + 5;
                break;
            case 6:
                temp = index * boxSize + 6;
                break;
            case 7:
                temp = index * boxSize + 7;
                break;
        }
        return temp;
    }

    private int getHorizontalIndex(int i, int index)
    {
        int temp = 0;
        switch(i){
            case 0:
                temp = index;
                break;
            case 1:
                temp = boxSize + index;
                break;
            case 2:
                temp = boxSize * 2 + index;
                break;
            case 3:
                temp = boxSize * 3 +  index;
                break;
            case 4:
                temp = boxSize * 4 +  index;
                break;
            case 5:
                temp = boxSize * 5 +  index;
                break;
            case 6:
                temp = boxSize * 6 +  index;
                break;
            case 7:
                temp = boxSize * 7 +  index;
                break;
        }
        return temp;
    }

    private void savePrevArray(){
        for(int i = 0; i < boxSize; i++){
            for(int j = 0; j < boxSize; j++){
                prevArray[i][j] = array[i][j];
            }
        }

        isPrev = true;
    }

    public boolean isPrev(){
        return isPrev;
    }

    public int isPrevArray(int i, int j){
        return prevArray[i][j];
    }

    public boolean isSpawnNew(){
        return spawnNew;
    }

    public void setSpawnNew(boolean spawnNew){
        this.spawnNew = spawnNew;
    }


    private void printArray() {
        int index = 0;

        int radius = 15, color;
        GradientDrawable gradientDrawable;

        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++, index++) {
                if (array[i][j] == 0)
                    ((View) textArray.get(index)).setVisibility(View.INVISIBLE);
                else {
                    if (array[i][j] <= 4)
                        ((TextView) textArray.get(index)).setTextColor(activity.getResources().getColor(R.color.txtColor));
                    else
                        ((TextView) textArray.get(index)).setTextColor(Color.WHITE);

                    ((TextView) textArray.get(index)).setText(String.valueOf(array[i][j]));
                    ((View) textArray.get(index)).setVisibility(View.VISIBLE);

                    gradientDrawable = new GradientDrawable();
                    gradientDrawable.setCornerRadius(radius);

                    switch (array[i][j]) {
                        case 2:
                            color = Color.parseColor("#eee4da");
                            gradientDrawable.setColor(color);
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 4:
                            color = Color.parseColor("#ede0c8");
                            gradientDrawable.setColor(color);
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 8:
                            gradientDrawable.setColor(Color.parseColor("#f2b179"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 16:
                            gradientDrawable.setColor(Color.parseColor("#f59563"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 32:
                            gradientDrawable.setColor(Color.parseColor("#f67c5f"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 64:
                            gradientDrawable.setColor(Color.parseColor("#f65e3b"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 128:
                            gradientDrawable.setColor(Color.parseColor("#edcf72"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 256:
                            gradientDrawable.setColor(Color.parseColor("#edcc61"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 512:
                            gradientDrawable.setColor(Color.parseColor("#edc850"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 1024:
                            gradientDrawable.setColor(Color.parseColor("#edc53f"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        case 2048:
                            gradientDrawable.setColor(Color.parseColor("#edc22e"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                        default:
                            gradientDrawable.setColor(Color.parseColor("#3e3933"));
                            ((View) textArray.get(index)).setBackground(gradientDrawable);
                            break;
                    }
                }
            }
        }
        setIsMove(true);
    }

    public boolean getIsMove(){
        return isMove;
    }

    public void setIsMove(boolean isMove){
        this.isMove = isMove;
    }
    private void addNewNumber() {
        int x = (int) (Math.random() * boxSize);
        int y = (int) (Math.random() * boxSize);
        while (array[x][y] != 0) {
            x = (int) (Math.random() * boxSize);
            y = (int) (Math.random() * boxSize);
        }

        int chance = (int) (Math.random() * 10);
        if (chance < 8)
            array[x][y] = 2;
        else
            array[x][y] = 4;
    }
}
