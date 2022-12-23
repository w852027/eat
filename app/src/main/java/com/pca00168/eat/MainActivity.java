package com.pca00168.eat;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login_click(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Home.class);
        startActivity(intent);
    }
}