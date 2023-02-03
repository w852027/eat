package com.pca00168.eat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.EditText;

public class public_func {
    public static int getDrawableId(Context context, String var) {
        try {  return context.getResources().getIdentifier(var, "drawable", context.getPackageName());  } catch (Exception e) {  return 0;  }
    }
    public static String readData(Context context,String key){
        if (key.length() == 0) return "";
        /**創建SharedPreferences，索引為"Data"*/
        SharedPreferences sharedPreferences=context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        /**回傳在"Saved"索引之下的資料；若無儲存則回傳"未存任何資料"*/
        return sharedPreferences.getString(key,"");
    }
    public static boolean writeData(Context context,String key,String value){
        if (key.length()*value.length() == 0)
            return false;
        /**創建SharedPreferences，索引為"Data"*/
        SharedPreferences sharedPreferences= context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        /**取得SharedPreferences.Editor編輯內容*/
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /**放入字串，並定義索引為"Saved"*/
        editor.putString(key,value);
        /**提交；提交結果將會回傳一布林值*/
        /**若不需要提交結果，則可使用.apply()*/
        return editor.commit();
    }
    interface dialogResultCallBack {
        public void OK(String text);
        public void Cancel();
    }
    public static void TextInput_Dialog(Context context,String title,dialogResultCallBack Done) {
        TextInput_Dialog(context,title,"確定","取消",Done);
    }
    public static void TextInput_Dialog(Context context,String title,String okText,String cancelText,dialogResultCallBack Done) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText editText = new EditText(context); //final一個editText
        builder.setView(editText);
        builder.setTitle(title);
        builder.setPositiveButton(okText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(MainActivity.this, editText.getText().toString()+"您好~", Toast.LENGTH_SHORT).show();
                //將get到的文字轉成字串才可以給Toast顯示哦
                Done.OK(editText.getText().toString());
            }
        });
        builder.setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Done.Cancel();
            }
        });
        builder.create().show();
    }

}