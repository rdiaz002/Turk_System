package com.reds.turk_system;

import android.provider.BaseColumns;

/**
 * Created by Ronny on 10/25/2017.
 */

public class FeedReaderContract {

    private FeedReaderContract() {

    }

    public static final String CREATE_USER_TABLE =
            "CREATE TABLE users (user_num INT PRIMARY KEY," +
                    "user_type TEXT CHECK(user_type IN ('dev','client'))," +
                    "password TEXT," +
                    "status TEXT CHECK(status IN ('active','blacklist','temp'))," +
                    "info TEXT," +
                    "project_info TEXT," +
                    "bank TEXT)";

    public static final String CREATE_INFO_TABLE =
            " CREATE TABLE info(user_num INT PRIMARY KEY," +
                    "resume TEXT," +
                    "pic TEXT ," +
                    "interest TEXT," +
                    "sample_work TEXT ," +
                    "business_creds TEXT," +
                    "rating INT NON NULL" +
                    ")";

    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE project_info" +
            "(user_num_client INT," +
            "user_num_dev INT," +
            "title text," +
            "description text," +
            "deadline DATE," +
            "bid INT," +
            "FOREIGN KEY(user_num_client) REFERENCES users(user_num)," +
            "FOREIGN KEY(user_num_dev) REFERENCES users(user_num)" +
            ")";


    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


}
