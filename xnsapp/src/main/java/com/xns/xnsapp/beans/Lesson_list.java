package com.xns.xnsapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-12.
 */
public class Lesson_list implements Serializable{
    String id;
    String type;
    String title;
    String poster;
    String summary;
    String clicks;
    String share_count;
    String date;
    List<Arr_tag> arr_tag;
    List<Arr_comment> arr_comment;
    String author_id;
    String author_nickname;
    String author_avatar;
    String author_date;
    String author_cert_type;
    String author_cert_desc;
    String is_collected;
    String is_thumbsup;
    String thumbsup_count;
    String comment_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getShare_count() {
        return share_count;
    }

    public void setShare_count(String share_count) {
        this.share_count = share_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Arr_tag> getArr_tag() {
        return arr_tag;
    }

    public void setArr_tag(List<Arr_tag> arr_tag) {
        this.arr_tag = arr_tag;
    }

    public List<Arr_comment> getArr_comment() {
        return arr_comment;
    }

    public void setArr_comment(List<Arr_comment> arr_comment) {
        this.arr_comment = arr_comment;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_nickname() {
        return author_nickname;
    }

    public void setAuthor_nickname(String author_nickname) {
        this.author_nickname = author_nickname;
    }

    public String getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }

    public String getAuthor_date() {
        return author_date;
    }

    public void setAuthor_date(String author_date) {
        this.author_date = author_date;
    }

    public String getAuthor_cert_type() {
        return author_cert_type;
    }

    public void setAuthor_cert_type(String author_cert_type) {
        this.author_cert_type = author_cert_type;
    }

    public String getAuthor_cert_desc() {
        return author_cert_desc;
    }

    public void setAuthor_cert_desc(String author_cert_desc) {
        this.author_cert_desc = author_cert_desc;
    }

    public String getIs_collected() {
        return is_collected;
    }

    public void setIs_collected(String is_collected) {
        this.is_collected = is_collected;
    }

    public String getIs_thumbsup() {
        return is_thumbsup;
    }

    public void setIs_thumbsup(String is_thumbsup) {
        this.is_thumbsup = is_thumbsup;
    }

    public String getThumbsup_count() {
        return thumbsup_count;
    }

    public void setThumbsup_count(String thumbsup_count) {
        this.thumbsup_count = thumbsup_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    @Override
    public String toString() {
        return "Lesson_list{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", summary='" + summary + '\'' +
                ", clicks='" + clicks + '\'' +
                ", share_count='" + share_count + '\'' +
                ", date='" + date + '\'' +
                ", arr_tag=" + arr_tag +
                ", arr_comment=" + arr_comment +
                ", author_id='" + author_id + '\'' +
                ", author_nickname='" + author_nickname + '\'' +
                ", author_avatar='" + author_avatar + '\'' +
                ", author_date='" + author_date + '\'' +
                ", author_cert_type='" + author_cert_type + '\'' +
                ", author_cert_desc='" + author_cert_desc + '\'' +
                ", is_collected='" + is_collected + '\'' +
                ", is_thumbsup='" + is_thumbsup + '\'' +
                ", thumbsup_count='" + thumbsup_count + '\'' +
                ", comment_count='" + comment_count + '\'' +
                '}';
    }
}
