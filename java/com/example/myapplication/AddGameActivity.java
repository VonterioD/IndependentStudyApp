package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddGameActivity extends AppCompatActivity implements View.OnClickListener {


    private Button addGame;
    private Button goBack;
    private TextView teamNameTitle;
    private ArrayList<String> games;
    private ListView listOfGames;
    private ArrayAdapter<String> gameAdapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);

        games = new ArrayList<>();
        listOfGames =  (ListView) findViewById(R.id.gameList);
        gameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, games);
        addGame = (Button) findViewById(R.id.addGame);
        goBack = (Button) findViewById(R.id.goBackToEditTeam);
        teamNameTitle = (TextView) findViewById(R.id.teamNameGames);
        db = new DatabaseHelper(this);

        addGame.setOnClickListener(this);
        goBack.setOnClickListener(this);

        String teamName = getIntent().getStringExtra("team");
        teamNameTitle.setText("Games: " + teamName);


        if (db.isGameTableEmpty() == false)
            populateGameList(teamName);

        listOfGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opponentName = parent.getItemAtPosition(position).toString();

                getGameData(opponentName);

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addGame:
                Intent newIntent = new Intent (AddGameActivity.this, StartGameActivity.class);
                newIntent.putExtra("team", getIntent().getStringExtra("team"));
                startActivity(newIntent);
                break;

            case R.id.goBackToEditTeam:
                finish();
                break;
        }
    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }

    public void populateGameList(String team) {

        Cursor myCursor = db.getGames(team);

       while (myCursor.moveToNext()) {

            String opponent = myCursor.getString(myCursor.getColumnIndex("Opponent"));

            //Check to make sure player is not already on the team roster
            if (games.contains(opponent)) {
                continue;
            }

            games.add(opponent);
            gameAdapter.notifyDataSetChanged();
        }

        listOfGames.setAdapter(gameAdapter);
        myCursor.close();
    }

    public void getGameData(String opp){

        Cursor myCursor = db.getSpecificGame(opp);

        String team = getIntent().getStringExtra("team");

        Intent detailIntent = new Intent(AddGameActivity.this, GameDetailsActivity.class);
        detailIntent.putExtra("opp", opp);
        detailIntent.putExtra("team", team);

        if (myCursor.moveToFirst()){

            //Get data from the DB
            String oOrD = myCursor.getString(myCursor.getColumnIndex("Next_Point_O_D"));
            String tourney = myCursor.getString(myCursor.getColumnIndex("Tournament"));
            int cap = myCursor.getInt(myCursor.getColumnIndex("Point_Cap"));
            int ourScore = myCursor.getInt(myCursor.getColumnIndex("Our_Score"));
            int oppScore = myCursor.getInt(myCursor.getColumnIndex("Opponent_Score"));

            //toastMessage("db data we got is o/d: " +oOrD+ " tourney: " +tourney+ " cap " +cap+ " ourScore " +ourScore+ " oppScore " + oppScore);

            //Add to intent
            detailIntent.putExtra("cap", cap);
            detailIntent.putExtra("tourney", tourney);
            detailIntent.putExtra("o/d", oOrD);
            detailIntent.putExtra("ourScore", ourScore);
            detailIntent.putExtra("oppScore", oppScore);

        }

        startActivity(detailIntent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String teamName = getIntent().getStringExtra("team");
        populateGameList(teamName);
    }
}

