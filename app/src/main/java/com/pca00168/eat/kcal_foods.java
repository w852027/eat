package com.pca00168.eat;
import java.util.ArrayList;
public class kcal_foods extends ArrayList<kcal_food> {
    public int total_kcal(){
        int total=0;
        for(kcal_food food:this) total+=food.kcal;
        return total;
    }
    public static String foodtype2string(short type){
        String[] str={"正餐","點心","飲品","其他"};
        if(type>str.length-1)return "";
        return str[type];
    }
    public static int foodtype2icon_resource_id(short type){
        int[] icon={R.drawable.icon_meal,R.drawable.icon_dessert,R.drawable.icon_drink,R.drawable.icon_other};
        if(type>icon.length-1)return -1;
        return icon[type];
    }
}