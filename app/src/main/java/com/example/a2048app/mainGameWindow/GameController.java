package com.example.a2048app.mainGameWindow;

public class GameController {
    private int[][] array;
    int x = 0, y = 0;

    public GameController() {
        array = new int[4][4];

        addNewNumber();
        addNewNumber();

    }

    int[][] getArray(){
        return array;
    }

    void addNewNumber(){
        x = (int)(Math.random()*4);
        y = (int)(Math.random()*4);
        while(array[x][y]!=0){
            x = (int)(Math.random()*4);
            y = (int)(Math.random()*4);
        }
        array[x][y] = 32;
    }



    void swipeTop(){
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 3; i++){
                if(array[i][j] == array[i + 1][j] || array[i][j] == 0 ||  array[i + 1][j] == 0) {
                    array[i][j] += array[i + 1][j];
                    array[i + 1][j] = 0;
                }
            }
        }
        addNewNumber();
    }

    void swipeBot(){
        for(int j = 0; j < 4; j++){
            for(int i = 3; i > 0; i--){
                if(array[i][j] == array[i - 1][j] || array[i][j] == 0 || array[i - 1][j] == 0) {
                    array[i][j] += array[i - 1][j];
                    array[i - 1][j] = 0;
                }
            }
        }
        addNewNumber();
    }

    void swipeLeft(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(array[i][j] == array[i][j + 1] || array[i][j] == 0 || array[i][j + 1] == 0) {
                    array[i][j] += array[i][j + 1];
                    array[i][j + 1] = 0;
                }
            }
        }
        addNewNumber();
    }

    void swipeRight(){
        for(int i = 0; i < 4; i++){
            for(int j = 3; j > 0; j--){
                if(array[i][j] == array[i][j - 1] || array[i][j] == 0 || array[i][j - 1] == 0) {
                    array[i][j] += array[i][j - 1];
                    array[i][j - 1] = 0;
                }
            }
        }
        addNewNumber();
    }
}
