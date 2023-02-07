package com.pca00168.eat.ui.dashboard;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.pca00168.eat.R;
import com.pca00168.eat.User;
import com.pca00168.eat.databinding.FragmentDashboardBinding;
import com.pca00168.eat.public_func;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onStart() {
        super.onStart();
        load_data();
    }
    private void load_data(){
        TextView to_eat_value=getActivity().findViewById(R.id.to_eat_value);
        to_eat_value.setText(String.valueOf(
                User.load_kcal_input(
                        getActivity(),
                        -1,
                        public_func.timestamp_today(),
                        public_func.timestamp_now()
                ).total_kcal())
        );

        TextView dumbbel_value=getActivity().findViewById(R.id.dumbbel_value);
        dumbbel_value.setText(String.valueOf(
                User.load_kcal_output(
                        getActivity(),
                        -1,
                        public_func.timestamp_today(),
                        public_func.timestamp_now()
                ).total_kcal())
        );

        ConstraintLayout layout=getActivity().findViewById(R.id.kcal_toast_view);
        layout.setVisibility(View.INVISIBLE);
        String delta_kcal=public_func.readData(getActivity(),"delta_kcal");
        if(delta_kcal!=""){
            TextView kcal=getActivity().findViewById(R.id.kcal_toast_delta);
            kcal.setText(Integer.parseInt(delta_kcal) <0?delta_kcal.substring(1):delta_kcal);
            TextView add_minus=getActivity().findViewById(R.id.kcal_toast_text);
            add_minus.setText(Integer.parseInt(delta_kcal) <0?"消耗":"增加");
            ImageView icon=getActivity().findViewById(R.id.kcal_toast_icon);
            icon.setImageDrawable(getResources().getDrawable( Integer.valueOf(delta_kcal) <0? R.drawable.dumbbel:R.drawable.apple));
            public_func.writeData(getActivity(),"delta_kcal","");
            layout.setVisibility(View.VISIBLE);
            layout.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                @Override
                    public void run() {
                        layout.animate().alpha(0).setDuration(1000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                layout.setVisibility(View.INVISIBLE);
                            }
                        });
                }
            });
        }

    }

}