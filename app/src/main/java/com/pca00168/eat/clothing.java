package com.pca00168.eat;
import java.io.Serializable;
public class clothing implements Serializable {
    public short type=0;
    public int icon_resource_id=0;
    public String name="";
    public int cost=0;
    clothing(){
        super();
    }
    clothing(short type,int icon_resource_id,String name,int cost){
        super();
        this.type=type;
        this.icon_resource_id=icon_resource_id;
        this.name=name;
        this.cost=cost;
    }
    clothing(short type,int icon_resource_id){
        super();
        this.type=type;
        this.icon_resource_id=icon_resource_id;
    }
}