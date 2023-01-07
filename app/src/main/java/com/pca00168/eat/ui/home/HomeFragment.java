package com.pca00168.eat.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.ImageView;
import android.support.annotation.NonNull;
import android.arch.lifecycle.ViewModelProvider;

import com.pca00168.eat.R;
import com.pca00168.eat.databinding.FragmentHomeBinding;
import com.pca00168.eat.public_func;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View root;
    private TextInputEditText input_kcal;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =  new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        root = FragmentHomeBinding.inflate(inflater, container, false).getRoot();
        Button confirm_add_btn = (Button)root.findViewById(R.id.confirm_add_btn);
        input_kcal = (TextInputEditText)root.findViewById(R.id.input_kcal);
        input_kcal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {   }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirm_add_btn.setBackground(getResources().getDrawable( count>0 ? R.drawable.conform_add_orange : R.drawable.conform_add_gray));
                confirm_add_btn.setClickable(count>0);
            }
            @Override
            public void afterTextChanged(Editable editable) {  }
        });

        load_sport_list();
        return root;
    }

    private  void load_sport_list(){
        ArrayList<String[]> sport_list_arr = new ArrayList();//{運動名稱,圖片檔名}
        sport_list_arr.add(new String[]{"健行", "sport_item_hike","1"});
        sport_list_arr.add(new String[]{"划船機", "sport_item_boat","2"});
        sport_list_arr.add(new String[]{"功能性肌力訓練", "sport_item_strength_training","3"});
        sport_list_arr.add(new String[]{"高強度間歇訓練", "sport_item_hiit","4"});
        sport_list_arr.add(new String[]{"室內健身車", "sport_item_bike_indoor","5"});
        sport_list_arr.add(new String[]{"室外腳踏車", "sport_item_bike","6"});
        sport_list_arr.add(new String[]{"封閉水域游泳", "sport_item_swim","7"});
        sport_list_arr.add(new String[]{"開放水域游泳", "sport_item_swim_outside","8"});
        sport_list_arr.add(new String[]{"室內跑步", "sport_item_run","9"});
        sport_list_arr.add(new String[]{"室外跑步", "sport_item_run","10"});
        sport_list_arr.add(new String[]{"室外步行", "sport_item_walk","11"});
        sport_list_arr.add(new String[]{"室內步行", "sport_item_walk","12"});
        sport_list_arr.add(new String[]{"核心訓練", "sport_item_core","13"});
        sport_list_arr.add(new String[]{"橢圓機", "sport_item_elliptical","14"});
        sport_list_arr.add(new String[]{"瑜珈", "sport_item_yoga","15"});
        sport_list_arr.add(new String[]{"緩和運動", "sport_item_exercise","16"});
        sport_list_arr.add(new String[]{"舞蹈", "sport_item_dance","17"});
        sport_list_arr.add(new String[]{"踏步機", "sport_item_stepper","18"});

        LinearLayout sport_list = (LinearLayout) root.findViewById(R.id.sport_list);
        for (String[] sport_item:sport_list_arr ) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.sport_item, null);
            TextView text = (TextView)view.findViewById(R.id.sport_item_text);
            text.setText(sport_item[0]);
            if(sport_item[0].length()>5) {
                text.setTextSize(24);
            }
            ((ImageView)view.findViewById(R.id.sport_item_image)).setImageDrawable(getResources().getDrawable(public_func.getDrawableId(getContext(),sport_item[1]) ));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    input_kcal.setText(sport_item[2]);
                }
            });

           sport_list.addView(view);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }/*
    public static String getkcal(String var) {
        int age=1;
        int height=1;
        int weight=40;

    }*/
}