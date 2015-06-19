package com.xns.xnsapp.beans;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-12.
 */
public class Home {
    String pages;
    List<Lesson_list> lesson_list;

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
        return "Home{" +
                "pages='" + pages + '\'' +
                ", lesson_list=" + lesson_list +
                '}';
    }
}
