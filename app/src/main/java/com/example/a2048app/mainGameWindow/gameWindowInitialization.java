package com.example.a2048app.mainGameWindow;

import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2048app.R;
import com.example.a2048app.onSwipe.onSwipe;

import java.util.ArrayList;

public class gameWindowInitialization {
    private GameController gameController;
    private gameWindow activity;
    private int index = 0;
    private ArrayList<LinearLayout> linearList = new ArrayList<>();
    private ArrayList<TextView> textList = new ArrayList<>();

    gameWindowInitialization(gameWindow activity) {
        this.activity = activity;

        gameController = new GameController();

        createGameBox();
        printArray();
    }

    void Listener(){
        activity.findViewById(R.id.mainLinear).setOnTouchListener(new onSwipe(activity){
            public void onSwipeTop() {
                gameController.swipeTop();
                printArray();
            }

            public void onSwipeRight() {
                gameController.swipeRight();
                printArray();
            }

            public void onSwipeLeft() {
                gameController.swipeLeft();
                printArray();
            }

            public void onSwipeBottom() {
                gameController.swipeBot();
                printArray();
            }
        });
    }


    void createGameBox(){

        LinearLayout mainLinear = activity.findViewById(R.id.mainLinear);
        mainLinear.setOrientation(LinearLayout.VERTICAL);
        //ArrayList<DataCon> arrayList = new ArrayList<>();

        for(int j = 0; j < 4; j++){
            LinearLayout linearLine = new LinearLayout(activity);
            linearLine.setOrientation(LinearLayout.HORIZONTAL);
            linearLine.setWeightSum(j + 1);

            for(int i = 0; i < 4; i++, index++){

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10, 10, 10, 10);



                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                layoutParam.setMargins(30, 30, 30, 30);

                layoutParam.height = 125;
                layoutParam.width = 125;

                LinearLayout linearBox = new LinearLayout(activity);
                TextView textView = new TextView(activity);
                //textView.setTextSize(30);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                }
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);


                linearBox.setBackgroundResource(R.drawable.play_btn_style);


                linearBox.addView(textView, layoutParam);

                linearBox.setId(index);

                linearLine.addView(linearBox, layoutParams);


                linearList.add(linearBox);
                textList.add(textView);

                //arrayList.add(new DataCon(index, i, j));
            }
            mainLinear.addView(linearLine);
        }

    }

    void printArray(){
        int[][] array = gameController.getArray();

        index = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++, index++){
                if(array[i][j] == 0) {
                    textList.get(index).setText(" ");
                }
                else if(array[i][j] >= 128) {
                    //textList.get(index).setTextSize(25);
                    textList.get(index).setGravity(Gravity.CENTER);
                    textList.get(index).setText(String.valueOf(array[i][j]));
                }
                else{
                    //textList.get(index).setTextSize(30);
                    textList.get(index).setGravity(Gravity.CENTER);
                    textList.get(index).setText(String.valueOf(array[i][j]));
                }
            }
        }
    }
}

