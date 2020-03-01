package com.juicyteam.a2048app.mainGameWindow;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.juicyteam.a2048app.Enum.Bonus;
import com.juicyteam.a2048app.R;

class BonusesInfo {
    private gameWindow activity;
    private int[][] array;
    private Statistic statistic;
    private int deleteCount;
    private int doubleCount;
    private int positionCount;
    private int idPosition = 0;
    private int bonusUseCount = 5;
    private int[] arrayPosition = new int[4];
    private Bonus bonus = Bonus.NONE;

    BonusesInfo(gameWindow activity,int[][] array, Statistic statistic){
        this.activity = activity;
        this.statistic = statistic;
        this.array = array;


        deleteCount = statistic.getDeleteCount();
        doubleCount = statistic.getDoubleCount();
        positionCount = statistic.getPositionCount();
    }

    void checkBonus(int x, int y){
        switch (bonus){
            case DOUBLE:
                deleteAndDouble(x, y, false);
                break;
            case DELETE:
                deleteAndDouble(x, y, true);
                break;
            case POSITION:
                if(array[x][y] != 0) {
                    changeSectorPosition(x, y);
                }
                break;
        }
    }

    private void deleteAndDouble(int x, int y, boolean delete) {
        if(array[x][y] != 0) {
            if(delete)
                deleteSector(x, y);
            else
                doubleSector(x, y);
            bonus = Bonus.NONE;
            activity.findViewById(R.id.swipeReader).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.linearDelete).setBackgroundResource(R.drawable.bonus_style);
            activity.findViewById(R.id.linearDouble).setBackgroundResource(R.drawable.bonus_style);
            ((TextView) activity.findViewById(R.id.textDeleteCount)).setText(String.valueOf(deleteCount));
            ((TextView) activity.findViewById(R.id.textDoubleCount)).setText(String.valueOf(doubleCount));
            activity.findViewById(R.id.textDesc).setVisibility(View.INVISIBLE);
        }

    }

    void setBonus(Bonus bonus){
        this.bonus = bonus;
    }

    Bonus getBonus(){
        return bonus;
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
        deleteCount -= 1;
        bonusUseCount -= 1;
        statistic.setDeleteCount(deleteCount);
    }

    private void doubleSector(int x, int y){ // double sector with max number
        array[x][y] *= 2;
        doubleCount -=1;
        bonusUseCount -= 1;
        statistic.setDoubleCount(doubleCount);
    }

    private void changeSectorPosition(int x, int y){ // change position of random number
        idPosition++;
        switch(idPosition){
            case 1:
                arrayPosition[0] = x;
                arrayPosition[1] = y;
                break;
            case 2:
                positionCount -= 1;
                bonusUseCount -= 1;
                statistic.setPositionCount(positionCount);
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

    int getBonusUseCount() {
        return bonusUseCount;
    }

    void setBonusUseCount(int temp){
        bonusUseCount = temp;
    }

    void saveBonusUseCount(int boxesSize){
         SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
         SharedPreferences.Editor myEditor = myPreferences.edit();

         myEditor.putInt("bonusesCount" + boxesSize, bonusUseCount);

         myEditor.apply();
    }

    void setBonusUseCountFromSave(int boxesSize){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        bonusUseCount = myPreferences.getInt("bonusesCount" + boxesSize, 5);
    }
}
