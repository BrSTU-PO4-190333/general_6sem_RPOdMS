package com.example.memorygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_p1, tv_p2;
    ImageView iv_11, iv_12, iv_13, iv_14,
              iv_21, iv_22, iv_23, iv_24,
              iv_31, iv_32, iv_33, iv_34,
              iv_41, iv_42, iv_43, iv_44;

    //array for the images
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 107, 108,
                            201, 202, 203, 204, 205, 206, 207, 208};

    //actual images
    int image101, image102, image103, image104, image105, image106, image107, image108,
        image201, image202, image203, image204, image205, image206, image207, image208;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0, cpuPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_p1 = (TextView) findViewById(R.id.tv_p1);
        tv_p2 = (TextView) findViewById(R.id.tv_p2);

        iv_11 = (ImageView) findViewById(R.id.iv11);
        iv_12 = (ImageView) findViewById(R.id.iv12);
        iv_13 = (ImageView) findViewById(R.id.iv13);
        iv_14 = (ImageView) findViewById(R.id.iv14);
        iv_11.setOnClickListener(this);
        iv_12.setOnClickListener(this);
        iv_13.setOnClickListener(this);
        iv_14.setOnClickListener(this);

        iv_21 = (ImageView) findViewById(R.id.iv21);
        iv_22 = (ImageView) findViewById(R.id.iv22);
        iv_23 = (ImageView) findViewById(R.id.iv23);
        iv_24 = (ImageView) findViewById(R.id.iv24);
        iv_21.setOnClickListener(this);
        iv_22.setOnClickListener(this);
        iv_23.setOnClickListener(this);
        iv_24.setOnClickListener(this);

        iv_31 = (ImageView) findViewById(R.id.iv31);
        iv_32 = (ImageView) findViewById(R.id.iv32);
        iv_33 = (ImageView) findViewById(R.id.iv33);
        iv_34 = (ImageView) findViewById(R.id.iv34);
        iv_31.setOnClickListener(this);
        iv_32.setOnClickListener(this);
        iv_33.setOnClickListener(this);
        iv_34.setOnClickListener(this);

        iv_41 = (ImageView) findViewById(R.id.iv41);
        iv_42 = (ImageView) findViewById(R.id.iv42);
        iv_43 = (ImageView) findViewById(R.id.iv43);
        iv_44 = (ImageView) findViewById(R.id.iv44);
        iv_41.setOnClickListener(this);
        iv_42.setOnClickListener(this);
        iv_43.setOnClickListener(this);
        iv_44.setOnClickListener(this);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");

        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");

        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");

        iv_41.setTag("12");
        iv_42.setTag("13");
        iv_43.setTag("14");
        iv_44.setTag("15");

        //load the cards images
        fontsOfCardsResources();

        //shuffle the images
        Collections.shuffle(Arrays.asList(cardsArray));

        //inactive second player
        tv_p2.setTextColor(Color.GRAY);
    }

    private void doStuff(ImageView iv, int card) {
        //set the correct image to the imageview
        switch(cardsArray[card]) {
            case 101:
                iv.setImageResource(image101);
                break;
            case 102:
                iv.setImageResource(image102);
                break;
            case 103:
                iv.setImageResource(image103);
                break;
            case 104:
                iv.setImageResource(image104);
                break;
            case 105:
                iv.setImageResource(image105);
                break;
            case 106:
                iv.setImageResource(image106);
                break;
            case 107:
                iv.setImageResource(image107);
                break;
            case 108:
                iv.setImageResource(image108);
                break;
            case 201:
                iv.setImageResource(image201);
                break;
            case 202:
                iv.setImageResource(image202);
                break;
            case 203:
                iv.setImageResource(image203);
                break;
            case 204:
                iv.setImageResource(image204);
                break;
            case 205:
                iv.setImageResource(image205);
                break;
            case 206:
                iv.setImageResource(image206);
                break;
            case 207:
                iv.setImageResource(image207);
                break;
            case 208:
                iv.setImageResource(image208);
                break;
        }

        //check which image is selected and save it to temporary var
        if(cardNumber == 1) {
            firstCard = cardsArray[card];
            if(firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        } else if(cardNumber == 2) {
            secondCard = cardsArray[card];
            if(secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);

            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);

            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            iv_41.setEnabled(false);
            iv_42.setEnabled(false);
            iv_43.setEnabled(false);
            iv_44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if the selected images are equals
                    calculate();
                }
            }, 1000);
        }
    }


    private void calculate() {
        //if images are equal remove them
        if(firstCard == secondCard) {
           switch(clickedFirst) {
               case 0:
                   iv_11.setVisibility(View.INVISIBLE);
                   break;
               case 1:
                   iv_12.setVisibility(View.INVISIBLE);
                   break;
               case 2:
                   iv_13.setVisibility(View.INVISIBLE);
                   break;
               case 3:
                   iv_14.setVisibility(View.INVISIBLE);
                   break;
               case 4:
                   iv_21.setVisibility(View.INVISIBLE);
                   break;
               case 5:
                   iv_22.setVisibility(View.INVISIBLE);
                   break;
               case 6:
                   iv_23.setVisibility(View.INVISIBLE);
                   break;
               case 7:
                   iv_24.setVisibility(View.INVISIBLE);
                   break;
               case 8:
                   iv_31.setVisibility(View.INVISIBLE);
                   break;
               case 9:
                   iv_32.setVisibility(View.INVISIBLE);
                   break;
               case 10:
                   iv_33.setVisibility(View.INVISIBLE);
                   break;
               case 11:
                   iv_34.setVisibility(View.INVISIBLE);
                   break;
               case 12:
                   iv_41.setVisibility(View.INVISIBLE);
                   break;
               case 13:
                   iv_42.setVisibility(View.INVISIBLE);
                   break;
               case 14:
                   iv_43.setVisibility(View.INVISIBLE);
                   break;
               case 15:
                   iv_44.setVisibility(View.INVISIBLE);
                   break;
           }

            switch(clickedSecond) {
                case 0:
                    iv_11.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    iv_12.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    iv_13.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    iv_14.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    iv_21.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    iv_22.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    iv_23.setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    iv_24.setVisibility(View.INVISIBLE);
                    break;
                case 8:
                    iv_31.setVisibility(View.INVISIBLE);
                    break;
                case 9:
                    iv_32.setVisibility(View.INVISIBLE);
                    break;
                case 10:
                    iv_33.setVisibility(View.INVISIBLE);
                    break;
                case 11:
                    iv_34.setVisibility(View.INVISIBLE);
                    break;
                case 12:
                    iv_41.setVisibility(View.INVISIBLE);
                    break;
                case 13:
                    iv_42.setVisibility(View.INVISIBLE);
                    break;
                case 14:
                    iv_43.setVisibility(View.INVISIBLE);
                    break;
                case 15:
                    iv_44.setVisibility(View.INVISIBLE);
                    break;
            }

            //add points to the correct player
            if (turn == 1) {
                playerPoints++;
                tv_p1.setText("P1: " + playerPoints);
            } else if (turn == 2) {
                cpuPoints++;
                tv_p2.setText("P2: " + cpuPoints);
            }
        } else {
            iv_11.setImageResource(R.drawable.questionicon);
            iv_12.setImageResource(R.drawable.questionicon);
            iv_13.setImageResource(R.drawable.questionicon);
            iv_14.setImageResource(R.drawable.questionicon);

            iv_21.setImageResource(R.drawable.questionicon);
            iv_22.setImageResource(R.drawable.questionicon);
            iv_23.setImageResource(R.drawable.questionicon);
            iv_24.setImageResource(R.drawable.questionicon);

            iv_31.setImageResource(R.drawable.questionicon);
            iv_32.setImageResource(R.drawable.questionicon);
            iv_33.setImageResource(R.drawable.questionicon);
            iv_34.setImageResource(R.drawable.questionicon);

            iv_41.setImageResource(R.drawable.questionicon);
            iv_42.setImageResource(R.drawable.questionicon);
            iv_43.setImageResource(R.drawable.questionicon);
            iv_44.setImageResource(R.drawable.questionicon);

            //change the player turn
            if (turn == 1) {
                turn = 2;
                tv_p1.setTextColor(Color.GRAY);
                tv_p2.setTextColor(Color.BLACK);
            } else {
                turn = 1;
                tv_p2.setTextColor(Color.GRAY);
                tv_p1.setTextColor(Color.BLACK);
            }
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);

        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);

        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        iv_41.setEnabled(true);
        iv_42.setEnabled(true);
        iv_43.setEnabled(true);
        iv_44.setEnabled(true);

        //check if the game is over
        checkEnd();
    }

    private void checkEnd() {
        if (iv_11.getVisibility() == View.INVISIBLE &&
            iv_12.getVisibility() == View.INVISIBLE &&
            iv_13.getVisibility() == View.INVISIBLE &&
            iv_14.getVisibility() == View.INVISIBLE &&
            iv_21.getVisibility() == View.INVISIBLE &&
            iv_22.getVisibility() == View.INVISIBLE &&
            iv_23.getVisibility() == View.INVISIBLE &&
            iv_24.getVisibility() == View.INVISIBLE &&
            iv_31.getVisibility() == View.INVISIBLE &&
            iv_32.getVisibility() == View.INVISIBLE &&
            iv_33.getVisibility() == View.INVISIBLE &&
            iv_34.getVisibility() == View.INVISIBLE &&
            iv_41.getVisibility() == View.INVISIBLE &&
            iv_42.getVisibility() == View.INVISIBLE &&
            iv_43.getVisibility() == View.INVISIBLE &&
            iv_44.getVisibility() == View.INVISIBLE ) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder
                    .setMessage("GAME OVER!\nP1: " + playerPoints + "\nP2: " + cpuPoints)
                    .setCancelable(false)
                    .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

    private void fontsOfCardsResources() {
        image101 = R.drawable.icon1_1;
        image102 = R.drawable.icon2_1;
        image103 = R.drawable.icon3_1;
        image104 = R.drawable.icon4_1;
        image105 = R.drawable.icon5_1;
        image106 = R.drawable.icon6_1;
        image107 = R.drawable.icon7_1;
        image108 = R.drawable.icon8_1;

        image201 = R.drawable.icon1_2;
        image202 = R.drawable.icon2_2;
        image203 = R.drawable.icon3_2;
        image204 = R.drawable.icon4_2;
        image205 = R.drawable.icon5_2;
        image206 = R.drawable.icon6_2;
        image207 = R.drawable.icon7_2;
        image208 = R.drawable.icon8_2;
    }

    @Override
    public void onClick(View view) {
        int theCard;
        switch (view.getId()) {
            case R.id.iv11:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_11, theCard);
                break;
            case R.id.iv12:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_12, theCard);
                break;
            case R.id.iv13:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_13, theCard);
                break;
            case R.id.iv14:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_14, theCard);
                break;
            case R.id.iv21:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_21, theCard);
                break;
            case R.id.iv22:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_22, theCard);
                break;
            case R.id.iv23:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_23, theCard);
                break;
            case R.id.iv24:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_24, theCard);
                break;
            case R.id.iv31:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_31, theCard);
                break;
            case R.id.iv32:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_32, theCard);
                break;
            case R.id.iv33:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_33, theCard);
                break;
            case R.id.iv34:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_34, theCard);
                break;
            case R.id.iv41:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_41, theCard);
                break;
            case R.id.iv42:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_42, theCard);
                break;
            case R.id.iv43:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_43, theCard);
                break;
            case R.id.iv44:
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_44, theCard);
                break;
        }
    }
}