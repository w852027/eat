package com.pca00168.eat;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class public_func {
    public static int getDrawableId(Context context, String var) {
        try {  return context.getResources().getIdentifier(var, "drawable", context.getPackageName());  } catch (Exception e) {  return 0;  }
    }
    public static String readData(Context context,String key){
        if (key.length() == 0) return "";
        /**創建SharedPreferences，索引為"Data"*/
        SharedPreferences sharedPreferences=context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");//回傳在"Saved"索引之下的資料；若無儲存則回傳""
    }
    public static boolean writeData(Context context,String key,String value){
        if (key.length() == 0)return false;
        /**創建SharedPreferences，索引為"Data"*/
        SharedPreferences sharedPreferences= context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//取得SharedPreferences.Editor編輯內容
        if(value.length()==0)editor.remove(key);// 移除key
        else editor.putString(key,value);//放入字串，並定義索引為key
        /**提交；提交結果將會回傳一布林值，若不需要提交結果，則可使用.apply()*/
        return editor.commit();
    }
    public static String date2string(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return df.format(c.getTime());
    }
    public static Date string2date(String date){
        return java.util.Calendar.getInstance().getTime();
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

                Done.OK(editText.getText().toString());//將get到的文字轉成字串才可以給Toast顯示哦
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