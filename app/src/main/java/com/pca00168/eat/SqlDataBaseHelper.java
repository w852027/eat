package com.pca00168.eat;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class SqlDataBaseHelper extends SQLiteOpenHelper {
    public SqlDataBaseHelper(@Nullable Context context) {
        super(context, "kcal.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS input_kcal (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time TEXT not null," +
                "foodname TEXT not null," +
                "foodtype INTEGER not null," +
                "kcal INTEGER not null" +
                ")"
        );
        db.execSQL( "CREATE TABLE IF NOT EXISTS output_kcal (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time DATETIME not null," +
                "sporttype INTEGER not null," +
                "kcal INTEGER not null" +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE input_kcal");
        db.execSQL("DROP TABLE output_kcal");
    }
}
