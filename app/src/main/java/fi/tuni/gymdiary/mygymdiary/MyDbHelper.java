package fi.tuni.gymdiary.mygymdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fi.tuni.gymdiary.mygymdiary.exercise.Set;
import fi.tuni.gymdiary.mygymdiary.exercise.Exercise;
import fi.tuni.gymdiary.mygymdiary.weight.Weight;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //Table names
    private static final String WEIGHT_TABLE = "weight";
    private static final String EXERCISE_TABLE = "exercise";
    private static final String SET_TABLE = "sets";

    //Common columns name
    private static final String KEY_ID = "ID";
    private static final String KEY_DATE = "date";


    //Weight and Set table- columns
    private static final String KEY_WEIGHT = "weight";

    //Exercise nad Set
    private static final String KEY_EXERCISE = "exercise";

    //Set table- columns
    private static final String KEY_SETS = "sets";
    private static final String KEY_REPS = "reps";
    private static final String KEY_EXERCISE_ID = "exerciseId";

    //Create table statement for weight
    private static final String CREATE_TABLE_WEIGHT = "CREATE TABLE "
            + WEIGHT_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE
            + " DATE," + KEY_WEIGHT + " REAL"  + ")";

    //Create table statement for exercise
    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE "
            + EXERCISE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," +KEY_EXERCISE + " TEXT" + ")";

    //Create table statement for activity
    private static final String CREATE_TABLE_SET = "CREATE TABLE "
            + SET_TABLE + "( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE
            + " DATE," + KEY_EXERCISE_ID + " INTEGER," + KEY_SETS +" INTEGER,"
            +KEY_REPS +" INTEGER," + KEY_WEIGHT +" REAL" + ")";


    public MyDbHelper(Context context){
        super(context,"gymdiaryDatabase",null,DATABASE_VERSION);

    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCISE);
        db.execSQL(CREATE_TABLE_WEIGHT);
        db.execSQL(CREATE_TABLE_SET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEIGHT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SET_TABLE);
        onCreate(db);
    }

    public void addExercise(Exercise e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISE, e.getExercise());
        db.insert(EXERCISE_TABLE, null, values);
        db.close();
    }

    public void addSet(Set a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        values.put(KEY_DATE, dateFormat.format(a.getDate()));
        values.put(KEY_EXERCISE_ID, a.getExerciseId());
        values.put(KEY_SETS, a.getSets());
        values.put(KEY_REPS, a.getReps());
        values.put(KEY_WEIGHT, a.getWeight());


        db.insert(SET_TABLE, null, values);
        db.close();
    }

    public void addBodyWeight(Weight weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        values.put(KEY_DATE, dateFormat.format(weight.getDate()));
        values.put(KEY_WEIGHT, weight.getWeight());
        db.insert(WEIGHT_TABLE, null, values);
        db.close();
    }

    public ArrayList getAllBodyWeights() {
        ArrayList<Weight> weightList = new ArrayList<Weight>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + WEIGHT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weight weight = new Weight();
                weight.setId(Integer.parseInt(cursor.getString(0)));
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                Date date = new Date();
                try {
                    date = simpleDateFormat.parse(cursor.getString(1));
                    Log.d("MyTag", ""+simpleDateFormat.format(date));

                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d("MyTag", e.toString());

                }
                weight.setDate(date);
                weight.setWeight(Double.parseDouble(cursor.getString(2)));
                weightList.add(weight);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return weightList;
    }

    public ArrayList getAllExercises() {
        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + EXERCISE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setId(Integer.parseInt(cursor.getString(0)));
                exercise.setExercise(cursor.getString(1));
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return exerciseList;
    }

    public ArrayList getAllSetsByExercise(Exercise exercise) {
        ArrayList<Set> setList = new ArrayList<Set>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SET_TABLE +" WHERE "+KEY_EXERCISE_ID +" = "+exercise.getId();

        Log.d("MyTag", ""+selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Set set = new Set();
                set.setId(Integer.parseInt(cursor.getString(0)));
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                Date date = new Date();
                try {
                    date = simpleDateFormat.parse(cursor.getString(1));
                    Log.d("MyTag", ""+simpleDateFormat.format(date));

                } catch (ParseException e) {
                    Log.d("MyTag", e.toString());
                    e.printStackTrace();
                }
                set.setDate(date);
                set.setExerciseId(Integer.parseInt(cursor.getString(2)));
                set.setSets(Integer.parseInt(cursor.getString(3)));
                set.setReps(Integer.parseInt(cursor.getString(4)));
                set.setWeight(Integer.parseInt(cursor.getString(5)));
                setList.add(set);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return setList;
    }

    public void deleteSet(int setId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQueryQuery = "DELETE FROM" + SET_TABLE +" WHERE " +KEY_ID+" = " + setId;
        String[] whereArgs = {KEY_EXERCISE_ID +" = " +setId };
      //  Log.d("MyTag", deleteQueryQuery);
        String where = KEY_ID+" = "+setId;
        db.delete(SET_TABLE,where,null);
        db.close();
    }

    public void deleteWeight(int weightId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQueryQuery = "DELETE FROM" + WEIGHT_TABLE +" WHERE " +KEY_ID+" = " + weightId;
        String[] whereArgs = {KEY_EXERCISE_ID +" = " +weightId };
        //  Log.d("MyTag", deleteQueryQuery);
        String where = KEY_ID+" = "+weightId;
        db.delete(WEIGHT_TABLE,where,null);
        db.close();
    }

}
