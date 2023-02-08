package com.pca00168.eat;
import java.util.ArrayList;
public class kcal_sports extends ArrayList<kcal_sport> {
    public int total_kcal(){
        int total=0;
        for(kcal_sport sport:this) total+=sport.kcal;
        return total;
    }
    public static kcal_sports sport_list(){
        kcal_sports arr=new kcal_sports();
        arr.add(new kcal_sport((short) 0,1,"健行", R.drawable.sport_item_hike));
        arr.add(new kcal_sport((short) 1,1,"划船機", R.drawable.sport_item_boat));
        arr.add(new kcal_sport((short) 2,1,"功能性肌力訓練", R.drawable.sport_item_strength_training));
        arr.add(new kcal_sport((short) 3,1,"高強度間歇訓練", R.drawable.sport_item_hiit));
        arr.add(new kcal_sport((short) 4,1,"室內健身車", R.drawable.sport_item_bike_indoor));
        arr.add(new kcal_sport((short) 5,1,"室外腳踏車", R.drawable.sport_item_bike));
        arr.add(new kcal_sport((short) 6,1,"封閉水域游泳", R.drawable.sport_item_swim));
        arr.add(new kcal_sport((short) 7,1,"開放水域游泳", R.drawable.sport_item_swim_outside));
        arr.add(new kcal_sport((short) 8,1,"室內跑步", R.drawable.sport_item_run));
        arr.add(new kcal_sport((short) 9,1,"室外跑步", R.drawable.sport_item_run));
        arr.add(new kcal_sport((short) 10,1,"室外步行", R.drawable.sport_item_walk));
        arr.add(new kcal_sport((short) 11,1,"室內步行", R.drawable.sport_item_walk));
        arr.add(new kcal_sport((short) 12,1,"核心訓練", R.drawable.sport_item_core));
        arr.add(new kcal_sport((short) 13,1,"橢圓機", R.drawable.sport_item_elliptical));
        arr.add(new kcal_sport((short) 14,1,"瑜珈", R.drawable.sport_item_yoga));
        arr.add(new kcal_sport((short) 15,1,"緩和運動", R.drawable.sport_item_exercise));
        arr.add(new kcal_sport((short) 16,1,"舞蹈", R.drawable.sport_item_dance));
        arr.add(new kcal_sport((short) 17,1,"踏步機", R.drawable.sport_item_stepper));
        return arr;
    }
}