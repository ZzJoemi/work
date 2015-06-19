package com.xns.xnsapp.beans;

import java.io.Serializable;

/**
 * Created by kinnyo-imac-24 on 15-6-18.
 */
public class UserSimpleInfo implements Serializable {
    String auth;
    String is_my_friend;
    String avatar;
    String nickname;
    String cert_type;
    String cert_desc;
    String poster;
    String wedding_date;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getWedding_date() {
        return wedding_date;
    }

    public void setWedding_date(String wedding_date) {
        this.wedding_date = wedding_date;
    }


    @Override
    public String toString() {
        return "UserSimpleInfo{" +
                "auth='" + auth + '\'' +
                ", is_my_friend='" + is_my_friend + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", cert_type='" + cert_type + '\'' +
                ", cert_desc='" + cert_desc + '\'' +
                ", poster='" + poster + '\'' +
                ", wedding_date='" + wedding_date + '\'' +
                '}';
    }

}
