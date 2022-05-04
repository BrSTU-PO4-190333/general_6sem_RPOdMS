package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ImageView[][] iv = new ImageView[4][4];
    private final Integer[] cardsArray = {11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28};
    private int[][] image = new int[2][8];

    private int firstCard, secondCard;
    private int clickedFirst, clickedSecond;

    private int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv[0][0] = (ImageView) findViewById(R.id.iv_11);
        iv[0][1] = (ImageView) findViewById(R.id.iv_12);
        iv[0][2] = (ImageView) findViewById(R.id.iv_13);
        iv[0][3] = (ImageView) findViewById(R.id.iv_14);

        iv[1][0] = (ImageView) findViewById(R.id.iv_21);
        iv[1][1] = (ImageView) findViewById(R.id.iv_22);
        iv[1][2] = (ImageView) findViewById(R.id.iv_23);
        iv[1][3] = (ImageView) findViewById(R.id.iv_24);

        iv[2][0] = (ImageView) findViewById(R.id.iv_31);
        iv[2][1] = (ImageView) findViewById(R.id.iv_32);
        iv[2][2] = (ImageView) findViewById(R.id.iv_33);
        iv[2][3] = (ImageView) findViewById(R.id.iv_34);

        iv[3][0] = (ImageView) findViewById(R.id.iv_41);
        iv[3][1] = (ImageView) findViewById(R.id.iv_42);
        iv[3][2] = (ImageView) findViewById(R.id.iv_43);
        iv[3][3] = (ImageView) findViewById(R.id.iv_44);

        iv[0][0].setTag("0");
        iv[0][1].setTag("1");
        iv[0][2].setTag("2");
        iv[0][3].setTag("3");

        iv[1][0].setTag("4");
        iv[1][1].setTag("5");
        iv[1][2].setTag("6");
        iv[1][3].setTag("7");

        iv[2][0].setTag("8");
        iv[2][1].setTag("9");
        iv[2][2].setTag("10");
        iv[2][3].setTag("11");

        iv[3][0].setTag("12");
        iv[3][1].setTag("13");
        iv[3][2].setTag("14");
        iv[3][3].setTag("15");

        frontOfCardResourses();

        Collections.shuffle(Arrays.asList(cardsArray));

        iv[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[0][0], theCard);
            }
        });

        iv[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[0][1], theCard);
            }
        });

        iv[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[0][2], theCard);
            }
        });

        iv[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[0][3], theCard);
            }
        });

        iv[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[1][0], theCard);
            }
        });

        iv[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[1][1], theCard);
            }
        });

        iv[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[1][2], theCard);
            }
        });

        iv[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[1][3], theCard);
            }
        });

        iv[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[2][0], theCard);
            }
        });

        iv[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[2][1], theCard);
            }
        });

        iv[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[2][2], theCard);
            }
        });

        iv[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[2][3], theCard);
            }
        });

        iv[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[3][0], theCard);
            }
        });

        iv[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[3][1], theCard);
            }
        });

        iv[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[3][2], theCard);
            }
        });

        iv[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String)view.getTag());
                doStuff(iv[3][3], theCard);
            }
        });

    }

    private void frontOfCardResourses(){
        image[0][0] = R.drawable.black_bishop;
        image[0][1] = R.drawable.white_king;
        image[0][2] = R.drawable.black_knite;
        image[0][3] = R.drawable.white_pawn;
        image[0][4] = R.drawable.black_queen;
        image[0][5] = R.drawable.white_rook;
        image[0][6] = R.drawable.black_king;
        image[0][7] = R.drawable.white_queen;

        image[1][0] = R.drawable.black_bishop;
        image[1][1] = R.drawable.white_king;
        image[1][2] = R.drawable.black_knite;
        image[1][3] = R.drawable.white_pawn;
        image[1][4] = R.drawable.black_queen;
        image[1][5] = R.drawable.white_rook;
        image[1][6] = R.drawable.black_king;
        image[1][7] = R.drawable.white_queen;
    }

    private void doStuff(ImageView iv, int card){
        switch (cardsArray[card]){
            case 11:
                iv.setImageResource(image[0][0]);
                break;
            case 12:
                iv.setImageResource(image[0][1]);
                break;
            case 13:
                iv.setImageResource(image[0][2]);
                break;
            case 14:
                iv.setImageResource(image[0][3]);
                break;
            case 15:
                iv.setImageResource(image[0][4]);
                break;
            case 16:
                iv.setImageResource(image[0][5]);
                break;
            case 17:
                iv.setImageResource(image[0][6]);
                break;
            case 18:
                iv.setImageResource(image[0][7]);
                break;
            case 21:
                iv.setImageResource(image[1][0]);
                break;
            case 22:
                iv.setImageResource(image[1][1]);
                break;
            case 23:
                iv.setImageResource(image[1][2]);
                break;
            case 24:
                iv.setImageResource(image[1][3]);
                break;
            case 25:
                iv.setImageResource(image[1][4]);
                break;
            case 26:
                iv.setImageResource(image[1][5]);
                break;
            case 27:
                iv.setImageResource(image[1][6]);
                break;
            case 28:
                iv.setImageResource(image[1][7]);
                break;
        }

        switch(cardNumber){
            case 1:
                firstCard = cardsArray[card];
                if(firstCard > 20){
                    firstCard -= 10;
                }

                cardNumber = 2;
                clickedFirst = card;

                iv.setEnabled(false);
                break;
            case 2:
                secondCard = cardsArray[card];

                if(secondCard > 20){
                    secondCard -= 10;
                }

                cardNumber = 1;
                clickedSecond = card;

                for(int i=0; i < 4; i++)
                    for(int k=0; k < 4; k++)
                        this.iv[i][k].setEnabled(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                    }
                }, 1000);
                break;
        }
    }

    private void calculate(){
        if(firstCard == secondCard){
            switch(clickedFirst){
                case 0:
                    this.iv[0][0].setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    this.iv[0][1].setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    this.iv[0][2].setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    this.iv[0][3].setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    this.iv[1][0].setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    this.iv[1][1].setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    this.iv[1][2].setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    this.iv[1][3].setVisibility(View.INVISIBLE);
                    break;
                case 8:
                    this.iv[2][0].setVisibility(View.INVISIBLE);
                    break;
                case 9:
                    this.iv[2][1].setVisibility(View.INVISIBLE);
                    break;
                case 10:
                    this.iv[2][2].setVisibility(View.INVISIBLE);
                    break;
                case 11:
                    this.iv[2][3].setVisibility(View.INVISIBLE);
                    break;
                case 12:
                    this.iv[3][0].setVisibility(View.INVISIBLE);
                    break;
                case 13:
                    this.iv[3][1].setVisibility(View.INVISIBLE);
                    break;
                case 14:
                    this.iv[3][2].setVisibility(View.INVISIBLE);
                    break;
                case 15:
                    this.iv[3][3].setVisibility(View.INVISIBLE);
                    break;
            }

            switch(clickedSecond){
                case 0:
                    this.iv[0][0].setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    this.iv[0][1].setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    this.iv[0][2].setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    this.iv[0][3].setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    this.iv[1][0].setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    this.iv[1][1].setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    this.iv[1][2].setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    this.iv[1][3].setVisibility(View.INVISIBLE);
                    break;
                case 8:
                    this.iv[2][0].setVisibility(View.INVISIBLE);
                    break;
                case 9:
                    this.iv[2][1].setVisibility(View.INVISIBLE);
                    break;
                case 10:
                    this.iv[2][2].setVisibility(View.INVISIBLE);
                    break;
                case 11:
                    this.iv[2][3].setVisibility(View.INVISIBLE);
                    break;
                case 12:
                    this.iv[3][0].setVisibility(View.INVISIBLE);
                    break;
                case 13:
                    this.iv[3][1].setVisibility(View.INVISIBLE);
                    break;
                case 14:
                    this.iv[3][2].setVisibility(View.INVISIBLE);
                    break;
                case 15:
                    this.iv[3][3].setVisibility(View.INVISIBLE);
                    break;
            }
        } else {
            for(int i=0; i < 4; i++)
                for(int k=0; k < 4; k++)
                    this.iv[i][k].setImageResource(R.drawable.denchess);
        }

        for(int i=0; i < 4; i++)
            for(int k=0; k < 4; k++)
                this.iv[i][k].setEnabled(true);

        checkEnd();
    }

    private void checkEnd(){
        if(this.iv[0][0].getVisibility() == View.INVISIBLE &&
                this.iv[0][1].getVisibility() == View.INVISIBLE &&
                this.iv[0][2].getVisibility() == View.INVISIBLE &&
                this.iv[0][3].getVisibility() == View.INVISIBLE &&
                this.iv[1][0].getVisibility() == View.INVISIBLE &&
                this.iv[1][1].getVisibility() == View.INVISIBLE &&
                this.iv[1][2].getVisibility() == View.INVISIBLE &&
                this.iv[1][3].getVisibility() == View.INVISIBLE &&
                this.iv[2][0].getVisibility() == View.INVISIBLE &&
                this.iv[2][1].getVisibility() == View.INVISIBLE &&
                this.iv[2][2].getVisibility() == View.INVISIBLE &&
                this.iv[2][3].getVisibility() == View.INVISIBLE &&
                this.iv[3][0].getVisibility() == View.INVISIBLE &&
                this.iv[3][1].getVisibility() == View.INVISIBLE &&
                this.iv[3][2].getVisibility() == View.INVISIBLE &&
                this.iv[3][3].getVisibility() == View.INVISIBLE){

            for(int i=0; i < 4; i++)
                for(int k=0; k < 4; k++){
                    this.iv[i][k].setVisibility(View.VISIBLE);
                    this.iv[i][k].setImageResource(R.drawable.denchess);
                }

            Collections.shuffle(Arrays.asList(cardsArray));
        }
    }

    public void restart(View view) {
        for(int i=0; i < 4; i++)
            for(int k=0; k < 4; k++){
                this.iv[i][k].setVisibility(View.VISIBLE);
                this.iv[i][k].setImageResource(R.drawable.denchess);
            }

        Collections.shuffle(Arrays.asList(cardsArray));
    }
}