package com.pca00168.eat.ui.home;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import com.pca00168.eat.R;
import com.pca00168.eat.User;
import com.pca00168.eat.databinding.FragmentHomeBinding;
import com.pca00168.eat.kcal_sport;
import com.pca00168.eat.kcal_sports;
import com.pca00168.eat.public_func;

public class HomeFragment extends Fragment {
    private View root;
    private TextInputEditText input_kcal;
    private kcal_sport sport;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =  new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        root = FragmentHomeBinding.inflate(inflater, container, false).getRoot();
        Button confirm_add_btn = (Button)root.findViewById(R.id.confirm_add_btn);


        confirm_add_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //check_field_empty();
                if(confirm_add_btn.isClickable()){
                    sport.time=public_func.timestamp_now();
                    sport.kcal=Integer.parseInt(input_kcal.getText().toString());
                    User.add_kcal_output(getActivity(),sport);
                    getActivity().onBackPressed();
                }
            }
        });
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
        LinearLayout sport_list = (LinearLayout) root.findViewById(R.id.sport_list);
        for (kcal_sport sport_item: kcal_sports.sport_list_arr()) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.sport_item, null);
            TextView text = (TextView)view.findViewById(R.id.sport_item_text);
            text.setText(sport_item.name);
            if(sport_item.name.length()>5) {
                text.setTextSize(24);
            }
            ((ImageView)view.findViewById(R.id.sport_item_image)).setImageDrawable(getResources().getDrawable(public_func.getDrawableId(getContext(),sport_item.icon_name) ));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sport=sport_item;
                    input_kcal.setText(String.valueOf(sport_item.kcal));
                }
            });

           sport_list.addView(view);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }
}