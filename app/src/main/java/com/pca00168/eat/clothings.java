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
        arr.add(new clothing((short) 0, R.drawable.cloth_face_1,"神秘人海苔",100));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_2,"書生眼鏡",135));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_3,"潛水鏡",550));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_4,"眉框眼鏡",120));
        arr.add(new clothing((short) 0, R.drawable.cloth_face_5,"海盜眼罩",200));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_1,"小花領巾",200));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_2,"咖啡館領巾",120));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_3,"格子領巾",200));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_4,"小紅領巾",120));
        arr.add(new clothing((short) 1, R.drawable.cloth_scarf2_5,"絲綢領巾",235));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_1,"抹茶圍巾",300));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_2,"點點圍巾",350));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_3,"海洋藍圍巾",550));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_4,"可愛圍巾",450));
        arr.add(new clothing((short) 2, R.drawable.cloth_scarf_5,"酒紅圍巾",300));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_1,"鴨舌帽",230));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_2,"探險帽",300));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_3,"兔耳髮箍",800));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_4,"球球毛帽",380));
        arr.add(new clothing((short) 3, R.drawable.cloth_cap_5,"巫師帽",400));
        return arr;
    }
    public static String clothtype2string(short type){
        String[] str={"臉上物品","領巾","圍巾","帽子"};
        if(type>str.length-1)return "";
        return str[type];
    }
}