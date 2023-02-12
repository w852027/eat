package com.pca00168.eat.ui.dashboard;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.pca00168.eat.R;
import com.pca00168.eat.User;
import com.pca00168.eat.databinding.FragmentDashboardBinding;
import com.pca00168.eat.public_func;
import com.pca00168.eat.today_detail;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
public class DashboardFragment extends Fragment {
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DashboardViewModel.class);
        root=FragmentDashboardBinding.inflate(inflater, container, false).getRoot();
        ConstraintLayout layout=root.findViewById(R.id.kcal_toast_view);
        layout.setVisibility(View.INVISIBLE);
        return root;
    }
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }
    public void onResume() {
        super.onResume();
        load_data();
    }
    private void load_data(){
        TextView to_eat_value=root.findViewById(R.id.to_eat_value);
        to_eat_value.setText(String.valueOf(
                User.load_kcal_input(
                        getActivity(),
                        -1,
                        public_func.timestamp_today(),
                        public_func.timestamp_now()
                ).total_kcal())
        );
        TextView dumbbel_value=root.findViewById(R.id.dumbbel_value);
        dumbbel_value.setText(String.valueOf(
                User.load_kcal_output(
                        getActivity(),
                        -1,
                        public_func.timestamp_today(),
                        public_func.timestamp_now()
                ).total_kcal())
        );
        ConstraintLayout layout=root.findViewById(R.id.kcal_toast_view);
        String delta_kcal=public_func.readData(getActivity(),"delta_kcal");
        if(delta_kcal!=""){
            TextView kcal=root.findViewById(R.id.kcal_toast_delta);
            kcal.setText(Integer.parseInt(delta_kcal) <0?delta_kcal.substring(1):delta_kcal);
            TextView add_minus=root.findViewById(R.id.kcal_toast_text);
            add_minus.setText(Integer.parseInt(delta_kcal) <0?"消耗":"增加");
            ImageView icon=root.findViewById(R.id.kcal_toast_icon);
            icon.setImageDrawable(getResources().getDrawable( Integer.valueOf(delta_kcal) <0? R.drawable.dumbbel:R.drawable.apple));
            public_func.writeData(getActivity(),"delta_kcal","");
            TextView look=root.findViewById(R.id.look);
            look.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), today_detail.class);
                    intent.putExtra("request_input",Integer.parseInt(delta_kcal) >0);
                    startActivity(intent);
                }
            });
            layout.setVisibility(View.VISIBLE);
            layout.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                    public void run() {
                        layout.animate().alpha(0).setDuration(1000).withEndAction(new Runnable() {
                            public void run() {
                                layout.setVisibility(View.INVISIBLE);
                            }
                        });
                }
            });
        }
    }
}