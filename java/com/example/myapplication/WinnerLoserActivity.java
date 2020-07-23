package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class WinnerLoserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button finish;
    private TextView winLossText;
    private ConstraintLayout background;
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_loser);

        finish = (Button) findViewById(R.id.finish);
        winLossText = (TextView) findViewById(R.id.winLoseText);
        background = (ConstraintLayout) findViewById(R.id.layout);
        db = new DatabaseHelper(this);

        int ourScore = getIntent().getIntExtra("ourScore",0);
        int oppScore = getIntent().getIntExtra("oppScore",0);
        String opponent = getIntent().getStringExtra("opp");

        finish.setOnClickListener(this);

        if (ourScore > oppScore){
            background.setBackgroundColor(Color.GREEN);
            winLossText.setText("Congrats! You beat " +opponent+ " (" +ourScore+ "-" +oppScore+ ")!");
        }

        else {
            background.setBackgroundColor(Color.RED);
            winLossText.setText("Dang! You lost to " +opponent+ " (" +ourScore+ "-" +oppScore+ ") :(");

        }


    }

    @Override
    public void onClick(View v) {

        Intent newIntent = new Intent(WinnerLoserActivity.this, AddGameActivity.class);
        int ourScore = getIntent().getIntExtra("ourScore",0);
        int oppScore = getIntent().getIntExtra("oppScore",0);
        String opponent = getIntent().getStringExtra("opp");
        String team = getIntent().getStringExtra("team");
        String oOrD = getIntent().getStringExtra("o/d");

        switch (v.getId()){
            case R.id.finish:
                newIntent.putExtra("team", team);
                db.updateGameData(ourScore,oppScore,oOrD,opponent);
                startActivity(newIntent);
                finish();
                break;
        }
    }
}
