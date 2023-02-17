package com.pca00168.eat;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class shop extends AppCompatActivity {
    public void add_branch_onClick(View v){
        startActivity(new Intent(this,shop_branch.class));
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_yellow);
        setContentView(R.layout.shop);
        load_cothing();
    }

    private void load_cothing(){
        LinearLayout clothing_maintype = findViewById(R.id.clothing_maintype);
        for(short type=0;type<4;type++){
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View maintype_cell = layoutInflater.inflate(R.layout.clothing_maintype_cell, null);
            LinearLayout clothing_maintype_cell_inside = maintype_cell.findViewById(R.id.clothing_maintype_cell_inside);
            boolean haveclothing=false;
            for(clothing c:clothings.clothing_list(type)){
                haveclothing=true;
                View subtype_cell= layoutInflater.inflate(R.layout.clothing_subtype_cell, null);
                ImageView cloth_img=subtype_cell.findViewById(R.id.clothing_image);
                cloth_img.setImageDrawable(getResources().getDrawable(c.icon_resource_id));
                TextView name=subtype_cell.findViewById(R.id.clothing_name);
                name.setText(c.name);
                TextView cost=subtype_cell.findViewById(R.id.cost);
                cost.setText(String.valueOf(c.cost));
                short finalType = type;
                /*
                cloth.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        switch (finalType){
                            case 0:
                                ((ImageView)animal1.findViewById(R.id.cloth_0)).setImageDrawable(cloth_img.getDrawable());
                                break;
                            case 1:
                                ((ImageView)animal1.findViewById(R.id.cloth_2)).setImageDrawable(null);
                                ((ImageView)animal1.findViewById(R.id.cloth_1)).setImageDrawable(cloth_img.getDrawable());
                                break;
                            case 2:
                                ((ImageView)animal1.findViewById(R.id.cloth_1)).setImageDrawable(null);
                                ((ImageView)animal1.findViewById(R.id.cloth_2)).setImageDrawable(cloth_img.getDrawable());
                                break;
                            case 3:
                                ((ImageView)animal1.findViewById(R.id.cloth_3)).setImageDrawable(cloth_img.getDrawable());
                        }
                    }
                });*/
                clothing_maintype_cell_inside.addView(subtype_cell);
            }
            clothing_maintype.addView(maintype_cell);
        }
    }
}