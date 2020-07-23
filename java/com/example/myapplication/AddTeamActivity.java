package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class AddTeamActivity extends AppCompatActivity implements View.OnClickListener {

    private Button saveBtn;
    private EditText teamName;
    private Button playerBtn;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_team);

        saveBtn = (Button) findViewById(R.id.save);
        playerBtn = (Button) findViewById(R.id.players);
        teamName = (EditText) findViewById(R.id.teamName);

        saveBtn.setOnClickListener(this);
        playerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        String nameOfTeam = teamName.getText().toString();
        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.save:

                Toast toast = Toast.makeText(getApplicationContext(), "Team Name Cannot Be Empty", Toast.LENGTH_SHORT);

                //Make sure string not empty, so we don't save empty name
                if (nameOfTeam.equalsIgnoreCase( ""))
                    toast.show();

                else {
                    intent.putExtra("team", nameOfTeam);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;

            case R.id.players:
                nameOfTeam = teamName.getText().toString();

                if (nameOfTeam.length() != 0) {
                    intent = new Intent(AddTeamActivity.this, PlayerListActivity.class);
                    intent.putExtra("team", nameOfTeam);
                    startActivity(intent);
                }

                else {
                    Toast makeToast = Toast.makeText(getApplicationContext(), "Team name cannot be empty", Toast.LENGTH_SHORT);
                    makeToast.show();
                }

                break;
        }
    }

   /* public void addTeamToList(){
        saveBtn.setOnClickListener(new View.OnClickListener() {

            String nameOfTeam = teamName.getText().toString();
            Toast toast = Toast.makeText(getApplicationContext(), "Team Name Cannot Be Empty", Toast.LENGTH_LONG);

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                //Make sure string not empty, so we don't save empty name
                if (nameOfTeam.equalsIgnoreCase( ""))
                    toast.show();

                else {
                    intent.putExtra("team", nameOfTeam);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    public void addPlayerScreen(){
        playerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOfTeam = teamName.getText().toString();
                Intent intent = new Intent(AddTeamActivity.this, PlayerListActivity.class);
                intent.putExtra("team", nameOfTeam);
                startActivity(intent);
            }
        });
    }*/
}
