package com.luke.atm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kenya on 2016/5/9.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context){
        super(context, "expense.db", null, 1);
    }
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE \"main\".\"exp\" (\"_id\" INTEGER PRIMARY KEY  NOT NULL , \"cdate\" DATETIME NOT NULL , \"info\" VARCHAR, \"amount\" INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
