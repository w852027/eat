package com.pca00168.eat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class My_Closet extends AppCompatActivity {
    private ImageView animal_cloth_2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_yellow);
        setContentView(R.layout.my_closet);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.sport_item, null);
        animal_cloth_2=findViewById(R.id.cloth_2);
        load_cothing();
    }

    private void load_cothing(){
        LinearLayout closet = findViewById(R.id.closet);
        for(short type=0;type<4;type++){
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View cell = layoutInflater.inflate(R.layout.closet_cell, null);
            ((TextView)cell.findViewById(R.id.type)).setText(clothings.clothtype2string(type));
            LinearLayout row1=cell.findViewById(R.id.row1);
            LinearLayout row2=cell.findViewById(R.id.row2);
            for(clothing c:clothings.clothing_list(type)){
                View cloth = layoutInflater.inflate(R.layout.cloth_cell, null);
                ImageView cloth_img=cloth.findViewById(R.id.clothing_image);
                ((ImageView)cloth_img).setImageDrawable(getResources().getDrawable(c.icon_resource_id));
                cloth.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        animal_cloth_2.setImageDrawable(cloth_img.getDrawable());
                    }
                });
                if(row1.getChildCount()<3)
                    row1.addView(cloth);
                else
                    row2.addView(cloth);
            }

            closet.addView(cell);
        }
    }
}