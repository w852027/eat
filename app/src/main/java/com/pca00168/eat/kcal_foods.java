package com.pca00168.eat;
import java.util.ArrayList;
public class kcal_foods extends ArrayList<kcal_food> {
    public int total_kcal(){
        int total=0;
        for(kcal_food food:this) total+=food.kcal;
        return total;
    }

}