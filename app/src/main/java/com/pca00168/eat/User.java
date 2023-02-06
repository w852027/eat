package com.pca00168.eat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
public class User {
    //public static void setUserName(String username){    }
    //public static void setUserAvatar(String username){    }
    public static String getUserAvatar(Context context){
        return public_func.readData(context, "avatar");
    }
    public static String getUserName(Context context){
        return public_func.readData(context,"name");
    }
    public static void add_kcal_input(Context context, kcal_food food){
        SqlDataBaseHelper db=new SqlDataBaseHelper(context);
        SQLiteDatabase s = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time",public_func.date2string( food.time ));
        values.put("foodtype", food.type);
        values.put("foodname",food.name);
        values.put("kcal", food.kcal);
        s.insert("input_kcal", null, values);
        s.close();
        public_func.writeData(context,"delta_kcal", String.valueOf(food.kcal));
    }

    public static ArrayList<kcal_food> load_kcal_input(Context context){
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
        ArrayList<kcal_food> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            kcal_food item=new kcal_food();
            item.time=public_func.string2date(cursor.getString(cursor.getColumnIndexOrThrow("time")));
            item.type = cursor.getShort(cursor.getColumnIndexOrThrow("foodtype"));
            item.name = cursor.getString(cursor.getColumnIndexOrThrow("foodname"));
            item.kcal = cursor.getInt(cursor.getColumnIndexOrThrow("kcal"));
            items.add(item);
        }
        cursor.close();
        return items;
    }
}
