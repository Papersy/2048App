package com.example.a2048app.Animation;

import android.view.View;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;

public class Anim {
    private int animTransition;
    private int boxSize;
    private int[][] array, prevArray;
    private boolean isMove = true, spawnNew = false, isPrev = false;

    private ArrayList textArray;


    public Anim(int boxSize, int[][] array, ArrayList textArray){
        this.boxSize = boxSize;
        this.array = array;
        this.textArray = textArray;

        animTransition = 800/boxSize + 10;
        prevArray = new int[boxSize][boxSize];
    }

    public void animFunc(int index){
        TranslateAnimation translateAnimation;

        isMove = new CheckMove(boxSize, array).isMove();
        if(isMove)
            savePrevArray();

        switch(index){
            case 1: //top
                for(int i = 0; i < boxSize; i++){
                    for(int g = 0; g < boxSize; g++){
                        for(int j = g + 1; j < boxSize; j++){
                            if(array[j][i] != 0 && (array[g][i] == array[j][i] || array[g][i] == 0)){
                                spawnNew = true;
                                int temp = getVerticalIndex(i, j);

                                View view = (View) textArray.get(temp);
                                translateAnimation = new TranslateAnimation(0, 0, 0, (g - j) * animTransition);
                                translateAnimation.setDuration(320);
                                view.startAnimation(translateAnimation);

                                array[g][i] += array[j][i];
                                array[j][i] = 0;
                            }
                            else if(array[j][i] != array[g][i] && array[j][i] != 0)
                                break;
                        }
                    }
                }
                break;
            case 2: //bot
                for(int i = 0; i < boxSize; i++){
                    for(int g = boxSize - 1; g > 0; g--){
                        for(int j = g - 1; j >= 0; j--){
                            if(array[j][i] != 0 && (array[g][i] == array[j][i] || array[g][i] == 0)){
                                spawnNew = true;
                                int temp = getVerticalIndex(i, j);

                                View view = (View) textArray.get(temp);
                                translateAnimation = new TranslateAnimation(0, 0, 0, (g - j) * animTransition);
                                translateAnimation.setDuration(320);
                                view.startAnimation(translateAnimation);

                                array[g][i] += array[j][i];
                                array[j][i] = 0;
                            }
                            else if(array[j][i] != array[g][i] && array[j][i] != 0)
                                break;
                        }
                    }
                }
                break;
            case 3: //left
                for(int i = 0; i < boxSize; i++){
                    for(int g = 0; g < boxSize - 1; g++){
                        for(int j = g + 1; j < boxSize; j++){
                            if(array[i][j] != 0 && (array[i][g] == array[i][j] || array[i][g] == 0)){
                                spawnNew = true;
                                int temp = getHorizontalIndex(i, j);

                                View view = (View) textArray.get(temp);
                                translateAnimation = new TranslateAnimation(0, (j - g) * -1 * animTransition, 0, 0);
                                translateAnimation.setDuration(320);
                                view.startAnimation(translateAnimation);

                                array[i][g] += array[i][j];
                                array[i][j] = 0;
                            }
                            else if(array[i][j] != array[i][g] && array[i][j] != 0)
                                break;
                        }
                    }
                }
                break;
            case 4: //right
                for(int i = 0; i < boxSize; i++){
                    for(int g = boxSize - 1; g > 0; g--){
                        for(int j = g - 1; j >= 0; j--){
                            if(array[i][j] != 0 && (array[i][g] == array[i][j] || array[i][g] == 0)){
                                spawnNew = true;
                                int temp = getHorizontalIndex(i, j);

                                View view = (View) textArray.get(temp);
                                translateAnimation = new TranslateAnimation(0, (g - j) * animTransition, 0, 0);
                                translateAnimation.setDuration(320);
                                view.startAnimation(translateAnimation);

                                array[i][g] += array[i][j];
                                array[i][j] = 0;
                            }
                            else if(array[i][j] != array[i][g] && array[i][j] != 0)
                                break;
                        }
                    }
                }
                break;
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

    public boolean isMove(){
        return isMove;
    }

    public boolean isSpawnNew(){
        return spawnNew;
    }

    public void setSpawnNew(boolean spawnNew){
        this.spawnNew = spawnNew;
    }
}
