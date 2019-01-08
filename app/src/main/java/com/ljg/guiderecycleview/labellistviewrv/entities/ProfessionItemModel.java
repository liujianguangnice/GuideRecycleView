package com.ljg.guiderecycleview.labellistviewrv.entities;

import java.io.Serializable;

public class ProfessionItemModel implements Serializable {

    public String id;
    public String name;//名称
    public String value;//值
    public String valueUnit;//单位

    public String imgUrl;
    public boolean isSelected; 
    public boolean isHaveDivider;

    /** 是否为占位符 最后一个空的item*/
    public boolean isPlaceholder; //true 为占位符，false不是占位符

}
