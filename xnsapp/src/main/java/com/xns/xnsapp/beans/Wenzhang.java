package com.xns.xnsapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-18.
 */
public class Wenzhang implements Serializable{
    String auth;
    String total_count;
    String pages;
    List<Lesson_list> lesson_list;

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

    public List<Lesson_list> getLesson_list() {
        return lesson_list;
    }

    public void setLesson_list(List<Lesson_list> lesson_list) {
        this.lesson_list = lesson_list;
    }

    @Override
    public String toString() {
        return "Wenzhang{" +
                "auth='" + auth + '\'' +
                ", total_count='" + total_count + '\'' +
                ", pages='" + pages + '\'' +
                ", lesson_list=" + lesson_list +
                '}';
    }
}
