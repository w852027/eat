package com.pca00168.eat;

import android.content.Context;

public class public_func {
    public static int getDrawableId(Context context, String var) {
        try {  return context.getResources().getIdentifier(var, "drawable", context.getPackageName());  } catch (Exception e) {  return 0;  }
    }


}
