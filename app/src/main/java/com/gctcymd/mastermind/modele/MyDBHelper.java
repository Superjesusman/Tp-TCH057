package com.gctcymd.mastermind.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context){
        super(context, MyDBContract.DATABASE_NAME, null, MyDBContract.DB_VERSION);
    }

    public static final String SQL_CREATE_ENTITIES =
            "CREATE TABLE" + MyDBContract.MyTable.TABLE_NAME + "(" +
                    MyDBContract.MyTable._ID + "INTEGER PRIMARY KEY" +
                    MyDBContract.MyTable.COLUMN_NAME1 + "INTEGER" +
                    MyDBContract.MyTable.COLUMN_NAME2 + "TEXT" +
                    MyDBContract.MyTable.COLUMN_NAME3 + "TEXT" +
                    MyDBContract.MyTable.COLUMN_NAME4 + "INTEGER" +
                    MyDBContract.MyTable.COLUMN_NAME5 + "TEXT" +
                    MyDBContract.MyTable.COLUMN_NAME6 + "INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyDBContract.MyTable.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTITIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
