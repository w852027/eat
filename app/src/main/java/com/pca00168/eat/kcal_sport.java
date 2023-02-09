package com.pca00168.eat;
import java.io.Serializable;
public class kcal_sport implements Serializable {
    public short type=0;
    public long time=public_func.timestamp_now();
    public int kcal=0,icon_resource_id=0;
    public String name="";
    kcal_sport(){
        super();
    }
    kcal_sport(short type,int kcal,String name,int icon_resource_id){
        super();
        this.type=type;
        this.kcal=kcal;
        this.name=name;
        this.icon_resource_id=icon_resource_id;
        this.time=0;
    }
    /*
    public static String getkcal(String var) {
        int age=1;
        int height=1;
        int weight=40;
        return "";
    }
    public static ArrayList<String[]> sport_list_arr(){
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
        return sport_list_arr;
    }*/
}