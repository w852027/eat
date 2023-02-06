package com.pca00168.eat;
import java.util.Date;
enum Food_Type{
    meal,
    dessert,
    drink,
    other
}
public class kcal_food {
    public short type=0;
    public String name="";
    public Date time=java.util.Calendar.getInstance().getTime();
    public int kcal=0;
}