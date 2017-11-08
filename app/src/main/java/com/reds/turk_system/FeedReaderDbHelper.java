package com.reds.turk_system;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by Ronny on 10/25/2017.
 */

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Turk.db";


    public FeedReaderDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("tag","this is done");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("tag","this is done");
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        sqLiteDatabase.execSQL(FeedReaderContract.CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(FeedReaderContract.CREATE_INFO_TABLE);
        sqLiteDatabase.execSQL(FeedReaderContract.CREATE_PROJECT_TABLE);

    }
    public static String get_users(String param1 , String param2 ){
        return ("SELECT * FROM users WHERE " + param1 + " = " + param2);
    }
    public static String insert_user(int user_num , String user_type , String password ,String status, String Text, String proj , String bank){
        return String.format("INSERT INTO users VALUES(%d,'%s','%s','%s','%s','%s','%s');",user_num,user_type,password,status,Text,proj,bank);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
