package com.example.harish.maps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shreyas on 06-01-2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Loc";
    private static int DATABASE_VERSION=1;
    public DbHelper(Context context)
    {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE = "CREATE TABLE " + Contract.ContractEntry.TABLE_NAME+"("
                + Contract.ContractEntry.COLUMN_LATITIUDE+" DOUBLE NOT NULL,"
                + Contract.ContractEntry.COLUMN_LONGITUDE+" DOUBLE NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
