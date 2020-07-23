package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

public class CreatePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backToRoster;
    private Button savePlayer;
    private EditText playerName;
    DatabaseHelper myDB;
    boolean addedPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        backToRoster = (Button) findViewById(R.id.backToRoster);
        savePlayer = (Button) findViewById(R.id.savePlayer);
        playerName = (EditText) findViewById(R.id.playerName);
        myDB = new DatabaseHelper(getApplicationContext());
        addedPlayer = false;

        backToRoster.setOnClickListener(this);
        savePlayer.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String team = getIntent().getStringExtra("team");
        Intent newIntent = new Intent();
        switch (v.getId()){
            case R.id.backToRoster:
                if (addedPlayer == true){
                    newIntent.putExtra("playerAdded", 1);
                    newIntent.putExtra("teamName", team);
                    setResult(RESULT_OK, newIntent);
                }

                else {
                    newIntent.putExtra("playerAdded", 0);
                    newIntent.putExtra("teamName", team);
                    setResult(RESULT_OK, newIntent);
                }
                finish();
                break;

            case R.id.savePlayer:

                //Get player name from text field and team name from intent
                String myPlayer = playerName.getText().toString();
                boolean dataInput = myDB.addData(team, myPlayer); //Insert into database

                //Check if data was inserted successfully
                if (dataInput == false) {
                    toastMessage("Player was not added to the roster");
                }

                else {
                    toastMessage("Player was successfully added to the roster!");
                    addedPlayer = true;

                }

                playerName.setText(""); //Clear text field

                break;

        }
    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }
}
