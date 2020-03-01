package com.juicyteam.a2048app.Animation;

public class CheckMove {
    private int boxSize;
    private int[][] array;


    public CheckMove(int boxSize, int[][] array){
        this.boxSize = boxSize;
        this.array = array;
    }


    public boolean isMove(){
        boolean left = false, right = false, top = false, bot = false;
        for(int i = 0; i < boxSize; i++){ //right
            for(int g = boxSize - 1; g > 0; g--){
                if(array[i][g] == 0 || array[i][g - 1] == array[i][g]){
                    right = true;
                    break;
                }
            }
        }

        for(int i = 0; i < boxSize; i++){ //left
            for(int g = 0; g < boxSize - 1; g++){
                if(array[i][g] == 0 || array[i][g + 1] == array[i][g]){
                    left = true;
                    break;
                }
            }
        }

        for(int i = 0; i < boxSize; i++){//bot
            for(int g = boxSize - 1; g > 0; g--){
                if(array[g][i] == 0 || array[g][i] == array[g - 1][i]){
                    bot = true;
                    break;
                }
            }
        }

        for(int i = 0; i < boxSize; i++){//top
            for(int g = 0; g < boxSize - 1; g++){
                if(array[g][i] == 0 || array[g][i] == array[g + 1][i]){
                    top = true;
                    break;
                }
            }
        }

        return left || right || bot || top;
    }
}
