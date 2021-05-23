package com.example.testshareperference.activity.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final String sql = "Create table student(" +
            "id integer primary key autoincrement," +
            "name text," +
            "sex integer," +
            "score integer" +
            ")";
    private Context myContext;

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Toast.makeText(myContext,"create table student successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
