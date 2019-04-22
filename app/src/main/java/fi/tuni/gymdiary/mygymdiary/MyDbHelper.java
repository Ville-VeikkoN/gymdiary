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

/**
 * Class for database connection
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class MyDbHelper extends SQLiteOpenHelper {

    /**
     * Version of the database
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Table name for the weights
     */
    private static final String WEIGHT_TABLE = "weight";
    /**
     * Table name for the exercises
     */
    private static final String EXERCISE_TABLE = "exercise";
    /**
     * Table name for the sets
     */
    private static final String SET_TABLE = "sets";
    /**
     * Column name for the id
     */
    private static final String KEY_ID = "ID";
    /**
     * Column name for the date
     */
    private static final String KEY_DATE = "date";
    /**
     * Column name for the weight
     */
    private static final String KEY_WEIGHT = "weight";
    /**
     * Column name for the exercise
     */
    private static final String KEY_EXERCISE = "exercise";
    /**
     * Column name for the sets
     */
    private static final String KEY_SETS = "sets";
    /**
     * Column name for the reps
     */
    private static final String KEY_REPS = "reps";
    /**
     * Column name for the exerciseId
     */
    private static final String KEY_EXERCISE_ID = "exerciseId";
    /**
     * String to create weight table
     */
    private static final String CREATE_TABLE_WEIGHT = "CREATE TABLE "
            + WEIGHT_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE
            + " DATE," + KEY_WEIGHT + " REAL"  + ")";
    /**
     * String to create exercise table
     */
    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE "
            + EXERCISE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," +KEY_EXERCISE + " TEXT" + ")";
    /**
     * String to create set table
     */
    private static final String CREATE_TABLE_SET = "CREATE TABLE "
            + SET_TABLE + "( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE
            + " DATE," + KEY_EXERCISE_ID + " INTEGER," + KEY_SETS +" INTEGER,"
            +KEY_REPS +" INTEGER," + KEY_WEIGHT +" REAL" + ")";


    /**
     * Constructor for MyDbHelper
     *
     * @param context Context
     */
    public MyDbHelper(Context context){
        super(context,"gymdiaryDatabase",null,DATABASE_VERSION);

    };

    /**
     * Method called when MyDbHelper is created.
     * Creates tables for exercise, set and weight
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCISE);
        db.execSQL(CREATE_TABLE_WEIGHT);
        db.execSQL(CREATE_TABLE_SET);
    }

    /**
     * Method called if version number has changed.
     *
     * @param db SQLiteDatabase
     * @param oldVersion Integer containing old version number
     * @param newVersion Integer containing new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEIGHT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SET_TABLE);
        onCreate(db);
    }

    /**
     * Adds exercise to database.
     *
     * @param e Exercise containing info about exercise
     */
    public void addExercise(Exercise e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISE, e.getExercise());
        db.insert(EXERCISE_TABLE, null, values);
        db.close();
    }

    /**
     * Adds set to database.
     *
     * @param s Set containing info about set
     */
    public void addSet(Set s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        values.put(KEY_DATE, dateFormat.format(s.getDate()));
        values.put(KEY_EXERCISE_ID, s.getExerciseId());
        values.put(KEY_SETS, s.getSets());
        values.put(KEY_REPS, s.getReps());
        values.put(KEY_WEIGHT, s.getWeight());


        db.insert(SET_TABLE, null, values);
        db.close();
    }

    /**
     * Adds weight to database.
     *
     * @param weight Weight containing info about weight
     */
    public void addBodyWeight(Weight weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        values.put(KEY_DATE, dateFormat.format(weight.getDate()));
        values.put(KEY_WEIGHT, weight.getWeight());
        db.insert(WEIGHT_TABLE, null, values);
        db.close();
    }

    /**
     * Gets all weights from database and returns them.
     *
     * @return ArrayList representing weights from database
     */
    public ArrayList getAllBodyWeights() {
        ArrayList<Weight> weightList = new ArrayList<Weight>();
        String selectQuery = "SELECT  * FROM " + WEIGHT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

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
        return weightList;
    }

    /**
     * Gets all exercises from database and returns them.
     *
     * @return ArrayList representing all weights from database
     */
    public ArrayList getAllExercises() {
        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        String selectQuery = "SELECT  * FROM " + EXERCISE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setId(Integer.parseInt(cursor.getString(0)));
                exercise.setExercise(cursor.getString(1));
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }
        db.close();
        return exerciseList;
    }

    /**
     * Gets all sets by exercise from database and returns them.
     *
     * @param exercise Exercise containing info about exercise
     * @return ArrayList representing all Sets for the exercise
     */
    public ArrayList getAllSetsByExercise(Exercise exercise) {
        ArrayList<Set> setList = new ArrayList<Set>();
        String selectQuery = "SELECT  * FROM " + SET_TABLE +" WHERE "+KEY_EXERCISE_ID +" = "+exercise.getId();

        Log.d("MyTag", ""+selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

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
                set.setWeight(Double.parseDouble(cursor.getString(5)));
                setList.add(set);
            } while (cursor.moveToNext());
        }
        db.close();
        return setList;
    }

    public void deleteSet(int setId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID+" = "+setId;
        db.delete(SET_TABLE,where,null);
        db.close();
    }

    /**
     * Removes all sets by exercise from the database.
     *
     * @param exerciseId Integer containing id of the exercise
     */
    public void deleteAllSets(int exerciseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_EXERCISE_ID+" = "+exerciseId;
        db.delete(SET_TABLE,where,null);
        db.close();
    }

    /**
     * Removes weight from the database
     *
     * @param weightId Integer containing id of the weight
     */
    public void deleteWeight(int weightId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID+" = "+weightId;
        db.delete(WEIGHT_TABLE,where,null);
        db.close();
    }

    /**
     * Removes exercise from the database.
     *
     * @param exerciseId Integer containing id of the exercise
     */
    public void deleteExercise(int exerciseId) {
        deleteAllSets(exerciseId);

        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID+" = "+exerciseId;
        db.delete(EXERCISE_TABLE,where,null);
        db.close();
    }

}
