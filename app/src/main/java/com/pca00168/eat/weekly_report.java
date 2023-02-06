package com.pca00168.eat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class weekly_report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_yellow);
        setContentView(R.layout.weekly_report);

        load_data();
    }

    private void load_data(){

        LinearLayout input_kcal_table = findViewById(R.id.input_kcal_table);

       // ArrayList<kcal_food> foods= User.load_kcal_input(this);
        for (kcal_food food: User.load_kcal_input(this) ) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View view = layoutInflater.inflate(R.layout.weekly_report_table_cell, null);

/*
            TextView count = (TextView)view.findViewById(R.id.count_value);
            count.setText(foods.size());
            */

            TextView kcal = (TextView)view.findViewById(R.id.kcal_value);
            kcal.setText(String.valueOf( food.kcal));


            //((ImageView) view.findViewById(R.id.type_icon)).setImageDrawable(getResources().getDrawable(public_func.getDrawableId(this, sport_item[1])));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            input_kcal_table.addView(view);
        }

    }
}