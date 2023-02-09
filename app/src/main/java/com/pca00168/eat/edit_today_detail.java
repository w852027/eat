package com.pca00168.eat;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
public class edit_today_detail extends Activity {
    private boolean is_request_input;
    private kcal_food food;
    private kcal_sport sport;
    private TextInputEditText kcal_value,name;
    private ArrayList<View> cells=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_today_detail);
        kcal_value=findViewById(R.id.kcal_value);
        name=findViewById(R.id.name);

        is_request_input=getIntent().getBooleanExtra("request_input",true);
       if(is_request_input){
           food= (kcal_food)getIntent().getSerializableExtra("data");
           name.setText(food.name);
           kcal_value.setText(String.valueOf(food.kcal));
           load_food_types();
       }else{
           sport= (kcal_sport) getIntent().getSerializableExtra("data");
           ((ImageView)findViewById(R.id.apple)).setImageDrawable(getResources().getDrawable(R.drawable.dumbbel));
           findViewById(R.id.food_name).setVisibility(View.GONE);
           kcal_value.setText(String.valueOf(sport.kcal));
           load_sport_types();
       }
    }
    private void load_food_types(){
        LinearLayout type_table=findViewById(R.id.type_table);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for(short t=0;t<4;t++){
            View cell = layoutInflater.inflate(R.layout.edit_kcal_type_item, null);
            cell.setTag(t);
            TextView type = cell.findViewById(R.id.type);
            type.setText(kcal_foods.foodtype2string(t));
            ImageView icon=cell.findViewById(R.id.icon);
            icon.setImageDrawable(getResources().getDrawable(kcal_foods.foodtype2icon_resource_id(t)));
            ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
            type_table.addView(cell);
            cells.add(cell);
            cell_select(cell,food.type==t);
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(View c:cells) cell_select(c,false);
                    cell_select(cell,true);
                }
            });
        }
    }
    private void load_sport_types() {
        LinearLayout type_table = findViewById(R.id.type_table);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (kcal_sport s:kcal_sports.sport_list()) {
            View cell = layoutInflater.inflate(R.layout.edit_kcal_type_item, null);
            cell.setTag(s.type);
            TextView type = cell.findViewById(R.id.type);
            type.setText(s.name);
            ImageView icon = cell.findViewById(R.id.icon);
            icon.setImageDrawable(getResources().getDrawable(s.icon_resource_id));
            ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
            type_table.addView(cell);
            cells.add(cell);
            cell_select(cell, s.type == sport.type);
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (View c : cells) cell_select(c, false);
                    cell_select(cell, true);
                }
            });
        }
    }
    private void cell_select(View cell, boolean selected){
        cell.setAlpha(selected?1:(float)0.4);
        if(selected){
            if(is_request_input) food.type = (short)cell.getTag();
            else sport.type= (short)cell.getTag();
        }
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(
                            findViewById(R.id.scrollView),
                            "scrollX",
                            0,(int)cells.get(is_request_input ? food.type : sport.type).getX()-50)
                            .setDuration(300);
        objectAnimator.start();
    }
    public void delete_click(View view){

    }
}