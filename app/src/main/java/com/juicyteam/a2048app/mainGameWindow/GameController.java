package com.juicyteam.a2048app.mainGameWindow;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.juicyteam.a2048app.Animation.Anim;
import com.juicyteam.a2048app.Animation.CheckMove;
import com.juicyteam.a2048app.Enum.Bonus;
import com.juicyteam.a2048app.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

class GameController {
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    private int index = 0;
    private BonusesInfo bonusesInfo;
    private Anim anim;
    private CheckMove checkMove;
    private Statistic statistic;
    private gameWindow activity;
    private int[][] array;
    private int boxSize;
    private TextView txtScore, txtBestScore;

    private ArrayList textArray;

    GameController(gameWindow activity, ArrayList textArray, int boxSize) {
        this.activity = activity;
        this.textArray = textArray;
        this.boxSize = boxSize;

        myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        myEditor = myPreferences.edit();
        myEditor.putInt("time", 340);
        myEditor.apply();

        array = new int[boxSize][boxSize];

        statistic = new Statistic(activity);
        bonusesInfo = new BonusesInfo(activity, array, statistic);
        anim = new Anim(boxSize, array, textArray, activity);
        checkMove = new CheckMove(boxSize, array);

        try {
            setArray();
            bonusesInfo.setBonusUseCountFromSave(boxSize);
        } catch (Exception e) {
            bonusesInfo.setBonusUseCount(5);
            addNewNumber();
            addNewNumber();
        }

        txtScore = activity.findViewById(R.id.txtScore);
        txtBestScore = activity.findViewById(R.id.txtBestScore);


        printArray();
        calcScore();
    }

    // spawn new number
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

    // timer for ending animation
    void countTimer(int index)
    {
        this.index = index;
        if (checkMove.isMove() && anim.getIsMove()) {
            anim.setIsMove(false);

            new Thread(this::animFunction).start();

            calcScore();
            //new Thread(this::waitAnimation).start();
        } else if (!checkMove.isMove()) {
            activity.findViewById(R.id.textDesc).setVisibility(View.VISIBLE);
            ((TextView) activity.findViewById(R.id.textDesc)).setText(activity.getString(R.string.game_over));
        }
    }

    private void animFunction(){
        activity.runOnUiThread(() -> anim.animFunc(index));
    }

    private void waitAnimation() {
        activity.runOnUiThread(() -> {
            if (anim.isSpawnNew()) {
                addNewNumber();
                calcScore();
            }
            anim.setSpawnNew(false);

            printArray();
        });
    }

    int bonusCount(Bonus bonus) { // get count of bonus
        return bonusesInfo.bonusCount(bonus);
    }

    Bonus getBonus() { // get current bonus
        return bonusesInfo.getBonus();
    }

    void setBonus(Bonus bonus) { // set bonus
        bonusesInfo.setBonus(bonus);
    }

    int getIndex() { // get position index
        return bonusesInfo.getIndex();
    }

    int getBonusUseCount() {
        return bonusesInfo.getBonusUseCount();
    }

    void checkBonus(int x, int y) { // function to use one bonus
        bonusesInfo.checkBonus(x, y);
        bonusesInfo.saveBonusUseCount(boxSize);
        calcScore();
        printArray();
    }

    // set background color and text
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
    }


    // calculate scores
    private void calcScore() {
        int temp = 0;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                temp += array[i][j];
            }
        }

        txtScore.setText(String.format(activity.getString(R.string.string_int_format), activity.getString(R.string.score), temp));
        if (temp > statistic.getBestScr(boxSize)) {
            txtBestScore.setText(String.format(activity.getString(R.string.string_int_format), activity.getString(R.string.best_score), temp));
            statistic.setBestScr(boxSize, temp);
        } else
            txtBestScore.setText(String.format(activity.getString(R.string.string_int_format), activity.getString(R.string.best_score), statistic.getBestScr(boxSize)));
    }

    // saves current array
    void saveArray() {
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                str.append(array[i][j]).append(",");
            }
        }
        myEditor.putString("strArray" + boxSize, str.toString());
        myEditor.putInt("savedArray", boxSize);
        myEditor.apply();
    }

    //sets saved array
    private void setArray() {
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);

        String savedString = myPreferences.getString("strArray" + boxSize, "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    //saves prev array
    void setPrevArray() {
        if (anim.isPrev()) {
            for (int i = 0; i < boxSize; i++) {
                for (int j = 0; j < boxSize; j++) {
                    array[i][j] = anim.isPrevArray(i, j);
                }
            }
            printArray();
        }
    }

    //restarts current game level
    void restartGame() {
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                array[i][j] = 0;
            }
        }
        addNewNumber();
        addNewNumber();
        printArray();
        calcScore();
        bonusesInfo.setBonusUseCount(5);
        //anim = new Anim(boxSize, array, textArray);
        activity.findViewById(R.id.textDesc).setVisibility(View.INVISIBLE);
    }
}