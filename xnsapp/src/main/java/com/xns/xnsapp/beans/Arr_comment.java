package com.xns.xnsapp.beans;

import java.io.Serializable;

/**
 * Created by kinnyo-imac-24 on 15-6-12.
 */
public class Arr_comment implements Serializable{
    String user_id;
    String comment_id;
    String summary;
    String nickname;
    String cert_type;
    String cert_desc;
    String reply_to_id;
    String reply_to_name;

    public String getUser_id() {
        return user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public String getSummary() {
        return summary;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCert_type() {
        return cert_type;
    }

    public String getCert_desc() {
        return cert_desc;
    }

    public String getReply_to_id() {
        return reply_to_id;
    }

    public String getReply_to_name() {
        return reply_to_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public void setCert_desc(String cert_desc) {
        this.cert_desc = cert_desc;
    }

    public void setReply_to_id(String reply_to_id) {
        this.reply_to_id = reply_to_id;
    }

    public void setReply_to_name(String reply_to_name) {
        this.reply_to_name = reply_to_name;
    }

    @Override
    public String toString() {
        return "Arr_comment{" +
                "user_id='" + user_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                ", summary='" + summary + '\'' +
                ", nickname='" + nickname + '\'' +
                ", cert_type='" + cert_type + '\'' +
                ", cert_desc='" + cert_desc + '\'' +
                ", reply_to_id='" + reply_to_id + '\'' +
                ", reply_to_name='" + reply_to_name + '\'' +
                '}';
    }
}
