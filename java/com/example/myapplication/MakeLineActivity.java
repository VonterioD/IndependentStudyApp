package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.Collections;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MakeLineActivity extends AppCompatActivity implements View.OnClickListener {

    private Button openSpot1;
    private Button openSpot2;
    private Button openSpot3;
    private Button openSpot4;
    private Button openSpot5;
    private Button openSpot6;
    private Button openSpot7;
    private Button startTheGame;
    private Button gameList;
    private TextView odTitle;
    private LinearLayout playersContainer;
    private ArrayList<String> playersOnTeam;
    private ArrayList<Integer> spots = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
    private int ourScore = 0;
    private int oppScore = 0;
    DatabaseHelper db;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_line);

        db = new DatabaseHelper(this);
        openSpot1 = (Button)findViewById(R.id.openSpot1);
        openSpot2 = (Button)findViewById(R.id.openSpot2);
        openSpot3 = (Button)findViewById(R.id.openSpot3);
        openSpot4 = (Button)findViewById(R.id.openSpot4);
        openSpot5 = (Button)findViewById(R.id.openSpot5);
        openSpot6 = (Button)findViewById(R.id.openSpot6);
        openSpot7 = (Button)findViewById(R.id.openSpot7);
        gameList = (Button) findViewById(R.id.backToTheGameList);
        startTheGame = (Button)findViewById(R.id.actionButton);
        odTitle = (TextView)findViewById(R.id.odLineTitle);
        playersContainer = (LinearLayout)findViewById(R.id.playerContainer);
        playersOnTeam = new ArrayList<>();


        openSpot1.setOnClickListener(this);
        openSpot2.setOnClickListener(this);
        openSpot3.setOnClickListener(this);
        openSpot4.setOnClickListener(this);
        openSpot5.setOnClickListener(this);
        openSpot6.setOnClickListener(this);
        openSpot7.setOnClickListener(this);
        startTheGame.setOnClickListener(this);
        gameList.setOnClickListener(this);

        String oOrD = getIntent().getStringExtra("o/d");
        String team = getIntent().getStringExtra("team");

        if (oOrD.startsWith("O")) {
            odTitle.setText("O-Line");
        }
        else {
            odTitle.setText("D-Line");
        }

        //Get all players and put into array list
        playersOnTeam = getPlayerNames(team);

        populatePlayerButtons(playersOnTeam);
        updateScore();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.backToTheGameList:

                Intent backIntent = new Intent(MakeLineActivity.this, AddGameActivity.class);

                String oOrD = getIntent().getStringExtra("o/d");
                String team = getIntent().getStringExtra("team");
                String opp = getIntent().getStringExtra("opp");
                int usScore = getIntent().getIntExtra("ourScore",0);
                int theirScore = getIntent().getIntExtra("oppScore",0);

                backIntent.putExtra("team", team);
                db.updateGameData(usScore,theirScore,oOrD,opp); //Update the DB
                startActivity(backIntent);
                finish();
                break;

            case R.id.openSpot1:
                String currPlayer = openSpot1.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot1.setText("open");
                    openSpot1.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);

                    spots.add(1); //Add open spot back to list
                }
                break;

            case R.id.openSpot2:
                currPlayer = openSpot2.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot2.setText("open");
                    openSpot2.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);
                    spots.add(2); //Add open spot back to list
                }
                break;

            case R.id.openSpot3:
                currPlayer = openSpot3.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot3.setText("open");
                    openSpot3.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);playerBtn.setBackgroundResource(android.R.drawable.btn_default);
                    spots.add(3); //Add open spot back to list
                }
                break;

            case R.id.openSpot4:
                currPlayer = openSpot4.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot4.setText("open");
                    openSpot4.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);

                    spots.add(4); //Add open spot back to list
                }
                break;


            case R.id.openSpot5:
                currPlayer = openSpot5.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot5.setText("open");
                    openSpot5.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);
                    spots.add(5); //Add open spot back to list
                }
                break;

            case R.id.openSpot6:
                currPlayer = openSpot6.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot6.setText("open");
                    openSpot6.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);
                    spots.add(6); //Add open spot back to list
                }
                break;

            case R.id.openSpot7:
                currPlayer = openSpot7.getText().toString(); //Get the name of the player
                if (currPlayer.equals("open")) //Check to make sure spot is not open (player is there)
                    break;

                else {
                    openSpot7.setText("open");
                    openSpot7.setBackgroundColor(getResources().getColor(R.color.whitish));

                    Button playerBtn = playersContainer.findViewWithTag(currPlayer);
                    playerBtn.setTextColor(Color.BLACK);
                    playerBtn.setBackgroundResource(android.R.drawable.btn_default);
                    spots.add(7); //Add open spot back to list
                }
                break;

            case R.id.actionButton:

                //Add all the players to list for gameplay
                ArrayList<String> fieldPlayers = new ArrayList<>();
                fieldPlayers.add(openSpot1.getText().toString());
                fieldPlayers.add(openSpot2.getText().toString());
                fieldPlayers.add(openSpot3.getText().toString());
                fieldPlayers.add(openSpot4.getText().toString());
                fieldPlayers.add(openSpot5.getText().toString());
                fieldPlayers.add(openSpot6.getText().toString());
                fieldPlayers.add(openSpot7.getText().toString());

                int pointCap = getIntent().getIntExtra("cap",0);
                //toastMessage("point cap is " + pointCap);
                String teamName = getIntent().getStringExtra("team");
                String oppName = getIntent().getStringExtra("opp");
                oOrD = getIntent().getStringExtra("o/d");


                //Make new table here


                Intent newIntent = new Intent(MakeLineActivity.this, PlayGameActivity.class);
                newIntent.putExtra("playerList", fieldPlayers);
                newIntent.putExtra("cap", pointCap); //Send over the point cap
                newIntent.putExtra("team", teamName); //Send over the team name
                newIntent.putExtra("opp", oppName); //Send over opponent name
                newIntent.putExtra("o/d", oOrD); //Send offense/defense
                newIntent.putExtra("ourScore", ourScore);
                newIntent.putExtra("oppScore", oppScore);
                startActivity(newIntent);
                finish();


                //THINGS TO DO/ THINK ABOUT
                //Create new intent and put list here, start game screen
                //Send over team name, opp name, what we they are starting on
                //Edit Team/Game table to add point cap, curr score, next start (O/D)
                //Create new table (Game, team name, player, stats catches, passes, drops, assists, scores, d's, drops, throwaways)

                break;

            default:
                break;


        }

        Collections.sort(spots); //Sort the spots to make sure they are in order
    }

    public ArrayList<String> getPlayerNames (String teamName){
        ArrayList<String> players = new ArrayList<>();

        Cursor myCursor = db.getData(teamName);

        //Iterate through DB cursor of team and its assoc. players
        while (myCursor.moveToNext()){

            //Get each player and add to array list only if they have not been added already
            String playerToAdd = myCursor.getString(myCursor.getColumnIndex("Player_Name"));

            if (players.contains(playerToAdd)){
                continue;
            }

            players.add(playerToAdd);

        }

        return players;
    }

    public void populatePlayerButtons (ArrayList<String> players){

        int rows = (int)Math.ceil(players.size()/4.0); //Figure out how many rows to add to the scrollview
        int totalPlayers = players.size();
        final int screenWidth = getResources().getDisplayMetrics().widthPixels/4;
        int playerCounter = 0;

        for (int i = 0; i < rows; i++) {
            LinearLayout newRow = new LinearLayout(this);
            newRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newRow.setOrientation(LinearLayout.HORIZONTAL);
            newRow.setTag(i);
            playersContainer.addView(newRow);
        }

        for (int i = 0; i < rows; i++) {

            boolean outOfPlayers = false;
            LinearLayout newRow = playersContainer.findViewWithTag(i);

            for (int j = 0; j < 4; j++) { //4 buttons per row

                if (outOfPlayers == true) {
                    break;
                }

                final Button newPlayer = new Button(this);
                newPlayer.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.MATCH_PARENT));
                newPlayer.setText(players.get(playerCounter));
                newPlayer.setBackgroundResource(android.R.drawable.btn_default);
                newPlayer.setId(playerCounter);
                newPlayer.setTag(players.get(playerCounter));
                playerCounter++;

                if (playerCounter + 1 > totalPlayers)
                    outOfPlayers = true;

                newPlayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Make sure spots aren't all taken
                        if (spots.size() > 0) {

                            newPlayer.setBackgroundColor(getResources().getColor(R.color.whitish));
                            newPlayer.setTextColor(Color.GRAY);

                            //Get the location where player should go on field line
                            int spotLocation = spots.get(0);

                            //Depending on spot set player to that spot and change button attrib.
                            switch (spotLocation) {
                                case 1:
                                    openSpot1.setText(newPlayer.getText().toString());
                                    openSpot1.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot1.setTextColor(Color.BLACK);
                                    openSpot1.setMaxWidth(screenWidth);
                                    break;

                                case 2:
                                    openSpot2.setText(newPlayer.getText().toString());
                                    openSpot2.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot2.setTextColor(Color.BLACK);
                                    openSpot2.setMaxWidth(screenWidth);
                                    break;

                                case 3:
                                    openSpot3.setText(newPlayer.getText().toString());
                                    openSpot3.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot3.setTextColor(Color.BLACK);
                                    openSpot3.setMaxWidth(screenWidth);
                                    break;

                                case 4:
                                    openSpot4.setText(newPlayer.getText().toString());
                                    openSpot4.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot4.setTextColor(Color.BLACK);
                                    openSpot4.setMaxWidth(screenWidth);
                                    break;

                                case 5:
                                    openSpot5.setText(newPlayer.getText().toString());
                                    openSpot5.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot5.setTextColor(Color.BLACK);
                                    openSpot5.setMaxWidth(screenWidth);
                                    break;

                                case 6:
                                    openSpot6.setText(newPlayer.getText().toString());
                                    openSpot6.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot6.setTextColor(Color.BLACK);
                                    openSpot6.setMaxWidth(screenWidth);
                                    break;

                                case 7:
                                    openSpot7.setText(newPlayer.getText().toString());
                                    openSpot7.setBackgroundResource(android.R.drawable.btn_default);
                                    openSpot7.setTextColor(Color.BLACK);
                                    openSpot7.setMaxWidth(screenWidth);
                                    break;

                                default:
                                    break;
                            }

                            spots.remove(0);

                        }
                    }
                });

                if (outOfPlayers == true) {
                    newRow.addView(newPlayer);
                    break;
                }
                newRow.addView(newPlayer);

            }
        }
    }

    public  void updateScore(){
        ourScore = getIntent().getIntExtra("ourScore",0);
        oppScore = getIntent().getIntExtra("oppScore",0);
    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }
}
