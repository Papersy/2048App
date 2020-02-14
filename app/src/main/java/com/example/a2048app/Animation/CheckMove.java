package com.example.a2048app.Animation;

import android.widget.TextView;

class CheckMove {
    private int boxSize;
    private int[][] array;


    CheckMove(int boxSize, int[][] array){
        this.boxSize = boxSize;
        this.array = array;
    }


    boolean isMove(){
        boolean left = false, right = false, top = false, bot = false;
        for(int i = 0; i < boxSize; i++){ //right
            for(int g = boxSize - 1; g > 0; g--){
                for(int j = g - 1; j >= 0; j--){
                    if((array[i][j] != 0 || array[i][j] == 0) && (array[i][g] == array[i][j] || array[i][g] == 0)){
                        right = true;
                        break;
                    }
                    else if(array[i][j] != array[i][g] && array[i][j] != 0)
                        break;
                }
            }
        }

        for(int i = 0; i < boxSize; i++){ //left
            for(int g = 0; g < boxSize - 1; g++){
                for(int j = g + 1; j < boxSize; j++){
                    if((array[i][j] != 0 || array[i][j] == 0) && (array[i][g] == array[i][j] || array[i][g] == 0)){
                        left = true;
                        break;
                    }
                    else if(array[i][j] != array[i][g] && array[i][j] != 0)
                        break;
                }
            }
        }

        for(int i = 0; i < boxSize; i++){//bot
            for(int g = boxSize - 1; g > 0; g--){
                for(int j = g - 1; j >= 0; j--){
                    if((array[j][i] != 0 || array[j][i] == 0) && (array[g][i] == array[j][i] || array[g][i] == 0)){
                        bot = true;
                        break;
                    }
                    else if(array[j][i] != array[g][i] && array[j][i] != 0)
                        break;
                }
            }
        }

        for(int i = 0; i < boxSize; i++){//top
            for(int g = 0; g < boxSize; g++){
                for(int j = g + 1; j < boxSize; j++){
                    if((array[j][i] != 0 || array[j][i] == 0) && (array[g][i] == array[j][i] || array[g][i] == 0)){
                        top = true;
                        break;
                    }
                    else if(array[j][i] != array[g][i] && array[j][i] != 0)
                        break;
                }
            }
        }

        return left && right && bot && top;
    }
}
