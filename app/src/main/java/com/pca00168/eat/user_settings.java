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
import java.util.Arrays;

public class user_settings extends AppCompatActivity {
    ImageView user_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        StatusBarUtils.setWindowStatusBarColor(this,R.color.main_pink);
        setContentView(R.layout.user_settings);
        load_data();
        load_settings_list();

/*
        TextView logout=(TextView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView delete_account=(TextView)findViewById(R.id.delete_account);
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
    }

    private void load_data(){
        user_avatar=(ImageView) findViewById(R.id.user_avatar);
        user_avatar.setImageResource(R.drawable.jungjung);
    }

    private void load_settings_list() {
        LinearLayout table_list = (LinearLayout) findViewById(R.id.table);


        ArrayList<ArrayList> table_list_arr = new ArrayList();//{名稱,圖片檔名,資訊(true false為開關),點擊事件,接下來要不要分隔線(不要的話填null，要的話什麼都不要填)}

        table_list_arr.add(new ArrayList<>(Arrays.asList("用戶名稱",  "0kcal",(new View.OnClickListener() {
            public void onClick(View v) {
                public_func.dialogResultCallBack callBack= new public_func.dialogResultCallBack() {
                    public void OK(String text) {
                            User.setUserName(text);
                            //TODO: refresh page
                    }
                    public void Cancel() { }
                };
                public_func.TextInput_Dialog(user_settings.this,"請輸入新的用戶名稱",callBack);
            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("用戶身份", "0kcal",null)));
        table_list_arr.add(new ArrayList<>(Arrays.asList("帳號", "PM/11:00-AM/07:00",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));
        table_list_arr.add(new ArrayList<>(Arrays.asList("生日", "PM/11:00-AM/07:00",(new View.OnClickListener() {
            public void onClick(View v) {

            }
        }))));

        for (ArrayList table_item : table_list_arr) {
            LayoutInflater layoutInflater = LayoutInflater.from(user_settings.this);
            View view = layoutInflater.inflate(R.layout.user_settings_table_cell_info, null);
            TextView info = (TextView) view.findViewById(R.id.info);
            info.setText((String)table_item.get(1));
            view.setOnClickListener((View.OnClickListener) table_item.get(2));
            TextView text = (TextView) view.findViewById(R.id.word);
            text.setText((String) table_item.get(0));
            table_list.addView(view);

            View space=new View(user_settings.this);
            space.setMinimumHeight(3);
            space.setBackgroundColor(android.graphics.Color.argb(20,0,0,0));
            table_list.addView(space);

        }
    }
    public void camera_click(View view){

    }
    public void logout_click(View view){

    }
    public void delete_account(View view){

    }


}