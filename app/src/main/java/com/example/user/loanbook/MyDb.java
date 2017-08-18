package com.example.user.loanbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 8/18/2017.
 */

public class MyDb extends SQLiteOpenHelper {

    //database version
    public static final int DATABASE_VERSION = 3;
    //database name
    public static final String DATABASE_NAME = "loanbook.db";
    //table name
    public static final String TABLE_NAME = "UserTable";

    //column name
    public static final String COLUMN_ONE = "name";
    public static final String COLUMN_TWO = "password";

    //new table name
    public static final String TABLE_PEOPLE = "PeoplesTable";
    //column name
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DATE = "date";


    //table query
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ONE + " TEXT," +
            COLUMN_TWO + " TEXT)";

    public static final String SQL_CREATE_PEOPLE = "CREATE TABLE "+ TABLE_PEOPLE+ "("+ COLUMN_NAME+ " TEXT,"+
            COLUMN_MOBILE+" TEXT,"+
            COLUMN_TYPE+" TEXT,"+
            COLUMN_DATE+" TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public MyDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PEOPLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    //adding user in the database
    public void insetUser(String value1, String value2) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Create a new map of values
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ONE, value1);
        contentValues.put(COLUMN_TWO, value2);

        //create a new row in the database every time
        long newRowId = db.insert(TABLE_NAME, null, contentValues);

    }

    //adding people in the database
    public void insertPeople(String name,String number,String type,String date){
        SQLiteDatabase db = this.getWritableDatabase();

        //create a new map of values
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_MOBILE,number);
        contentValues.put(COLUMN_TYPE,type);
        contentValues.put(COLUMN_DATE,date);

        //create a new row in the table
        long newRowId = db.insert(TABLE_PEOPLE,null,contentValues);
    }

    //get user valued from the database
    public Cursor getUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ONE, COLUMN_TWO
        };

        Cursor cursor = db.query(TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        return cursor;
    }

    //get peoples information from the database
    public Cursor getPeople(){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_NAME,
                COLUMN_MOBILE,
                COLUMN_TYPE,
                COLUMN_DATE
        };

        Cursor cursor = db.query(TABLE_PEOPLE,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

}
