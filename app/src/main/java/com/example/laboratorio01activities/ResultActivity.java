package com.example.laboratorio01activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView edtResult;

    private Double priceFirstGame;
    private Double discountPrice;
    private Double lowerPrice;
    private Double budget;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();

        initVariables();

        calculatePriceGames();

        edtResult.setText(result);
    }

    private void initView() {
        edtResult = (TextView)findViewById(R.id.edtResult);
    }

    private void initVariables() {
        Intent intent = getIntent();
        priceFirstGame = intent.getDoubleExtra(Config.PRICE_FIRST_GAME,0);
        discountPrice = intent.getDoubleExtra(Config.DISCOUNT_PRICE,0);
        lowerPrice = intent.getDoubleExtra(Config.LOWER_PRICE,0);
        budget = intent.getDoubleExtra(Config.BUDGET,0);
    }

    private void calculatePriceGames() {
        result = getResources().getString(R.string.resultPart1);
        double aux = 0;
        for (int i = 0; i< 10; i++){
            if (priceFirstGame - (discountPrice*i) >= lowerPrice){
                aux = priceFirstGame - (discountPrice*i);
            } else {
                aux = lowerPrice;
            }

            if (i!=9){
                result += aux + ", ";
            } else {
                result += aux;
            }

        }

        result += getResources().getString(R.string.resultPart2) + budget + getResources().getString(R.string.resultPart3);

        double totalPayment = 0;
        double actualPrice = 0;
        double priceGame = priceFirstGame;
        String pricesGames = "";
        int quantity = 0;
        do {
            totalPayment += priceGame;
            if ((totalPayment + priceGame - discountPrice) <= budget){
                pricesGames += String.valueOf(priceGame) + " + ";
            } else {
                pricesGames += String.valueOf(priceGame);
            }
            priceGame -= discountPrice;
            if(priceGame<=lowerPrice){
                priceGame =lowerPrice;
            }
            quantity ++;

        } while ((totalPayment + priceGame) <= budget);

        result += quantity + getResources().getString(R.string.resultPart4) + pricesGames + getResources().getString(R.string.resultPart5) + totalPayment + getResources().getString(R.string.resultPart6);
    }
}