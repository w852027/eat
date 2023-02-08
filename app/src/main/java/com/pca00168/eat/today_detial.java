package com.pca00168.eat;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

public class today_detial extends Activity {
    private boolean is_request_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.today_detial);
        is_request_input=getIntent().getBooleanExtra("request_input",true);
        if(is_request_input) load_input_data();
        else load_output_data();
    }

    private void load_input_data(){
        LinearLayout io_kcal_today_table = findViewById(R.id.io_kcal_today_table);
            for(kcal_food food :User.load_kcal_input(this,-1,public_func.timestamp_today(),public_func.timestamp_now())){
            View cell = LayoutInflater.from(this).inflate(R.layout.today_detial_table_cell, null);
            ((TextView)cell.findViewById(R.id.type)).setText(kcal_foods.foodtype2string(food.type));
            ((ImageView)cell.findViewById(R.id.type_icon)).setImageDrawable(getResources().getDrawable(kcal_foods.foodtype2icon_resource_id(food.type)));
            ((TextView)cell.findViewById(R.id.kcal_value)).setText(String.valueOf(food.kcal));
            ((TextView)cell.findViewById(R.id.name)).setText(food.name);
            ImageView edit_btn=cell.findViewById(R.id.edit_btn);
            edit_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
            ConstraintLayout kcal_layout=cell.findViewById(R.id.kcal_layout);


            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*
                    TranslateAnimation animate = new TranslateAnimation(0,-edit_btn.getWidth(),0,0);
                    animate.setDuration(500);
                    animate.setFillAfter(true);
                    kcal_layout.startAnimation(animate);*/
                    edit_btn.setVisibility(View.VISIBLE);

                    /*
                    edit_btn.animate().translationX(-100).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });*/
                }
            });
                io_kcal_today_table.addView(cell);
        }
    }
    private void load_output_data(){
        LinearLayout io_kcal_today_table = findViewById(R.id.io_kcal_today_table);
        for(kcal_sport sport :User.load_kcal_output(this,-1,public_func.timestamp_today(),public_func.timestamp_now())){
            View cell = LayoutInflater.from(this).inflate(R.layout.today_detial_table_cell, null);
            ((TextView)cell.findViewById(R.id.type)).setText(sport.name);
            ((ImageView)cell.findViewById(R.id.type_icon)).setImageDrawable(getResources().getDrawable(sport.icon_resource_id));
            ((TextView)cell.findViewById(R.id.kcal_value)).setText(String.valueOf(sport.kcal));
            ((TextView)cell.findViewById(R.id.name)).setText("");
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            io_kcal_today_table.addView(cell);
        }
    }

}