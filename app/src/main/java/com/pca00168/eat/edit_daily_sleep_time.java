package com.pca00168.eat;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
public class edit_daily_sleep_time extends Activity {
    private int dailySleepH,dailySleepM,dailyWakeH,dailyWakeM;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.dialog_transparent);
        setContentView(R.layout.edit_daily_sleep_time);
        dailySleepH=public_func.readDataInt(this, "dailySleepH");
        dailySleepM=public_func.readDataInt(this, "dailySleepM");
        dailyWakeH=public_func.readDataInt(this, "dailyWakeH");
        dailyWakeM=public_func.readDataInt(this, "dailyWakeM");
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ((TextView)findViewById(R.id.sleep_start)).setText(String.format("%02d:%02d",dailySleepH,dailySleepM));
            ((TextView)findViewById(R.id.sleep_end)).setText(String.format("%02d:%02d",dailyWakeH,dailyWakeM));
        }
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
                dailyWakeM=minute;
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
}