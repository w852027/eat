package com.pca00168.eat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class User {
    //public static void setUserName(String username){    }
    //public static void setUserAvatar(String username){    }
    public static String getUserAvatar(Context context){
        return public_func.readData(context, "avatar");
    }
    public static String getUserName(Context context){
        return public_func.readData(context,"name");
    }
    public static void add_kcal_input(Context context,short type,int kcal){
        SqlDataBaseHelper db=new SqlDataBaseHelper(context);
        SQLiteDatabase s = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", public_func.date2string(java.util.Calendar.getInstance().getTime()) );
        values.put("foodtype", type);
        values.put("kcal", kcal);
        s.insert("input_kcal", null, values);
        load_kcal_input(context);
    }

    public static void load_kcal_input(Context context){
        SqlDataBaseHelper db=new SqlDataBaseHelper(context);
        SQLiteDatabase s = db.getReadableDatabase();
        Cursor cursor = s.query(
                "input_kcal",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("foodtype")
            );
            Log.v("666", "a"+String.valueOf(itemId));
            itemIds.add(itemId);
        }
        cursor.close();
    }
}
