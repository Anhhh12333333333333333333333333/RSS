package com.example.news;

public class News {
    public String title;
    public String desc;
    public String date;

    public String url;
    public News() {
    }


    public News(String title, String desc, String date, String url) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
