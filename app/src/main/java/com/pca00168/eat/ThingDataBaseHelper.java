package com.pca00168.eat;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ThingDataBaseHelper extends SQLiteOpenHelper {
    public ThingDataBaseHelper(@Nullable Context context) {
        super(context, "thing.db", null, 1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS clothing (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maintype INTEGER not null," +
                "subtype INTEGER not null" +
                ")"
        );
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE clothing");
    }
}