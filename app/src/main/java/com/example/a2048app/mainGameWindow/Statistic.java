package com.example.a2048app.mainGameWindow;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Statistic {
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    private int bestScore;
    private int deleteCount;
    private int doubleCount;
    private int positionCount;

    public Statistic(Context activity){
        myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        myEditor = myPreferences.edit();

        deleteCount = myPreferences.getInt("deleteCount", 3);
        doubleCount = myPreferences.getInt("doubleCount", 1);
        positionCount = myPreferences.getInt("positionCount", 2);
    }

    public int getBestScore() {
        return bestScore;
    }

    private void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getBestScr(int index){
        switch (index){
            case 4:
                getBestFour();
                break;
            case 5:
                getBestFive();
                break;
            case 6:
                getBestSix();
                break;
            case 7:
                getBestSeven();
                break;
            case 8:
                getBestEight();
                break;
        }
        return bestScore;
    }

    void setBestScr(int index, int count){
        setBestScore(count);
        switch (index){
            case 4:
                setBestFour();
                break;
            case 5:
                setBestFive();
                break;
            case 6:
                setBestSix();
                break;
            case 7:
                setBestSeven();
                break;
            case 8:
                setBestEight();
                break;
        }
    }

    private void getBestFour(){
        setBestScore(myPreferences.getInt("bestScoreFour", 0));
    }

    private void setBestFour(){
        myEditor.putInt("bestScoreFour", bestScore);
        myEditor.apply();
    }

    private void getBestFive(){
        setBestScore(myPreferences.getInt("bestScoreFive", 0));
    }

    private void setBestFive(){
        myEditor.putInt("bestScoreFive", bestScore);
        myEditor.apply();
    }

    private void getBestSix(){
        setBestScore(myPreferences.getInt("bestScoreSix", 0));
    }

    private void setBestSix(){
        myEditor.putInt("bestScoreSix", bestScore);
        myEditor.apply();
    }

    private void getBestSeven(){
        setBestScore(myPreferences.getInt("bestScoreSeven", 0));
    }

    private void setBestSeven(){
        myEditor.putInt("bestScoreSeven", bestScore);
        myEditor.apply();
    }

    private void getBestEight(){
        setBestScore(myPreferences.getInt("bestScoreEight", 0));
    }

    private void setBestEight(){
        myEditor.putInt("bestScoreEight", bestScore);
        myEditor.apply();
    }

    int getDeleteCount() {
        return deleteCount;
    }

    void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
        myEditor.putInt("deleteCount", deleteCount);
        myEditor.apply();
    }

    public void addDeleteCount(int deleteCount){
        this.deleteCount += deleteCount;
        myEditor.putInt("deleteCount", deleteCount);
        myEditor.apply();
    }



    int getDoubleCount() {
        return doubleCount;
    }

    void setDoubleCount(int doubleCount) {
        this.doubleCount = doubleCount;
        myEditor.putInt("doubleCount", doubleCount);
        myEditor.apply();
    }

    public void addDoubleCount(int doubleCount){
        this.doubleCount += doubleCount;
        myEditor.putInt("doubleCount", doubleCount);
        myEditor.apply();
    }



    int getPositionCount() {
        return positionCount;
    }

    void setPositionCount(int positionCount) {
        this.positionCount = positionCount;
        myEditor.putInt("positionCount", positionCount);
        myEditor.apply();
    }

    public void addPositionCount(int positionCount) {
        this.positionCount += positionCount;
        myEditor.putInt("positionCount", positionCount);
        myEditor.apply();
    }
}
