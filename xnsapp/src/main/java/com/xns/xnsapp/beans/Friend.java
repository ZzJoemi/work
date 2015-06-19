package com.xns.xnsapp.beans;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class Friend {
    String user_id;
    String is_my_friend;
    String avatar;
    String nickname;
    String cert_type;
    String cert_desc;
    String wedding_date;
    String collect_count;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIs_my_friend() {
        return is_my_friend;
    }

    public void setIs_my_friend(String is_my_friend) {
        this.is_my_friend = is_my_friend;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getWedding_date() {
        return wedding_date;
    }

    public void setWedding_date(String wedding_date) {
        this.wedding_date = wedding_date;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "user_id='" + user_id + '\'' +
                ", is_my_friend='" + is_my_friend + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", cert_type='" + cert_type + '\'' +
                ", cert_desc='" + cert_desc + '\'' +
                ", wedding_date='" + wedding_date + '\'' +
                ", collect_count='" + collect_count + '\'' +
                '}';
    }
}
