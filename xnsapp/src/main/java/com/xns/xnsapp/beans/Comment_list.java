package com.xns.xnsapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-17.
 */
public class Comment_list implements Serializable{
    String auth;
    String max_page;
    List<Comments> comments;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getMax_page() {
        return max_page;
    }

    public void setMax_page(String max_page) {
        this.max_page = max_page;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comment_list{" +
                "auth='" + auth + '\'' +
                ", max_page='" + max_page + '\'' +
                ", comments=" + comments +
                '}';
    }
}
