package com.xns.xnsapp.beans;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class Item {
    String cat;
    String cat_name;
    String id;
    String name;
    String poster;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Item{" +
                "cat='" + cat + '\'' +
                ", cat_name='" + cat_name + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
