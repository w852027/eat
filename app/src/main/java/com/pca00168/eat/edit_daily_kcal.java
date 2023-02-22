package com.pca00168.eat;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
public class edit_daily_kcal extends Activity {
    private boolean is_request_input;
    private TextInputEditText kcal_value;
    private Button confirm_edit_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.dialog_transparent);
        setContentView(R.layout.edit_daily_kcal);
        confirm_edit_btn=findViewById(R.id.confirm_edit_btn);
        is_request_input=getIntent().getBooleanExtra("request_input",true);
        ((TextView)findViewById(R.id.edit)).setText(is_request_input?"每日基礎代謝":"每日運動目標");
        ((ImageView)findViewById(R.id.apple)).setImageDrawable(getResources().getDrawable(is_request_input?R.drawable.icon_apple:R.drawable.icon_dumbbell));
        kcal_value=findViewById(R.id.kcal_value);
        kcal_value.setText(String.valueOf(public_func.readDataInt(this,is_request_input?"dailyBMR":"dailySTK")));
        kcal_value.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {  check_field_empty();}
            public void afterTextChanged(Editable editable) {}
        });
        kcal_value.requestFocus();
    }
    private void check_field_empty(){
        boolean no_empty=kcal_value.getText().length()>0;
        confirm_edit_btn.setBackground(getResources().getDrawable( no_empty ? R.drawable.conform_add_orange : R.drawable.conform_add_gray));
        confirm_edit_btn.setClickable(no_empty);
    }
    public void okClick(View view){
        public_func.writeData(this,is_request_input?"dailyBMR":"dailySTK",Integer.parseInt(kcal_value.getText().toString()));
        hide_keyboard(null);
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
            kcal_value.clearFocus();
        }
    }
    public void onBackPressed(){
        if(kcal_value.isFocused())
            hide_keyboard(null);
        else
            super.onBackPressed();
    }
}