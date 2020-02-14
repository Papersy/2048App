package com.example.a2048app.mainGameWindow;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.a2048app.Animation.Anim;
import com.example.a2048app.Enum.Bonus;
import com.example.a2048app.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

class GameController {
    private Anim anim;
    private Statistic statistic;
    private gameWindow activity;
    private int[][] array;
    private int idPosition = 0;
    private int boxSize;
    private int[] arrayPosition = new int[4];
    private int doubleCount = 10;
    private int deleteCount = 10;
    private int positionCount = 10;
    private Bonus bonus;
    private TextView txtScore, txtBestScore;

    private ArrayList textArray;

    GameController(gameWindow activity, ArrayList textArray, int boxSize, Statistic statistic) {
        this.activity = activity;
        this.textArray = textArray;
        this.boxSize = boxSize;
        this.statistic = statistic;



        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        int savedArraySize = myPreferences.getInt("savedArraySize", 0);

        array = new int[boxSize][boxSize];

        anim = new Anim(boxSize, array, textArray);

        if(boxSize == savedArraySize)
            setArray();
        else{
            addNewNumber();
            addNewNumber();
        }

        bonus = Bonus.NONE;

        txtScore = activity.findViewById(R.id.txtScore);
        txtBestScore = activity.findViewById(R.id.txtBestScore);


        printArray();
        calcScore();
    }

    private void addNewNumber(){
        int x = (int) (Math.random() * 4);
        int y = (int) (Math.random() * 4);
        while(array[x][y]!=0){
            x = (int)(Math.random()*4);
            y = (int)(Math.random()*4);
        }
        array[x][y] = 2;
    }

    void swipeTop(){
        if(anim.isMove()) {
            anim.animFunc(1);

            new CountDownTimer(350, 1000){
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    //isMove = true;

                    if(anim.isSpawnNew()) {
                        addNewNumber();
                        calcScore();
                    }
                    anim.setSpawnNew(false);

                    printArray();
                }
            }.start();
        }
        else {
            activity.findViewById(R.id.textDesc).setVisibility(View.VISIBLE);
            ((TextView) activity.findViewById(R.id.textDesc)).setText(activity.getString(R.string.game_over));
        }

    }

    void swipeBot(){
        if(anim.isMove()) {
            anim.animFunc(2);

            new CountDownTimer(350, 1000){
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    if(anim.isSpawnNew()) {
                        addNewNumber();
                        calcScore();
                    }
                    anim.setSpawnNew(false);

                    printArray();
                }
            }.start();
        }
        else {
            activity.findViewById(R.id.textDesc).setVisibility(View.VISIBLE);
            ((TextView) activity.findViewById(R.id.textDesc)).setText(activity.getString(R.string.game_over));
        }
    }

    void swipeLeft(){
        if(anim.isMove()) {
            anim.animFunc(3);

            new CountDownTimer(350, 1000){
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    if(anim.isSpawnNew()) {
                        addNewNumber();
                        calcScore();
                    }
                    anim.setSpawnNew(false);

                    printArray();
                }
            }.start();
        }
        else {
            activity.findViewById(R.id.textDesc).setVisibility(View.VISIBLE);
            ((TextView) activity.findViewById(R.id.textDesc)).setText(activity.getString(R.string.game_over));
        }
    }

    void swipeRight(){
        if(anim.isMove()) {
            anim.animFunc(4);

            new CountDownTimer(350, 1000){
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    if(anim.isSpawnNew()) {
                        addNewNumber();
                        calcScore();
                    }
                    anim.setSpawnNew(false);

                    printArray();
                }
            }.start();
        }
        else {
            activity.findViewById(R.id.textDesc).setVisibility(View.VISIBLE);
            ((TextView) activity.findViewById(R.id.textDesc)).setText(activity.getString(R.string.game_over));
        }
    }

    void checkBonus(int x, int y){
        switch (bonus){
            case DOUBLE:
                if(array[x][y] != 0) {
                    doubleSector(x, y);
                    bonus = Bonus.NONE;
                    activity.findViewById(R.id.swipeReader).setVisibility(View.VISIBLE);
                    activity.findViewById(R.id.linearDouble).setBackgroundResource(R.drawable.bonus_style);
                    ((TextView) activity.findViewById(R.id.textDoubleCount)).setText(String.valueOf(doubleCount));
                    activity.findViewById(R.id.textDesc).setVisibility(View.INVISIBLE);
                }
                break;
            case DELETE:
                if(array[x][y] != 0) {
                    deleteSector(x, y);
                    bonus = Bonus.NONE;
                    activity.findViewById(R.id.swipeReader).setVisibility(View.VISIBLE);
                    activity.findViewById(R.id.linearDelete).setBackgroundResource(R.drawable.bonus_style);
                    ((TextView) activity.findViewById(R.id.textDelteCount)).setText(String.valueOf(deleteCount));
                    activity.findViewById(R.id.textDesc).setVisibility(View.INVISIBLE);
                }
                break;
            case POSITION:
                if(array[x][y] != 0) {
                    changeSectorPosition(x, y);
                }
                break;
        }
        calcScore();
        printArray();
    }

    void setBonus(Bonus bonus){
        this.bonus = bonus;
    }

    Bonus getBonus(){
        return bonus;
    }

    boolean getBonusCount(Bonus bonus){
        switch (bonus){
            case DELETE:
                return deleteCount > 0;
            case DOUBLE:
                return doubleCount > 0;
            case POSITION:
                return positionCount > 0;
        }
        return false;
    }

    int bonusCount(Bonus bonus){
        switch (bonus){
            case DELETE:
                return deleteCount;
            case DOUBLE:
                return doubleCount;
            case POSITION:
                return positionCount;
        }
        return 0;
    }

    private void deleteSector(int x, int y){ // delete sector with min number
        array[x][y] = 0;
        deleteCount -=1;
    }

    private void doubleSector(int x, int y){ // double sector with max number
        array[x][y] *= 2;
        doubleCount -=1;
    }

    private void changeSectorPosition(int x, int y){ // change position of random number
        idPosition++;
           switch(idPosition){
               case 1:
                    arrayPosition[0] = x;
                    arrayPosition[1] = y;
                   break;
               case 2:
                   positionCount -=1;
                   arrayPosition[2] = x;
                   arrayPosition[3] = y;
                   int temp = array[arrayPosition[0]][arrayPosition[1]];
                   array[arrayPosition[0]][arrayPosition[1]] = array[arrayPosition[2]][arrayPosition[3]];
                   array[arrayPosition[2]][arrayPosition[3]] = temp;
                   idPosition = 0;
                   activity.findViewById(R.id.swipeReader).setVisibility(View.VISIBLE);
                   activity.findViewById(R.id.linearPosition).setBackgroundResource(R.drawable.bonus_style);
                   ((TextView) activity.findViewById(R.id.textPositionCount)).setText(String.valueOf(positionCount));
                   activity.findViewById(R.id.textDesc).setVisibility(View.INVISIBLE);
                   bonus = Bonus.NONE;
                   break;
           }
    }

    int getIndex(){
        return idPosition;
    }

    private void printArray(){
        int index = 0;

        for(int i = 0; i < boxSize; i++){
            for(int j = 0; j < boxSize; j++, index++){
                ((TextView)textArray.get(index)).setText(String.valueOf(array[i][j]));
                if(array[i][j] == 0)
                    ((View)textArray.get(index)).setVisibility(View.INVISIBLE);
                else
                    ((View)textArray.get(index)).setVisibility(View.VISIBLE);
            }
        }
    }

    private void calcScore(){
        int temp = 0;
        for(int i = 0; i < boxSize; i++){
            for(int j = 0; j < boxSize; j++){
                temp += array[i][j];
            }
        }

        txtScore.setText(String.format(activity.getString(R.string.string_int_format), activity.getString(R.string.score), temp));
        if(temp > statistic.getBestScr(boxSize)) {
            txtBestScore.setText(String.format(activity.getString(R.string.string_int_format), activity.getString(R.string.best_score), temp));
            statistic.setBestScr(boxSize, temp);
        }
        else
            txtBestScore.setText(String.format(activity.getString(R.string.string_int_format), activity.getString(R.string.best_score), statistic.getBestScr(boxSize)));
    }

    void saveArray(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++){
                str.append(array[i][j]).append(",");
            }
        }
        myEditor.putString("strArray", str.toString());
        myEditor.putInt("savedArraySize", boxSize);
        myEditor.apply();
    }

    private void setArray(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);

        String savedString = myPreferences.getString("strArray", "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void setPrevArray(){
        if(anim.isPrev()) {
            for (int i = 0; i < boxSize; i++) {
                for (int j = 0; j < boxSize; j++) {
                    array[i][j] = anim.isPrevArray(i,j);
                }
            }
            printArray();
        }
    }
    void restartGame(){
        for(int i = 0; i < boxSize; i++){
            for (int j = 0; j < boxSize; j++){
                array[i][j] = 0;
            }
        }
        addNewNumber();
        addNewNumber();
        printArray();
        calcScore();
    }
}