package com.xns.xnsapp.beans;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class HuodongItem {
    String id;
    String title;
    String time;
    String poster;
    String promo;
    String promo_poster;
    String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getPromo_poster() {
        return promo_poster;
    }

    public void setPromo_poster(String promo_poster) {
        this.promo_poster = promo_poster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HuodongItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", poster='" + poster + '\'' +
                ", promo='" + promo + '\'' +
                ", promo_poster='" + promo_poster + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
