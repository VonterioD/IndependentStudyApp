package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

public class PlayerListActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView playerList;
    private ArrayAdapter<String> playerAdapter;
    private ArrayList<String> playersOnTeam;
    private Button addToTeam;
    private Button backToTeamEdit;
    boolean addedPlayers;
    DatabaseHelper myDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_roster);

        addToTeam = (Button) findViewById(R.id.addPlayers);
        backToTeamEdit = (Button) findViewById(R.id.backToTeam);
        playerList = (ListView) findViewById(R.id.playerList);
        playersOnTeam = new ArrayList<>();
        playerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playersOnTeam);
        myDB = new DatabaseHelper(this);
        addedPlayers = false;

        addToTeam.setOnClickListener(this);
        backToTeamEdit.setOnClickListener(this);

        if (myDB.isPlayerTableEmpty() == false)
            populateRoster(getIntent().getStringExtra("team"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addPlayers:

                //Go to create player activity
                Intent intent = new Intent(PlayerListActivity.this, CreatePlayerActivity.class);

                //Put team name into intent and start for result
                String teamName = getIntent().getStringExtra("team");
                intent.putExtra("team", teamName);
                //startActivityForResult(intent,1);
                startActivityForResult(intent,1);
                break;

            case R.id.backToTeam:
                finish();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        int playerAdded = data.getIntExtra("playerAdded", 0);

        if (requestCode == 1 && resultCode == RESULT_OK  && playerAdded == 1){
            String teamName = data.getStringExtra("teamName");

            Cursor myCursor = myDB.getData(teamName);

            while (myCursor.moveToNext()){

                String player = myCursor.getString(myCursor.getColumnIndex("Player_Name"));

                //Check to make sure player is not already on the team roster
                if (playersOnTeam.contains(player)){
                    continue;
                }

                playersOnTeam.add(player);
                playerAdapter.notifyDataSetChanged();
            }

            playerList.setAdapter(playerAdapter);
            myCursor.close();

        }
    }

    protected void onResume(){
        super.onResume();

        /*String teamName = getIntent().getStringExtra("team");
        Cursor myCursor = myDB.getData(teamName);

        /*do {
            playersOnTeam.add(myCursor.getString(myCursor.getColumnIndex("Player_Name")));
        } while (myCursor.moveToNext());*/

        /*playerAdapter.notifyDataSetChanged();
        playerList.setAdapter(playerAdapter);*/

    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }

    public void populateRoster(String teamName){

        Cursor myCursor = myDB.getData(teamName);

        while (myCursor.moveToNext()){

            String player = myCursor.getString(myCursor.getColumnIndex("Player_Name"));

            //Check to make sure player is not already on the team roster
            if (playersOnTeam.contains(player)){
                continue;
            }

            playersOnTeam.add(player);
            playerAdapter.notifyDataSetChanged();
        }

        playerList.setAdapter(playerAdapter);
        myCursor.close();
    }


}
