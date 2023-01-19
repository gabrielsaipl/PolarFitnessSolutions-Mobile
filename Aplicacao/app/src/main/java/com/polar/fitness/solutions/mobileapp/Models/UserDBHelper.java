package com.polar.fitness.solutions.mobileapp.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Camera;

import java.util.ArrayList;


public class UserDBHelper extends SQLiteOpenHelper {

    //Constantes sempre em maiusculas
    private final static String DB_NAME = "polarfitnesssolutions";
    private final static String TABLE_NAME = "User";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String USERNAME = "username";
    private final static String EMAIL = "email";
    private final static String STREET = "street";
    private final static String ZIP_CODE = "zip_code";
    private final static String AREA = "area";
    private final static String PHONE_NUMBER = "phone_number";
    private final static String NIF = "nif";

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
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT,"
                + EMAIL + " TEXT,"
                + STREET + " TEXT,"
                + ZIP_CODE + " INTEGER,"
                + AREA + " TEXT,"
                + PHONE_NUMBER + " INTEGER,"
                + NIF + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //metodos CRUD

    //CREATE
    public User addUserBD(User user)
    {
        ContentValues values = new ContentValues();
        values.put(ID, user.getId());
        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(STREET, user.getStreet());
        values.put(ZIP_CODE, user.getZip_code());
        values.put(AREA, user.getArea());
        values.put(PHONE_NUMBER, user.getPhone_number());
        values.put(NIF, user.getNif());

        long id = this.db.insert(TABLE_NAME, null, values);

        if(id > -1)
        {
            user.setId((int) id);
            return user;
        }
        return null;
    }

    public boolean editUserBD(User user)
    {
        ContentValues values = new ContentValues();
        values.put(ID, user.getId());
        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(STREET, user.getStreet());
        values.put(ZIP_CODE, user.getZip_code());
        values.put(AREA, user.getArea());
        values.put(PHONE_NUMBER, user.getPhone_number());
        values.put(NIF, user.getNif());
        int nreg = this.db.update(TABLE_NAME, values, ID + " = ?", new String[]{"" + user.getId()});

        return nreg >0;
    }

    public boolean removeUserBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " =?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<User> getAllUserBD()
    {
        ArrayList<User> listUsers = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, USERNAME, EMAIL, STREET, ZIP_CODE, AREA, PHONE_NUMBER, NIF}, null, null, null, null, null);
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
    public void removeAllUsersBD() {
        db.delete(TABLE_NAME, null, null);
    }
}
