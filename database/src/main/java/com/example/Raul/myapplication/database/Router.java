package com.example.Raul.myapplication.database;

/**
 * Created by Raul on 03/07/2016.
 */
public class Router {
    private Long id;
    private String bssid;

    public Long getId() {
        return id;
    }
    public String getBssid() {
        return bssid;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setBssid(String bssid) {
        this.bssid = bssid;
    }
}
