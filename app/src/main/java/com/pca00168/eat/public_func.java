package com.pca00168.eat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

public class public_func {
    public static int getDrawableId(Context context, String var) {
        try {  return context.getResources().getIdentifier(var, "drawable", context.getPackageName());  } catch (Exception e) {  return 0;  }
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