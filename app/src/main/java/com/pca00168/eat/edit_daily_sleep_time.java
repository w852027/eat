package com.pca00168.eat;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
public class edit_daily_sleep_time extends Activity {
    private int dailySleepH,dailySleepM,dailyWakeH,dailyWakeM;
    private Button confirm_edit_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_daily_sleep_time);
        confirm_edit_btn=findViewById(R.id.confirm_edit_btn);
        dailySleepH=public_func.readDataInt(this, "dailySleepH");
        dailySleepM=public_func.readDataInt(this, "dailySleepM");
        dailyWakeH=public_func.readDataInt(this, "dailyWakeH");
        dailyWakeM=public_func.readDataInt(this, "dailyWakeM");
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ((TextView)findViewById(R.id.textView4)).setText(String.format("睡覺時間：%02d:%02d",dailySleepH,dailySleepM));
            ((TextView)findViewById(R.id.textView6)).setText(String.format("起床時間：%02d:%02d",dailyWakeH,dailyWakeM));
        }
    }
    private void check_field_empty(){
        //boolean no_empty=kcal_value.getText().length()>0;
        //confirm_edit_btn.setBackground(getResources().getDrawable( no_empty ? R.drawable.conform_add_orange : R.drawable.conform_add_gray));
        //confirm_edit_btn.setClickable(no_empty);
    }
    public void edit_go_to_bed(View view){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dailySleepH=hourOfDay;
                dailySleepM=minute;
            }
        }, dailySleepH, dailySleepM, true).show();
    }
    public void edit_wakeup(View view){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dailyWakeH=hourOfDay;
                dailyWakeH=minute;
            }
        }, dailyWakeH, dailyWakeH, true).show();
    }
    public void okClick(View view){
        public_func.writeData(this, "dailySleepH",dailySleepH);
        public_func.writeData(this, "dailySleepM",dailySleepM);
        public_func.writeData(this, "dailyWakeH",dailyWakeH);
        public_func.writeData(this, "dailyWakeM",dailyWakeM);
        onBackPressed();
        Toast.makeText(this,"修改將從明日生效" , Toast.LENGTH_SHORT).show();
    }
    public void exit_onClick(View v){
        onBackPressed();
    }
    public void hide_keyboard(View v){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager= (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow( view.getWindowToken(), 0);
        }
    }

}