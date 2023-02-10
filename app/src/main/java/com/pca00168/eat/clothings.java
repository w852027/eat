package com.pca00168.eat;
import java.util.ArrayList;
public class clothings extends ArrayList<clothing>{
    public static clothings clothing_list(short type){
        clothings arr=new clothings();
        for(clothing item:clothing_list())
            if(item.type==type)
                arr.add(item);
        return arr;
    }
    public static clothings clothing_list(){
        clothings arr=new clothings();
        arr.add(new clothing((short) 0, R.drawable.cloth_face_1));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_2));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_3));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_4));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_5));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_1));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_2));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_3));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_4));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_5));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_1));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_2));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_3));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_4));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_5));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_1));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_2));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_3));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_4));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_5));
        return arr;
    }
    public static String clothtype2string(short type){
        String[] str={"臉上物品","領巾","圍巾","帽子"};
        if(type>str.length-1)return "";
        return str[type];
    }
}