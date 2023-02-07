package com.pca00168.eat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        values.put("time",food.time);
        values.put("foodtype", food.type);
        values.put("foodname",food.name);
        values.put("kcal", food.kcal);
        s.insert("input_kcal", null, values);
        s.close();
        public_func.writeData(context,"delta_kcal", String.valueOf(food.kcal));
    }
    public static void add_kcal_output(Context context, kcal_sport sport){
        SqlDataBaseHelper db=new SqlDataBaseHelper(context);
        SQLiteDatabase s = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time",sport.time);
        values.put("sporttype", sport.type);
        values.put("kcal", sport.kcal);
        s.insert("output_kcal", null, values);
        s.close();
        public_func.writeData(context,"delta_kcal", String.valueOf(-sport.kcal));
    }
    public static kcal_foods load_kcal_input(Context context,int food_type,long from_timestamp,long to_timestamp){//-1:全部
        SqlDataBaseHelper db=new SqlDataBaseHelper(context);
        SQLiteDatabase s = db.getReadableDatabase();
        Cursor cursor = s.query(
                "input_kcal",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                String.format("%s time >= %d AND time <= %d",
                        food_type == -1 ? "" : String.format( "foodtype=%d AND",food_type),
                        from_timestamp,
                        to_timestamp),  // The columns for the WHERE clause
                 null,        // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        kcal_foods items = new kcal_foods();
        while(cursor.moveToNext()) {
            kcal_food item=new kcal_food();
            item.time=cursor.getInt(cursor.getColumnIndexOrThrow("time"));
            item.type = cursor.getShort(cursor.getColumnIndexOrThrow("foodtype"));
            item.name = cursor.getString(cursor.getColumnIndexOrThrow("foodname"));
            item.kcal = cursor.getInt(cursor.getColumnIndexOrThrow("kcal"));
            items.add(item);
        }
        cursor.close();
        return items;
    }
    public static kcal_sports load_kcal_output(Context context,int sport_type,long from_timestamp,long to_timestamp){//-1:全部
        SqlDataBaseHelper db=new SqlDataBaseHelper(context);
        SQLiteDatabase s = db.getReadableDatabase();
        Cursor cursor = s.query(
                "output_kcal",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                String.format("%s time >= %d AND time <= %d",
                        sport_type == -1 ? "" : String.format( "sporttype=%d AND",sport_type),
                        from_timestamp,
                        to_timestamp),  // The columns for the WHERE clause
                null,        // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        kcal_sports items = new kcal_sports();
        while(cursor.moveToNext()) {
            kcal_sport item=new kcal_sport();
            item.time=cursor.getInt(cursor.getColumnIndexOrThrow("time"));
            item.type = cursor.getShort(cursor.getColumnIndexOrThrow("sporttype"));
            item.kcal = cursor.getInt(cursor.getColumnIndexOrThrow("kcal"));
            items.add(item);
        }
        cursor.close();
        return items;
    }
}
