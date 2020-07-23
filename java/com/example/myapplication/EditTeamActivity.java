package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTeamActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText team;
    private Button saveEdits;
    private Button addPlayers;
    private Button goToGames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_team);

        String newTeamName = getIntent().getStringExtra("teamName");
        int listPos = getIntent().getIntExtra("listPos", 0);
        team = (EditText) findViewById(R.id.teamName);
        team.setText(newTeamName);

        saveEdits = (Button) findViewById(R.id.saveBtn);
        addPlayers = (Button) findViewById(R.id.addPlayers);
        goToGames = (Button) findViewById(R.id.gamesButton);

        saveEdits.setOnClickListener(this);
        addPlayers.setOnClickListener(this);
        goToGames.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        String theTeam = team.getText().toString();
        String intentTeam = getIntent().getStringExtra("teamName");
        int listPos = getIntent().getIntExtra("listPos", 0);

        switch(v.getId()){
            case R.id.saveBtn:

                //Make sure team is not empty
                if (team.getText().length() != 0){
                    Intent newIntent = new Intent(EditTeamActivity.this, MainActivity.class);
                    newIntent.putExtra("listPos", listPos); //Send back team list position
                    newIntent.putExtra("teamName", theTeam); //Send back new team Name


                    setResult(RESULT_OK, newIntent);
                    finish();
                }

                else {
                    toastMessage("Team Name Cannot Be Null");
                }
                break;

            case R.id.addPlayers:
                Intent playersIntent = new Intent(EditTeamActivity.this, PlayerListActivity.class);
                playersIntent.putExtra("team", intentTeam);
                startActivity(playersIntent);
                break;

            case R.id.gamesButton:
                Intent addGameIntent = new Intent(EditTeamActivity.this, AddGameActivity.class);
                addGameIntent.putExtra("team", team.getText().toString());
                startActivity(addGameIntent);
                break;
        }
    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }
}
