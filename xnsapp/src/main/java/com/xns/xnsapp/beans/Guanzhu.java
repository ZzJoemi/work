package com.xns.xnsapp.beans;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class Guanzhu {
    String auth;
    String total_count;
    String max_page;
    List<Friend> friends;

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

    public String getMax_page() {
        return max_page;
    }

    public void setMax_page(String max_page) {
        this.max_page = max_page;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Guanzhu{" +
                "auth='" + auth + '\'' +
                ", total_count='" + total_count + '\'' +
                ", max_page='" + max_page + '\'' +
                ", friends=" + friends +
                '}';
    }
}
