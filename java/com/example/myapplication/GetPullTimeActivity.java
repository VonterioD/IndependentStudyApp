package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GetPullTimeActivity extends AppCompatActivity implements View.OnClickListener {


    /*
     *  Copyright 2006 Corey Goldberg (cgoldberg _at_ gmail.com)
     *
     *  This file is part of NetPlot.
     *
     *  NetPlot is free software; you can redistribute it and/or modify
     *  it under the terms of the GNU General Public License as published by
     *  the Free Software Foundation; either version 2 of the License, or
     *  (at your option) any later version.
     *
     *  NetPlot is distributed in the hope that it will be useful,
     *  but without any warranty; without even the implied warranty of
     *  merchantability or fitness for a particular purpose.  See the
     *  GNU General Public License for more details.
     */
    public class Stopwatch {

        private long startTime = 0;
        private long stopTime = 0;
        private boolean running = false;


        public void start() {
            this.startTime = System.currentTimeMillis();
            this.running = true;
        }


        public void stop() {
            this.stopTime = System.currentTimeMillis();
            this.running = false;
        }


        // elaspsed time in milliseconds
        public long getElapsedTime() {
            if (running) {
                return System.currentTimeMillis() - startTime;
            }
            return stopTime - startTime;
        }


        // elaspsed time in seconds
        public long getElapsedTimeSecs() {
            if (running) {
                return ((System.currentTimeMillis() - startTime) / 1000);
            }
            return ((stopTime - startTime) / 1000);
        }

    }


    private TextView myTime;
    private Button caught;
    private Button caughtNoAirTime;
    private Button OB;
    private Button cancelPull;
    private Button startTime;
    private Stopwatch timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_pull_time);

        myTime = (TextView) findViewById(R.id.pullTime);
        caught = (Button) findViewById(R.id.caughtLanded);
        caughtNoAirTime = (Button) findViewById(R.id.noHangTime);
        OB = (Button) findViewById(R.id.OB);
        cancelPull = (Button) findViewById(R.id.cancel);
        timer = new Stopwatch();

        caught.setOnClickListener(this);
        caughtNoAirTime.setOnClickListener(this);
        OB.setOnClickListener(this);
        cancelPull.setOnClickListener(this);

        timer.start();

    }

    @Override
    public void onClick(View v) {

        Intent pullData = new Intent(GetPullTimeActivity.this, PlayGameActivity.class);
        String playerData = getIntent().getStringExtra("playerValue");
        pullData.putExtra("playerValue",  playerData);

        switch (v.getId()){
            case R.id.caughtLanded:

                //Stop timer and make textview show time
                timer.stop();
                myTime.setVisibility(View.VISIBLE);
                long time = timer.getElapsedTimeSecs();
                myTime.setText(time + "s");

                //Send time back to play game screen
                pullData.putExtra("pullTime", time);
                setResult(RESULT_OK, pullData);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;

            case R.id.noHangTime:

                //0 time in air, send back 0
                timer.stop();
                myTime.setVisibility(View.VISIBLE);
                myTime.setText(0 + "s");

                pullData.putExtra("pullTime", 0);
                setResult(RESULT_OK, pullData);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;

            case R.id.OB:

                timer.stop();
                time = timer.getElapsedTimeSecs();
                myTime.setVisibility(View.VISIBLE);
                myTime.setText(time + "s");

                pullData.putExtra("pullTime", time);
                setResult(RESULT_OK, pullData);

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                //finish();
                break;

            case R.id.cancel:

                //Cancel pull
                timer.stop();
                setResult(RESULT_CANCELED, pullData);
                finish();
                break;

            default:
                break;
        }

    }
}



