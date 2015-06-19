package com.xns.xnsapp.beans;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class Huodong {
    String auth;
    String total_count;
    String pages;
    List<HuodongItem> items;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<HuodongItem> getItems() {
        return items;
    }

    public void setItems(List<HuodongItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Huodong{" +
                "auth='" + auth + '\'' +
                ", total_count='" + total_count + '\'' +
                ", pages='" + pages + '\'' +
                ", items=" + items +
                '}';
    }
}
