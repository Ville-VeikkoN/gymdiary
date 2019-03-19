package fi.tuni.gymdiary.mygymdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    //Table names
    private static final String WEIGHT_TABLE = "weight";
    private static final String EXERCISE_TABLE = "exercise";

    //Common columns name
    private static final String KEY_ID = "ID";
    private static final String KEY_DATE = "date";

    //Weight table- columns
    private static final String KEY_WEIGHT = "weight";

    //Exercise table- columns
    private static final String KEY_ACTIVITY = "activity";
    private static final String KEY_SETS = "sets";
    private static final String KEY_REPS = "reps";
    private static final String KEY_WEIGHTAMOUNT = "weight";

    //Create table statement for weight
    private static final String CREATE_TABLE_WEIGHT = "CREATE TABLE "
            + WEIGHT_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE
            + " DATE," + KEY_WEIGHT + " REAL"  + ")";

    //Create table statement for exercise
    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE "
            + EXERCISE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE
            + " DATE," + KEY_ACTIVITY + " TEXT," + KEY_SETS +"INTEGER,"
            + KEY_REPS + "INTEGER," + KEY_WEIGHTAMOUNT + "REAL" + ")";


    public MyDbHelper(Context context){
        super(context,"tableName",null,1);
    };

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createWeightTable() {

    }

    public void createExercisesTable() {

    }
}
