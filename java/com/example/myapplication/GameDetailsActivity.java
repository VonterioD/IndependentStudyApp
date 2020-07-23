package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button goBack;
    private Button makeGameLine;
    private Button stats;
    private Button delete;
    private TextView opponent;
    private TextView tournament;
    private TextView offenseDefense;
    private TextView pointCap;
    private TextView title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_details);

        goBack = (Button) findViewById(R.id.backToGames);
        makeGameLine = (Button) findViewById(R.id.goMakeLine);
        stats = (Button) findViewById(R.id.goSeeGameStats);
        delete = (Button) findViewById(R.id.deleteGame);
        opponent = (TextView) findViewById(R.id.oppDB);
        tournament = (TextView) findViewById(R.id.tourneyDB);
        offenseDefense = (TextView) findViewById(R.id.odDB);
        pointCap = (TextView) findViewById(R.id.pointCapDB);
        title = (TextView) findViewById(R.id.gameTitle);

        goBack.setOnClickListener(this);
        makeGameLine.setOnClickListener(this);
        stats.setOnClickListener(this);
        delete.setOnClickListener(this);

        int ourScore = getIntent().getIntExtra("ourScore",0);
        int oppScore = getIntent().getIntExtra("oppScore",0);


        if (ourScore > oppScore)
            title.setText("(" + ourScore + "-" +oppScore+ ") US");

        else if (ourScore < oppScore){
            title.setText("(" + ourScore + "-" +oppScore+ ") THEM");
            title.setTextColor(Color.RED);
        }
        
        else
            title.setText("(" + ourScore + "-" +oppScore+ ") TIED");

        opponent.setText(getIntent().getStringExtra("opp"));
        tournament.setText(getIntent().getStringExtra("tourney"));
        offenseDefense.setText(getIntent().getStringExtra("o/d"));
        pointCap.setText(getIntent().getIntExtra("cap",0) + "");
    }

    @Override
    public void onClick(View v) {

        String team = getIntent().getStringExtra("team");

        switch (v.getId()){

            case R.id.backToGames:

                Intent gameDetailsIntent = new Intent(GameDetailsActivity.this, AddGameActivity.class);
                gameDetailsIntent.putExtra("team", team);
                startActivity(gameDetailsIntent);
                finish();
                break;

            case  R.id.goMakeLine:
                Intent makeLine = new Intent(GameDetailsActivity.this, MakeLineActivity.class);

                String opponent = getIntent().getStringExtra("opp");
                String oOrD = getIntent().getStringExtra("o/d");
                int pointCap = getIntent().getIntExtra("cap",0);
                int ourScore = getIntent().getIntExtra("ourScore",0);
                int oppScore = getIntent().getIntExtra("oppScore",0);

                if (ourScore >= pointCap || oppScore >= pointCap){
                    toastMessage("This Game Has Reached Its Point Limit");
                    break;
                }

                makeLine.putExtra("team",team);
                makeLine.putExtra("opp",opponent);
                makeLine.putExtra("cap",pointCap);
                makeLine.putExtra("o/d",oOrD);
                makeLine.putExtra("ourScore",ourScore);
                makeLine.putExtra("oppScore",oppScore);

                startActivity(makeLine);
                finish();
        }

    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }
}
