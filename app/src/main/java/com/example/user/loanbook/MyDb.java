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
    public static final int DATABASE_VERSION = 1;
    //database name
    public static final String DATABASE_NAME = "loanbook.db";
    //table name
    public static final String TABLE_NAME = "UserTable";

    //column name
    public static final String COLUMN_ONE = "name";
    public static final String COLUMN_TWO = "password";

    //table query
    public static final String  SQL_CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ONE+" TEXT,"+
            COLUMN_TWO+" TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;



    public MyDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    //adding value in the database
    public void insetUser(String value1,String value2){
        SQLiteDatabase db = this.getWritableDatabase();

        //Create a new map of values
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ONE,value1);
        contentValues.put(COLUMN_TWO,value2);

        //create a new row in the database every time
        long newRowId = db.insert(TABLE_NAME,null,contentValues);

    }

    //showing values in the database
    public Cursor getUser(){
        SQLiteDatabase db = this.getReadableDatabase();

        String [] projection = {
                COLUMN_ONE,COLUMN_TWO
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

}
