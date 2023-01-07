package com.pca00168.eat;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class Mine extends AppCompatActivity {
    ImageView user_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_green);
        setContentView(R.layout.activity_mine);
        load_data();
    }

    private void load_data(){
        user_avatar=(ImageView) findViewById(R.id.user_avatar);
        user_avatar.setImageResource(R.drawable.jungjung);




     }



}