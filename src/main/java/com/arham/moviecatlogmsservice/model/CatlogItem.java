package com.arham.moviecatlogmsservice.model;

public class CatlogItem {

    String name;
    String desc;
    int rateing;

    public CatlogItem() {
    }

    public CatlogItem(String name, String desc, int rateing) {
        this.name = name;
        this.desc = desc;
        this.rateing = rateing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRateing() {
        return rateing;
    }

    public void setRateing(int rateing) {
        this.rateing = rateing;
    }
}
