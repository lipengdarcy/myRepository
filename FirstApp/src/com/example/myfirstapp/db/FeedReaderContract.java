package com.example.myfirstapp.db;

import android.provider.BaseColumns;

//使用SQL Helper创建DB
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {
    	
    }
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
        FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
        FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
         // Any other options for the CREATE command
        " )";
    public static final String TABLE_NAME_ENTRIES = FeedReaderContract.FeedEntry.TABLE_NAME;
    public static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + TABLE_NAME_ENTRIES;

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_NULLABLE = "nullable";
        
        
    }
}
