package com.xns.xnsapp.beans;

import java.io.Serializable;

/**
 * Created by kinnyo-imac-24 on 15-6-17.
 */
public class Comments implements Serializable{
    String user_id;
    String comment_id;
    String summary;
    String useful;
    String avatar;
    String cert_type;
    String cert_desc;
    String nickname;
    String create_time;
    String reply_to_id;
    String reply_to_name;
    String is_thumbsup;
    String thumbsup_count;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUseful() {
        return useful;
    }

    public void setUseful(String useful) {
        this.useful = useful;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public String getCert_desc() {
        return cert_desc;
    }

    public void setCert_desc(String cert_desc) {
        this.cert_desc = cert_desc;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getReply_to_id() {
        return reply_to_id;
    }

    public void setReply_to_id(String reply_to_id) {
        this.reply_to_id = reply_to_id;
    }

    public String getReply_to_name() {
        return reply_to_name;
    }

    public void setReply_to_name(String reply_to_name) {
        this.reply_to_name = reply_to_name;
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

    @Override
    public String toString() {
        return "Comments{" +
                "user_id='" + user_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                ", summary='" + summary + '\'' +
                ", useful='" + useful + '\'' +
                ", avatar='" + avatar + '\'' +
                ", cert_type='" + cert_type + '\'' +
                ", cert_desc='" + cert_desc + '\'' +
                ", nickname='" + nickname + '\'' +
                ", create_time='" + create_time + '\'' +
                ", reply_to_id='" + reply_to_id + '\'' +
                ", reply_to_name='" + reply_to_name + '\'' +
                ", is_thumbsup='" + is_thumbsup + '\'' +
                ", thumbsup_count='" + thumbsup_count + '\'' +
                '}';
    }
}
