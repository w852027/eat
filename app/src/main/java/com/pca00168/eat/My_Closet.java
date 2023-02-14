package com.pca00168.eat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class My_Closet extends AppCompatActivity {
    private FrameLayout animal;
    private View animal1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_yellow);
        setContentView(R.layout.my_closet);
        animal=findViewById(R.id.animal);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        animal1 = layoutInflater.inflate(R.layout.animal1_closet, null);
        animal.addView(animal1);
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
                cloth_img.setImageDrawable(getResources().getDrawable(c.icon_resource_id));
                short finalType = type;
                cloth.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        switch (finalType){
                            case 0:
                                ((ImageView)animal1.findViewById(R.id.cloth_0)).setImageDrawable(cloth_img.getDrawable());
                                break;
                            case 1: case 2:
                                ((ImageView)animal1.findViewById(R.id.cloth_1)).setImageDrawable(cloth_img.getDrawable());
                                break;
                            case 3:
                                //((ImageView)animal1.findViewById(R.id.cloth_3)).setImageDrawable(cloth_img.getDrawable());
                        }
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