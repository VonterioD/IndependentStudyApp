package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private Button addTeamButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> allTeams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTeamButton = (Button) findViewById(R.id.addTeamButton);
        listView = (ListView) findViewById(R.id.teamList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allTeams);

        //Add Team button functionality
        addTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddTeamActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //List View Click Functionality
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                //Get the item clicked, put the name of team in intent, then go to edit team screen
                String nameOfTeam = parent.getItemAtPosition(i).toString();
                Intent listIntent = new Intent(MainActivity.this, EditTeamActivity.class);
                listIntent.putExtra("teamName", nameOfTeam);

                //Save and put list item pos into intent also when it is time to return we can update
                int pos = i;
                listIntent.putExtra("listPos", pos);
                //startActivity(listIntent);
                startActivityForResult(listIntent,2);

        }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){

            String currTeam = data.getStringExtra("team"); //Get team name from intent
            if (currTeam != null) {
                allTeams.add(currTeam); //Add team from intent to the list
                adapter.notifyDataSetChanged(); //Update adapter
            }

            listView.setAdapter(adapter); //Link listview to adapter to show on screen
        }

        if (requestCode == 2 && resultCode == RESULT_OK){

            String currTeam = data.getStringExtra("teamName"); //Get team name from intent
            int listPos = data.getIntExtra("listPos",0);

            allTeams.set(listPos, currTeam); //Add team from intent to the list
            adapter.notifyDataSetChanged(); //Update adapter

            listView.setAdapter(adapter); //Link listview to adapter to show on screen
        }
    }

    protected void onResume(){
        super.onResume();

        /*String currTeam = getIntent().getStringExtra("teamName"); //Get team name from intent
        int listPos = getIntent().getIntExtra("listPos",0);
        allTeams.set(listPos, currTeam); //Add team from intent to the list at the given index
        adapter.notifyDataSetChanged(); //Update adapter


        listView.setAdapter(adapter); //Link listview to adapter to show on screen*/
    }

    protected void updateTeamList(){
        String currTeam = getIntent().getStringExtra("teamName"); //Get team name from intent
        int listPos = getIntent().getIntExtra("listPos",0);
        allTeams.set(listPos, currTeam); //Add team from intent to the list at the given index
        adapter.notifyDataSetChanged(); //Update adapter

        listView.setAdapter(adapter); //Link listview to adapter to show on screen
    }
}
