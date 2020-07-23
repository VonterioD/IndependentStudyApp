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
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class PlayGameActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB;
    private ArrayList<String> playersOnField;
    private ArrayList<String> eventTracker;
    private int eventCounter = 0;
    private String playerLink;
    private ConstraintLayout playGameScreen;
    private int ourScore = 0;
    private int oppScore = 0;
    //private Stack<String> eventTracker;


    //Special Purpose Buttons
    private Button stats;
    private Button editLine;
    private Button undoEvent;
    private Button gameOver;
    private Button theyScored;
    private Button dThrowaway;
    private Button oThrowaway;

    //Special Purpose Textviews
    private TextView scoreKeep;
    private TextView event1;
    private TextView event2;
    private TextView event3;
    private TextView passer;
    private TextView receiver;
    private TextView defender;
    private TextView whoPickedUp;

    //Pick Up Buttons
    private Button pickup1;
    private Button pickup2;
    private Button pickup3;
    private Button pickup4;
    private Button pickup5;
    private Button pickup6;
    private Button pickup7;


    //D's Buttons
    private Button d1;
    private Button d2;
    private Button d3;
    private Button d4;
    private Button d5;
    private Button d6;
    private Button d7;

    //Catch Buttons
    private Button catch1;
    private Button catch2;
    private Button catch3;
    private Button catch4;
    private Button catch5;
    private Button catch6;
    private Button catch7;

    //Drop Buttons
    private Button drop1;
    private Button drop2;
    private Button drop3;
    private Button drop4;
    private Button drop5;
    private Button drop6;
    private Button drop7;

    //Goal Buttons
    private Button goal1;
    private Button goal2;
    private Button goal3;
    private Button goal4;
    private Button goal5;
    private Button goal6;
    private Button goal7;

    //Pull Buttons
    private  Button pull1;
    private  Button pull2;
    private  Button pull3;
    private  Button pull4;
    private  Button pull5;
    private  Button pull6;
    private  Button pull7;


    //Player Textviews
    private TextView player1;
    private TextView player2;
    private TextView player3;
    private TextView player4;
    private TextView player5;
    private TextView player6;
    private TextView player7;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);

        myDB = new DatabaseHelper(this);
        eventTracker = new ArrayList<>();
        playGameScreen = (ConstraintLayout) findViewById(R.id.playGameScreen);


        //Special Buttons
        stats = (Button)findViewById(R.id.currStats);
        editLine = (Button) findViewById(R.id.line);
        undoEvent = (Button) findViewById(R.id.undoEvent);
        gameOver = (Button) findViewById(R.id.gameOver);
        theyScored = (Button) findViewById(R.id.theyScored);
        dThrowaway = (Button) findViewById(R.id.defenseThrowaway);
        oThrowaway = (Button) findViewById(R.id.throwaway);

        //Special Textviews
        scoreKeep = (TextView) findViewById(R.id.currScore);
        event1 = (TextView) findViewById(R.id.event1);
        event2 = (TextView) findViewById(R.id.event2);
        event3 = (TextView) findViewById(R.id.event3);
        passer = (TextView) findViewById(R.id.passer);
        receiver = (TextView) findViewById(R.id.receiver);
        defender = (TextView) findViewById(R.id.defender);
        whoPickedUp = (TextView) findViewById(R.id.whoseDisc);

        //Players
        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);
        player3 = (TextView) findViewById(R.id.player3);
        player4 = (TextView) findViewById(R.id.player4);
        player5 = (TextView) findViewById(R.id.player5);
        player6 = (TextView) findViewById(R.id.player6);
        player7 = (TextView) findViewById(R.id.player7);

        //D's
        d1 = (Button) findViewById(R.id.p1D);
        d2 = (Button) findViewById(R.id.p2D);
        d3 = (Button) findViewById(R.id.p3D);
        d4 = (Button) findViewById(R.id.p4D);
        d5 = (Button) findViewById(R.id.p5D);
        d6 = (Button) findViewById(R.id.p6D);
        d7 = (Button) findViewById(R.id.p7D);

        //Catch
        catch1 = (Button) findViewById(R.id.p1Catch);
        catch2 = (Button) findViewById(R.id.p2Catch);
        catch3 = (Button) findViewById(R.id.p3Catch);
        catch4 = (Button) findViewById(R.id.p4Catch);
        catch5 = (Button) findViewById(R.id.p5Catch);
        catch6 = (Button) findViewById(R.id.p6Catch);
        catch7 = (Button) findViewById(R.id.p7Catch);

        //Drop
        drop1 = (Button) findViewById(R.id.p1Drop);
        drop2 = (Button) findViewById(R.id.p2Drop);
        drop3 = (Button) findViewById(R.id.p3Drop);
        drop4 = (Button) findViewById(R.id.p4Drop);
        drop5 = (Button) findViewById(R.id.p5Drop);
        drop6 = (Button) findViewById(R.id.p6Drop);
        drop7 = (Button) findViewById(R.id.p7Drop);

        //Goals
        goal1 = (Button) findViewById(R.id.p1Goal);
        goal2 = (Button) findViewById(R.id.p2Goal);
        goal3 = (Button) findViewById(R.id.p3Goal);
        goal4 = (Button) findViewById(R.id.p4Goal);
        goal5 = (Button) findViewById(R.id.p5Goal);
        goal6 = (Button) findViewById(R.id.p6Goal);
        goal7 = (Button) findViewById(R.id.p7Goal);

        //Pulls
        pull1 = (Button) findViewById(R.id.p1Pull);
        pull2 = (Button) findViewById(R.id.p2Pull);
        pull3 = (Button) findViewById(R.id.p3Pull);
        pull4 = (Button) findViewById(R.id.p4Pull);
        pull5 = (Button) findViewById(R.id.p5Pull);
        pull6 = (Button) findViewById(R.id.p6Pull);
        pull7 = (Button) findViewById(R.id.p7Pull);

        //Pick Ups
        pickup1 = (Button) findViewById(R.id.p1Pick);
        pickup2 = (Button) findViewById(R.id.p2Pick);
        pickup3 = (Button) findViewById(R.id.p3Pick);
        pickup4 = (Button) findViewById(R.id.p4Pick);
        pickup5 = (Button) findViewById(R.id.p5Pick);
        pickup6 = (Button) findViewById(R.id.p6Pick);
        pickup7 = (Button) findViewById(R.id.p7Pick);


        //Get players from intent
        playersOnField = (ArrayList<String>)getIntent().getSerializableExtra("playerList");

        //Set score
        ourScore = getIntent().getIntExtra("ourScore", 0);
        oppScore = getIntent().getIntExtra("oppScore", 0);
        scoreKeep.setText("(Us " + ourScore + "-" + oppScore + " Them)");

        //Offense or Defense start
        String oOrD = getIntent().getStringExtra("o/d");
        if (oOrD.startsWith("O")) {
            showPickUpLayout();
        }
        else {
            showPullLayout();
        }

        //Add names to the player buttons
        populateNames(playersOnField);

        //Add click listeners

        //Pull listeners
        pull1.setOnClickListener(this);
        pull2.setOnClickListener(this);
        pull3.setOnClickListener(this);
        pull4.setOnClickListener(this);
        pull5.setOnClickListener(this);
        pull6.setOnClickListener(this);
        pull7.setOnClickListener(this);

        //D listeners
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);

        //Other listeners
        oThrowaway.setOnClickListener(this);
        dThrowaway.setOnClickListener(this);
        theyScored.setOnClickListener(this);

        //Pickup listeners
        pickup1.setOnClickListener(this);
        pickup2.setOnClickListener(this);
        pickup3.setOnClickListener(this);
        pickup4.setOnClickListener(this);
        pickup5.setOnClickListener(this);
        pickup6.setOnClickListener(this);
        pickup7.setOnClickListener(this);

        //Catch listeners
        catch1.setOnClickListener(this);
        catch2.setOnClickListener(this);
        catch3.setOnClickListener(this);
        catch4.setOnClickListener(this);
        catch5.setOnClickListener(this);
        catch6.setOnClickListener(this);
        catch7.setOnClickListener(this);

        //Drop Listeners
        drop1.setOnClickListener(this);
        drop2.setOnClickListener(this);
        drop3.setOnClickListener(this);
        drop4.setOnClickListener(this);
        drop5.setOnClickListener(this);
        drop6.setOnClickListener(this);
        drop7.setOnClickListener(this);

        //Goal listeners
        goal1.setOnClickListener(this);
        goal2.setOnClickListener(this);
        goal3.setOnClickListener(this);
        goal4.setOnClickListener(this);
        goal5.setOnClickListener(this);
        goal6.setOnClickListener(this);
        goal7.setOnClickListener(this);

        //Other Buttons
        editLine.setOnClickListener(this);
        gameOver.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){

            String whichPlayer = data.getStringExtra("playerValue");
            Long pullTime = data.getLongExtra("pullTime", 0);

            switch (whichPlayer){
                case "player1":
                    eventTracker.add(player1.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;

                case "player2":
                    eventTracker.add(player2.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;

                case "player3":
                    eventTracker.add(player3.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;

                case "player4":
                    eventTracker.add(player4.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;

                case "player5":
                    eventTracker.add(player5.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;

                case "player6":
                    eventTracker.add(player6.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;

                case "player7":
                    eventTracker.add(player7.getText().toString() + " pulled disc for " + pullTime + "s");
                    updateEventHolder();
                    eventCounter++;
                    showDefenseLayout();
                    break;
            }

        }
    }

    @Override
    public void onClick(View v) {

        String opponent = getIntent().getStringExtra("opp");
        Intent pullIntent = new Intent(PlayGameActivity.this, GetPullTimeActivity.class);


        switch (v.getId()){

            //Pull Cases
            case R.id.p1Pull:

                pullIntent.putExtra("playerValue", "player1");
                startActivityForResult(pullIntent,1);
                break;

            case R.id.p2Pull:
                pullIntent.putExtra("playerValue", "player2");
                startActivityForResult(pullIntent,1);
                break;

            case R.id.p3Pull:

                pullIntent.putExtra("playerValue", "player3");
                startActivityForResult(pullIntent,1);
                break;

            case R.id.p4Pull:
                pullIntent.putExtra("playerValue", "player4");
                startActivityForResult(pullIntent,1);
                break;

            case R.id.p5Pull:

                pullIntent.putExtra("playerValue", "player5");
                startActivityForResult(pullIntent,1);
                break;

            case R.id.p6Pull:
                pullIntent.putExtra("playerValue", "player6");
                startActivityForResult(pullIntent,1);
                break;

            case R.id.p7Pull:
                pullIntent.putExtra("playerValue", "player7");
                startActivityForResult(pullIntent,1);
                break;

            //D cases
            case R.id.p1D:
                eventTracker.add(player1.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.p2D:
                eventTracker.add(player2.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.p3D:
                eventTracker.add(player3.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.p4D:
                eventTracker.add(player4.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.p5D:
                eventTracker.add(player5.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.p6D:
                eventTracker.add(player6.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.p7D:
                eventTracker.add(player7.getText().toString() + " just got a D!");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            //Pickups
            case R.id.p1Pick:
                playerLink = player1.getText().toString();
                player1.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch1.setVisibility(View.GONE);
                drop1.setVisibility(View.GONE);
                goal1.setVisibility(View.GONE);
                break;

            case R.id.p2Pick:
                playerLink = player2.getText().toString();
                player2.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch2.setVisibility(View.GONE);
                drop2.setVisibility(View.GONE);
                goal2.setVisibility(View.GONE);
                break;

            case R.id.p3Pick:
                playerLink = player3.getText().toString();
                player3.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch3.setVisibility(View.GONE);
                drop3.setVisibility(View.GONE);
                goal3.setVisibility(View.GONE);
                break;

            case R.id.p4Pick:
                playerLink = player4.getText().toString();
                player4.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch4.setVisibility(View.GONE);
                drop4.setVisibility(View.GONE);
                goal4.setVisibility(View.GONE);
                break;

            case R.id.p5Pick:
                playerLink = player5.getText().toString();
                player5.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch5.setVisibility(View.GONE);
                drop5.setVisibility(View.GONE);
                goal5.setVisibility(View.GONE);
                break;

            case R.id.p6Pick:
                playerLink = player6.getText().toString();
                player6.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch6.setVisibility(View.GONE);
                drop6.setVisibility(View.GONE);
                goal6.setVisibility(View.GONE);
                break;

            case R.id.p7Pick:
                playerLink = player7.getText().toString();
                player7.setTextColor(getResources().getColor(R.color.btnGray));
                showOffenseLayout();
                catch7.setVisibility(View.GONE);
                drop7.setVisibility(View.GONE);
                goal7.setVisibility(View.GONE);
                break;

            //Catches
            case R.id.p1Catch:

                TextView playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player1.getText().toString() + " caught pass from " + playerLink);
                playerLink = player1.getText().toString();
                updateEventHolder();
                eventCounter++;

                player1.setTextColor(getResources().getColor(R.color.btnGray));
                catch1.setVisibility(View.GONE);
                drop1.setVisibility(View.GONE);
                goal1.setVisibility(View.GONE);

                break;

            case R.id.p2Catch:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player2.getText().toString() + " caught pass from " + playerLink);
                playerLink = player2.getText().toString();
                updateEventHolder();
                eventCounter++;

                player2.setTextColor(getResources().getColor(R.color.btnGray));
                catch2.setVisibility(View.GONE);
                drop2.setVisibility(View.GONE);
                goal2.setVisibility(View.GONE);

                break;

            case R.id.p3Catch:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player3.getText().toString() + " caught pass from " + playerLink);
                playerLink = player3.getText().toString();
                updateEventHolder();
                eventCounter++;

                player3.setTextColor(getResources().getColor(R.color.btnGray));
                catch3.setVisibility(View.GONE);
                drop3.setVisibility(View.GONE);
                goal3.setVisibility(View.GONE);

                break;

            case R.id.p4Catch:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player4.getText().toString() + " caught pass from " + playerLink);
                playerLink = player4.getText().toString();
                updateEventHolder();
                eventCounter++;

                player4.setTextColor(getResources().getColor(R.color.btnGray));
                catch4.setVisibility(View.GONE);
                drop4.setVisibility(View.GONE);
                goal4.setVisibility(View.GONE);

                break;

            case R.id.p5Catch:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player5.getText().toString() + " caught pass from " + playerLink);
                playerLink = player5.getText().toString();
                updateEventHolder();
                eventCounter++;

                player5.setTextColor(getResources().getColor(R.color.btnGray));
                catch5.setVisibility(View.GONE);
                drop5.setVisibility(View.GONE);
                goal5.setVisibility(View.GONE);

                break;

            case R.id.p6Catch:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player6.getText().toString() + " caught pass from " + playerLink);
                playerLink = player6.getText().toString();
                updateEventHolder();
                eventCounter++;

                player6.setTextColor(getResources().getColor(R.color.btnGray));
                catch6.setVisibility(View.GONE);
                drop6.setVisibility(View.GONE);
                goal6.setVisibility(View.GONE);

                break;

            case R.id.p7Catch:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);
                showOffenseLayout();

                eventTracker.add(player7.getText().toString() + " caught pass from " + playerLink);
                playerLink = player7.getText().toString();
                updateEventHolder();
                eventCounter++;

                player7.setTextColor(getResources().getColor(R.color.btnGray));
                catch7.setVisibility(View.GONE);
                drop7.setVisibility(View.GONE);
                goal7.setVisibility(View.GONE);

                break;

            //Throwaways
            case R.id.defenseThrowaway:

                eventTracker.add(opponent + " threw away the disc");
                updateEventHolder();
                eventCounter++;
                showPickUpLayout();
                break;

            case R.id.throwaway:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(playerLink + " threw away the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p1Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(playerLink + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p2Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(player2.getText().toString() + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p3Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(player3.getText().toString() + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p4Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(player4.getText().toString() + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p5Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(player5.getText().toString() + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p6Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(player6.getText().toString() + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p7Drop:

                playerView = playGameScreen.findViewWithTag(playerLink);
                playerView.setTextColor(Color.BLACK);

                eventTracker.add(player7.getText().toString() + " dropped the disc");
                updateEventHolder();
                eventCounter++;
                showDefenseLayout();
                break;

            case R.id.p1Goal:

                eventTracker.add(player1.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                int cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }
                break;

            case R.id.p2Goal:

                eventTracker.add(player2.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }

                break;

            case R.id.p3Goal:

                eventTracker.add(player3.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }

                break;

            case R.id.p4Goal:

                eventTracker.add(player4.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }

                break;

            case R.id.p5Goal:

                eventTracker.add(player5.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }

                break;

            case R.id.p6Goal:

                eventTracker.add(player6.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }

                break;

            case R.id.p7Goal:

                eventTracker.add(player7.getText().toString() + " scored with assist from " + playerLink);
                updateEventHolder();
                ourScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    weScoredPutExtras();
                }

                break;

            case R.id.theyScored:

                eventTracker.add(getIntent().getStringExtra("opp") + " scored a goal");
                updateEventHolder();
                oppScore++;
                eventCounter = 0;
                //Update db with stats
                eventTracker.clear();

                cap = getIntent().getIntExtra("cap",0);
                if (ourScore >= cap || oppScore >= cap) {
                    checkScore();
                }

                else {
                    theyScoredPutExtras();
                }

                break;

            case R.id.line:
                makeLineButton();
                break;

            case R.id.gameOver:
                gameOverButton();
                break;

             //when db works after score clear event holder and reset counter

            default:
                break;

        }
    }

    public void updateEventHolder(){
        event3.setText(event2.getText().toString());
        event2.setText(event1.getText().toString());
        event1.setText(eventTracker.get(eventCounter));
    }

    public void toastMessage(String text){
        Toast makeToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        makeToast.show();
    }

    public void populateNames(ArrayList<String> players){
        player1.setText(players.get(0));
        player1.setTag(players.get(0));

        player2.setText(players.get(1));
        player2.setTag(players.get(1));

        player3.setText(players.get(2));
        player3.setTag(players.get(2));

        player4.setText(players.get(3));
        player4.setTag(players.get(3));

        player5.setText(players.get(4));
        player5.setTag(players.get(4));

        player6.setText(players.get(5));
        player6.setTag(players.get(5));

        player7.setText(players.get(6));
        player7.setTag(players.get(6));
    }

    public void showDefenseLayout(){

        //Mini Titles
        defender.setVisibility(View.VISIBLE);
        receiver.setVisibility(View.GONE);
        passer.setVisibility(View.GONE);

        //Ds
        d1.setVisibility(View.VISIBLE);
        d2.setVisibility(View.VISIBLE);
        d3.setVisibility(View.VISIBLE);
        d4.setVisibility(View.VISIBLE);
        d5.setVisibility(View.VISIBLE);
        d6.setVisibility(View.VISIBLE);
        d7.setVisibility(View.VISIBLE);

        //Pulls
        pull1.setVisibility(View.GONE);
        pull2.setVisibility(View.GONE);
        pull3.setVisibility(View.GONE);
        pull4.setVisibility(View.GONE);
        pull5.setVisibility(View.GONE);
        pull6.setVisibility(View.GONE);
        pull7.setVisibility(View.GONE);

        //Drops
        drop1.setVisibility(View.GONE);
        drop2.setVisibility(View.GONE);
        drop3.setVisibility(View.GONE);
        drop4.setVisibility(View.GONE);
        drop5.setVisibility(View.GONE);
        drop6.setVisibility(View.GONE);
        drop7.setVisibility(View.GONE);

        //Catches
        catch1.setVisibility(View.GONE);
        catch2.setVisibility(View.GONE);
        catch3.setVisibility(View.GONE);
        catch4.setVisibility(View.GONE);
        catch5.setVisibility(View.GONE);
        catch6.setVisibility(View.GONE);
        catch7.setVisibility(View.GONE);

        //Goals
        goal1.setVisibility(View.GONE);
        goal2.setVisibility(View.GONE);
        goal3.setVisibility(View.GONE);
        goal4.setVisibility(View.GONE);
        goal5.setVisibility(View.GONE);
        goal6.setVisibility(View.GONE);
        goal7.setVisibility(View.GONE);

        //Throwaway bar
        dThrowaway.setVisibility(View.VISIBLE);
        oThrowaway.setVisibility(View.GONE);

        //They Scored
        theyScored.setVisibility(View.VISIBLE);

    }

    public void showPullLayout(){

        //Mini Titles
        defender.setVisibility(View.VISIBLE);
        receiver.setVisibility(View.GONE);
        passer.setVisibility(View.GONE);

        //Ds
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        d3.setVisibility(View.GONE);
        d4.setVisibility(View.GONE);
        d5.setVisibility(View.GONE);
        d6.setVisibility(View.GONE);
        d7.setVisibility(View.GONE);

        //Pulls
        pull1.setVisibility(View.VISIBLE);
        pull2.setVisibility(View.VISIBLE);
        pull3.setVisibility(View.VISIBLE);
        pull4.setVisibility(View.VISIBLE);
        pull5.setVisibility(View.VISIBLE);
        pull6.setVisibility(View.VISIBLE);
        pull7.setVisibility(View.VISIBLE);

        //Drops
        drop1.setVisibility(View.GONE);
        drop2.setVisibility(View.GONE);
        drop3.setVisibility(View.GONE);
        drop4.setVisibility(View.GONE);
        drop5.setVisibility(View.GONE);
        drop6.setVisibility(View.GONE);
        drop7.setVisibility(View.GONE);

        //Catches
        catch1.setVisibility(View.GONE);
        catch2.setVisibility(View.GONE);
        catch3.setVisibility(View.GONE);
        catch4.setVisibility(View.GONE);
        catch5.setVisibility(View.GONE);
        catch6.setVisibility(View.GONE);
        catch7.setVisibility(View.GONE);

        //Goals
        goal1.setVisibility(View.GONE);
        goal2.setVisibility(View.GONE);
        goal3.setVisibility(View.GONE);
        goal4.setVisibility(View.GONE);
        goal5.setVisibility(View.GONE);
        goal6.setVisibility(View.GONE);
        goal7.setVisibility(View.GONE);

        //Throwaway bar
        dThrowaway.setVisibility(View.GONE);
        oThrowaway.setVisibility(View.GONE);

        //They Scored
        theyScored.setVisibility(View.GONE);

    }

    public void showPickUpLayout(){

        //Mini Titles
        defender.setVisibility(View.GONE);
        receiver.setVisibility(View.VISIBLE);
        passer.setVisibility(View.VISIBLE);
        whoPickedUp.setVisibility(View.VISIBLE);

        //Pickups
        pickup1.setVisibility(View.VISIBLE);
        pickup2.setVisibility(View.VISIBLE);
        pickup3.setVisibility(View.VISIBLE);
        pickup4.setVisibility(View.VISIBLE);
        pickup5.setVisibility(View.VISIBLE);
        pickup6.setVisibility(View.VISIBLE);
        pickup7.setVisibility(View.VISIBLE);

        //Ds
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        d3.setVisibility(View.GONE);
        d4.setVisibility(View.GONE);
        d5.setVisibility(View.GONE);
        d6.setVisibility(View.GONE);
        d7.setVisibility(View.GONE);

        //Pulls
        pull1.setVisibility(View.GONE);
        pull2.setVisibility(View.GONE);
        pull3.setVisibility(View.GONE);
        pull4.setVisibility(View.GONE);
        pull5.setVisibility(View.GONE);
        pull6.setVisibility(View.GONE);
        pull7.setVisibility(View.GONE);

        //Drops
        drop1.setVisibility(View.GONE);
        drop2.setVisibility(View.GONE);
        drop3.setVisibility(View.GONE);
        drop4.setVisibility(View.GONE);
        drop5.setVisibility(View.GONE);
        drop6.setVisibility(View.GONE);
        drop7.setVisibility(View.GONE);

        //Catches
        catch1.setVisibility(View.GONE);
        catch2.setVisibility(View.GONE);
        catch3.setVisibility(View.GONE);
        catch4.setVisibility(View.GONE);
        catch5.setVisibility(View.GONE);
        catch6.setVisibility(View.GONE);
        catch7.setVisibility(View.GONE);

        //Goals
        goal1.setVisibility(View.GONE);
        goal2.setVisibility(View.GONE);
        goal3.setVisibility(View.GONE);
        goal4.setVisibility(View.GONE);
        goal5.setVisibility(View.GONE);
        goal6.setVisibility(View.GONE);
        goal7.setVisibility(View.GONE);

        //Throwaway bar
        dThrowaway.setVisibility(View.GONE);
        oThrowaway.setVisibility(View.GONE);

        //They Scored
        theyScored.setVisibility(View.GONE);

    }

    public void showOffenseLayout(){

        //Mini Titles
        defender.setVisibility(View.GONE);
        receiver.setVisibility(View.VISIBLE);
        passer.setVisibility(View.VISIBLE);
        whoPickedUp.setVisibility(View.GONE);

        //Pickups
        pickup1.setVisibility(View.GONE);
        pickup2.setVisibility(View.GONE);
        pickup3.setVisibility(View.GONE);
        pickup4.setVisibility(View.GONE);
        pickup5.setVisibility(View.GONE);
        pickup6.setVisibility(View.GONE);
        pickup7.setVisibility(View.GONE);

        //Ds
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        d3.setVisibility(View.GONE);
        d4.setVisibility(View.GONE);
        d5.setVisibility(View.GONE);
        d6.setVisibility(View.GONE);
        d7.setVisibility(View.GONE);

        //Pulls
        pull1.setVisibility(View.GONE);
        pull2.setVisibility(View.GONE);
        pull3.setVisibility(View.GONE);
        pull4.setVisibility(View.GONE);
        pull5.setVisibility(View.GONE);
        pull6.setVisibility(View.GONE);
        pull7.setVisibility(View.GONE);

        //Drops
        drop1.setVisibility(View.VISIBLE);
        drop2.setVisibility(View.VISIBLE);
        drop3.setVisibility(View.VISIBLE);
        drop4.setVisibility(View.VISIBLE);
        drop5.setVisibility(View.VISIBLE);
        drop6.setVisibility(View.VISIBLE);
        drop7.setVisibility(View.VISIBLE);

        //Catches
        catch1.setVisibility(View.VISIBLE);
        catch2.setVisibility(View.VISIBLE);
        catch3.setVisibility(View.VISIBLE);
        catch4.setVisibility(View.VISIBLE);
        catch5.setVisibility(View.VISIBLE);
        catch6.setVisibility(View.VISIBLE);
        catch7.setVisibility(View.VISIBLE);

        //Goals
        goal1.setVisibility(View.VISIBLE);
        goal2.setVisibility(View.VISIBLE);
        goal3.setVisibility(View.VISIBLE);
        goal4.setVisibility(View.VISIBLE);
        goal5.setVisibility(View.VISIBLE);
        goal6.setVisibility(View.VISIBLE);
        goal7.setVisibility(View.VISIBLE);

        //Throwaway bar
        dThrowaway.setVisibility(View.GONE);
        oThrowaway.setVisibility(View.VISIBLE);

        //They Scored
        theyScored.setVisibility(View.GONE);

    }

    public void theyScoredPutExtras(){

        Intent newIntent = new Intent(PlayGameActivity.this, MakeLineActivity.class);

        String oOrD = getIntent().getStringExtra("o/d");
        int pointCap = getIntent().getIntExtra("cap",0);
        String teamName = getIntent().getStringExtra("team");
        String oppName = getIntent().getStringExtra("opp");

        newIntent.putExtra("cap", pointCap); //Send over the point cap
        newIntent.putExtra("team", teamName); //Send over the team name
        newIntent.putExtra("opp", oppName); //Send over opponent name
        newIntent.putExtra("o/d", "Offense");

        //toastMessage("our score and theirs respectively " + ourScore + " " + oppScore);
        newIntent.putExtra("ourScore", ourScore); //Send updated team score
        newIntent.putExtra("oppScore", oppScore); //Send updated opp score

        startActivity(newIntent);
        finish();
    }

    public void weScoredPutExtras(){

        Intent newIntent = new Intent(PlayGameActivity.this, MakeLineActivity.class);

        String oOrD = getIntent().getStringExtra("o/d");
        int pointCap = getIntent().getIntExtra("cap",0);
        String teamName = getIntent().getStringExtra("team");
        String oppName = getIntent().getStringExtra("opp");

        newIntent.putExtra("cap", pointCap); //Send over the point cap
        newIntent.putExtra("team", teamName); //Send over the team name
        newIntent.putExtra("opp", oppName); //Send over opponent name
        newIntent.putExtra("o/d", "Defense");

        //toastMessage("our score and theirs respectively " + ourScore + " " + oppScore);
        newIntent.putExtra("ourScore", ourScore); //Send updated team score
        newIntent.putExtra("oppScore", oppScore); //Send updated opp score

        startActivity(newIntent);
        finish();
    }

    public void makeLineButton(){
        Intent newIntent = new Intent(PlayGameActivity.this, MakeLineActivity.class);

        String oOrD = getIntent().getStringExtra("o/d");
        int pointCap = getIntent().getIntExtra("cap",0);
        String teamName = getIntent().getStringExtra("team");
        String oppName = getIntent().getStringExtra("opp");

        newIntent.putExtra("cap", pointCap); //Send over the point cap
        newIntent.putExtra("team", teamName); //Send over the team name
        newIntent.putExtra("opp", oppName); //Send over opponent name

        if (oOrD.startsWith("O"))
            newIntent.putExtra("o/d", "Offense"); //Send next point start: offense/defense
        else
            newIntent.putExtra("o/d", "Defense");

        newIntent.putExtra("ourScore", ourScore); //Send updated team score
        newIntent.putExtra("oppScore", oppScore); //Send updated opp score

        startActivity(newIntent);
        finish();
    }

    public void checkScore(){
        int cap = getIntent().getIntExtra("cap",0);
        String opp = getIntent().getStringExtra("opp");
        String oOrD = getIntent().getStringExtra("o/d");
        String team = getIntent().getStringExtra("team");

        //toastMessage("cap is: " +cap+ " and our score is " +ourScore+ " and opp score is " + oppScore);

        Intent gameOverIntent = new Intent(PlayGameActivity.this, WinnerLoserActivity.class);
        gameOverIntent.putExtra("ourScore", ourScore);
        gameOverIntent.putExtra("oppScore", oppScore);
        gameOverIntent.putExtra("opp", opp);
        gameOverIntent.putExtra("o/d", oOrD);
        gameOverIntent.putExtra("team", team);
        startActivity(gameOverIntent);
        finish();
    }

    public void gameOverButton(){

        if (ourScore == oppScore){
            toastMessage("Game Cannot End In Tie");
            return;
        }

        int cap = getIntent().getIntExtra("cap",0);
        String opp = getIntent().getStringExtra("opp");
        String oOrD = getIntent().getStringExtra("o/d");
        String team = getIntent().getStringExtra("team");

        Intent gameOverIntent = new Intent(PlayGameActivity.this, WinnerLoserActivity.class);
        gameOverIntent.putExtra("ourScore", ourScore);
        gameOverIntent.putExtra("oppScore", oppScore);
        gameOverIntent.putExtra("opp", opp);
        gameOverIntent.putExtra("o/d", oOrD);
        gameOverIntent.putExtra("team", team);
        startActivity(gameOverIntent);
        finish();
    }
}
