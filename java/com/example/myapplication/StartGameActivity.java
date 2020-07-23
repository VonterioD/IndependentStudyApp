package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartGameActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB;
    private Button goBack;
    private EditText opponentName;
    private EditText tourneyName;
    private ToggleButton offenseOrDefense;
    private Button startGame;
    private RadioGroup pointCap;
    private RadioButton gameToNine;
    private RadioButton gameToEleven;
    private RadioButton gameToThirteen;
    private RadioButton gameToFifteen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_new_game);

        myDB = new DatabaseHelper(this);
        goBack = (Button) findViewById(R.id.backToTheGameList);
        startGame = (Button)findViewById(R.id.startGame);
        offenseOrDefense = (ToggleButton) findViewById(R.id.odToggle);
        opponentName = (EditText) findViewById(R.id.opponentName);
        tourneyName = (EditText) findViewById(R.id.tourneyName);
        pointCap = (RadioGroup) findViewById(R.id.pointCapDB);
        gameToNine = (RadioButton) findViewById(R.id.gameTo9);
        gameToEleven = (RadioButton) findViewById(R.id.gameTo11);
        gameToThirteen = (RadioButton) findViewById(R.id.gameTo13);
        gameToFifteen = (RadioButton) findViewById(R.id.gameTo15);



        goBack.setOnClickListener(this);
        startGame.setOnClickListener(this);

    }
    public void onClick(View v) {

        Intent startGameIntent = new Intent(StartGameActivity.this, MakeLineActivity.class);
        String teamName = getIntent().getStringExtra("team"); //Get team name from intent
        String oppName = opponentName.getText().toString(); //Get opponent name from text field
        String tourney = tourneyName.getText().toString(); //Get the tournament name
        String oOrD = offenseOrDefense.getText().toString();


        switch (v.getId()){
            case R.id.startGame:

                int selectedCap = pointCap.getCheckedRadioButtonId();
                RadioButton myCap = (RadioButton) findViewById(selectedCap);
                int pointCap = Integer.parseInt(myCap.getText().toString());
                int ourScore = 0;
                int oppScore = 0;

                if (oppName.equals("")){
                    toastMessage("Opponent name cannot be empty");
                    break;
                }

                else if (tourney.equals("")){
                    toastMessage("Tournament name cannot be empty");
                    break;
                }

                boolean addedGame = myDB.addGame(teamName,oppName,tourney,ourScore,oppScore,oOrD,pointCap);
                //toastMessage("parameters in order: Team Name " +teamName+ " Opponent name " +oppName+ " Tournament name " +tourney+ " O/D " +oOrD+ " Point Cap " + pointCap);
                if (addedGame == true){
                    toastMessage("game added to db against " + oppName);
                }

                else {
                    toastMessage("game NOT added to db against " + oppName);
                    break;
                }

                startGameIntent.putExtra("cap", pointCap); //Send over the point cap
                startGameIntent.putExtra("team", teamName); //Send over the team name
                startGameIntent.putExtra("opp", oppName); //Send over opponent name
                startGameIntent.putExtra("o/d", oOrD); //Send offense/defense
                startActivity(startGameIntent);
                finish();
                break;

            case R.id.backToTheGameList:
                finish();
                break;
        }
    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }
}
