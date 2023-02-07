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
import java.util.Date;

public class weekly_report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_yellow);
        setContentView(R.layout.weekly_report);
        TextView date=findViewById(R.id.week_date);
        date.setText(String.format("%s.%s - %s.%s",
                public_func.timestamp_get( public_func.timestamp_a_week_ago(),true),
                public_func.timestamp_get( public_func.timestamp_a_week_ago(),false),
                public_func.timestamp_get( public_func.timestamp_now(),true),
                public_func.timestamp_get( public_func.timestamp_now(),false)
        ));
        load_data();
    }
    private void load_data(){
        LinearLayout input_kcal_table = findViewById(R.id.input_kcal_table);
        for (int type=0;type<=3;type++) {
            kcal_foods foods=User.load_kcal_input(this,type,public_func.timestamp_a_week_ago(),public_func.timestamp_now());
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View view = layoutInflater.inflate(R.layout.weekly_report_table_cell, null);
            TextView type_name=view.findViewById(R.id.type);
            ImageView icon= view.findViewById(R.id.type_icon);
            switch (type){
                case 0:
                    type_name.setText("正餐");
                    icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_meal));
                    break;
                case 1:
                    type_name.setText("點心");
                    icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_dessert));
                    break;
                case 2:
                    type_name.setText("飲品");
                    icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_drink));
                    break;
                case 3:
                    type_name.setText("其他");
                    icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_other));
                    break;
            }
            ((TextView)view.findViewById(R.id.count_value)).setText(String.valueOf(foods.size()));
            ((TextView)view.findViewById(R.id.kcal_value)).setText(String.valueOf(foods.total_kcal()));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            input_kcal_table.addView(view);
        }

    }
}