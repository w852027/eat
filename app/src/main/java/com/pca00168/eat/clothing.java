package com.pca00168.eat;
import java.io.Serializable;
public class clothing implements Serializable {
    public short type=0;
    public int icon_resource_id=0;
    clothing(){
        super();
    }
    clothing(short type,int icon_resource_id){
        super();
        this.type=type;
        this.icon_resource_id=icon_resource_id;
    }
}