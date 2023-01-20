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

    //Constantes sempre em maiusculas
    private final static String DB_NAME = "polarfitnesssolutions";
    //TABELA user
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

    //TABELA nutrition_plan
    private final static String nutrition_plan_TABLE_NAME = "nutrition_plan";
    private final static String nutrition_plan_ID = "id";
    private final static String nutrition_plan_NUTRITIONNAME = "nutritionname";
    private final static String nutrition_plan_CONTENT = "content";
    private final static String nutrition_plan_CLIENT_ID = "client_id";
    private final static String nutrition_plan_WORKER_ID = "worker_id";

    private SQLiteDatabase db;

    public UserDBHelper(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String sqltable1 = "CREATE TABLE " + user_TABLE_NAME + " ("
                + user_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + user_USERNAME + " TEXT,"
                + user_EMAIL + " TEXT,"
                + user_STREET + " TEXT,"
                + user_ZIP_CODE + " INTEGER,"
                + user_AREA + " TEXT,"
                + user_PHONE_NUMBER + " INTEGER,"
                + user_NIF + " INTEGER);";
        String sqltable2 = "CREATE TABLE " + nutrition_plan_TABLE_NAME + " ("
                + nutrition_plan_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nutrition_plan_NUTRITIONNAME + " TEXT,"
                + nutrition_plan_CONTENT + " TEXT,"
                + nutrition_plan_CLIENT_ID + " INTEGER,"
                + nutrition_plan_WORKER_ID + " INTEGER);";
        db.execSQL(sqltable1);
        db.execSQL(sqltable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + user_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + nutrition_plan_TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //metodos CRUD

    //USER
    //CREATE
    public User addUserBD(User user)
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

        long id = this.db.insert(user_TABLE_NAME, null, values);

        if(id > -1)
        {
            user.setId((int) id);
            return user;
        }
        return null;
    }
    //EDIT
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
    //DELETE
    public boolean removeUserBD(long id)
    {
        int nreg = this.db.delete(user_TABLE_NAME, user_ID + " =?", new String[]{"" + id});
        return nreg > 0;
    }
    //GET ALL USERS
    public ArrayList<User> getAllUserBD()
    {
        ArrayList<User> listUsers = new ArrayList<>();
        Cursor cursor = this.db.query(user_TABLE_NAME, new String[]{user_ID, user_USERNAME, user_EMAIL, user_STREET, user_ZIP_CODE, user_AREA, user_PHONE_NUMBER, user_NIF}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                User user = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getInt(7));
                listUsers.add(user);
            } while (cursor.moveToNext());
        }
        return listUsers;
    }
    //REMOVE ALL USERS
    public void removeAllUsersBD() {
        db.delete(user_TABLE_NAME, null, null);
    }

    //Nutrition_plan
    //CREATE
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
    //EDIT
    public boolean editNutrition_planBD(Nutrition_plan nutrition_plan)
    {

        ContentValues values = new ContentValues();
        values.put(nutrition_plan_ID, nutrition_plan.getId());
        values.put(nutrition_plan_NUTRITIONNAME, nutrition_plan.getNutritionname());
        values.put(nutrition_plan_CONTENT, nutrition_plan.getContent());
        values.put(nutrition_plan_CLIENT_ID, nutrition_plan.getClient_id());
        values.put(nutrition_plan_WORKER_ID, nutrition_plan.getWorker_id());
        int nreg = this.db.update(nutrition_plan_TABLE_NAME, values, nutrition_plan_ID + " = ?", new String[]{"" + nutrition_plan.getId()});

        return nreg >0;
    }
    //DELETE
    public boolean removeNutrition_planBD(long id)
    {
        int nreg = this.db.delete(nutrition_plan_TABLE_NAME, nutrition_plan_ID + " =?", new String[]{"" + id});
        return nreg > 0;
    }
    //GET ALL Nutrition_plan
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
    //REMOVE ALL Nutrition_plan
    public void removeAllNutrition_planBD() {
        db.delete(nutrition_plan_TABLE_NAME, null, null);
    }



}
