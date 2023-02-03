package com.pca00168.eat;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

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
        load_settings_list();
    }

    private void load_data(){
        user_avatar=(ImageView) findViewById(R.id.user_avatar);
        user_avatar.setImageResource(R.drawable.jungjung);




     }

    private void load_settings_list() {
        LinearLayout table_list = (LinearLayout) findViewById(R.id.table);


        ArrayList<ArrayList> table_list_arr = new ArrayList();//{名稱,圖片檔名,資訊(true false為開關),點擊事件,接下來要不要分隔線(不要的話填null，要的話什麼都不要填)}
        table_list_arr.add(new ArrayList<>(Arrays.asList("基本設定", null, "section",null)));
        table_list_arr.add(new ArrayList<>(Arrays.asList("音效", "icon_sound", "true",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("背景音樂", "icon_background_music", "true",(new View.OnClickListener() {
            public void onClick(View v) {
            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("通知", "icon_notification", "true",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("語言", "icon_language", "繁體中文",null)));
        table_list_arr.add(new ArrayList<>(Arrays.asList("用戶設定", "icon_user_settings", "",(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Mine.this, user_settings.class);
                startActivity(intent);
            }
        }),null)));

        table_list_arr.add(new ArrayList<>(Arrays.asList("數值設定", null, "section",null)));

        table_list_arr.add(new ArrayList<>(Arrays.asList("每日基礎代謝", "icon_apple", "0kcal",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("每日運動目標", "icon_dumbbell", "0kcal",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("睡眠時程", "icon_bed", "PM/11:00-AM/07:00",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));
/*
        table_list_arr.add(new String[]{"音效", "icon_sound", "true"});
        table_list_arr.add(new String[]{"背景音樂", "icon_background_music", "true"});
        table_list_arr.add(new String[]{"通知", "icon_notification", "true"});
        table_list_arr.add(new String[]{"語言", "icon_language", "繁體中文"});

        table_list_arr.add(new String[]{"每日基礎代謝", "icon_apple", "0kcal"});
        table_list_arr.add(new String[]{"每日運動目標", "icon_dumbbell", "0kcal"});
        table_list_arr.add(new String[]{"睡眠時程", "icon_bed", "PM/11:00-AM/07:00"});
*/


        for (ArrayList table_item : table_list_arr) {
            LayoutInflater layoutInflater = LayoutInflater.from(Mine.this);
            View view = null;
            switch ((String)table_item.get(2)){
                case "true": case "false":
                    view = layoutInflater.inflate(R.layout.mine_table_cell_switch, null);
                    break;
                case "section":
                    view = layoutInflater.inflate(R.layout.mine_table_section, null);
                    TextView text = (TextView) view.findViewById(R.id.word);
                    text.setText((String) table_item.get(0));
                    table_list.addView(view);
                    continue;
                default:
                    view = layoutInflater.inflate(R.layout.mine_table_cell_info, null);
                    TextView info = (TextView) view.findViewById(R.id.info);
                    info.setText((String)table_item.get(2));
                    break;
            }
            ((ImageView)view.findViewById(R.id.icon)).setImageDrawable(getResources().getDrawable(public_func.getDrawableId(Mine.this, (String)table_item.get(1)) ));
            view.setOnClickListener((View.OnClickListener) table_item.get(3));

            TextView text = (TextView) view.findViewById(R.id.word);
            text.setText((String) table_item.get(0));
            table_list.addView(view);


            try{
                table_item.get(4);
            }catch (Exception e){
                View space=new View(Mine.this);
                space.setMinimumHeight(3);
                space.setBackgroundColor(android.graphics.Color.argb(20,0,0,0));
                table_list.addView(space);
            }




        }
    }

}