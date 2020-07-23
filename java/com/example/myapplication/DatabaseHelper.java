package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "Database Helper";
    public static final String TABLE_NAME = "Player_Roster";
    public static final String TABLE_NAME_GAMES = "Games";
    public static final String COL_0 = "Team_Name";
    public static final String COL_1 = "Player_Name";
    public static final String OPP = "Opponent";
    public static final String TOURNEY = "Tournament";
    public static final String OURSCORE = "Our_Score";
    public static final String OPPSCORE = "Opponent_Score";
    public static final String OD = "Next_Point_O/D";
    public static final String CAP = "Point_Cap";


    public DatabaseHelper(Context context){
        super(context, TAG,null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table Team name is col 1 and player name is col 2
        String createTable = "CREATE TABLE " + TABLE_NAME + " (Team_Name TEXT, " +COL_1+ " TEXT)";

        //Create table Team name is col 1 and opponent name is col 2
        String createGameTable = "CREATE TABLE " + TABLE_NAME_GAMES + " (Team_Name TEXT, " +OPP+ " TEXT, " + TOURNEY + " TEXT, " + OURSCORE + " INTEGER, " + OPPSCORE + " INTEGER, " + OD + " TEXT, " + CAP + " INTEGER)";
        String tryThis = "CREATE TABLE " + TABLE_NAME_GAMES + " (Team_Name TEXT, Opponent TEXT, Tournament TEXT, Our_Score INTEGER, Opponent_Score INTEGER, Next_Point_O_D TEXT, Point_Cap INTEGER)";

        db.execSQL(createTable);
        db.execSQL(tryThis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GAMES);
        onCreate(db);
    }

    public void updateGameData(int ourScore, int oppScore, String nextPoint, String opp){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Our_Score", ourScore);
        cv.put("Opponent_Score", oppScore);
        cv.put("Next_Point_O_D", nextPoint);

        String[] whereArgs = {opp};
        db.update(TABLE_NAME_GAMES, cv, "Opponent= ?", whereArgs);

    }

    public boolean addData(String teamName, String playerName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Put values into context values object
        values.put(COL_0, teamName);
        values.put(COL_1, playerName);

        long result = db.insert(TABLE_NAME,null, values);

        if (result == -1) //If -1 not inserted
            return false;
        else
            return true;
    }

    public boolean isPlayerTableEmpty(){

        SQLiteDatabase db = this.getWritableDatabase();

        boolean empty = true;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " +TABLE_NAME, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if (icount > 0)
            empty = false;
        cursor.close();
        return empty;
    }

    public boolean isGameTableEmpty(){

        SQLiteDatabase db = this.getWritableDatabase();

        boolean empty = true;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " +TABLE_NAME_GAMES, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if (icount > 0)
            empty = false;
        cursor.close();
        return empty;
    }

    public Cursor getData(String teamName){

        //Select data
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * FROM " + TABLE_NAME + "WHERE " + COL_0 + " = " + teamName + "?";
        //Cursor data = db.rawQuery(query,null);
        String whereClause = "Team_Name=?";
        String[] whereArgs = {teamName};
        String[] cols = {COL_0,COL_1};
        Cursor myData = db.query(TABLE_NAME, cols, whereClause, whereArgs, null, null, null);
        return myData;
    }

    public boolean addGame(String teamName, String opponentName, String tourney, int ourScore, int oppScore, String nextPoint, int pointCap){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Put values into context values object
        values.put(COL_0, teamName);
        values.put(OPP, opponentName);
        values.put(TOURNEY, tourney);
        values.put(OURSCORE, ourScore);
        values.put(OPPSCORE, oppScore);
        values.put("Next_Point_O_D", nextPoint);
        values.put(CAP, pointCap);

        long result = db.insert(TABLE_NAME_GAMES,null, values);

        if (result == -1) //If -1 not inserted
            return false;
        else
            return true;
    }

    public Cursor getGames(String teamName){

        SQLiteDatabase db = this.getReadableDatabase();

        //Grab data where team name equals given name
        String whereClause = "Team_Name=?";
        String[] whereArgs = {teamName};

        //Grab team name column and opponent column
        String[] cols = {COL_0,OPP,TOURNEY,OURSCORE,OPPSCORE,"Next_Point_O_D",CAP};

        //Return cursor with all data that matches criteria
        Cursor myData = db.query(TABLE_NAME_GAMES, cols, whereClause, whereArgs, null, null, null);
        return myData;
    }

    public Cursor getSpecificGame(String opponent){

        SQLiteDatabase db = this.getReadableDatabase();

        //Grab data where team name equals given name
        String whereClause = "Opponent=?";
        String[] whereArgs = {opponent};

        //Grab team name column and opponent column
        String[] cols = {COL_0,OPP,TOURNEY,OURSCORE,OPPSCORE,"Next_Point_O_D",CAP};

        //Return cursor with all data that matches criteria
        Cursor myData = db.query(TABLE_NAME_GAMES, cols, whereClause, whereArgs, null, null, null);
        return myData;

    }

}
