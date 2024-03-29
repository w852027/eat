package com.pca00168.eat;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class SqlDataBaseHelper extends SQLiteOpenHelper {
    public SqlDataBaseHelper(@Nullable Context context) {
        super(context, "kcal.db", null, 1);
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS input_kcal (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time INTEGER not null," +
                "foodname TEXT not null," +
                "foodtype INTEGER not null," +
                "kcal INTEGER not null" +
                ")"
        );
        db.execSQL( "CREATE TABLE IF NOT EXISTS output_kcal (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time INTEGER not null," +
                "sporttype INTEGER not null," +
                "kcal INTEGER not null" +
                ")"
        );
        db.execSQL( "CREATE TABLE IF NOT EXISTS google_fit_step (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time INTEGER not null," +
                "num INTEGER not null" +
                ")"
        );
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE input_kcal");
        db.execSQL("DROP TABLE output_kcal");
        db.execSQL("DROP TABLE google_fit_step");
    }
}
