package com.polar.fitness.solutions.mobileapp.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Camera;

import java.util.ArrayList;
import java.util.zip.ZipEntry;


public class UserDBHelper extends SQLiteOpenHelper {


    private final static String DB_NAME = "polarfitnesssolutions";

    //Tabelas

    //user
    private final static String user_TABLE_NAME = "User";
    private final static int DB_VERSION = 1;
    private final static String user_ID = "id";
    private final static String user_USERNAME = "username";
    private final static String user_EMAIL = "email";
    private final static String user_STREET = "street";
    private final static String user_ZIP_CODE = "zip_code";
    private final static String user_AREA = "area";
    private final static String user_PHONE_NUMBER = "phone_number";
    private final static String user_NIF = "nif";
    private final static String user_GENDER = "gender";

    //nutrition_plan
    private final static String nutrition_plan_TABLE_NAME = "nutrition_plan";
    private final static String nutrition_plan_ID = "id";
    private final static String nutrition_plan_NUTRITIONNAME = "nutritionname";
    private final static String nutrition_plan_CONTENT = "content";
    private final static String nutrition_plan_CLIENT_ID = "client_id";
    private final static String nutrition_plan_WORKER_ID = "worker_id";

    //workout_plan
    private final static String workout_plan_TABLE_NAME = "workout_plan";
    private final static String workout_plan_ID = "id";
    private final static String workout_plan_WORKOUT_NAME = "workout_name";
    private final static String workout_plan_CLIENT_ID = "client_id";
    private final static String workout_plan_WORKER_ID = "worker_id";

    //exercise
    private final static String exercise_TABLE_NAME = "exercise";
    private final static String exercise_ID = "id";
    private final static String exercise_EXERCISE_NAME = "exercise_name";
    private final static String exercise_MAX_REP = "max_rep";
    private final static String exercise_MIN_REP = "min_rep";
    private final static String exercise_SETS = "sets";

    //workout_plan_exercise_relation
    private final static String workout_plan_exercise_relation_TABLE_NAME = "workout_plan_exercise_relation";
    private final static String workout_plan_exercise_relation_ID = "id";
    private final static String workout_plan_exercise_relation_WORKOUT_PLAN_ID = "workout_plan_id";
    private final static String workout_plan_exercise_relation_EXERCISE_ID = "exercise_id";

    //workout
    private final static String workout_TABLE_NAME = "workout";
    private final static String workout_ID = "id";
    private final static String workout_NAME = "name";
    private final static String workout_DATE = "date";
    private final static String workout_DURATION = "duration";
    private final static String workout_WORKOUT_PLAN_NAME = "workout_plan_name";
    private final static String workout_WORKOUT_PLAN_ID = "workout_plan_id";

    private SQLiteDatabase db;

    public UserDBHelper(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqltable1 = "CREATE TABLE " + user_TABLE_NAME + " ("
                + user_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + user_USERNAME + " TEXT,"
                + user_EMAIL + " TEXT,"
                + user_STREET + " TEXT,"
                + user_ZIP_CODE + " INTEGER,"
                + user_AREA + " TEXT,"
                + user_PHONE_NUMBER + " INTEGER,"
                + user_NIF + " INTEGER,"
                + user_GENDER + " TEXT);";
        String sqltable2 = "CREATE TABLE " + nutrition_plan_TABLE_NAME + " ("
                + nutrition_plan_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nutrition_plan_NUTRITIONNAME + " TEXT,"
                + nutrition_plan_CONTENT + " TEXT,"
                + nutrition_plan_CLIENT_ID + " INTEGER,"
                + nutrition_plan_WORKER_ID + " INTEGER);";
        String sqltable3 = "CREATE TABLE " + workout_plan_TABLE_NAME + "("
                + workout_plan_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + workout_plan_WORKOUT_NAME + " TEXT NOT NULL, "
                + workout_plan_CLIENT_ID + " INTEGER NOT NULL, "
                + workout_plan_WORKER_ID + " INTEGER NOT NULL);";
        String sqltable4 = "CREATE TABLE " + exercise_TABLE_NAME + "("
                + exercise_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + exercise_EXERCISE_NAME + " TEXT NOT NULL, "
                + exercise_MAX_REP + " INTEGER NOT NULL, "
                + exercise_MIN_REP + " INTEGER NOT NULL, "
                + exercise_SETS + " INTEGER NOT NULL);";
        String sqltable5 = "CREATE TABLE " + workout_plan_exercise_relation_TABLE_NAME + "("
                + workout_plan_exercise_relation_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + workout_plan_exercise_relation_WORKOUT_PLAN_ID + " INTEGER  NOT NULL,"
                + workout_plan_exercise_relation_EXERCISE_ID + " INTEGER NOT NULL);";
        String sqltable6 = "CREATE TABLE " + workout_TABLE_NAME + "("
                + workout_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + workout_NAME + " TEXT NOT NULL, "
                + workout_DATE + " TEXT NOT NULL, "
                + workout_DURATION + " TEXT NOT NULL, "
                + workout_WORKOUT_PLAN_NAME + " TEXT NOT NULL, "
                + workout_WORKOUT_PLAN_ID + " INTEGER NOT NULL);";

        db.execSQL(sqltable1);
        db.execSQL(sqltable2);
        db.execSQL(sqltable3);
        db.execSQL(sqltable4);
        db.execSQL(sqltable5);
        db.execSQL(sqltable6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + user_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + nutrition_plan_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + workout_plan_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + exercise_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + workout_plan_exercise_relation_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + workout_TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //metodos CRUD

    //User
    public ArrayList<String> addUserBD(ArrayList<String> users)
    {
        ContentValues values = new ContentValues();
        values.put(user_ID, users.get(0));
        values.put(user_USERNAME, users.get(1));
        values.put(user_EMAIL, users.get(2));
        values.put(user_STREET, users.get(3));
        values.put(user_ZIP_CODE, users.get(4));
        values.put(user_AREA, users.get(5));
        values.put(user_PHONE_NUMBER, users.get(6));
        values.put(user_NIF, users.get(7));
        values.put(user_GENDER, users.get(8));

        this.db.insert(user_TABLE_NAME, null, values);
        return users;
    }

    public boolean editUserBD(User user)
    {
        ContentValues values = new ContentValues();
        values.put(user_ID, user.getId());
        values.put(user_USERNAME, user.getUsername());
        values.put(user_EMAIL, user.getEmail());
        values.put(user_STREET, user.getStreet());
        values.put(user_ZIP_CODE, user.getZip_code());
        values.put(user_AREA, user.getArea());
        values.put(user_PHONE_NUMBER, user.getPhone_number());
        values.put(user_NIF, user.getNif());
        int nreg = this.db.update(user_TABLE_NAME, values, user_ID + " = ?", new String[]{"" + user.getId()});

        return nreg >0;
    }

    public boolean removeUserBD(long id)
    {
        int nreg = this.db.delete(user_TABLE_NAME, user_ID + " =?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<User> getUserBD()
    {
        ArrayList<User> listUsers = new ArrayList<>();
        Cursor cursor = this.db.query(user_TABLE_NAME, new String[]{user_ID, user_USERNAME, user_EMAIL, user_STREET, user_ZIP_CODE, user_AREA, user_PHONE_NUMBER, user_NIF, user_GENDER}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                User user = new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getString(8));

                listUsers.add(user);
            } while (cursor.moveToNext());
        }
        return listUsers;
    }

    public void removeAllUsersBD() {
        db.delete(user_TABLE_NAME, null, null);
    }


    //Planos de nutrição
    public Nutrition_plan addNutrition_planBD(Nutrition_plan nutrition_plan)
    {
        ContentValues values = new ContentValues();
        values.put(nutrition_plan_ID, nutrition_plan.getId());
        values.put(nutrition_plan_NUTRITIONNAME, nutrition_plan.getNutritionname());
        values.put(nutrition_plan_CONTENT, nutrition_plan.getContent());
        values.put(nutrition_plan_CLIENT_ID, nutrition_plan.getClient_id());
        values.put(nutrition_plan_WORKER_ID, nutrition_plan.getWorker_id());
        long id = this.db.insert(nutrition_plan_TABLE_NAME, null, values);

        if(id > -1)
        {
            nutrition_plan.setId((int) id);
            return nutrition_plan;

        }
        return null;
    }

    public ArrayList<Nutrition_plan> getAllNutrition_planBD()
    {
        ArrayList<Nutrition_plan> listNutrition_plan = new ArrayList<>();
        Cursor cursor = this.db.query(nutrition_plan_TABLE_NAME, new String[]{nutrition_plan_ID, nutrition_plan_NUTRITIONNAME, nutrition_plan_CONTENT, nutrition_plan_CLIENT_ID, nutrition_plan_WORKER_ID}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Nutrition_plan nutrition_plan = new Nutrition_plan(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4));
                listNutrition_plan.add(nutrition_plan);
            } while (cursor.moveToNext());
        }
        return listNutrition_plan;
    }

    public void removeAllNutrition_planBD() {
        db.delete(nutrition_plan_TABLE_NAME, null, null);
    }

    //Exercícios
    public Exercise addExerciseBD(Exercise exercise){
        ContentValues values = new ContentValues();
        values.put(exercise_ID, exercise.getId());
        values.put(exercise_EXERCISE_NAME, exercise.getExercise_name());
        values.put(exercise_MAX_REP, exercise.getMax_rep());
        values.put(exercise_MIN_REP, exercise.getMin_rep());
        values.put(exercise_SETS, exercise.getSets());

        long id = this.db.insert(exercise_TABLE_NAME, null, values);
        if (id > -1){
            exercise.setId((int)id);
            return exercise;
        }
        return null;
    }

    public ArrayList<Exercise> getAllExercisesDB(){
        ArrayList<Exercise> listExercises = new ArrayList<>();
        Cursor cursor =this.db.query(exercise_TABLE_NAME, new String[]{exercise_ID,exercise_EXERCISE_NAME, exercise_MAX_REP, exercise_MIN_REP, exercise_SETS}, null, null, null,null, null);
        if(cursor.moveToFirst()){
            do {
                Exercise aux = new Exercise
                        (cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getInt(3),
                                cursor.getInt(4));
                listExercises.add(aux);
            }while (cursor.moveToNext());
        }
        return listExercises;
    }

    public void removeAllExercisesDB(){
        db.delete(exercise_TABLE_NAME, null , null);
    }

    //Planos de treino
    public Workout_plan addWorkout_planDB(Workout_plan workout_plan){
        ContentValues values = new ContentValues();
        values.put(workout_plan_ID, workout_plan.getId());
        values.put(workout_plan_WORKOUT_NAME, workout_plan.getWorkout_name());
        values.put(workout_plan_CLIENT_ID, workout_plan.getClient_id());
        values.put(workout_plan_WORKER_ID, workout_plan.getWorker_id());
        long id = this.db.insert(workout_plan_TABLE_NAME, null, values);

        if (id > -1)
        {
            workout_plan.setId((int) id);
            return workout_plan;
        }
        return null;
    }

    public boolean editWorkout_planDB(Workout_plan workout_plan){
        ContentValues values = new ContentValues();
        values.put(workout_plan_ID, workout_plan.getId());
        values.put(workout_plan_WORKOUT_NAME, workout_plan.getWorkout_name());
        values.put(workout_plan_CLIENT_ID, workout_plan.getClient_id());
        values.put(workout_plan_WORKER_ID, workout_plan.getWorker_id());
        int nreg = this.db.update(workout_plan_TABLE_NAME, values, workout_plan_ID + " = ?", new String[]{"" + workout_plan.getId()});

        return nreg > 0;
    }

    public boolean removeWorkout_planDB(long id){
        int nreg = this.db.delete(workout_plan_TABLE_NAME, workout_plan_ID + " =?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<Workout_plan> getAllWorkout_planDB()
    {
        ArrayList<Workout_plan> listWorkout_plan = new ArrayList<>();
        Cursor cursor = this.db.query(workout_plan_TABLE_NAME, new String[]{workout_plan_ID, workout_plan_WORKOUT_NAME, workout_plan_CLIENT_ID, workout_plan_WORKER_ID}, null, null, null,null, null);
        if(cursor.moveToFirst()){
            do{
                Workout_plan aux = new Workout_plan(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3));
                listWorkout_plan.add(aux);
            }while (cursor.moveToNext());
        }
        return listWorkout_plan;
    }

    public void removeAllWorkout_planDB(){
        db.delete(workout_plan_TABLE_NAME, null, null);
    }

    //Relaçao Plano de treino e Exercício
    public Workout_Plan_Exercise_Relation addWorkout_Plan_Exercise_RelationBD(Workout_Plan_Exercise_Relation workout_plan_exercise_relation)
    {
        ContentValues values = new ContentValues();
        values.put(workout_plan_exercise_relation_ID, workout_plan_exercise_relation.getId());
        values.put(workout_plan_exercise_relation_WORKOUT_PLAN_ID, workout_plan_exercise_relation.getWorkout_plan_id());
        values.put(workout_plan_exercise_relation_EXERCISE_ID, workout_plan_exercise_relation.getExercise_id());
        long id = this.db.insert(workout_plan_exercise_relation_TABLE_NAME, null, values);

        if (id > -1)
        {
            workout_plan_exercise_relation.setId((int) id);
            return workout_plan_exercise_relation;
        }

        return null;
    }
    public ArrayList<Workout_Plan_Exercise_Relation> getAllWorkout_Plan_Exercise_RelationBD()
    {
        ArrayList<Workout_Plan_Exercise_Relation> listWorkout_Plan_Exercise_Relation = new ArrayList<>();
        Cursor cursor = this.db.query(workout_plan_exercise_relation_TABLE_NAME, new String[]{workout_plan_exercise_relation_ID, workout_plan_exercise_relation_WORKOUT_PLAN_ID, workout_plan_exercise_relation_EXERCISE_ID}, null, null, null,null, null);
        if(cursor.moveToFirst()){
            do{
                Workout_Plan_Exercise_Relation aux = new Workout_Plan_Exercise_Relation(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2));
                listWorkout_Plan_Exercise_Relation.add(aux);
            }while (cursor.moveToNext());
        }
        return listWorkout_Plan_Exercise_Relation;
    }

    public void removeAllWorkout_Plan_Exercise_Relation()
    {
        db.delete(workout_plan_exercise_relation_TABLE_NAME, null, null);
    }

    //Workout
    public Workout addWorkoutBD(Workout workout)
    {
        ContentValues values = new ContentValues();
        values.put(workout_NAME, workout.getName());
        values.put(workout_DATE, workout.getDate());
        values.put(workout_DURATION, workout.getDuration());
        values.put(workout_WORKOUT_PLAN_NAME, workout.getWorkout_planName());
        values.put(workout_WORKOUT_PLAN_ID, workout.getWorkout_planId());

        long id = this.db.insert(workout_TABLE_NAME, null, values);

        if (id > -1)
        {
            workout.setId((int) id);
            return workout;
        }

        return null;
    }

    public ArrayList<Workout> getAllWorkoutBD()
    {
        ArrayList<Workout> workout = new ArrayList<>();
        Cursor cursor = this.db.query(workout_TABLE_NAME, new String[]{workout_ID, workout_NAME, workout_DATE, workout_DURATION,workout_WORKOUT_PLAN_NAME,workout_WORKOUT_PLAN_ID}, null, null, null,null, null);
        if(cursor.moveToFirst()){
            do{
                Workout aux = new Workout(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5));
                workout.add(aux);
            }while (cursor.moveToNext());
        }
        return workout;
    }

    public void removeAllWorkoutDB(){
        db.delete(workout_TABLE_NAME, null, null);
    }

    public boolean removeWorkoutDB(long id){
        int nreg = this.db.delete(workout_TABLE_NAME, workout_ID + " =?", new String[]{"" + id});
        return nreg > 0;
    }

}
