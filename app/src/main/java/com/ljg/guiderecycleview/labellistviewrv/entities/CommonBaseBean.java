package com.ljg.guiderecycleview.labellistviewrv.entities;

public class CommonBaseBean {
    public String name;
    public String value;
    public String unit;
    public boolean isSelect;
    public boolean isHaveDivide;

    public CommonBaseBean(String name, String value, String unit, boolean isSelect, boolean isHaveDivide) {
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.isSelect = isSelect;
        this.isHaveDivide = isHaveDivide;
    }
}
